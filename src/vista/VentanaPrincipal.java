package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import funcionalidades.GestionPrestamos;
import funcionalidades.GestionRecursos;
import funcionalidades.GestionUsuarios;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	// ========================================
	// COMPONENTES PRINCIPALES
	// ========================================
	private JPanel panelTarjetas;
	private CardLayout gestorTarjetas;

	// Panel unificado de usuarios, recursos y préstamos
	private PanelContenido panelContenido;

	// ========================================
	// BARRA SUPERIOR
	// ========================================
	private JPanel barraSuperior;
	private JLabel etiquetaLogo;
	private JLabel etiquetaNombreAplicacion;

	// ========================================
	// BARRA LATERAL
	// ========================================
	private JButton botonInicio;
	private JButton botonUsuarios;
	private JButton botonRecursos;
	private JButton botonPrestamos;

	// ========================================
	// COLOR DE LA APLICACIÓN
	// ========================================
	private Color colorBarraSuperior = new Color(40, 40, 40);

	// ========================================
	// CONTROLADORES
	// ========================================
	private GestionUsuarios gestionUsuarios;
	private GestionRecursos gestionRecursos;
	private GestionPrestamos gestionPrestamos;

	// ========================================
	// CONSTRUCTOR
	// ========================================
	public VentanaPrincipal(GestionUsuarios gestionUsuarios, GestionRecursos gestionRecursos,
			GestionPrestamos gestionPrestamos) {

		this.gestionUsuarios = gestionUsuarios;
		this.gestionRecursos = gestionRecursos;
		this.gestionPrestamos = gestionPrestamos;

		setTitle("Biblioteca Multimedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);

		// ========================================
		// PANEL PRINCIPAL
		// ========================================
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(panelPrincipal);

		// ========================================
		// BARRA SUPERIOR / NAVBAR
		// ========================================
		barraSuperior = new JPanel(new BorderLayout());
		barraSuperior.setPreferredSize(new Dimension(0, 60));
		barraSuperior.setBackground(new Color(52, 80, 154));
		panelPrincipal.add(barraSuperior, BorderLayout.NORTH);

		// ========================================
		// LOGO
		// ========================================
		etiquetaLogo = new JLabel("LOGO");
		etiquetaLogo.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLogo.setVerticalAlignment(SwingConstants.CENTER);
		etiquetaLogo.setPreferredSize(new Dimension(70, 60));
		etiquetaLogo.setForeground(Color.WHITE);
		barraSuperior.add(etiquetaLogo, BorderLayout.WEST);

		// ========================================
		// NOMBRE DE LA APLICACIÓN
		// ========================================
		etiquetaNombreAplicacion = new JLabel("   Biblioteca Multimedia");
		etiquetaNombreAplicacion.setForeground(Color.WHITE);
		etiquetaNombreAplicacion.setFont(new Font("Segoe UI", Font.BOLD, 20));
		barraSuperior.add(etiquetaNombreAplicacion, BorderLayout.CENTER);

		// ========================================
		// BARRA LATERAL
		// ========================================
		JPanel barraLateral = new JPanel();
		barraLateral.setBackground(new Color(207, 215, 235));
		barraLateral.setLayout(new BoxLayout(barraLateral, BoxLayout.Y_AXIS));
		barraLateral.setPreferredSize(new Dimension(180, 0));
		panelPrincipal.add(barraLateral, BorderLayout.WEST);

		// ========================================
		// BOTONES DE NAVEGACIÓN
		// ========================================
		botonInicio = new JButton("Inicio");
		botonInicio.setFont(new Font("Segoe UI", Font.BOLD, 18));
		botonUsuarios = new JButton("Usuarios");
		botonUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 18));
		botonRecursos = new JButton("Recursos");
		botonRecursos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		botonPrestamos = new JButton("Préstamos");
		botonPrestamos.setFont(new Font("Segoe UI", Font.BOLD, 18));

		// ========================================
		// DIMENSIONES DE LOS BOTONES
		// ========================================
		Dimension tamañoBoton = new Dimension(140, 45);

		botonInicio.setMinimumSize(tamañoBoton);
		botonInicio.setPreferredSize(tamañoBoton);
		botonInicio.setMaximumSize(tamañoBoton);

		botonUsuarios.setMinimumSize(tamañoBoton);
		botonUsuarios.setPreferredSize(tamañoBoton);
		botonUsuarios.setMaximumSize(tamañoBoton);

		botonRecursos.setMinimumSize(tamañoBoton);
		botonRecursos.setPreferredSize(tamañoBoton);
		botonRecursos.setMaximumSize(tamañoBoton);

		botonPrestamos.setMinimumSize(tamañoBoton);
		botonPrestamos.setPreferredSize(tamañoBoton);
		botonPrestamos.setMaximumSize(tamañoBoton);

		// ========================================
		// ALINEACIÓN DE LOS BOTONES
		// ========================================
		botonInicio.setAlignmentX(CENTER_ALIGNMENT);
		botonUsuarios.setAlignmentX(CENTER_ALIGNMENT);
		botonRecursos.setAlignmentX(CENTER_ALIGNMENT);
		botonPrestamos.setAlignmentX(CENTER_ALIGNMENT);

		// ========================================
		// AÑADIR BOTONES A LA BARRA LATERAL
		// ========================================
		barraLateral.add(Box.createVerticalStrut(30));
		barraLateral.add(botonInicio);
		barraLateral.add(Box.createVerticalStrut(10));
		barraLateral.add(botonUsuarios);
		barraLateral.add(Box.createVerticalStrut(10));
		barraLateral.add(botonRecursos);
		barraLateral.add(Box.createVerticalStrut(10));
		barraLateral.add(botonPrestamos);

		// ========================================
		// PANEL DE TARJETAS
		// ========================================
		gestorTarjetas = new CardLayout();
		panelTarjetas = new JPanel(gestorTarjetas);
		panelPrincipal.add(panelTarjetas, BorderLayout.CENTER);

		// ========================================
		// PANEL INICIAL
		// ========================================
		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(new Color(249, 247, 242));

		JLabel etiquetaInicio = new JLabel("Bienvenido a la Biblioteca Multimedia");
		etiquetaInicio.setFont(new Font("Segoe UI", Font.BOLD, 24));

		panelInicio.add(etiquetaInicio);

		// ========================================
		// PANEL DE CONTENIDO UNIFICADO
		// ========================================
		panelContenido = new PanelContenido(gestionUsuarios, gestionRecursos, gestionPrestamos);

		// ========================================
		// AÑADIR LAS DOS TARJETAS
		// ========================================
		panelTarjetas.add(panelInicio, "INICIO");
		panelTarjetas.add(panelContenido, "CONTENIDO");

		// ========================================
		// NAVEGACIÓN
		// ========================================
		botonInicio.addActionListener(e -> {
			mostrarInicio();
		});

		botonUsuarios.addActionListener(e -> {
			panelContenido.mostrarUsuarios();
			mostrarContenido();
		});

		botonRecursos.addActionListener(e -> {
			panelContenido.mostrarRecursos();
			mostrarContenido();
		});

		botonPrestamos.addActionListener(e -> {
			panelContenido.mostrarPrestamos();
			mostrarContenido();
		});

		// ========================================
		// MOSTRAR INICIO AL ARRANCAR
		// ========================================
		mostrarInicio();
	}

	// ========================================
	// MOSTRAR INICIO
	// ========================================
	private void mostrarInicio() {

		gestorTarjetas.show(panelTarjetas, "INICIO");
	}

	// ========================================
	// MOSTRAR CONTENIDO
	// ========================================

	private void mostrarContenido() {

		gestorTarjetas.show(panelTarjetas, "CONTENIDO");
	}
}