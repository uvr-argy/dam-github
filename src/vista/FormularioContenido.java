package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;

/**
 * Formulario para añadir y editar usuarios, recursos y préstamos.
 * 
 * El modo (usuarios, recursos o préstamos) se decide al crearlo y ya no cambia,
 * así que los campos se crean una sola vez según el modo. Para editar se crea
 * igual. **/
public class FormularioContenido extends JDialog {

	private static final long serialVersionUID = 1L;

	private final int modo;

	// Siguiente fila libre del GridBagLayout
	private int filaActual = 0;

	// =========================================================
	// COMPONENTES GENERALES
	// =========================================================
	private JPanel panelFormulario;
	private JButton botonGuardar;

	// El ID lo asigna el controlador: solo se muestra al editar
	private JLabel etiquetaId;
	private JTextField campoId;

	// =========================================================
	// CAMPOS DE USUARIO
	// =========================================================
	private JTextField campoNombre;
	private JTextField campoEmail;

	// =========================================================
	// CAMPOS DE RECURSO
	// =========================================================
	private JTextField campoTitulo;
	private JTextField campoAno;
	private JComboBox<String> comboTipo;
	private JTextField campoEspecifico1;
	private JTextField campoEspecifico2;
	private JLabel etiquetaEspecifico1;
	private JLabel etiquetaEspecifico2;

	// =========================================================
	// CAMPOS DE PRÉSTAMO
	// =========================================================
	// Guardan los objetos (no textos): así no hay que "trocear" cadenas después
	private JComboBox<Usuario> comboUsuario;
	private JComboBox<Recurso> comboRecurso;

	public FormularioContenido(Window propietario, int modo) {

		// Con propietario, el formulario se centra sobre la aplicación y no se
		// puede quedar escondido detrás de ella
		super(propietario, Dialog.ModalityType.APPLICATION_MODAL);

		this.modo = modo;

		configurarVentana();
		crearPanelFormulario();
		crearBotones();
		crearCamposSegunModo();
	}

	// =========================================================
	// CONFIGURACIÓN DE LA VENTANA
	// =========================================================
	private void configurarVentana() {

		setSize(550, 500);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(UtilVista.COLOR_FONDO);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		setContentPane(panelPrincipal);
	}

	private void crearPanelFormulario() {

		panelFormulario = new JPanel(new GridBagLayout());
		panelFormulario.setBackground(Color.WHITE);
		panelFormulario.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(UtilVista.COLOR_BORDE), BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		getContentPane().add(panelFormulario, BorderLayout.CENTER);
	}

	private void crearBotones() {

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		panelBotones.setOpaque(false);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setPreferredSize(new Dimension(100, 35));
		botonCancelar.addActionListener(e -> dispose());

		botonGuardar = UtilVista.crearBoton("Guardar", UtilVista.COLOR_VERDE);
		botonGuardar.setPreferredSize(new Dimension(100, 35));

		panelBotones.add(botonCancelar);
		panelBotones.add(botonGuardar);

		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Pulsar Enter equivale a pulsar Guardar
		getRootPane().setDefaultButton(botonGuardar);
	}

	// =========================================================
	// CAMPOS SEGÚN EL MODO
	// =========================================================
	private void crearCamposSegunModo() {

		switch (modo) {
		case PanelContenido.MODO_USUARIOS:
			crearCamposUsuario();
			break;
		case PanelContenido.MODO_RECURSOS:
			crearCamposRecurso();
			break;
		case PanelContenido.MODO_PRESTAMOS:
			crearCamposPrestamo();
			break;
		}
	}

	private void crearCamposUsuario() {

		setTitle("Añadir usuario");

		campoNombre = new JTextField();
		campoEmail = new JTextField();

		añadirCampoId();
		añadirCampo("Nombre:", campoNombre);
		añadirCampo("Email:", campoEmail);
	}

