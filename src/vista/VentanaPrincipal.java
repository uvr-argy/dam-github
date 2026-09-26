package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.Box;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    // COMPONENTES PRINCIPALES
    private JPanel panelContenido;
    private CardLayout gestorTarjetas;

    // BARRA SUPERIOR
    private JPanel barraSuperior;
    private JLabel etiquetaLogo;
    private JLabel etiquetaNombreAplicacion;

    // BARRA LATERAL   private JButton botonInicio;
    private JButton botonUsuarios;
    private JButton botonRecursos;
    private JButton botonPrestamos;

    // COLOR DE LA APLICACIÓN
    private Color colorBarraSuperior = new Color(40, 40, 40);


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {

                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaPrincipal() {

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

        etiquetaNombreAplicacion.setFont(
            new Font("Segoe UI", Font.BOLD, 20)
        );

        barraSuperior.add(
            etiquetaNombreAplicacion,
            BorderLayout.CENTER
        );

        // ========================================
        // BARRA LATERAL
        // ========================================
        JPanel barraLateral = new JPanel();
        barraLateral.setBackground(new Color(207, 215, 235));

        barraLateral.setLayout(
            new BoxLayout(barraLateral, BoxLayout.Y_AXIS)
        );

        barraLateral.setPreferredSize(
            new Dimension(180, 0)
        );

        panelPrincipal.add(
            barraLateral,
            BorderLayout.WEST
        );


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

        // Dimensiones custom de los botones
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
        
        
        // Alineación de los botones
        botonInicio.setAlignmentX(CENTER_ALIGNMENT);
        botonUsuarios.setAlignmentX(CENTER_ALIGNMENT);
        botonRecursos.setAlignmentX(CENTER_ALIGNMENT);
        botonPrestamos.setAlignmentX(CENTER_ALIGNMENT);


        // ========================================
        // AÑADIR BOTONES A LA BARRA LATERAL
        // ========================================
        barraLateral.add(
                Box.createVerticalStrut(10)
            );
        
        barraLateral.add(botonInicio);

        barraLateral.add(
            Box.createVerticalStrut(10)
        );

        barraLateral.add(botonUsuarios);

        barraLateral.add(
            Box.createVerticalStrut(10)
        );

        barraLateral.add(botonRecursos);

        barraLateral.add(
            Box.createVerticalStrut(10)
        );

        barraLateral.add(botonPrestamos);

        // ========================================
        // PANEL DE CONTENIDO
        // ========================================
        gestorTarjetas = new CardLayout();

        panelContenido = new JPanel();
        panelContenido.setLayout(gestorTarjetas);

        panelPrincipal.add(
            panelContenido,
            BorderLayout.CENTER
        );

        // ========================================
        // PANELES TEMPORALES
        // ========================================
        JPanel panelInicio = new JPanel();
        JPanel panelUsuarios = new JPanel();
        JPanel panelRecursos = new JPanel();
        JPanel panelPrestamos = new JPanel();

        panelContenido.add(
            panelInicio,
            "INICIO"
        );

        panelContenido.add(
            panelUsuarios,
            "USUARIOS"
        );

        panelContenido.add(
            panelRecursos,
            "RECURSOS"
        );

        panelContenido.add(
            panelPrestamos,
            "PRESTAMOS"
        );

        // ========================================
        // NAVEGACIÓN
        // ========================================
        botonInicio.addActionListener(e ->
            mostrarPanel("INICIO")
        );

        botonUsuarios.addActionListener(e ->
            mostrarPanel("USUARIOS")
        );

        botonRecursos.addActionListener(e ->
            mostrarPanel("RECURSOS")
        );

        botonPrestamos.addActionListener(e ->
            mostrarPanel("PRESTAMOS")
        );

        // ========================================
        // PANEL INICIAL
        // ========================================
        mostrarPanel("INICIO");
    }

    // ========================================
    // CAMBIAR PANEL
    // ========================================
    private void mostrarPanel(String nombrePanel) {

        gestorTarjetas.show(
            panelContenido,
            nombrePanel
        );
    }
}