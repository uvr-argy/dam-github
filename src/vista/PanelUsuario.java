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

public class PanelUsuario extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel panelSubnavbar;
    private JPanel panelListaUsuarios;

    private JTextField campoBuscar;

    private JButton botonBuscar;
    private JButton botonAnadir;

    public PanelUsuario() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        // =========================
        // SUBNAVBAR
        // =========================

        panelSubnavbar = new JPanel(new BorderLayout());

        panelSubnavbar.setPreferredSize(
            new Dimension(0, 60)
        );

        panelSubnavbar.setBackground(
            new Color(227, 235, 222)
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
            "Usuarios"
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
            "Buscar usuario por ID, nombre o email"
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
            "Añadir usuario"
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
        // LISTA DE USUARIOS
        // =========================

        panelListaUsuarios = new JPanel();

        panelListaUsuarios.setLayout(
            new BoxLayout(
                panelListaUsuarios,
                BoxLayout.Y_AXIS
            )
        );

        panelListaUsuarios.setBackground(
            Color.WHITE
        );

        añadirUsuarioTemporal(
            "001",
            "Juan Pérez",
            "juan@email.com"
        );

        añadirUsuarioTemporal(
            "002",
            "Ana García",
            "ana@email.com"
        );

        añadirUsuarioTemporal(
            "003",
            "Carlos López",
            "carlos@email.com"
        );

        JScrollPane scrollUsuarios =
            new JScrollPane(
                panelListaUsuarios
            );

        scrollUsuarios.setBorder(null);

        panelCentral.add(
            scrollUsuarios,
            BorderLayout.CENTER
        );
    }

    // =========================================================
    // CREAR USUARIO TEMPORAL
    // =========================================================

    private void añadirUsuarioTemporal(
        String id,
        String nombre,
        String email
    ) {

        JPanel panelUsuario = new JPanel(
            new BorderLayout()
        );

        panelUsuario.setBackground(
            Color.WHITE
        );

        panelUsuario.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 220, 220)
                ),
                BorderFactory.createEmptyBorder(
                    10, 15, 10, 10
                )
            )
        );

        panelUsuario.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                70
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

        JLabel etiquetaNombre = new JLabel(
            id + "   " + nombre
        );

        etiquetaNombre.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                16
            )
        );

        JLabel etiquetaEmail = new JLabel(
            email
        );

        etiquetaEmail.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        panelDatos.add(
            etiquetaNombre
        );

        panelDatos.add(
            etiquetaEmail
        );

        panelUsuario.add(
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

        JButton botonEliminar = new JButton(
            "Eliminar"
        );

        botonEliminar.setBackground(
            new Color(243, 153, 131)
        );

        panelBotones.add(
            botonEditar
        );

        panelBotones.add(
            botonEliminar
        );

        panelUsuario.add(
            panelBotones,
            BorderLayout.EAST
        );

        // =========================
        // ABRIR DETALLE
        // =========================

        panelUsuario.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        panelDatos.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaNombre.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaEmail.setCursor(
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

                    PanelDetalleUsuario ventanaDetalle =
                        new PanelDetalleUsuario();

                    ventanaDetalle.setVisible(true);
                }
            };

        panelUsuario.addMouseListener(
            abrirDetalle
        );

        panelDatos.addMouseListener(
            abrirDetalle
        );

        etiquetaNombre.addMouseListener(
            abrirDetalle
        );

        etiquetaEmail.addMouseListener(
            abrirDetalle
        );

        panelListaUsuarios.add(
            panelUsuario
        );
    }
}