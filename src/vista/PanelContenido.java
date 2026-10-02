package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controlador.GestionPrestamos;
import controlador.GestionRecursos;
import controlador.GestionUsuarios;
import modelo.Libro;
import modelo.Pelicula;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import modelo.Videojuego;

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
	// ESTRUCTURA BASE
	// =========================================================
	private JPanel panelSubnavbar;
	private JPanel panelLista;

	private JLabel etiquetaTitulo;
	private JTextField campoBuscar;
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboDisponibilidad;
	private JButton botonAnadir;

	// Se activa mientras se restablecen los filtros por código, para que los
	// combos no lancen una búsqueda cada vez que cambian
	private boolean ajustandoFiltros = false;

	// =========================================================
	// CONTROLADORES
	// =========================================================
	private GestionUsuarios gestionUsuarios;
	private GestionRecursos gestionRecursos;
	private GestionPrestamos gestionPrestamos;

	public PanelContenido(GestionUsuarios gestionUsuarios, GestionRecursos gestionRecursos,
			GestionPrestamos gestionPrestamos) {

		this.gestionUsuarios = gestionUsuarios;
		this.gestionRecursos = gestionRecursos;
		this.gestionPrestamos = gestionPrestamos;

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		crearEstructura();
		mostrarUsuarios();
	}

	// =========================================================
	// ESTRUCTURA GENERAL
	// =========================================================
	private void crearEstructura() {

		add(crearSubnavbar(), BorderLayout.NORTH);
		add(crearZonaCentral(), BorderLayout.CENTER);
	}

	private JPanel crearSubnavbar() {

		panelSubnavbar = new JPanel(new BorderLayout());
		panelSubnavbar.setPreferredSize(new Dimension(0, 60));
		panelSubnavbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		etiquetaTitulo = new JLabel();
		etiquetaTitulo.setFont(UtilVista.FUENTE_TITULO);
		panelSubnavbar.add(etiquetaTitulo, BorderLayout.WEST);

		JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		panelBusqueda.setOpaque(false);

		// =========================
		// BUSCADOR
		// =========================
		campoBuscar = new JTextField();
		campoBuscar.setPreferredSize(new Dimension(180, 35));
		campoBuscar.addActionListener(e -> aplicarFiltros());

		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.setForeground(UtilVista.COLOR_FONDO);
		botonBuscar.setBackground(UtilVista.COLOR_AZUL);
		botonBuscar.setPreferredSize(new Dimension(90, 35));
		botonBuscar.addActionListener(e -> aplicarFiltros());

		// =========================
		// FILTROS (solo en recursos)
		// =========================
		comboTipo = new JComboBox<>(new String[] { UtilVista.TODOS, UtilVista.TIPO_LIBRO, UtilVista.TIPO_PELICULA,
				UtilVista.TIPO_VIDEOJUEGO });
		comboTipo.setPreferredSize(new Dimension(120, 35));
		comboTipo.addActionListener(e -> aplicarFiltros());

		comboDisponibilidad = new JComboBox<>(
				new String[] { UtilVista.TODOS, UtilVista.DISPONIBLE, UtilVista.PRESTADO });
		comboDisponibilidad.setPreferredSize(new Dimension(120, 35));
		comboDisponibilidad.addActionListener(e -> aplicarFiltros());

		// =========================
		// LIMPIAR
		// =========================
		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.setPreferredSize(new Dimension(90, 35));
		botonLimpiar.addActionListener(e -> {
			restablecerFiltros();
			aplicarFiltros();
		});

		panelBusqueda.add(campoBuscar);
		panelBusqueda.add(comboTipo);
		panelBusqueda.add(comboDisponibilidad);
		panelBusqueda.add(botonBuscar);
		panelBusqueda.add(botonLimpiar);

		panelSubnavbar.add(panelBusqueda, BorderLayout.EAST);

		return panelSubnavbar;
	}

	private JPanel crearZonaCentral() {

		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBackground(UtilVista.COLOR_FONDO);
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// -------------------------
		// BOTÓN AÑADIR
		// -------------------------
		JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelAcciones.setBackground(Color.WHITE);

		botonAnadir = new JButton();
		botonAnadir.setBackground(UtilVista.COLOR_VERDE);
		botonAnadir.setPreferredSize(new Dimension(160, 40));
		
		// Un único listener: lo que cambia según el modo se decide al pulsarlo
		botonAnadir.addActionListener(e -> abrirFormularioAlta());
		panelAcciones.add(botonAnadir);

		panelCentral.add(panelAcciones, BorderLayout.NORTH);

		// -------------------------
		// LISTA
		// -------------------------
		panelLista = new JPanel();
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		panelLista.setBackground(Color.WHITE);

		JScrollPane scroll = new JScrollPane(panelLista);
		scroll.setBorder(null);
		// Por defecto la rueda del ratón avanza 1 píxel por paso
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		panelCentral.add(scroll, BorderLayout.CENTER);

		return panelCentral;
	}

	// =========================================================
	// CAMBIO DE MODO
	// =========================================================
	public void mostrarUsuarios() {

		cambiarModo(MODO_USUARIOS);
	}

	public void mostrarRecursos() {

		cambiarModo(MODO_RECURSOS);
	}

	public void mostrarPrestamos() {

		cambiarModo(MODO_PRESTAMOS);
	}

	private void cambiarModo(int modo) {

		modoActual = modo;

		// Al cambiar de sección se parte de cero (antes el texto buscado se
		// quedaba escrito aunque la lista ya no estuviera filtrada)
		restablecerFiltros();
		configurarCabecera();
		aplicarFiltros();
	}

	// =========================================================
	// CABECERA ADAPTABLE (título, color, buscador y botón Añadir)
	// =========================================================
	private void configurarCabecera() {

		boolean esRecursos = modoActual == MODO_RECURSOS;
		comboTipo.setVisible(esRecursos);
		comboDisponibilidad.setVisible(esRecursos);

		switch (modoActual) {

		case MODO_USUARIOS:
			panelSubnavbar.setBackground(new Color(227, 235, 222));
			etiquetaTitulo.setText("Usuarios");
			campoBuscar.setToolTipText("Buscar usuario por ID, nombre o email");
			botonAnadir.setText("Añadir usuario");
			break;

		case MODO_RECURSOS:
			panelSubnavbar.setBackground(new Color(220, 230, 244));
			etiquetaTitulo.setText("Recursos");
			campoBuscar.setToolTipText("Buscar recurso por ID, título o tipo");
			botonAnadir.setText("Añadir recurso");
			break;

		case MODO_PRESTAMOS:
			panelSubnavbar.setBackground(new Color(239, 232, 209));
			etiquetaTitulo.setText("Préstamos");
			campoBuscar.setToolTipText("Buscar préstamo por usuario o recurso");
			botonAnadir.setText("Añadir préstamo");
			break;
		}
	}

	// =========================================================
	// LISTA: BÚSQUEDA, FILTROS Y PINTADO
	// =========================================================
	private void restablecerFiltros() {

		ajustandoFiltros = true;

		campoBuscar.setText("");
		comboTipo.setSelectedIndex(0);
		comboDisponibilidad.setSelectedIndex(0);

		ajustandoFiltros = false;
	}

	/**
	 * Vuelve a pintar la lista según el texto y los filtros actuales. Se usa para
	 * buscar, para limpiar y también para refrescar tras añadir, editar o eliminar.
	 * Sustituye a los 3 métodos "cargar" y los 3 "mostrar(lista)" que había antes.
	 */
	private void aplicarFiltros() {

		if (ajustandoFiltros) {
			return;
		}

		String texto = campoBuscar.getText().trim();

		panelLista.removeAll();

		switch (modoActual) {

		case MODO_USUARIOS:
			for (Usuario usuario : gestionUsuarios.filtrarUsuarios(texto)) {
				añadirUsuario(usuario);
			}
			break;

		case MODO_RECURSOS:
			String tipo = nombreDeClase((String) comboTipo.getSelectedItem());
			String disponibilidad = (String) comboDisponibilidad.getSelectedItem();

			for (Recurso recurso : gestionRecursos.filtrarRecursos(texto, tipo, disponibilidad)) {
				añadirRecurso(recurso);
			}
			break;

		case MODO_PRESTAMOS:
			for (Prestamo prestamo : gestionPrestamos.filtrarPrestamos(texto)) {
				añadirPrestamo(prestamo);
			}
			break;
		}

		panelLista.revalidate();
		panelLista.repaint();
	}

	// GestionRecursos.filtrarRecursos() compara con el nombre de la clase
	// ("Pelicula", sin tilde), pero en pantalla se muestra "Película"
	private String nombreDeClase(String tipoVisible) {

		if (UtilVista.TIPO_LIBRO.equals(tipoVisible)) {
			return Libro.class.getSimpleName();
		}
		if (UtilVista.TIPO_PELICULA.equals(tipoVisible)) {
			return Pelicula.class.getSimpleName();
		}
		if (UtilVista.TIPO_VIDEOJUEGO.equals(tipoVisible)) {
			return Videojuego.class.getSimpleName();
		}
		return tipoVisible;
	}

	// =========================================================
	// FILAS DE LA LISTA
	// =========================================================
	// Estructura común: datos a la izquierda, botones a la derecha, y un clic en
	// cualquier parte de la fila abre el detalle
	private void añadirFila(JPanel datos, JPanel botones, Runnable abrirDetalle) {

		JPanel fila = UtilVista.crearFila();
		fila.add(datos, BorderLayout.CENTER);
		fila.add(botones, BorderLayout.EAST);

		UtilVista.hacerClicable(fila, abrirDetalle);

		panelLista.add(fila);
	}

	private void añadirUsuario(Usuario usuario) {

		JPanel datos = UtilVista.crearPanelDatos();
		datos.add(UtilVista.crearEtiquetaPrincipal(usuario.getId() + " · " + usuario.getNombre()));
		datos.add(UtilVista.crearEtiquetaSecundaria("Email: " + usuario.getEmail()));

		JButton botonEditar = UtilVista.crearBotonEditar();
		botonEditar.addActionListener(e -> abrirEdicionUsuario(usuario));

		JButton botonEliminar = UtilVista.crearBotonEliminar();
		botonEliminar.addActionListener(e -> confirmarEliminacionUsuario(usuario));

		JPanel botones = UtilVista.crearPanelBotones();
		botones.add(botonEditar);
		botones.add(botonEliminar);

		añadirFila(datos, botones, () -> abrirDetalle(usuario));
	}

	private void añadirRecurso(Recurso recurso) {

		JPanel datos = UtilVista.crearPanelDatos();
		datos.add(UtilVista.crearEtiquetaPrincipal(recurso.getId() + " · " + recurso.getTitulo()));

		JButton botonEditar = UtilVista.crearBotonEditar();
		botonEditar.addActionListener(e -> abrirEdicionRecurso(recurso));

		JButton botonEliminar = UtilVista.crearBotonEliminar();
		botonEliminar.addActionListener(e -> confirmarEliminacionRecurso(recurso));

		JPanel botones = UtilVista.crearPanelBotones();
		botones.add(botonEditar);
		botones.add(botonEliminar);

		añadirFila(datos, botones, () -> abrirDetalle(recurso));
	}

	private void añadirPrestamo(Prestamo prestamo) {

		JPanel datos = UtilVista.crearPanelDatos();
		datos.add(UtilVista
				.crearEtiquetaPrincipal(prestamo.getRecurso().getTitulo() + " · " + prestamo.getUsuario().getNombre()));
		datos.add(UtilVista.crearEtiquetaSecundaria("Préstamo: " + prestamo.getFechaPrestamo()));

		if (!prestamo.isEstadoPrestamo() && prestamo.getFechaDevolucion() != null) {
			datos.add(UtilVista.crearEtiquetaSecundaria("Devolución: " + prestamo.getFechaDevolucion()));
		}

		JButton botonEditar = UtilVista.crearBotonEditar();
		botonEditar.addActionListener(e -> abrirEdicionPrestamo(prestamo));

		JPanel botones = UtilVista.crearPanelBotones();
		botones.add(botonEditar);

		// Solo los préstamos activos se pueden devolver
		if (prestamo.isEstadoPrestamo()) {
			JButton botonDevolver = UtilVista.crearBotonDevolver();
			botonDevolver.addActionListener(e -> confirmarDevolucion(prestamo));
			botones.add(botonDevolver);
		}

		añadirFila(datos, botones, () -> abrirDetalle(prestamo));
	}

	// =========================================================
	// VENTANAS DE DETALLE
	// =========================================================
	private void abrirDetalle(Usuario usuario) {

		VentanaDetalleContenido ventana = new VentanaDetalleContenido(propietario(), usuario, gestionPrestamos);

		// Desde el detalle de un usuario se pueden devolver sus préstamos
		ventana.setAccionDevolverPrestamo(this::confirmarDevolucion);
		ventana.setVisible(true);
	}

	private void abrirDetalle(Recurso recurso) {

		new VentanaDetalleContenido(propietario(), recurso, gestionPrestamos).setVisible(true);
	}

	private void abrirDetalle(Prestamo prestamo) {

		new VentanaDetalleContenido(propietario(), prestamo, gestionPrestamos).setVisible(true);
	}

	// Ventana (JFrame) que contiene este panel: se pasa como propietaria a los
	// diálogos para que se centren sobre ella y no queden escondidos detrás
	private Window propietario() {

		return SwingUtilities.getWindowAncestor(this);
	}

	// =========================================================
	// FORMULARIO DE ALTA (BOTÓN AÑADIR)
	// =========================================================
	private void abrirFormularioAlta() {

		switch (modoActual) {

		case MODO_USUARIOS:
			abrirAltaUsuario();
			break;

		case MODO_RECURSOS:
			abrirAltaRecurso();
			break;

		case MODO_PRESTAMOS:
			abrirAltaPrestamo();
			break;
		}
	}

	private void abrirAltaUsuario() {

		FormularioContenido formulario = new FormularioContenido(propietario(), MODO_USUARIOS);

		formulario.getBotonGuardar().addActionListener(e -> {

			String nombre = formulario.getCampoNombre().getText().trim();
			String email = formulario.getCampoEmail().getText().trim();

			if (!datosUsuarioValidos(formulario, nombre, email)) {
				return;
			}

			// El ID real lo asigna GestionUsuarios
			if (gestionUsuarios.crearUsuario(new Usuario("", nombre, email))) {
				formulario.dispose();
				aplicarFiltros();
			}
		});

		formulario.setVisible(true);
	}

	private void abrirAltaRecurso() {

		FormularioContenido formulario = new FormularioContenido(propietario(), MODO_RECURSOS);

		formulario.getBotonGuardar().addActionListener(e -> {

			DatosRecurso datos = leerDatosRecurso(formulario);

			if (datos == null) {
				return;
			}

			String tipo = (String) formulario.getComboTipo().getSelectedItem();
			Recurso recurso = construirRecurso(tipo, datos);

			if (recurso != null && gestionRecursos.crearRecurso(recurso)) {
				formulario.dispose();
				aplicarFiltros();
			} else {
				UtilVista.mostrarAviso(formulario, "No se ha podido crear el recurso.");
			}
		});

		formulario.setVisible(true);
	}

	private void abrirAltaPrestamo() {

		FormularioContenido formulario = new FormularioContenido(propietario(), MODO_PRESTAMOS);

		// null = préstamo nuevo
		formulario.cargarOpcionesPrestamo(gestionPrestamos.listarUsuarios(), gestionPrestamos.listarRecursos(), null);

		formulario.getBotonGuardar().addActionListener(e -> {

			Usuario usuario = formulario.getUsuarioSeleccionado();
			Recurso recurso = formulario.getRecursoSeleccionado();

			if (usuario == null || recurso == null) {
				UtilVista.mostrarAviso(formulario, "Hace falta al menos un usuario y un recurso disponible.");
				return;
			}

			if (gestionPrestamos.prestarRecurso(usuario.getId(), recurso.getId())) {
				formulario.dispose();
				aplicarFiltros();
			} else {
				UtilVista.mostrarAviso(formulario, "No se ha podido realizar el préstamo.");
			}
		});

		formulario.setVisible(true);
	}

	// =========================================================
	// FORMULARIO DE EDICIÓN
	// =========================================================
	private void abrirEdicionUsuario(Usuario usuario) {

		FormularioContenido formulario = new FormularioContenido(propietario(), MODO_USUARIOS);
		formulario.cargarDatos(usuario);

		formulario.getBotonGuardar().addActionListener(e -> {

			String nombre = formulario.getCampoNombre().getText().trim();
			String email = formulario.getCampoEmail().getText().trim();

			if (!datosUsuarioValidos(formulario, nombre, email)) {
				return;
			}

			if (gestionUsuarios.modificarUsuario(usuario.getId(), nombre, email)) {
				formulario.dispose();
				aplicarFiltros();
			}
		});

		formulario.setVisible(true);
	}

	private void abrirEdicionRecurso(Recurso recurso) {

		FormularioContenido formulario = new FormularioContenido(propietario(), MODO_RECURSOS);
		formulario.cargarDatos(recurso);

		formulario.getBotonGuardar().addActionListener(e -> {

			// Se valida todo antes de llamar a GestionRecursos: si fallara a mitad,
			// el título y el año ya estarían cambiados
			DatosRecurso datos = leerDatosRecurso(formulario);

			if (datos == null) {
				return;
			}

			boolean guardado = gestionRecursos.modificarRecurso(recurso.getId(), datos.titulo, datos.ano,
					UtilVista.tipoDe(recurso), datos.dato1, String.valueOf(datos.dato2));

			if (guardado) {
				formulario.dispose();
				aplicarFiltros();
			} else {
				UtilVista.mostrarAviso(formulario, "No se han podido guardar los cambios.");
			}
		});

		formulario.setVisible(true);
	}

	private void abrirEdicionPrestamo(Prestamo prestamo) {

		FormularioContenido formulario = new FormularioContenido(propietario(), MODO_PRESTAMOS);
		formulario.cargarOpcionesPrestamo(gestionPrestamos.listarUsuarios(), gestionPrestamos.listarRecursos(),
				prestamo);

		formulario.getBotonGuardar().addActionListener(e -> {

			Usuario usuario = formulario.getUsuarioSeleccionado();
			Recurso recurso = formulario.getRecursoSeleccionado();

			if (usuario == null || recurso == null) {
				return;
			}

			if (gestionPrestamos.modificarPrestamo(prestamo, usuario.getId(), recurso.getId())) {
				formulario.dispose();
				aplicarFiltros();
			} else {
				UtilVista.mostrarAviso(formulario, "No se han podido guardar los cambios.");
			}
		});

		formulario.setVisible(true);
	}

	// =========================================================
	// VALIDACIÓN DE LO ESCRITO EN LOS FORMULARIOS
	// =========================================================
	private boolean datosUsuarioValidos(FormularioContenido formulario, String nombre, String email) {

		if (nombre.isEmpty()) {
			UtilVista.mostrarAviso(formulario, "El nombre no puede estar vacío.");
			return false;
		}

		if (!email.contains("@")) {
			UtilVista.mostrarAviso(formulario, "Escribe un email válido (por ejemplo, nombre@correo.com).");
			return false;
		}

		return true;
	}

	// Valores de un formulario de recurso ya comprobados
	private static class DatosRecurso {

		private final String titulo;
		private final int ano;
		private final String dato1;
		private final int dato2;

		private DatosRecurso(String titulo, int ano, String dato1, int dato2) {
			this.titulo = titulo;
			this.ano = ano;
			this.dato1 = dato1;
			this.dato2 = dato2;
		}
	}

	/** Devuelve los datos validados, o null (tras avisar) si hay algo mal. */
	private DatosRecurso leerDatosRecurso(FormularioContenido formulario) {

		String tipo = (String) formulario.getComboTipo().getSelectedItem();
		String nombreDato1 = UtilVista.etiquetaDato1(tipo);
		String nombreDato2 = UtilVista.etiquetaDato2(tipo);

		String titulo = formulario.getCampoTitulo().getText().trim();
		String dato1 = formulario.getCampoEspecifico1().getText().trim();

		if (titulo.isEmpty() || dato1.isEmpty()) {
			UtilVista.mostrarAviso(formulario, "El título y el campo \"" + nombreDato1 + "\" son obligatorios.");
			return null;
		}

		try {
			// El año puede ser negativo (La Odisea es del año -700)
			int ano = Integer.parseInt(formulario.getCampoAno().getText().trim());
			int dato2 = Integer.parseInt(formulario.getCampoEspecifico2().getText().trim());

			if (dato2 <= 0) {
				UtilVista.mostrarAviso(formulario, "El campo \"" + nombreDato2 + "\" debe ser mayor que 0.");
				return null;
			}

			return new DatosRecurso(titulo, ano, dato1, dato2);

		} catch (NumberFormatException ex) {
			UtilVista.mostrarAviso(formulario,
					"El año y el campo \"" + nombreDato2 + "\" deben ser números enteros.");
			return null;
		}
	}

	private Recurso construirRecurso(String tipo, DatosRecurso datos) {

		// El ID real lo asigna GestionRecursos al guardarlo
		if (UtilVista.TIPO_LIBRO.equals(tipo)) {
			return new Libro("", datos.titulo, datos.ano, true, datos.dato1, datos.dato2);
		}
		if (UtilVista.TIPO_PELICULA.equals(tipo)) {
			return new Pelicula("", datos.titulo, datos.ano, true, datos.dato1, datos.dato2);
		}
		if (UtilVista.TIPO_VIDEOJUEGO.equals(tipo)) {
			return new Videojuego("", datos.titulo, datos.ano, true, datos.dato1, datos.dato2);
		}
		return null;
	}

	// =========================================================
	// CONFIRMACIONES: ELIMINAR Y DEVOLVER
	// =========================================================
	private void confirmarEliminacionUsuario(Usuario usuario) {

		FormularioEliminar dialogo = FormularioEliminar.paraEliminar(propietario(), "usuario",
				usuario.getNombre() + " (ID: " + usuario.getId() + ")");
		dialogo.setVisible(true);

		if (!dialogo.isConfirmado()) {
			return;
		}

		if (gestionUsuarios.eliminarUsuario(usuario.getId())) {
			aplicarFiltros();
		} else {
			UtilVista.mostrarAviso(this, "No se puede eliminar el usuario porque tiene un préstamo activo.");
		}
	}

	private void confirmarEliminacionRecurso(Recurso recurso) {

		FormularioEliminar dialogo = FormularioEliminar.paraEliminar(propietario(), "recurso",
				recurso.getTitulo() + " (ID: " + recurso.getId() + ")");
		dialogo.setVisible(true);

		if (!dialogo.isConfirmado()) {
			return;
		}

		if (gestionRecursos.eliminarRecurso(recurso.getId())) {
			aplicarFiltros();
		} else {
			UtilVista.mostrarAviso(this, "No se puede eliminar el recurso porque tiene un préstamo activo.");
		}
	}

	private void confirmarDevolucion(Prestamo prestamo) {

		FormularioEliminar dialogo = FormularioEliminar.paraDevolucion(propietario(),
				prestamo.getRecurso().getTitulo() + " · " + prestamo.getUsuario().getNombre());
		dialogo.setVisible(true);

		if (!dialogo.isConfirmado()) {
			return;
		}

		if (gestionPrestamos.devolverRecurso(prestamo.getRecurso().getId())) {
			aplicarFiltros();
		} else {
			UtilVista.mostrarAviso(this, "No se ha podido devolver el recurso.");
		}
	}
}