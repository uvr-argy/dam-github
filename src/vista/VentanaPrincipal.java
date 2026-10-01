package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.GestionPrestamos;
import controlador.GestionRecursos;
import controlador.GestionUsuarios;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	// Nombres de las dos tarjetas del CardLayout
	private static final String TARJETA_INICIO = "INICIO";
	private static final String TARJETA_CONTENIDO = "CONTENIDO";

	// ========================================
	// COMPONENTES PRINCIPALES
	// ========================================
	private JPanel panelTarjetas;
	private CardLayout gestorTarjetas;

	// Panel unificado de usuarios, recursos y préstamos
	private PanelContenido panelContenido;

	// ========================================
	// CONSTRUCTOR
	// ========================================
	public VentanaPrincipal(GestionUsuarios gestionUsuarios, GestionRecursos gestionRecursos,
			GestionPrestamos gestionPrestamos) {

		setTitle("Biblioteca Multimedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);

		// La barra de búsqueda de PanelContenido necesita este ancho para no romperse
		setMinimumSize(new Dimension(1000, 600));

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		setContentPane(panelPrincipal);

		// Se crea antes que la barra lateral, cuyos botones lo utilizan
		panelContenido = new PanelContenido(gestionUsuarios, gestionRecursos, gestionPrestamos);

		panelPrincipal.add(crearBarraSuperior(), BorderLayout.NORTH);
		panelPrincipal.add(crearBarraLateral(), BorderLayout.WEST);

		// ========================================
		// PANEL DE TARJETAS (INICIO / CONTENIDO)
		// ========================================
		gestorTarjetas = new CardLayout();
		panelTarjetas = new JPanel(gestorTarjetas);
		panelTarjetas.add(crearPanelInicio(), TARJETA_INICIO);
		panelTarjetas.add(panelContenido, TARJETA_CONTENIDO);
		panelPrincipal.add(panelTarjetas, BorderLayout.CENTER);

		// ========================================
		// MOSTRAR INICIO AL ARRANCAR
		// ========================================
		mostrarInicio();
	}

	// ========================================
	// BARRA SUPERIOR / NAVBAR
	// ========================================
	private JPanel crearBarraSuperior() {

		JPanel barraSuperior = new JPanel(new BorderLayout());
		barraSuperior.setPreferredSize(new Dimension(0, 60));
		barraSuperior.setBackground(UtilVista.COLOR_AZUL);

		JLabel etiquetaLogo = new JLabel("LOGO");
		etiquetaLogo.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLogo.setVerticalAlignment(SwingConstants.CENTER);
		etiquetaLogo.setPreferredSize(new Dimension(70, 60));
		etiquetaLogo.setForeground(Color.WHITE);
		barraSuperior.add(etiquetaLogo, BorderLayout.WEST);

		JLabel etiquetaNombreAplicacion = new JLabel("Biblioteca Multimedia");
		etiquetaNombreAplicacion.setForeground(Color.WHITE);
		etiquetaNombreAplicacion.setFont(UtilVista.FUENTE_SUBTITULO);
		// Margen izquierdo con un borde en lugar de espacios en el texto
		etiquetaNombreAplicacion.setBorder(new EmptyBorder(0, 20, 0, 0));
		barraSuperior.add(etiquetaNombreAplicacion, BorderLayout.CENTER);

		return barraSuperior;
	}

	// ========================================
	// BARRA LATERAL
	// ========================================
	private JPanel crearBarraLateral() {

		JPanel barraLateral = new JPanel();
		barraLateral.setBackground(new Color(207, 215, 235));
		barraLateral.setLayout(new BoxLayout(barraLateral, BoxLayout.Y_AXIS));
		barraLateral.setPreferredSize(new Dimension(180, 0));

		JButton botonInicio = crearBotonNavegacion("Inicio", () -> mostrarInicio());

		JButton botonUsuarios = crearBotonNavegacion("Usuarios", () -> {
			panelContenido.mostrarUsuarios();
			mostrarContenido();
		});

		JButton botonRecursos = crearBotonNavegacion("Recursos", () -> {
			panelContenido.mostrarRecursos();
			mostrarContenido();
		});

		JButton botonPrestamos = crearBotonNavegacion("Préstamos", () -> {
			panelContenido.mostrarPrestamos();
			mostrarContenido();
		});

		barraLateral.add(Box.createVerticalStrut(30));
		barraLateral.add(botonInicio);
		barraLateral.add(Box.createVerticalStrut(10));
		barraLateral.add(botonUsuarios);
		barraLateral.add(Box.createVerticalStrut(10));
		barraLateral.add(botonRecursos);
		barraLateral.add(Box.createVerticalStrut(10));
		barraLateral.add(botonPrestamos);

		return barraLateral;
	}

	// Los cuatro botones de la barra lateral son iguales salvo el texto y la acción
	private JButton crearBotonNavegacion(String texto, Runnable accion) {

		JButton boton = new JButton(texto);
		boton.setFont(UtilVista.FUENTE_NAVEGACION);

		Dimension tamano = new Dimension(140, 45);
		boton.setMinimumSize(tamano);
		boton.setPreferredSize(tamano);
		boton.setMaximumSize(tamano);

		boton.setAlignmentX(CENTER_ALIGNMENT);
		boton.addActionListener(e -> accion.run());

		return boton;
	}

	// ========================================
	// PANEL INICIAL
	// ========================================
	private JPanel crearPanelInicio() {

		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(UtilVista.COLOR_FONDO);

		JLabel etiquetaInicio = new JLabel("Bienvenido a la Biblioteca Multimedia");
		etiquetaInicio.setFont(UtilVista.FUENTE_TITULO);
		panelInicio.add(etiquetaInicio);

		return panelInicio;
	}

	// ========================================
	// CAMBIO DE TARJETA
	// ========================================
	private void mostrarInicio() {

		gestorTarjetas.show(panelTarjetas, TARJETA_INICIO);
	}

	private void mostrarContenido() {

		gestorTarjetas.show(panelTarjetas, TARJETA_CONTENIDO);
	}
}