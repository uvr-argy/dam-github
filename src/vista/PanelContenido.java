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

public class PanelContenido extends JPanel {

    private static final long serialVersionUID = 1L;

    // =========================================================
    // MODOS
    // =========================================================

    public static final int MODO_USUARIOS = 1;
    public static final int MODO_RECURSOS = 2;
    public static final int MODO_PRESTAMOS = 3;

    private int modoActual = MODO_USUARIOS;

    // =========================================================
    // ESTRUCTURA COMÚN
    // =========================================================

    private JPanel panelSubnavbar;
    private JPanel panelCentral;
    private JPanel panelAcciones;
    private JPanel panelLista;

    private JLabel etiquetaTitulo;
    private JTextField campoBuscar;
    private JButton botonBuscar;
    private JButton botonAnadir;

    // =========================================================
    // COLORES
    // =========================================================

    private final Color colorFondo = new Color(249, 247, 242);
    private final Color colorAzul = new Color(52, 80, 154);
    private final Color colorVerde = new Color(123, 220, 99);
    private final Color colorAmarillo = new Color(236, 206, 92);
    private final Color colorRojo = new Color(230, 100, 100);

    public PanelContenido() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        crearEstructura();

        mostrarUsuarios();
    }

    // =========================================================
    // ESTRUCTURA GENERAL
    // =========================================================

    private void crearEstructura() {

        // -------------------------
        // SUBNAVBAR
        // -------------------------

        panelSubnavbar = new JPanel(
            new BorderLayout()
        );

        panelSubnavbar.setPreferredSize(
            new Dimension(0, 60)
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

        etiquetaTitulo = new JLabel();

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

        // -------------------------
        // BUSCADOR
        // -------------------------

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

        botonBuscar = new JButton("Buscar");

        botonBuscar.setForeground(
            new Color(249, 247, 242)
        );

        botonBuscar.setBackground(colorAzul);

        botonBuscar.setPreferredSize(
            new Dimension(90, 35)
        );

        panelBusqueda.add(campoBuscar);
        panelBusqueda.add(botonBuscar);

        panelSubnavbar.add(
            panelBusqueda,
            BorderLayout.EAST
        );

        // -------------------------
        // PANEL CENTRAL
        // -------------------------

        panelCentral = new JPanel(
            new BorderLayout()
        );

        panelCentral.setBackground(colorFondo);

        panelCentral.setBorder(
            BorderFactory.createEmptyBorder(
                20, 20, 20, 20
            )
        );

        add(
            panelCentral,
            BorderLayout.CENTER
        );

        // -------------------------
        // BOTÓN AÑADIR
        // -------------------------

        panelAcciones = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT
            )
        );

        panelAcciones.setBackground(Color.WHITE);

        botonAnadir = new JButton();

        botonAnadir.setBackground(colorVerde);

        botonAnadir.setPreferredSize(
            new Dimension(160, 40)
        );

        panelAcciones.add(botonAnadir);

        panelCentral.add(
            panelAcciones,
            BorderLayout.NORTH
        );

        // -------------------------
        // LISTA
        // -------------------------

        panelLista = new JPanel();

        panelLista.setLayout(
            new BoxLayout(
                panelLista,
                BoxLayout.Y_AXIS
            )
        );

        panelLista.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(
            panelLista
        );

        scroll.setBorder(null);

        panelCentral.add(
            scroll,
            BorderLayout.CENTER
        );
    }

    // =========================================================
    // CAMBIO DE MODO
    // =========================================================

    public void mostrarUsuarios() {

        modoActual = MODO_USUARIOS;

        actualizarVista();
    }

    public void mostrarRecursos() {

        modoActual = MODO_RECURSOS;

        actualizarVista();
    }

    public void mostrarPrestamos() {

        modoActual = MODO_PRESTAMOS;

        actualizarVista();
    }

    private void actualizarVista() {

        configurarSubnavbar();
        configurarBotonAnadir();
        cargarDatosTemporales();

        revalidate();
        repaint();
    }

    // =========================================================
    // SUBNAVBAR ADAPTABLE
    // =========================================================

    private void configurarSubnavbar() {

        switch (modoActual) {

            case MODO_USUARIOS:

                panelSubnavbar.setBackground(
                    new Color(227, 235, 222)
                );

                etiquetaTitulo.setText("Usuarios");

                campoBuscar.setToolTipText(
                    "Buscar usuario por ID, nombre o email"
                );

                break;

            case MODO_RECURSOS:

                panelSubnavbar.setBackground(
                    new Color(220, 230, 244)
                );

                etiquetaTitulo.setText("Recursos");

                campoBuscar.setToolTipText(
                    "Buscar recurso por ID, título o tipo"
                );

                break;

            case MODO_PRESTAMOS:

                panelSubnavbar.setBackground(
                    new Color(239, 232, 209)
                );

                etiquetaTitulo.setText("Préstamos");

                campoBuscar.setToolTipText(
                    "Buscar préstamo por usuario o recurso"
                );

                break;
        }
    }

    // =========================================================
    // BOTÓN AÑADIR ADAPTABLE
    // =========================================================

    private void configurarBotonAnadir() {

        for (var listener : botonAnadir.getActionListeners()) {
            botonAnadir.removeActionListener(listener);
        }

        switch (modoActual) {

            case MODO_USUARIOS:

                botonAnadir.setText(
                    "Añadir usuario"
                );

                botonAnadir.addActionListener(
                    e -> abrirFormulario()
                );

                break;

            case MODO_RECURSOS:

                botonAnadir.setText(
                    "Añadir recurso"
                );

                botonAnadir.addActionListener(
                    e -> abrirFormulario()
                );

                break;

            case MODO_PRESTAMOS:

                botonAnadir.setText(
                    "Añadir préstamo"
                );

                botonAnadir.addActionListener(
                    e -> abrirFormulario()
                );

                break;
        }
    }

    // =========================================================
    // FORMULARIO UNIFICADO
    // =========================================================

    private void abrirFormulario() {

        /*
         * Un único formulario para los tres modos.
         *
         * La clase FormularioContenido será la encargada
         * de adaptar sus campos según el modo recibido.
         */

        FormularioContenido formulario =
            new FormularioContenido(modoActual);

        formulario.setVisible(true);
    }

    // =========================================================
    // DATOS TEMPORALES
    // =========================================================

    private void cargarDatosTemporales() {

        panelLista.removeAll();

        switch (modoActual) {

            case MODO_USUARIOS:

                cargarUsuariosTemporales();

                break;

            case MODO_RECURSOS:

                cargarRecursosTemporales();

                break;

            case MODO_PRESTAMOS:

                cargarPrestamosTemporales();

                break;
        }

        panelLista.revalidate();
        panelLista.repaint();
    }

    private void cargarUsuariosTemporales() {

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
    }

    private void cargarRecursosTemporales() {

        añadirRecursoTemporal(
            "R001",
            "El Hobbit",
            "Libro",
            "1937",
            "Disponible",
            "J.R.R. Tolkien",
            "310 páginas"
        );

        añadirRecursoTemporal(
            "R002",
            "Interstellar",
            "Película",
            "2014",
            "Prestado",
            "Christopher Nolan",
            "169 minutos"
        );

        añadirRecursoTemporal(
            "R003",
            "Minecraft",
            "Videojuego",
            "2011",
            "Disponible",
            "PC",
            "PEGI 7"
        );
    }

    private void cargarPrestamosTemporales() {

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
    }

    // =========================================================
    // ESTRUCTURA COMÚN DE FILAS
    // =========================================================

    private JPanel crearFila(int altura) {

        JPanel fila = new JPanel(
            new BorderLayout()
        );

        fila.setBackground(Color.WHITE);

        fila.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 220, 220)
                ),
                BorderFactory.createEmptyBorder(
                    12, 15, 12, 10
                )
            )
        );

        fila.setMaximumSize(
            new Dimension(
                Integer.MAX_VALUE,
                altura
            )
        );

        return fila;
    }

    private JPanel crearPanelDatos() {

        JPanel panelDatos = new JPanel();

        panelDatos.setLayout(
            new BoxLayout(
                panelDatos,
                BoxLayout.Y_AXIS
            )
        );

        panelDatos.setOpaque(false);

        return panelDatos;
    }

    private JPanel crearPanelBotones() {

        JPanel panelBotones = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT,
                5,
                5
            )
        );

        panelBotones.setOpaque(false);

        return panelBotones;
    }

    private JLabel crearEtiquetaPrincipal(
        String texto
    ) {

        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                16
            )
        );

        return etiqueta;
    }

    private JLabel crearEtiquetaSecundaria(
        String texto
    ) {

        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(
            new Font(
                "Segoe UI",
                Font.PLAIN,
                13
            )
        );

        return etiqueta;
    }

    // =========================================================
    // FILA DE USUARIO
    // =========================================================

    private void añadirUsuarioTemporal(
            String id,
            String nombre,
            String email
    ) {

        JPanel fila = crearFila(75);

        JPanel panelDatos = crearPanelDatos();

        panelDatos.add(
            crearEtiquetaPrincipal(
                id + " · " + nombre
            )
        );

        panelDatos.add(
            crearEtiquetaSecundaria(
                "Email: " + email
            )
        );

        fila.add(
            panelDatos,
            BorderLayout.CENTER
        );

        JPanel panelBotones = crearPanelBotones();

        JButton botonEditar =
            crearBotonEditar();

        JButton botonEliminar =
            crearBotonEliminar();

        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);

        fila.add(
            panelBotones,
            BorderLayout.EAST
        );

        // ========================================
        // EDITAR
        // ========================================

        botonEditar.addActionListener(
            e -> abrirFormularioEdicion(
                id,
                nombre,
                email
            )
        );

        // ========================================
        // ELIMINAR
        // ========================================

        botonEliminar.addActionListener(
            e -> abrirConfirmacionEliminacion(
                "Usuario",
                nombre + " (ID: " + id + ")"
            )
        );

        // ========================================
        // ABRIR DETALLE
        // ========================================

        añadirEventoDetalle(
            fila,
            crearDatosUsuario(
                id,
                nombre,
                email
            )
        );

        panelLista.add(fila);
    }

    // =========================================================
    // FILA DE RECURSO
    // =========================================================

    private void añadirRecursoTemporal(
            String id,
            String titulo,
            String tipo,
            String ano,
            String estado,
            String informacion1,
            String informacion2
    ) {

        // La fila solo necesita espacio para:
        // - Nombre del recurso
        // - Tipo
        // - Botones
        JPanel fila = crearFila(75);

        // ========================================
        // INFORMACIÓN VISIBLE DEL RECURSO
        // ========================================

        JPanel panelDatos = crearPanelDatos();

        panelDatos.add(crearEtiquetaPrincipal(id + " · " + titulo));
        panelDatos.add(crearEtiquetaSecundaria("Tipo: " + tipo));

        fila.add(
            panelDatos,
            BorderLayout.CENTER
        );

        // ========================================
        // BOTONES
        // ========================================

        JPanel panelBotones = crearPanelBotones();

        JButton botonEditar =
            crearBotonEditar();

        JButton botonEliminar =
            crearBotonEliminar();

        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);

        fila.add(
            panelBotones,
            BorderLayout.EAST
        );

        // ========================================
        // EDITAR
        // ========================================

        botonEditar.addActionListener(
            e -> abrirFormularioEdicion(
                id,
                titulo,
                tipo,
                ano,
                estado,
                informacion1,
                informacion2
            )
        );

        // ========================================
        // ELIMINAR
        // ========================================

        botonEliminar.addActionListener(
            e -> abrirConfirmacionEliminacion(
                "Recurso",
                titulo + " (ID: " + id + ")"
            )
        );

        // ========================================
        // ABRIR DETALLE
        // ========================================

        añadirEventoDetalle(
            fila,
            crearDatosRecurso(
                id,
                titulo,
                tipo,
                ano,
                estado,
                informacion1,
                informacion2
            )
        );

        panelLista.add(fila);
    }

    // =========================================================
    // FILA DE PRÉSTAMO
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
    	
        JPanel fila = crearFila(75);

        JPanel panelDatos = crearPanelDatos();

        panelDatos.add(crearEtiquetaPrincipal(
            idPrestamo + " · " + tituloRecurso + " · " + nombreUsuario
        ));

        panelDatos.add(crearEtiquetaSecundaria(fechaPrestamo));

        fila.add(panelDatos, BorderLayout.CENTER);

        JPanel panelBotones = crearPanelBotones();

        JButton botonEditar = crearBotonEditar();

        JButton botonDevolver = new JButton("Devolver");
        botonDevolver.setBackground(colorRojo);

        panelBotones.add(botonEditar);
        panelBotones.add(botonDevolver);

        fila.add(
            panelBotones,
            BorderLayout.EAST
        );
        
        botonEditar.addActionListener(
        	    e -> abrirFormularioEdicionPrestamo(
        	        idPrestamo,
        	        nombreUsuario,
        	        idRecurso,
        	        tituloRecurso,
        	        fechaPrestamo,
        	        estado,
        	        fechaDevolucion
        	    )
        	);
        
        botonDevolver.addActionListener(
        	    e -> abrirConfirmacionDevolucion(
        	        idPrestamo,
        	        nombreUsuario,
        	        tituloRecurso
        	    )
        	);

        añadirEventoDetalle(
            fila,
            crearDatosPrestamo(
                idPrestamo,
                nombreUsuario,
                idRecurso,
                tituloRecurso,
                fechaPrestamo,
                estado,
                fechaDevolucion
            )
        );

        panelLista.add(fila);
    }

    // =========================================================
    // BOTONES
    // =========================================================

    private JButton crearBotonEditar() {

        JButton boton = new JButton("Editar");

        boton.setBackground(colorAmarillo);

        return boton;
    }

    private JButton crearBotonEliminar() {

        JButton boton = new JButton("Eliminar");

        boton.setBackground(colorRojo);

        return boton;
    }

    // =========================================================
    // EDICIÓN: UN SOLO FORMULARIO
    // =========================================================

    private void abrirFormularioEdicion(
        String id,
        String nombre,
        String email
    ) {

        FormularioContenido formulario =
            new FormularioContenido(
                MODO_USUARIOS
            );

        formulario.cargarDatos(
            id,
            nombre,
            email
        );

        formulario.setVisible(true);
    }

    private void abrirFormularioEdicion(
        String id,
        String titulo,
        String tipo,
        String ano,
        String estado,
        String informacion1,
        String informacion2
    ) {

        FormularioContenido formulario =
            new FormularioContenido(
                MODO_RECURSOS
            );

        formulario.cargarDatos(
            id,
            titulo,
            tipo,
            ano,
            estado,
            informacion1,
            informacion2
        );

        formulario.setVisible(true);
    }
    
    private void abrirFormularioEdicionPrestamo(
            String idPrestamo,
            String nombreUsuario,
            String idRecurso,
            String tituloRecurso,
            String fechaPrestamo,
            String estado,
            String fechaDevolucion
    ) {
        FormularioContenido formulario =
            new FormularioContenido(MODO_PRESTAMOS);

        formulario.cargarDatosPrestamo(
            nombreUsuario,
            idRecurso,
            tituloRecurso,
            fechaPrestamo,
            estado,
            fechaDevolucion
        );

        formulario.setVisible(true);
    }

    // =========================================================
    // ELIMINACIÓN
    // =========================================================

    private void abrirConfirmacionEliminacion(
        String tipo,
        String descripcion
    ) {

        FormularioEliminar formulario =
            new FormularioEliminar(
                tipo,
                descripcion
            );

        formulario.setVisible(true);
    }
    
    
    // =========================================================
    // DEVOLUCIÓN DE PRESTAMOS
    // =========================================================
    private void abrirConfirmacionDevolucion(
            String idPrestamo,
            String nombreUsuario,
            String tituloRecurso
    ) {
        FormularioEliminar formulario =
            new FormularioEliminar(
                "devolución",
                idPrestamo
                    + " · "
                    + tituloRecurso
                    + " · "
                    + nombreUsuario,
                true
            );

        formulario.setVisible(true);
    }

    // =========================================================
    // DETALLE UNIFICADO
    // =========================================================

    private void añadirEventoDetalle(
        JPanel fila,
        String[] datos
    ) {

        fila.setCursor(
            Cursor.getPredefinedCursor(
                Cursor.HAND_CURSOR
            )
        );

        fila.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    if (e.getClickCount() == 1) {

                        VentanaDetalleContenido ventana =
                            new VentanaDetalleContenido(
                                modoActual,
                                datos
                            );

                        ventana.setVisible(true);
                    }
                }
            }
        );
    }

    // =========================================================
    // DATOS PARA EL DETALLE
    // =========================================================

    private String[] crearDatosUsuario(
        String id,
        String nombre,
        String email
    ) {

        return new String[] {
            id,
            nombre,
            email
        };
    }

    private String[] crearDatosRecurso(
        String id,
        String titulo,
        String tipo,
        String ano,
        String estado,
        String informacion1,
        String informacion2
    ) {

        return new String[] {
            id,
            titulo,
            tipo,
            ano,
            estado,
            informacion1,
            informacion2
        };
    }

    private String[] crearDatosPrestamo(
        String idPrestamo,
        String nombreUsuario,
        String idRecurso,
        String tituloRecurso,
        String fechaPrestamo,
        String estado,
        String fechaDevolucion
    ) {

        return new String[] {
            idPrestamo,
            nombreUsuario,
            idRecurso,
            tituloRecurso,
            fechaPrestamo,
            estado,
            fechaDevolucion
        };
    }

    // =========================================================
    // ACCESO AL MODO ACTUAL
    // =========================================================

    public int getModoActual() {

        return modoActual;
    }
}