	private void crearCamposRecurso() {

		setTitle("Añadir recurso");

		campoTitulo = new JTextField();
		campoAno = new JTextField();
		comboTipo = new JComboBox<>(
				new String[] { UtilVista.TIPO_LIBRO, UtilVista.TIPO_PELICULA, UtilVista.TIPO_VIDEOJUEGO });
		campoEspecifico1 = new JTextField();
		campoEspecifico2 = new JTextField();
		etiquetaEspecifico1 = new JLabel();
		etiquetaEspecifico2 = new JLabel();

		// Al cambiar el tipo cambian los nombres de los dos últimos campos
		comboTipo.addActionListener(e -> actualizarCamposEspecificos());

		añadirCampoId();
		añadirCampo("Título:", campoTitulo);
		añadirCampo("Año:", campoAno);
		añadirCampo("Tipo:", comboTipo);
		añadirCampo(etiquetaEspecifico1, campoEspecifico1);
		añadirCampo(etiquetaEspecifico2, campoEspecifico2);

		actualizarCamposEspecificos();
	}

	private void crearCamposPrestamo() {

		setTitle("Añadir préstamo");

		comboUsuario = new JComboBox<>();
		comboRecurso = new JComboBox<>();

		// Los combos guardan objetos; el renderizador decide qué texto se ve
		RenderizadorOpciones renderizador = new RenderizadorOpciones();
		comboUsuario.setRenderer(renderizador);
		comboRecurso.setRenderer(renderizador);

		añadirCampo("Usuario:", comboUsuario);
		añadirCampo("Recurso:", comboRecurso);
	}

	// =========================================================
	// CAMPOS ESPECÍFICOS DEL RECURSO
	// =========================================================
	private void actualizarCamposEspecificos() {

		String tipo = (String) comboTipo.getSelectedItem();

		etiquetaEspecifico1.setText(UtilVista.etiquetaDato1(tipo) + ":");
		etiquetaEspecifico2.setText(UtilVista.etiquetaDato2(tipo) + ":");
	}

	// =========================================================
	// AÑADIR FILAS AL FORMULARIO
	// =========================================================
	private void añadirCampoId() {

		etiquetaId = new JLabel("ID:");
		campoId = new JTextField();
		campoId.setEnabled(false);

		añadirCampo(etiquetaId, campoId);

		// Al crear, el ID todavía no existe: se oculta hasta que se edite
		etiquetaId.setVisible(false);
		campoId.setVisible(false);
	}

	private void mostrarId(String id) {

		campoId.setText(id);
		etiquetaId.setVisible(true);
		campoId.setVisible(true);
	}

	private void añadirCampo(String texto, Component componente) {

		añadirCampo(new JLabel(texto), componente);
	}

	private void añadirCampo(JLabel etiqueta, Component componente) {

		GridBagConstraints restriccionesEtiqueta = new GridBagConstraints();
		restriccionesEtiqueta.gridx = 0;
		restriccionesEtiqueta.gridy = filaActual;
		restriccionesEtiqueta.anchor = GridBagConstraints.WEST;
		restriccionesEtiqueta.insets = new Insets(5, 5, 5, 10);

		GridBagConstraints restriccionesCampo = new GridBagConstraints();
		restriccionesCampo.gridx = 1;
		restriccionesCampo.gridy = filaActual;
		restriccionesCampo.weightx = 1.0;
		restriccionesCampo.fill = GridBagConstraints.HORIZONTAL;
		restriccionesCampo.insets = new Insets(5, 5, 5, 5);

		panelFormulario.add(etiqueta, restriccionesEtiqueta);
		panelFormulario.add(componente, restriccionesCampo);

		filaActual++;
	}

	// =========================================================
	// GETTERS (los usa PanelContenido para leer lo escrito)
	// =========================================================
	public JButton getBotonGuardar() {
		return botonGuardar;
	}

	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JTextField getCampoTitulo() {
		return campoTitulo;
	}

