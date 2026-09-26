package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaDetalleUsuario extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelSubnavbar;
    private JPanel panelPrestamos;

    private JButton botonAnadirPrestamo;

    private Color colorFondo = new Color(249, 247, 242);
    private Color colorSubnavbar = new Color(227, 235, 222);
    private Color colorAzul = new Color(52, 80, 154);
    private Color colorVerde = new Color(123, 220, 99);
    private Color colorAmarillo = new Color(236, 206, 145);
    private Color colorRojo = new Color(243, 153, 131);

    public VentanaDetalleUsuario() {

        // =========================
        // CONFIGURACIÓN DE LA VENTANA
        // =========================

        setTitle(
            "Detalle del usuario"
        );

        setSize(
            800,
            600
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        // =========================
        // PANEL PRINCIPAL
        // =========================

        JPanel panelPrincipal = new JPanel(
            new BorderLayout()
        );

        panelPrincipal.setBackground(
            colorFondo
        );

        setContentPane(
            panelPrincipal
        );

        // =========================
        // SUBNAVBAR
        // =========================

        panelSubnavbar = new JPanel(
            new BorderLayout()
        );

        panelSubnavbar.setPreferredSize(
            new Dimension(0, 60)
        );

        panelSubnavbar.setBackground(
            colorSubnavbar
        );

        panelSubnavbar.setBorder(
            BorderFactory.createEmptyBorder(
                10,
                20,
                10,
                20
            )
        );

        panelPrincipal.add(
            panelSubnavbar,
            BorderLayout.NORTH
        );

        JLabel etiquetaTitulo = new JLabel(
            "Detalle del usuario"
        );

        etiquetaTitulo.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                24
            )
        );

        panelSubnavbar.add(
            etiquetaTitulo,
            BorderLayout.WEST
        );

        // =========================
        // PANEL CENTRAL
        // =========================

        JPanel panelCentral = new JPanel(
            new BorderLayout()
        );

        panelCentral.setBackground(
            colorFondo
        );

        panelCentral.setBorder(
            BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
            )
        );

        panelPrincipal.add(
            panelCentral,
            BorderLayout.CENTER
        );

        // =========================
        // INFORMACIÓN DEL USUARIO
        // =========================

        JPanel panelInformacion = new JPanel(
            new BorderLayout()
        );

        panelInformacion.setBackground(
            Color.WHITE
        );

        panelInformacion.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 220, 220)
                ),
                BorderFactory.createEmptyBorder(
                    15,
                    20,
                    15,
                    20
                )
            )
        );

        JLabel etiquetaInformacion = new JLabel(
            "Información del usuario"
        );

        etiquetaInformacion.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                18
            )
        );

        panelInformacion.add(
            etiquetaInformacion,
            BorderLayout.NORTH
        );

        JPanel panelDatosUsuario = new JPanel();

        panelDatosUsuario.setLayout(
            new BoxLayout(
                panelDatosUsuario,
                BoxLayout.Y_AXIS
            )
        );

        panelDatosUsuario.setBackground(
            Color.WHITE
        );

        panelDatosUsuario.setBorder(
            BorderFactory.createEmptyBorder(
                10,
                0,
                0,
                0
            )
        );

        JLabel etiquetaId = new JLabel(
            "ID: 001"
        );

        JLabel etiquetaNombre = new JLabel(
            "Nombre: Juan Pérez"
        );

        JLabel etiquetaEmail = new JLabel(
            "Email: juan@email.com"
        );

        Font fuenteDatos = new Font(
            "Segoe UI",
            Font.PLAIN,
            15
        );

        etiquetaId.setFont(
            fuenteDatos
        );

        etiquetaNombre.setFont(
            fuenteDatos
        );

        etiquetaEmail.setFont(
            fuenteDatos
        );

        panelDatosUsuario.add(
            etiquetaId
        );

        panelDatosUsuario.add(
            etiquetaNombre
        );

        panelDatosUsuario.add(
            etiquetaEmail
        );

        panelInformacion.add(
            panelDatosUsuario,
            BorderLayout.CENTER
        );

        panelCentral.add(
            panelInformacion,
            BorderLayout.NORTH
        );

        // =========================
        // SECCIÓN PRÉSTAMOS
        // =========================

        JPanel panelSeccionPrestamos = new JPanel(
            new BorderLayout()
        );

        panelSeccionPrestamos.setBackground(
            colorFondo
        );

        panelSeccionPrestamos.setBorder(
            BorderFactory.createEmptyBorder(
                50,
                0,
                0,
                0
            )
        );

        // =========================
        // CABECERA PRÉSTAMOS
        // =========================

        JPanel panelCabeceraPrestamos = new JPanel(
            new BorderLayout()
        );

        panelCabeceraPrestamos.setBackground(
            colorFondo
        );

        JLabel etiquetaPrestamos = new JLabel(
            "Préstamos"
        );

        etiquetaPrestamos.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                20
            )
        );

        panelCabeceraPrestamos.add(
            etiquetaPrestamos,
            BorderLayout.WEST
        );

        botonAnadirPrestamo = new JButton(
            "Añadir préstamo"
        );

        botonAnadirPrestamo.setForeground(
            Color.BLACK
        );

        botonAnadirPrestamo.setBackground(
            colorVerde
        );

        botonAnadirPrestamo.setPreferredSize(
            new Dimension(
                150,
                40
            )
        );

        panelCabeceraPrestamos.add(
            botonAnadirPrestamo,
            BorderLayout.EAST
        );

        panelSeccionPrestamos.add(
            panelCabeceraPrestamos,
            BorderLayout.NORTH
        );

        // =========================
        // LISTA DE PRÉSTAMOS
        // =========================

        panelPrestamos = new JPanel();

        panelPrestamos.setLayout(
            new BoxLayout(
                panelPrestamos,
                BoxLayout.Y_AXIS
            )
        );

        panelPrestamos.setBackground(
            colorFondo
        );

        // Datos temporales para visualizar
        añadirPrestamoTemporal(
            "L001",
            "El Hobbit",
            "20/09/2026",
            "Activo",
            "-"
        );

        añadirPrestamoTemporal(
            "P002",
            "Interstellar",
            "10/09/2026",
            "Finalizado",
            "20/09/2026"
        );

        JScrollPane scrollPrestamos =
            new JScrollPane(
                panelPrestamos
            );

        scrollPrestamos.setBorder(
        	    null
        	);

        	// ESPACIO ENTRE LA CABECERA Y LA LISTA
        	JPanel panelListaPrestamos = new JPanel(
        	    new BorderLayout()
        	);

        	panelListaPrestamos.setBackground(
        	    colorFondo
        	);

        	panelListaPrestamos.setBorder(
        	    BorderFactory.createEmptyBorder(
        	        15, 0, 0, 0
        	    )
        	);

        	panelListaPrestamos.add(
        	    scrollPrestamos,
        	    BorderLayout.CENTER
        	);

        	panelSeccionPrestamos.add(
        	    panelListaPrestamos,
        	    BorderLayout.CENTER
        	);

        panelCentral.add(
            panelSeccionPrestamos,
            BorderLayout.CENTER
        );
    }

    // =========================================================
    // PRÉSTAMO TEMPORAL
    // =========================================================

    private void añadirPrestamoTemporal(
        String idRecurso,
        String titulo,
        String fechaPrestamo,
        String estado,
        String fechaDevolucion
    ) {

        JPanel panelPrestamo = new JPanel(
            new BorderLayout()
        );

        panelPrestamo.setBackground(
            Color.WHITE
        );

        panelPrestamo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 220, 220)
                ),
                BorderFactory.createEmptyBorder(
                    12,
                    15,
                    12,
                    10
                )
            )
        );

        panelPrestamo.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                100
            )
        );

        // =========================
        // DATOS DEL PRÉSTAMO
        // =========================

        JPanel panelDatos = new JPanel();

        panelDatos.setLayout(
            new BoxLayout(
                panelDatos,
                BoxLayout.Y_AXIS
            )
        );

        panelDatos.setOpaque(
            false
        );

        JLabel etiquetaRecurso = new JLabel(
            titulo
        );

        etiquetaRecurso.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                16
            )
        );

        JLabel etiquetaId = new JLabel(
            "ID recurso: " + idRecurso
        );

        JLabel etiquetaFecha = new JLabel(
            "Fecha préstamo: " + fechaPrestamo
        );

        JLabel etiquetaEstado = new JLabel(
            "Estado: " + estado
        );

        JLabel etiquetaDevolucion = new JLabel(
            "Fecha devolución: " + fechaDevolucion
        );

        Font fuenteSecundaria = new Font(
            "Segoe UI",
            Font.PLAIN,
            13
        );

        etiquetaId.setFont(
            fuenteSecundaria
        );

        etiquetaFecha.setFont(
            fuenteSecundaria
        );

        etiquetaEstado.setFont(
            fuenteSecundaria
        );

        etiquetaDevolucion.setFont(
            fuenteSecundaria
        );

        panelDatos.add(
            etiquetaRecurso
        );

        panelDatos.add(
            etiquetaId
        );

        panelDatos.add(
            etiquetaFecha
        );

        panelDatos.add(
            etiquetaEstado
        );

        panelDatos.add(
            etiquetaDevolucion
        );

        panelPrestamo.add(
            panelDatos,
            BorderLayout.CENTER
        );

        // =========================
        // BOTONES
        // =========================

        JPanel panelBotones = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                5,
                5
            )
        );

        panelBotones.setOpaque(
            false
        );

        JButton botonEditar = new JButton(
            "Editar"
        );

        botonEditar.setBackground(
            colorAmarillo
        );

        JButton botonDevolver = new JButton(
            "Devolver"
        );

        botonDevolver.setBackground(
            colorRojo
        );

        panelBotones.add(
            botonEditar
        );

        panelBotones.add(
            botonDevolver
        );

        panelPrestamo.add(
            panelBotones,
            BorderLayout.EAST
        );

        panelPrestamos.add(
            panelPrestamo
        );
    }
}