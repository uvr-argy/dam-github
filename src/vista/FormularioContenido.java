package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioContenido extends JDialog {

    private static final long serialVersionUID = 1L;

    // =========================================================
    // CAMPOS COMUNES
    // =========================================================
    private JTextField campoId;
    private JTextField campoTitulo;
    private JTextField campoAno;

    // =========================================================
    // CAMPOS DE USUARIO
    // =========================================================
    private JTextField campoNombre;
    private JTextField campoEmail;

    // =========================================================
    // CAMPOS DE RECURSO
    // =========================================================
    private JComboBox<String> comboTipo;
    private JTextField campoEspecifico1;
    private JTextField campoEspecifico2;
    private JLabel etiquetaEspecifico1;
    private JLabel etiquetaEspecifico2;

    // =========================================================
    // CAMPOS DE PRÉSTAMO
    // =========================================================
    private JComboBox<String> comboUsuario;
    private JComboBox<String> comboRecurso;
    private JTextField campoFechaPrestamo;
    private JComboBox<String> comboEstado;
    private JTextField campoFechaDevolucion;

    // =========================================================
    // COMPONENTES GENERALES
    // =========================================================
    private JPanel panelFormulario;
    private JButton botonGuardar;
    private JButton botonCancelar;
    private int modoActual;

    public FormularioContenido(int modo) {
        modoActual = modo;
        configurarVentana();
        crearFormulario();
        configurarBotones();
        configurarModo(modo);
    }

    // =========================================================
    // CONFIGURACIÓN DE LA VENTANA
    // =========================================================
    private void configurarVentana() {
        setSize(550, 500);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(249, 247, 242));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setContentPane(panelPrincipal);
    }

    // =========================================================
    // FORMULARIO
    // =========================================================
    private void crearFormulario() {
        panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
            )
        );

        // -----------------------------------------------------
        // CAMPOS
        // -----------------------------------------------------
        campoId = new JTextField();
        campoTitulo = new JTextField();
        campoAno = new JTextField();
        campoNombre = new JTextField();
        campoEmail = new JTextField();
        comboTipo = new JComboBox<>(new String[] { "Libro", "Película", "Videojuego" });

        campoEspecifico1 = new JTextField();
        campoEspecifico2 = new JTextField();
        etiquetaEspecifico1 = new JLabel();
        etiquetaEspecifico2 = new JLabel();
        
        comboUsuario = new JComboBox<>(new String[] { "001 · Juan Pérez", "002 · Ana García", "003 · Carlos López" });
        comboRecurso = new JComboBox<>(new String[] { "R001 · El Hobbit", "R003 · Minecraft" });

        campoFechaPrestamo = new JTextField();
        comboEstado = new JComboBox<>(new String[] { "Activo", "Finalizado" });
        campoFechaDevolucion = new JTextField();

        // -----------------------------------------------------
        // CAMBIO DE CAMPOS DEL RECURSO
        // -----------------------------------------------------
        comboTipo.addActionListener(e -> actualizarCamposEspecificos());

        // -----------------------------------------------------
        // PANEL PRINCIPAL
        // -----------------------------------------------------
        JPanel panelPrincipal = (JPanel) getContentPane();
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
    }

    // =========================================================
    // CONFIGURACIÓN DEL BOTÓN
    // =========================================================
    private void configurarBotones() {
        JPanel panelPrincipal = (JPanel) getContentPane();
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setOpaque(false);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setPreferredSize(new Dimension(100, 35));

        botonGuardar = new JButton("Guardar");
        botonGuardar.setBackground(new Color(123, 220, 99));
        botonGuardar.setPreferredSize(new Dimension(100, 35));

        panelBotones.add(botonCancelar);
        panelBotones.add(botonGuardar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        botonCancelar.addActionListener(e -> dispose());
    }

    // =========================================================
    // CONFIGURAR MODO
    // =========================================================
    private void configurarModo(int modo) {
        modoActual = modo;
        limpiarFormulario();

        switch (modo) {
            case PanelContenido.MODO_USUARIOS:
                configurarModoUsuario();
                break;
            case PanelContenido.MODO_RECURSOS:
                configurarModoRecurso();
                break;
            case PanelContenido.MODO_PRESTAMOS:
                configurarModoPrestamo();
                break;
        }
    }

    // =========================================================
    // MODO USUARIO
    // =========================================================
    private void configurarModoUsuario() {
        setTitle("Añadir usuario");
        añadirCampo("ID:", campoId);
        añadirCampo("Nombre:", campoNombre);
        añadirCampo("Email:", campoEmail);
    }

    // =========================================================
    // MODO RECURSO
    // =========================================================
    private void configurarModoRecurso() {
        setTitle("Añadir recurso");
        añadirCampo("ID:", campoId);
        añadirCampo("Título:", campoTitulo);
        añadirCampo("Año:", campoAno);
        añadirCampo("Tipo:", comboTipo);
        añadirCampo(etiquetaEspecifico1, campoEspecifico1);
        añadirCampo(etiquetaEspecifico2, campoEspecifico2);
        actualizarCamposEspecificos();
    }

    // =========================================================
    // MODO PRÉSTAMO
    // =========================================================
    private void configurarModoPrestamo() {
        setTitle("Añadir préstamo");
        añadirCampo("Usuario:", comboUsuario);
        añadirCampo("Recurso:", comboRecurso);
    }

    // =========================================================
    // CAMPOS ESPECÍFICOS DEL RECURSO
    // =========================================================
    private void actualizarCamposEspecificos() {
        if (modoActual != PanelContenido.MODO_RECURSOS) {
            return;
        }

        String tipo = (String) comboTipo.getSelectedItem();
        
        if ("Libro".equals(tipo)) {
            etiquetaEspecifico1.setText("Autor:");
            etiquetaEspecifico2.setText("Páginas:");
        } else if ("Película".equals(tipo)) {
            etiquetaEspecifico1.setText("Director:");
            etiquetaEspecifico2.setText("Duración:");
        } else if ("Videojuego".equals(tipo)) {
            etiquetaEspecifico1.setText("Plataforma:");
            etiquetaEspecifico2.setText("PEGI:");
        }

        panelFormulario.revalidate();
        panelFormulario.repaint();
    }

    // =========================================================
    // AÑADIR CAMPO
    // =========================================================
    private void añadirCampo(String texto, java.awt.Component componente) {
        añadirCampo(new JLabel(texto), componente);
    }

    private void añadirCampo(JLabel etiqueta, java.awt.Component componente) {
        GridBagConstraints restriccionesEtiqueta = new GridBagConstraints();
        restriccionesEtiqueta.gridx = 0;
        restriccionesEtiqueta.gridy = panelFormulario.getComponentCount() / 2;
        restriccionesEtiqueta.anchor = GridBagConstraints.WEST;
        restriccionesEtiqueta.insets = new Insets(5, 5, 5, 10);

        GridBagConstraints restriccionesCampo = new GridBagConstraints();
        restriccionesCampo.gridx = 1;
        restriccionesCampo.gridy = panelFormulario.getComponentCount() / 2;
        restriccionesCampo.weightx = 1.0;
        restriccionesCampo.fill = GridBagConstraints.HORIZONTAL;
        restriccionesCampo.insets = new Insets(5, 5, 5, 5);

        panelFormulario.add(etiqueta, restriccionesEtiqueta);
        panelFormulario.add(componente, restriccionesCampo);
    }

    // =========================================================
    // LIMPIAR
    // =========================================================
    private void limpiarFormulario() {
        panelFormulario.removeAll();

        campoId.setText("");
        campoTitulo.setText("");
        campoAno.setText("");
        campoNombre.setText("");
        campoEmail.setText("");
        campoEspecifico1.setText("");
        campoEspecifico2.setText("");
        comboUsuario.setSelectedIndex(0);
        comboRecurso.setSelectedIndex(0);
        campoFechaPrestamo.setText("");
        campoFechaDevolucion.setText("");
        comboTipo.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        
        campoId.setEnabled(false);
        botonGuardar.setText("Guardar");
    }

    // =========================================================
    // GETTERS GENERALES
    // =========================================================
    public int getModoActual() {
        return modoActual;
    }

    public JTextField getCampoId() {
        return campoId;
    }

    public JTextField getCampoTitulo() {
        return campoTitulo;
    }

    public JTextField getCampoAno() {
        return campoAno;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoEmail() {
        return campoEmail;
    }

    public JComboBox<String> getComboTipo() {
        return comboTipo;
    }

    public JTextField getCampoEspecifico1() {
        return campoEspecifico1;
    }

    public JTextField getCampoEspecifico2() {
        return campoEspecifico2;
    }

    public JComboBox<String> getComboUsuario() {
        return comboUsuario;
    }

    public JComboBox<String> getComboRecurso() {
        return comboRecurso;
    }

    public JTextField getCampoFechaPrestamo() {
        return campoFechaPrestamo;
    }

    public JComboBox<String> getComboEstado() {
        return comboEstado;
    }

    public JTextField getCampoFechaDevolucion() {
        return campoFechaDevolucion;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    // =========================================================
    // CARGAR DATOS DE USUARIO
    // =========================================================
    public void cargarDatos(String id, String nombre, String email) {
        configurarModo(PanelContenido.MODO_USUARIOS);
        setTitle("Editar usuario");
        campoId.setText(id);
        campoNombre.setText(nombre);
        campoEmail.setText(email);
        campoId.setEnabled(false);
        botonGuardar.setText("Guardar cambios");
    }

    // =========================================================
    // CARGAR DATOS DE RECURSO
    // =========================================================
    public void cargarDatos(String id, String titulo, String tipo, String ano, String estado, String especifico1, String especifico2) {
        configurarModo(PanelContenido.MODO_RECURSOS);
        setTitle("Editar recurso");
        
        campoId.setText(id);
        campoTitulo.setText(titulo);
        campoAno.setText(ano);
        comboTipo.setSelectedItem(tipo);
        
        actualizarCamposEspecificos();
        
        campoEspecifico1.setText(especifico1);
        campoEspecifico2.setText(especifico2);
        
        /*
         * El estado todavía se conserva como dato visual
         * en PanelContenido. Cuando conectemos el modelo,
         * se añadirá aquí el campo de disponibilidad.
         */
        
        campoId.setEnabled(false);
        botonGuardar.setText("Guardar cambios");
    }

    // =========================================================
    // CARGAR DATOS DE PRÉSTAMO
    // =========================================================
    public void cargarDatosPrestamo(String nombreUsuario, String idRecurso, String tituloRecurso, String fechaPrestamo, String estado, String fechaDevolucion) {
        limpiarFormulario();
        panelFormulario.removeAll();
        modoActual = PanelContenido.MODO_PRESTAMOS;
        
        setTitle("Editar préstamo");
        
        añadirCampo("Usuario:", comboUsuario);
        añadirCampo("Recurso:", comboRecurso);
        añadirCampo("Fecha préstamo:", campoFechaPrestamo);
        añadirCampo("Estado:", comboEstado);
        añadirCampo("Fecha devolución:", campoFechaDevolucion);
        
        seleccionarComboPorTexto(comboUsuario, nombreUsuario);
        seleccionarComboPorTexto(comboRecurso, idRecurso + " · " + tituloRecurso);
        
        campoFechaPrestamo.setText(fechaPrestamo);
        comboEstado.setSelectedItem(estado);
        campoFechaDevolucion.setText(fechaDevolucion);
        
        botonGuardar.setText("Guardar cambios");
        
        panelFormulario.revalidate();
        panelFormulario.repaint();
    }

    private void seleccionarComboPorTexto(JComboBox<String> combo, String texto) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).contains(texto)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
}