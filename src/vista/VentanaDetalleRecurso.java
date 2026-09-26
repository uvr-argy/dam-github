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

public class VentanaDetalleRecurso extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelSubnavbar;
    private JPanel panelPrestamos;

    private JButton botonEditar;
    private JButton botonEliminar;

    private Color colorFondo = new Color(249, 247, 242);
    private Color colorSubnavbar = new Color(227, 235, 222);
    private Color colorAzul = new Color(52, 80, 154);
    private Color colorVerde = new Color(123, 220, 99);
    private Color colorAmarillo = new Color(236, 206, 145);
    private Color colorRojo = new Color(243, 153, 131);

    public VentanaDetalleRecurso() {

        // =========================
        // CONFIGURACIÓN DE LA VENTANA
        // =========================

        setTitle(
            "Detalle del recurso"
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
            new Color(225, 210, 227)
        );

        panelSubnavbar.setBorder(
            BorderFactory.createEmptyBorder(
                10, 20, 10, 20
            )
        );

        panelPrincipal.add(
            panelSubnavbar,
            BorderLayout.NORTH
        );

        JLabel etiquetaTitulo = new JLabel(
            "Detalle del recurso"
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
                20, 20, 20, 20
            )
        );

        panelPrincipal.add(
            panelCentral,
            BorderLayout.CENTER
        );

        // =========================
        // INFORMACIÓN DEL RECURSO
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
                    15, 20, 15, 20
                )
            )
        );

        JLabel etiquetaInformacion = new JLabel(
            "Información del recurso"
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

        // =========================
        // DATOS DEL RECURSO
        // =========================

        JPanel panelDatosRecurso = new JPanel();

        panelDatosRecurso.setLayout(
            new BoxLayout(
                panelDatosRecurso,
                BoxLayout.Y_AXIS
            )
        );

        panelDatosRecurso.setBackground(
            Color.WHITE
        );

        panelDatosRecurso.setBorder(
            BorderFactory.createEmptyBorder(
                10, 0, 0, 0
            )
        );

        JLabel etiquetaId = new JLabel(
            "ID: R001"
        );

        JLabel etiquetaTituloRecurso = new JLabel(
            "Título: El Hobbit"
        );

        JLabel etiquetaTipo = new JLabel(
            "Tipo: Libro"
        );

        JLabel etiquetaAno = new JLabel(
            "Año: 1937"
        );

        JLabel etiquetaEstado = new JLabel(
            "Estado: Disponible"
        );

        JLabel etiquetaEspecifico = new JLabel(
            "Autor: J.R.R. Tolkien · 310 páginas"
        );

        Font fuenteDatos = new Font(
            "Segoe UI",
            Font.PLAIN,
            15
        );

        etiquetaId.setFont(
            fuenteDatos
        );

        etiquetaTituloRecurso.setFont(
            fuenteDatos
        );

        etiquetaTipo.setFont(
            fuenteDatos
        );

        etiquetaAno.setFont(
            fuenteDatos
        );

        etiquetaEstado.setFont(
            fuenteDatos
        );

        etiquetaEspecifico.setFont(
            fuenteDatos
        );

        panelDatosRecurso.add(
            etiquetaId
        );

        panelDatosRecurso.add(
            etiquetaTituloRecurso
        );

        panelDatosRecurso.add(
            etiquetaTipo
        );

        panelDatosRecurso.add(
            etiquetaAno
        );

        panelDatosRecurso.add(
            etiquetaEstado
        );

        panelDatosRecurso.add(
            etiquetaEspecifico
        );

        panelInformacion.add(
            panelDatosRecurso,
            BorderLayout.CENTER
        );

        // =========================
        // BOTONES
        // =========================

        JPanel panelBotones = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                5,
                0
            )
        );

        panelBotones.setBackground(
            Color.WHITE
        );

        botonEditar = new JButton(
            "Editar"
        );

        botonEditar.setBackground(
            colorAmarillo
        );

        botonEditar.setPreferredSize(
            new Dimension(
                100,
                35
            )
        );

        botonEliminar = new JButton(
            "Eliminar"
        );

        botonEliminar.setBackground(
            colorRojo
        );

        botonEliminar.setPreferredSize(
            new Dimension(
                100,
                35
            )
        );

        panelBotones.add(
            botonEditar
        );

        panelBotones.add(
            botonEliminar
        );

        panelInformacion.add(
            panelBotones,
            BorderLayout.SOUTH
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
                30, 0, 0, 0
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
            "Historial de préstamos"
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

        añadirPrestamoTemporal(
            "001",
            "Juan Pérez",
            "20/09/2026",
            "Activo",
            "-"
        );

        añadirPrestamoTemporal(
            "002",
            "Ana García",
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

        // =========================
        // SEPARACIÓN DE LA CABECERA
        // =========================

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
        String idUsuario,
        String nombreUsuario,
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
                    12, 15, 12, 10
                )
            )
        );

        panelPrestamo.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                90
            )
        );

        // =========================
        // DATOS
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

        JLabel etiquetaUsuario = new JLabel(
            nombreUsuario
        );

        etiquetaUsuario.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                16
            )
        );

        JLabel etiquetaId = new JLabel(
            "ID usuario: " + idUsuario
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
            etiquetaUsuario
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
        // BOTON
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

        JButton botonDevolver = new JButton(
            "Devolver"
        );

        botonDevolver.setBackground(
            colorRojo
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