package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import funcionalidades.GestionPrestamos;
import modelo.Prestamo;

public class VentanaDetalleContenido extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Color colorFondo = new Color(249, 247, 242);
	private final Color colorSubnavbar = new Color(227, 235, 222);
	private final Color colorVerde = new Color(123, 220, 99);
	private final Color colorAmarillo = new Color(236, 206, 145);
	private final Color colorRojo = new Color(243, 153, 131);

	private JPanel panelDatos;
	private JPanel panelSecundario;
	private JPanel panelSeccion;
	private JLabel etiquetaTitulo;
	private JLabel etiquetaSeccionSecundaria;

	private Consumer<String[]> accionEditarPrestamo;
	private Consumer<String[]> accionDevolverPrestamo;

	private GestionPrestamos gestionPrestamos;

	public VentanaDetalleContenido(int modo, String[] datos, GestionPrestamos gestionPrestamos) {

		this.gestionPrestamos = gestionPrestamos;

		configurarVentana();

		switch (modo) {
		case PanelContenido.MODO_USUARIOS:
			mostrarUsuario(datos);
			break;
		case PanelContenido.MODO_RECURSOS:
			mostrarRecurso(datos);
			break;
		case PanelContenido.MODO_PRESTAMOS:
			mostrarPrestamo(datos);
			break;
		default:
			mostrarContenidoGenerico();
			break;
		}
	}

	// =========================================================
	// CONFIGURACIÓN GENERAL
	// =========================================================
	private void configurarVentana() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(colorFondo);
		setContentPane(panelPrincipal);

		// -----------------------------------------------------
		// SUBNAVBAR
		// -----------------------------------------------------
		JPanel panelSubnavbar = new JPanel(new BorderLayout());
		panelSubnavbar.setPreferredSize(new Dimension(0, 60));
		panelSubnavbar.setBackground(colorSubnavbar);
		panelSubnavbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panelPrincipal.add(panelSubnavbar, BorderLayout.NORTH);

		etiquetaTitulo = new JLabel();
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelSubnavbar.add(etiquetaTitulo, BorderLayout.WEST);

		// -----------------------------------------------------
		// PANEL CENTRAL
		// -----------------------------------------------------
		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBackground(colorFondo);
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		// -----------------------------------------------------
		// INFORMACIÓN PRINCIPAL
		// -----------------------------------------------------
		JPanel panelInformacion = new JPanel(new BorderLayout());
		panelInformacion.setBackground(Color.WHITE);
		panelInformacion
				.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)),
						BorderFactory.createEmptyBorder(15, 20, 15, 20)));
		panelCentral.add(panelInformacion, BorderLayout.NORTH);

		panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panelInformacion.add(panelDatos, BorderLayout.CENTER);

		// -----------------------------------------------------
		// SECCIÓN SECUNDARIA
		// -----------------------------------------------------
		panelSeccion = new JPanel(new BorderLayout());
		panelSeccion.setBackground(colorFondo);
		panelSeccion.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		etiquetaSeccionSecundaria = new JLabel();
		etiquetaSeccionSecundaria.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panelSeccion.add(etiquetaSeccionSecundaria, BorderLayout.NORTH);

		panelSecundario = new JPanel();
		panelSecundario.setLayout(new BoxLayout(panelSecundario, BoxLayout.Y_AXIS));
		panelSecundario.setBackground(colorFondo);

		JScrollPane scroll = new JScrollPane(panelSecundario);
		scroll.setBorder(null);
		scroll.setBackground(colorFondo);

		panelSeccion.add(scroll, BorderLayout.CENTER);

		panelCentral.add(panelSeccion, BorderLayout.CENTER);
	}

	// =========================================================
	// USUARIO
	// =========================================================
	private void mostrarUsuario(String[] datos) {

		setTitle("Detalle del usuario");
		etiquetaTitulo.setText("Detalle del usuario");
		etiquetaSeccionSecundaria.setText("Préstamos");

		limpiarPaneles();

		String id = obtenerDato(datos, 0);
		String nombre = obtenerDato(datos, 1);
		String email = obtenerDato(datos, 2);

		añadirDato("ID: " + id);
		añadirDato("Nombre: " + nombre);
		añadirDato("Email: " + email);

		JTabbedPane pestanasPrestamos = new JTabbedPane();

		JPanel panelActivos = crearPanelListaPrestamos();

		JPanel panelDevueltos = crearPanelListaPrestamos();

		ArrayList<Prestamo> prestamosUsuario = gestionPrestamos.getHistorialUsuario(id);

		for (Prestamo prestamo : prestamosUsuario) {

			if (prestamo.isEstadoPrestamo()) {

				añadirPrestamo(panelActivos, prestamo, true);

			} else {

				añadirPrestamo(panelDevueltos, prestamo, false);
			}
		}

		pestanasPrestamos.addTab("Activos", crearScroll(panelActivos));

		pestanasPrestamos.addTab("Devueltos", crearScroll(panelDevueltos));

		panelSecundario.setLayout(new BorderLayout());

		panelSecundario.add(pestanasPrestamos, BorderLayout.CENTER);

		panelSecundario.revalidate();
		panelSecundario.repaint();
	}

	// =========================================================
	// RECURSO
	// =========================================================
	private void mostrarRecurso(String[] datos) {

		setTitle("Detalle del recurso");
		etiquetaTitulo.setText("Detalle del recurso");
		etiquetaSeccionSecundaria.setText("Historial de préstamos");

		limpiarPaneles();

		String id = obtenerDato(datos, 0);
		String titulo = obtenerDato(datos, 1);
		String tipo = obtenerDato(datos, 2);
		String ano = obtenerDato(datos, 3);
		String estado = obtenerDato(datos, 4);
		String informacion1 = obtenerDato(datos, 5);
		String informacion2 = obtenerDato(datos, 6);

		añadirDato("ID: " + id);
		añadirDato("Título: " + titulo);
		añadirDato("Tipo: " + tipo);
		añadirDato("Año: " + ano);
		añadirDato("Estado: " + estado);
		añadirDato(informacion1 + " · " + informacion2);

		JTabbedPane pestanasPrestamos = new JTabbedPane();
		JPanel panelActivos = crearPanelListaPrestamos();
		JPanel panelDevueltos = crearPanelListaPrestamos();

		ArrayList<Prestamo> historialRecurso = gestionPrestamos.getHistorialRecurso(id);

		for (Prestamo prestamo : historialRecurso) {

			if (prestamo.isEstadoPrestamo()) {

				añadirPrestamo(panelActivos, prestamo, false);

			} else {

				añadirPrestamo(panelDevueltos, prestamo, false);
			}
		}

		pestanasPrestamos.addTab("Activos", crearScroll(panelActivos));
		pestanasPrestamos.addTab("Devueltos", crearScroll(panelDevueltos));

		panelSecundario.setLayout(new BorderLayout());
		panelSecundario.add(pestanasPrestamos, BorderLayout.CENTER);

		panelSecundario.revalidate();
		panelSecundario.repaint();
	}

	// =========================================================
	// PRÉSTAMO
	// =========================================================
	private void mostrarPrestamo(String[] datos) {
		setTitle("Detalle del préstamo");
		etiquetaTitulo.setText("Detalle del préstamo");

		limpiarPaneles();

		String nombreUsuario = obtenerDato(datos, 0);
		String idRecurso = obtenerDato(datos, 1);
		String tituloRecurso = obtenerDato(datos, 2);
		String fechaPrestamo = obtenerDato(datos, 3);
		String estado = obtenerDato(datos, 4);
		String fechaDevolucion = obtenerDato(datos, 5);

		añadirDato("Usuario: " + nombreUsuario);
		añadirDato("ID recurso: " + idRecurso);
		añadirDato("Recurso: " + tituloRecurso);
		añadirDato("Fecha préstamo: " + fechaPrestamo);
		añadirDato("Estado: " + estado);
		añadirDato("Fecha devolución: " + fechaDevolucion);

		panelSeccion.setVisible(false);
	}

	// =========================================================
	// CONTENIDO GENÉRICO
	// =========================================================
	private void mostrarContenidoGenerico() {
		setTitle("Detalle");
		etiquetaTitulo.setText("Detalle");
		etiquetaSeccionSecundaria.setText("Información");

		limpiarPaneles();

		añadirDato("No hay información disponible.");
	}

	// =========================================================
	// DATOS PRINCIPALES
	// =========================================================
	private void añadirDato(String texto) {
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		etiqueta.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		panelDatos.add(etiqueta);
	}

	// =========================================================
	// PRÉSTAMOS TEMPORALES
	// =========================================================
	private JPanel crearPanelListaPrestamos() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(colorFondo);
		return panel;
	}

	private JScrollPane crearScroll(JPanel panel) {
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setBackground(colorFondo);
		return scroll;
	}

	private void añadirPrestamo(JPanel panelDestino, Prestamo prestamo, boolean mostrarBotonDevolver) {

		String nombreUsuario = prestamo.getUsuario().getNombre();
		String idRecurso = prestamo.getRecurso().getId();
		String titulo = prestamo.getRecurso().getTitulo();
		String fechaPrestamo = prestamo.getFechaPrestamo().toString();
		String estado = prestamo.isEstadoPrestamo() ? "Activo" : "Finalizado";
		String fechaDevolucion = prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion().toString() : "-";
		String[] datosPrestamo = { nombreUsuario, idRecurso, titulo, fechaPrestamo, estado, fechaDevolucion };

		JPanel panelPrestamo = new JPanel(new BorderLayout());
		panelPrestamo.setBackground(Color.WHITE);
		panelPrestamo
				.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)),
						BorderFactory.createEmptyBorder(12, 15, 12, 10)));
		panelPrestamo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		JPanel panelDatosPrestamo = new JPanel();
		panelDatosPrestamo.setLayout(new BoxLayout(panelDatosPrestamo, BoxLayout.Y_AXIS));
		panelDatosPrestamo.setOpaque(false);

		JLabel etiquetaTitulo = new JLabel(titulo);
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel etiquetaUsuario = new JLabel("Usuario: " + nombreUsuario);
		JLabel etiquetaId = new JLabel("ID recurso: " + idRecurso);
		JLabel etiquetaFecha = new JLabel("Fecha préstamo: " + fechaPrestamo);
		JLabel etiquetaEstado = new JLabel("Estado: " + estado);
		JLabel etiquetaDevolucion = new JLabel("Fecha devolución: " + fechaDevolucion);

		Font fuenteSecundaria = new Font("Segoe UI", Font.PLAIN, 13);

		etiquetaUsuario.setFont(fuenteSecundaria);
		etiquetaId.setFont(fuenteSecundaria);
		etiquetaFecha.setFont(fuenteSecundaria);
		etiquetaEstado.setFont(fuenteSecundaria);
		etiquetaDevolucion.setFont(fuenteSecundaria);

		panelDatosPrestamo.add(etiquetaTitulo);
		panelDatosPrestamo.add(etiquetaUsuario);
		panelDatosPrestamo.add(etiquetaId);
		panelDatosPrestamo.add(etiquetaFecha);
		panelDatosPrestamo.add(etiquetaEstado);

		if (!prestamo.isEstadoPrestamo() && prestamo.getFechaDevolucion() != null) {

			panelDatosPrestamo.add(etiquetaDevolucion);
		}

		panelPrestamo.add(panelDatosPrestamo, BorderLayout.CENTER);

		if (mostrarBotonDevolver && prestamo.isEstadoPrestamo()) {

			JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelBotones.setOpaque(false);

			JButton botonDevolver = new JButton("Devolver");
			botonDevolver.setBackground(colorRojo);
			botonDevolver.addActionListener(e -> {

				if (accionDevolverPrestamo != null) {

					accionDevolverPrestamo.accept(datosPrestamo);
				}
			});

			panelBotones.add(botonDevolver);

			panelPrestamo.add(panelBotones, BorderLayout.EAST);
		}

		añadirEventoDetallePrestamo(panelPrestamo, datosPrestamo);
		añadirEventoDetallePrestamo(panelDatosPrestamo, datosPrestamo);

		panelDestino.add(panelPrestamo);
	}

	private void añadirEventoDetallePrestamo(JPanel panel, String[] datosPrestamo) {
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					VentanaDetalleContenido ventana = new VentanaDetalleContenido(PanelContenido.MODO_PRESTAMOS,
							datosPrestamo, gestionPrestamos);
					ventana.setAccionEditarPrestamo(accionEditarPrestamo);
					ventana.setAccionDevolverPrestamo(accionDevolverPrestamo);
					ventana.setVisible(true);
				}
			}
		});
	}

	// =========================================================
	// LIMPIEZA
	// =========================================================
	private void limpiarPaneles() {
		panelDatos.removeAll();
		panelSecundario.removeAll();
		panelSecundario.setLayout(new BoxLayout(panelSecundario, BoxLayout.Y_AXIS));
		panelSecundario.setVisible(true);
	}

	private String obtenerDato(String[] datos, int posicion) {
		if (datos == null || posicion < 0 || posicion >= datos.length || datos[posicion] == null) {
			return "";
		}
		return datos[posicion];
	}

	public void setAccionEditarPrestamo(Consumer<String[]> accionEditarPrestamo) {
		this.accionEditarPrestamo = accionEditarPrestamo;
	}

	public void setAccionDevolverPrestamo(Consumer<String[]> accionDevolverPrestamo) {
		this.accionDevolverPrestamo = accionDevolverPrestamo;
	}
}