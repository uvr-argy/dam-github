package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import controlador.GestionPrestamos;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;

/**
 * Ventana de detalle de un usuario, un recurso o un préstamo.
 * 
 * Antes recibía un String[] con los datos y cada posición significaba una cosa
 * distinta según el modo (y no coincidían con lo que enviaba PanelContenido).
 * Ahora recibe directamente el objeto (Usuario, Recurso o Prestamo) mediante
 * tres constructores, uno por cada tipo.
 */
public class VentanaDetalleContenido extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color COLOR_SUBNAVBAR = new Color(227, 235, 222);

	private JPanel panelDatos;
	private JPanel panelSecundario;

	private Consumer<Prestamo> accionDevolverPrestamo;

	private final GestionPrestamos gestionPrestamos;

	// Vuelve a pintar el contenido (se usa tras devolver un préstamo desde aquí)
	private Runnable recarga;

	// =========================================================
	// CONSTRUCTORES: UNO POR TIPO DE ELEMENTO
	// =========================================================
	public VentanaDetalleContenido(Window propietario, Usuario usuario, GestionPrestamos gestionPrestamos) {

		this(propietario, "Detalle del usuario", "Préstamos", gestionPrestamos);

		recarga = () -> mostrarUsuario(usuario);
		recarga.run();
	}

	public VentanaDetalleContenido(Window propietario, Recurso recurso, GestionPrestamos gestionPrestamos) {

		this(propietario, "Detalle del recurso", "Historial de préstamos", gestionPrestamos);

		recarga = () -> mostrarRecurso(recurso);
		recarga.run();
	}

	public VentanaDetalleContenido(Window propietario, Prestamo prestamo, GestionPrestamos gestionPrestamos) {

		// Sin sección secundaria: un préstamo no tiene lista debajo
		this(propietario, "Detalle del préstamo", null, gestionPrestamos);

		recarga = () -> mostrarPrestamo(prestamo);
		recarga.run();
	}

	private VentanaDetalleContenido(Window propietario, String titulo, String tituloSeccion,
			GestionPrestamos gestionPrestamos) {

		super(propietario);

		this.gestionPrestamos = gestionPrestamos;

		configurarVentana(titulo, tituloSeccion);
	}

	// =========================================================
	// CONFIGURACIÓN GENERAL
	// =========================================================
	private void configurarVentana(String titulo, String tituloSeccion) {

		setTitle(titulo);
		setSize(800, tituloSeccion != null ? 600 : 380);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(UtilVista.COLOR_FONDO);
		setContentPane(panelPrincipal);

		// -----------------------------------------------------
		// SUBNAVBAR
		// -----------------------------------------------------
		JPanel panelSubnavbar = new JPanel(new BorderLayout());
		panelSubnavbar.setPreferredSize(new Dimension(0, 60));
		panelSubnavbar.setBackground(COLOR_SUBNAVBAR);
		panelSubnavbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panelPrincipal.add(panelSubnavbar, BorderLayout.NORTH);

		JLabel etiquetaTitulo = new JLabel(titulo);
		etiquetaTitulo.setFont(UtilVista.FUENTE_TITULO);
		panelSubnavbar.add(etiquetaTitulo, BorderLayout.WEST);

		// -----------------------------------------------------
		// PANEL CENTRAL
		// -----------------------------------------------------
		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBackground(UtilVista.COLOR_FONDO);
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		// -----------------------------------------------------
		// INFORMACIÓN PRINCIPAL
		// -----------------------------------------------------
		JPanel panelInformacion = new JPanel(new BorderLayout());
		panelInformacion.setBackground(Color.WHITE);
		panelInformacion.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(UtilVista.COLOR_BORDE), BorderFactory.createEmptyBorder(15, 20, 15, 20)));
		panelCentral.add(panelInformacion, BorderLayout.NORTH);

		panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panelInformacion.add(panelDatos, BorderLayout.CENTER);

		// -----------------------------------------------------
		// SECCIÓN SECUNDARIA (lista de préstamos)
		// -----------------------------------------------------
		if (tituloSeccion != null) {

			JPanel panelSeccion = new JPanel(new BorderLayout());
			panelSeccion.setBackground(UtilVista.COLOR_FONDO);
			panelSeccion.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

			JLabel etiquetaSeccion = new JLabel(tituloSeccion);
			etiquetaSeccion.setFont(UtilVista.FUENTE_SUBTITULO);
			panelSeccion.add(etiquetaSeccion, BorderLayout.NORTH);

			panelSecundario = new JPanel(new BorderLayout());
			panelSecundario.setBackground(UtilVista.COLOR_FONDO);
			panelSeccion.add(panelSecundario, BorderLayout.CENTER);

			panelCentral.add(panelSeccion, BorderLayout.CENTER);
		}
	}

	// =========================================================
	// USUARIO
	// =========================================================
	private void mostrarUsuario(Usuario usuario) {

		limpiarPaneles();

		añadirDato("ID: " + usuario.getId());
		añadirDato("Nombre: " + usuario.getNombre());
		añadirDato("Email: " + usuario.getEmail());

		// En el detalle de un usuario se pueden devolver sus préstamos activos
		mostrarPestanasPrestamos(gestionPrestamos.getHistorialUsuario(usuario.getId()), true);
	}

	// =========================================================
	// RECURSO
	// =========================================================
	private void mostrarRecurso(Recurso recurso) {

		limpiarPaneles();

		String tipo = UtilVista.tipoDe(recurso);

		añadirDato("ID: " + recurso.getId());
		añadirDato("Título: " + recurso.getTitulo());
		añadirDato("Tipo: " + tipo);
		añadirDato("Año: " + recurso.getAno());
		añadirDato("Estado: " + UtilVista.estadoDe(recurso));
		añadirDato(UtilVista.etiquetaDato1(tipo) + ": " + UtilVista.dato1(recurso));
		añadirDato(UtilVista.etiquetaDato2(tipo) + ": " + UtilVista.dato2(recurso));

		mostrarPestanasPrestamos(gestionPrestamos.getHistorialRecurso(recurso.getId()), false);
	}

	// =========================================================
	// PRÉSTAMO
	// =========================================================
	private void mostrarPrestamo(Prestamo prestamo) {

		limpiarPaneles();

		añadirDato("Usuario: " + prestamo.getUsuario().getNombre());
		añadirDato("ID recurso: " + prestamo.getRecurso().getId());
		añadirDato("Recurso: " + prestamo.getRecurso().getTitulo());
		añadirDato("Fecha préstamo: " + prestamo.getFechaPrestamo());
		añadirDato("Estado: " + UtilVista.estadoDe(prestamo));
		añadirDato("Fecha devolución: " + UtilVista.fechaDevolucion(prestamo));
	}

	// =========================================================
	// DATOS PRINCIPALES
	// =========================================================
	private void añadirDato(String texto) {

		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(UtilVista.FUENTE_DATO);
		etiqueta.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		panelDatos.add(etiqueta);
	}

	private void limpiarPaneles() {

		panelDatos.removeAll();

		if (panelSecundario != null) {
			panelSecundario.removeAll();
		}
	}

	private void refrescar() {

		getContentPane().revalidate();
		getContentPane().repaint();
	}

	// =========================================================
	// LISTAS DE PRÉSTAMOS (pestañas Activos / Devueltos)
	// =========================================================
	private void mostrarPestanasPrestamos(List<Prestamo> prestamos, boolean conBotonDevolver) {

		JPanel panelActivos = crearPanelListaPrestamos();
		JPanel panelDevueltos = crearPanelListaPrestamos();

		for (Prestamo prestamo : prestamos) {

			if (prestamo.isEstadoPrestamo()) {
				añadirPrestamo(panelActivos, prestamo, conBotonDevolver);
			} else {
				añadirPrestamo(panelDevueltos, prestamo, false);
			}
		}

		JTabbedPane pestanas = new JTabbedPane();
		pestanas.addTab("Activos", crearScroll(panelActivos));
		pestanas.addTab("Devueltos", crearScroll(panelDevueltos));

		panelSecundario.add(pestanas, BorderLayout.CENTER);

		refrescar();
	}

	private JPanel crearPanelListaPrestamos() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(UtilVista.COLOR_FONDO);

		return panel;
	}

	private JScrollPane crearScroll(JPanel panel) {

		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setBackground(UtilVista.COLOR_FONDO);
		// Por defecto la rueda del ratón avanza 1 píxel por paso
		scroll.getVerticalScrollBar().setUnitIncrement(16);

		return scroll;
	}

	private void añadirPrestamo(JPanel panelDestino, Prestamo prestamo, boolean conBotonDevolver) {

		JPanel datos = UtilVista.crearPanelDatos();
		datos.add(UtilVista.crearEtiquetaPrincipal(prestamo.getRecurso().getTitulo()));
		datos.add(UtilVista.crearEtiquetaSecundaria("Usuario: " + prestamo.getUsuario().getNombre()));
		datos.add(UtilVista.crearEtiquetaSecundaria("ID recurso: " + prestamo.getRecurso().getId()));
		datos.add(UtilVista.crearEtiquetaSecundaria("Fecha préstamo: " + prestamo.getFechaPrestamo()));
		datos.add(UtilVista.crearEtiquetaSecundaria("Estado: " + UtilVista.estadoDe(prestamo)));

		if (!prestamo.isEstadoPrestamo() && prestamo.getFechaDevolucion() != null) {
			datos.add(UtilVista.crearEtiquetaSecundaria("Fecha devolución: " + prestamo.getFechaDevolucion()));
		}

		JPanel fila = UtilVista.crearFila();
		fila.add(datos, BorderLayout.CENTER);

		if (conBotonDevolver && prestamo.isEstadoPrestamo()) {

			JButton botonDevolver = UtilVista.crearBotonDevolver();
			botonDevolver.addActionListener(e -> devolver(prestamo));

			JPanel panelBotones = UtilVista.crearPanelBotones();
			panelBotones.add(botonDevolver);
			fila.add(panelBotones, BorderLayout.EAST);
		}

		// Un solo listener sobre toda la fila (antes se añadía a dos paneles)
		UtilVista.hacerClicable(fila, () -> new VentanaDetalleContenido(this, prestamo, gestionPrestamos).setVisible(true));

		panelDestino.add(fila);
	}

	private void devolver(Prestamo prestamo) {

		if (accionDevolverPrestamo != null) {

			// El cuadro de confirmación es modal: esta línea espera a que se cierre
			accionDevolverPrestamo.accept(prestamo);

			// La ventana sigue abierta, así que se actualiza (antes se quedaba
			// mostrando el préstamo como activo)
			recarga.run();
		}
	}

	// =========================================================
	// ACCIONES QUE DEFINE QUIEN ABRE LA VENTANA
	// =========================================================
	public void setAccionDevolverPrestamo(Consumer<Prestamo> accionDevolverPrestamo) {

		this.accionDevolverPrestamo = accionDevolverPrestamo;
	}
}