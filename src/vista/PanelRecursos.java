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

public class PanelRecursos extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel panelSubnavbar;
    private JPanel panelListaRecursos;

    private JTextField campoBuscar;

    private JButton botonBuscar;
    private JButton botonAnadir;

    public PanelRecursos() {

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
            new Color(225, 210, 227)
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
            "Recursos"
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
            "Buscar recurso por ID o título"
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
            "Añadir recurso"
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
        // LISTA DE RECURSOS
        // =========================

        panelListaRecursos = new JPanel();

        panelListaRecursos.setLayout(
            new BoxLayout(
                panelListaRecursos,
                BoxLayout.Y_AXIS
            )
        );

        panelListaRecursos.setBackground(
            Color.WHITE
        );

        // =========================
        // DATOS TEMPORALES
        // =========================

        añadirRecursoTemporal(
            "R001",
            "El Hobbit",
            "Libro",
            "1937",
            "Disponible",
            "Autor: J.R.R. Tolkien · 310 páginas"
        );

        añadirRecursoTemporal(
            "R002",
            "Interstellar",
            "Película",
            "2014",
            "Prestado",
            "Director: Christopher Nolan · 169 minutos"
        );

        añadirRecursoTemporal(
            "R003",
            "Minecraft",
            "Videojuego",
            "2011",
            "Disponible",
            "Plataforma: PC · PEGI: 7"
        );

        JScrollPane scrollRecursos =
            new JScrollPane(
                panelListaRecursos
            );

        scrollRecursos.setBorder(null);

        panelCentral.add(
            scrollRecursos,
            BorderLayout.CENTER
        );
    }

    private void añadirRecursoTemporal(
        String id,
        String titulo,
        String tipo,
        String ano,
        String estado,
        String informacionEspecifica
    ) {

        JPanel panelRecurso = new JPanel(
            new BorderLayout()
        );

        panelRecurso.setBackground(
            Color.WHITE
        );

        panelRecurso.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 220, 220)
                ),
                BorderFactory.createEmptyBorder(
                    10, 15, 10, 10
                )
            )
        );

        panelRecurso.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                85
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

        JLabel etiquetaTituloRecurso = new JLabel(
            id + "   " + titulo
        );

        etiquetaTituloRecurso.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                16
            )
        );

        JLabel etiquetaDatos = new JLabel(
            tipo + " · " + ano + " · " + estado
        );

        etiquetaDatos.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        JLabel etiquetaInformacion = new JLabel(
            informacionEspecifica
        );

        etiquetaInformacion.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        panelDatos.add(
            etiquetaTituloRecurso
        );

        panelDatos.add(
            etiquetaDatos
        );

        panelDatos.add(
            etiquetaInformacion
        );

        panelRecurso.add(
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

        panelRecurso.add(
            panelBotones,
            BorderLayout.EAST
        );

        // =========================
        // ABRIR DETALLE
        // =========================

        panelRecurso.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        panelDatos.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaTituloRecurso.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaDatos.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        etiquetaInformacion.setCursor(
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

                    VentanaDetalleRecurso ventanaDetalle =
                        new VentanaDetalleRecurso();

                    ventanaDetalle.setVisible(true);
                }
            };

        panelRecurso.addMouseListener(
            abrirDetalle
        );

        panelDatos.addMouseListener(
            abrirDetalle
        );

        etiquetaTituloRecurso.addMouseListener(
            abrirDetalle
        );

        etiquetaDatos.addMouseListener(
            abrirDetalle
        );

        etiquetaInformacion.addMouseListener(
            abrirDetalle
        );

        panelListaRecursos.add(
            panelRecurso
        );
    }
}