	public JTextField getCampoAno() {
		return campoAno;
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

	public Usuario getUsuarioSeleccionado() {
		return (Usuario) comboUsuario.getSelectedItem();
	}

	public Recurso getRecursoSeleccionado() {
		return (Recurso) comboRecurso.getSelectedItem();
	}

	// =========================================================
	// CARGAR DATOS PARA EDITAR
	// =========================================================
	public void cargarDatos(Usuario usuario) {

		setTitle("Editar usuario");
		mostrarId(usuario.getId());

		campoNombre.setText(usuario.getNombre());
		campoEmail.setText(usuario.getEmail());

		botonGuardar.setText("Guardar cambios");
	}

	public void cargarDatos(Recurso recurso) {

		setTitle("Editar recurso");
		mostrarId(recurso.getId());

		campoTitulo.setText(recurso.getTitulo());
		campoAno.setText(String.valueOf(recurso.getAno()));

		// Se selecciona el tipo REAL del recurso. El tipo no se puede cambiar al
		// editar (un Libro no puede convertirse en Película), por eso se bloquea.
		comboTipo.setSelectedItem(UtilVista.tipoDe(recurso));
		comboTipo.setEnabled(false);
		actualizarCamposEspecificos();

		campoEspecifico1.setText(UtilVista.dato1(recurso));
		campoEspecifico2.setText(UtilVista.dato2(recurso));

		botonGuardar.setText("Guardar cambios");
	}

	/**
	 * Rellena los desplegables de usuario y recurso.
	 * 
	 * @param prestamoActual null si se está creando un préstamo nuevo; si se está
	 *                       editando, el préstamo que se edita.
	 */
	public void cargarOpcionesPrestamo(List<Usuario> usuarios, List<Recurso> recursos, Prestamo prestamoActual) {

		comboUsuario.removeAllItems();
		comboRecurso.removeAllItems();

		for (Usuario usuario : usuarios) {
			comboUsuario.addItem(usuario);
		}

		// Solo se pueden prestar los recursos disponibles
		for (Recurso recurso : recursos) {
			if (recurso.isEstado()) {
				comboRecurso.addItem(recurso);
			}
		}

		if (prestamoActual == null) {
			return;
		}

		setTitle("Editar préstamo");
		botonGuardar.setText("Guardar cambios");

		// El usuario o el recurso del préstamo pueden no estar en las listas (el
		// recurso está prestado, el usuario se eliminó). Se añaden para que al
		// guardar no se cambien por otro sin querer.
		asegurarOpcion(comboUsuario, prestamoActual.getUsuario());
		asegurarOpcion(comboRecurso, prestamoActual.getRecurso());

		comboUsuario.setSelectedItem(prestamoActual.getUsuario());
		comboRecurso.setSelectedItem(prestamoActual.getRecurso());

		// En un préstamo ya devuelto no se cambia el recurso: ese recurso puede
		// estar ahora prestado a otra persona
		comboRecurso.setEnabled(prestamoActual.isEstadoPrestamo());
	}

	private <T> void asegurarOpcion(JComboBox<T> combo, T opcion) {

		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i) == opcion) {
				return;
			}
		}

		combo.addItem(opcion);
	}

	// =========================================================
	// TEXTO DE LAS OPCIONES DE LOS DESPLEGABLES
	// =========================================================
	private static class RenderizadorOpciones extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice, boolean seleccionado,
				boolean conFoco) {

			super.getListCellRendererComponent(lista, valor, indice, seleccionado, conFoco);

			if (valor instanceof Usuario) {
				Usuario usuario = (Usuario) valor;
				setText(usuario.getId() + " · " + usuario.getNombre());

			} else if (valor instanceof Recurso) {
				Recurso recurso = (Recurso) valor;
				setText(recurso.getId() + " · " + recurso.getTitulo());
			}

			return this;
		}
	}
}