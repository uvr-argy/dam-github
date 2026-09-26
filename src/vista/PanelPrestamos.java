package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelPrestamos extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel panelSubnavbar;
    private JPanel panelListaPrestamos;

    private JTextField campoBuscar;

    private JButton botonBuscar;
    private JButton botonAnadir;

    public PanelPrestamos() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

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
            new Color(239, 232, 209)
        );

        panelSubnavbar.setBorder(
            BorderFactory.createEmptyBorder(
                10, 20, 10, 20
            )
        );

        add(
            panelSubnavbar,
            BorderLayout.NORTH
        );

        JLabel etiquetaTitulo = new JLabel(
            "Préstamos"
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
        // BUSQUEDA
        // =========================

        JPanel panelBusqueda = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                5,
                0
            )
        );

        panelBusqueda.setOpaque(false);

        campoBuscar = new JTextField();

        campoBuscar.setPreferredSize(
            new Dimension(250, 35)
        );

        campoBuscar.setToolTipText(
            "Buscar préstamo por usuario o recurso"
        );

        botonBuscar = new JButton(
            "Buscar"
        );

        botonBuscar.setForeground(
            new Color(249, 247, 242)
        );

        botonBuscar.setBackground(
            new Color(52, 80, 154)
        );

        botonBuscar.setPreferredSize(
            new Dimension(90, 35)
        );

        panelBusqueda.add(
            campoBuscar
        );

        panelBusqueda.add(
            botonBuscar
        );

        panelSubnavbar.add(
            panelBusqueda,
            BorderLayout.EAST
        );

        // =========================
        // PANEL CENTRAL
        // =========================

        JPanel panelCentral = new JPanel(
            new BorderLayout()
        );

        panelCentral.setBackground(
            new Color(249, 247, 242)
        );

        panelCentral.setBorder(
            BorderFactory.createEmptyBorder(
                20, 20, 20, 20
            )
        );

        add(
            panelCentral,
            BorderLayout.CENTER
        );

        // =========================
        // BOTON AÑADIR
        // =========================

        FlowLayout fl_panelAcciones =
            new FlowLayout(
                FlowLayout.RIGHT
            );

        JPanel panelAcciones = new JPanel(
            fl_panelAcciones
        );

        panelAcciones.setBackground(
            Color.WHITE
        );

        botonAnadir = new JButton(
            "Añadir préstamo"
        );

        botonAnadir.setBackground(
            new Color(123, 220, 99)
        );

        botonAnadir.setPreferredSize(
            new Dimension(150, 40)
        );

        panelAcciones.add(
            botonAnadir
        );

        panelCentral.add(
            panelAcciones,
            BorderLayout.NORTH
        );

        // =========================
        // LISTA DE PRÉSTAMOS
        // =========================

        panelListaPrestamos = new JPanel();

        panelListaPrestamos.setLayout(
            new BoxLayout(
                panelListaPrestamos,
                BoxLayout.Y_AXIS
            )
        );

        panelListaPrestamos.setBackground(
            Color.WHITE
        );

        // =========================
        // DATOS TEMPORALES
        // =========================

        añadirPrestamoTemporal(
            "P001",
            "Juan Pérez",
            "R001",
            "El Hobbit",
            "20/09/2026",
            "Activo",
            "-"
        );

        añadirPrestamoTemporal(
            "P002",
            "Ana García",
            "R002",
            "Interstellar",
            "10/09/2026",
            "Finalizado",
            "20/09/2026"
        );

        añadirPrestamoTemporal(
            "P003",
            "Carlos López",
            "R003",
            "Minecraft",
            "22/09/2026",
            "Activo",
            "-"
        );

        JScrollPane scrollPrestamos =
            new JScrollPane(
                panelListaPrestamos
            );

        scrollPrestamos.setBorder(null);

        panelCentral.add(
            scrollPrestamos,
            BorderLayout.CENTER
        );
    }

    // =========================================================
    // PRÉSTAMO TEMPORAL
    // =========================================================

    private void añadirPrestamoTemporal(
        String idPrestamo,
        String nombreUsuario,
        String idRecurso,
        String tituloRecurso,
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
                100
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

        panelDatos.setOpaque(false);

        JLabel etiquetaRecurso = new JLabel(
            tituloRecurso
        );

        etiquetaRecurso.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                16
            )
        );

        JLabel etiquetaUsuario = new JLabel(
            "Usuario: " + nombreUsuario
        );

        JLabel etiquetaId = new JLabel(
            "ID préstamo: " + idPrestamo
            + " · ID recurso: " + idRecurso
        );

        JLabel etiquetaFecha = new JLabel(
            "Fecha préstamo: " + fechaPrestamo
        );

        JLabel etiquetaEstado = new JLabel(
            "Estado: " + estado
            + " · Fecha devolución: " + fechaDevolucion
        );

        Font fuenteSecundaria = new Font(
            "Segoe UI",
            Font.PLAIN,
            13
        );

        etiquetaUsuario.setFont(
            fuenteSecundaria
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

        panelDatos.add(
            etiquetaRecurso
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

        panelBotones.setOpaque(false);

        JButton botonEditar = new JButton(
            "Editar"
        );

        botonEditar.setBackground(
            new Color(236, 206, 145)
        );

        JButton botonDevolver = new JButton(
            "Devolver"
        );

        botonDevolver.setBackground(
            new Color(243, 153, 131)
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

        // =========================
        // ABRIR DETALLE
        // =========================

        panelPrestamo.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        panelDatos.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaRecurso.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaUsuario.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaId.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaFecha.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaEstado.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        MouseAdapter abrirDetalle =
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    // Aquí abriremos posteriormente
                    // el detalle del préstamo.
                }
            };

        panelPrestamo.addMouseListener(
            abrirDetalle
        );

        panelDatos.addMouseListener(
            abrirDetalle
        );

        etiquetaRecurso.addMouseListener(
            abrirDetalle
        );

        etiquetaUsuario.addMouseListener(
            abrirDetalle
        );

        etiquetaId.addMouseListener(
            abrirDetalle
        );

        etiquetaFecha.addMouseListener(
            abrirDetalle
        );

        etiquetaEstado.addMouseListener(
            abrirDetalle
        );

        panelListaPrestamos.add(
            panelPrestamo
        );
    }
}