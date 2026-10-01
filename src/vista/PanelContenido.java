package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import funcionalidades.GestionPrestamos;
import funcionalidades.GestionRecursos;
import funcionalidades.GestionUsuarios;
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
	
	// =========================================================
	//CONTROLADORES
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

		// -------------------------
		// SUBNAVBAR
		// -------------------------
		panelSubnavbar = new JPanel(new BorderLayout());
		panelSubnavbar.setPreferredSize(new Dimension(0, 60));
		panelSubnavbar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		add(panelSubnavbar, BorderLayout.NORTH);

		etiquetaTitulo = new JLabel();
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelSubnavbar.add(etiquetaTitulo, BorderLayout.WEST);

		// -------------------------
		// BUSCADOR
		// -------------------------
		JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		panelBusqueda.setOpaque(false);

		campoBuscar = new JTextField();
		campoBuscar.setPreferredSize(new Dimension(250, 35));
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.setForeground(new Color(249, 247, 242));
		botonBuscar.setBackground(colorAzul);
		botonBuscar.setPreferredSize(new Dimension(90, 35));

		panelBusqueda.add(campoBuscar);
		panelBusqueda.add(botonBuscar);

		panelSubnavbar.add(panelBusqueda, BorderLayout.EAST);

		// -------------------------
		// PANEL CENTRAL
		// -------------------------
		panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBackground(colorFondo);
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(panelCentral, BorderLayout.CENTER);

		// -------------------------
		// BOTÓN AÑADIR
		// -------------------------
		panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelAcciones.setBackground(Color.WHITE);

		botonAnadir = new JButton();
		botonAnadir.setBackground(colorVerde);
		botonAnadir.setPreferredSize(new Dimension(160, 40));
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
		panelCentral.add(scroll, BorderLayout.CENTER);
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
		cargarDatos();

		revalidate();
		repaint();
	}

	// =========================================================
	// SUBNAVBAR ADAPTABLE
	// =========================================================
	private void configurarSubnavbar() {

		switch (modoActual) {

		case MODO_USUARIOS:

			panelSubnavbar.setBackground(new Color(227, 235, 222));
			etiquetaTitulo.setText("Usuarios");
			campoBuscar.setToolTipText("Buscar usuario por ID, nombre o email");
			break;

		case MODO_RECURSOS:

			panelSubnavbar.setBackground(new Color(220, 230, 244));
			etiquetaTitulo.setText("Recursos");
			campoBuscar.setToolTipText("Buscar recurso por ID, título o tipo");
			break;

		case MODO_PRESTAMOS:

			panelSubnavbar.setBackground(new Color(239, 232, 209));
			etiquetaTitulo.setText("Préstamos");
			campoBuscar.setToolTipText("Buscar préstamo por usuario o recurso");
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

			botonAnadir.setText("Añadir usuario");
			botonAnadir.addActionListener(e -> abrirFormulario());
			break;

		case MODO_RECURSOS:

			botonAnadir.setText("Añadir recurso");
			botonAnadir.addActionListener(e -> abrirFormulario());
			break;

		case MODO_PRESTAMOS:

			botonAnadir.setText("Añadir préstamo");
			botonAnadir.addActionListener(e -> abrirFormulario());
			break;
		}
	}

	// =========================================================
	// FORMULARIO UNIFICADO
	// =========================================================
	private void abrirFormulario() {

		FormularioContenido formulario = new FormularioContenido(modoActual);

		// =====================================================
		// CREAR USUARIO
		// =====================================================

		if (modoActual == MODO_USUARIOS) {

			formulario.getBotonGuardar().addActionListener(e -> {

				String id = formulario.getCampoId().getText().trim();
				String nombre = formulario.getCampoNombre().getText().trim();
				String email = formulario.getCampoEmail().getText().trim();
				Usuario usuario = new Usuario(id, nombre, email);

				if (gestionUsuarios.crearUsuario(usuario)) {

					formulario.dispose();
					mostrarUsuarios();
				}
			});
		}

		// =====================================================
		// CREAR RECURSO
		// =====================================================

		else if (modoActual == MODO_RECURSOS) {

			formulario.getBotonGuardar().addActionListener(e -> {

				try {
					String id = formulario.getCampoId().getText().trim();
					String titulo = formulario.getCampoTitulo().getText().trim();
					String textoAno = formulario.getCampoAno().getText().trim();
					String tipo = (String) formulario.getComboTipo().getSelectedItem();
					String dato1 = formulario.getCampoEspecifico1().getText().trim();
					String dato2 = formulario.getCampoEspecifico2().getText().trim();
					LocalDate ano = LocalDate.parse(textoAno);
					Recurso recurso = null;
					
					// -------------------------------------------------
					// LIBRO
					// -------------------------------------------------
					if ("Libro".equals(tipo)) {

						int paginas = Integer.parseInt(dato2);
						recurso = new Libro(id, titulo, ano, true, dato1, paginas);
					}

					// -------------------------------------------------
					// PELÍCULA
					// -------------------------------------------------
					else if ("Película".equals(tipo)) {

						int duracion = Integer.parseInt(dato2);
						recurso = new Pelicula(id, titulo, ano, true, dato1, duracion);
					}

					// -------------------------------------------------
					// VIDEOJUEGO
					// -------------------------------------------------
					else if ("Videojuego".equals(tipo)) {
						
						int pegi = Integer.parseInt(dato2);
						recurso = new Videojuego(id, titulo, ano, true, dato1, pegi);
					}

					// -------------------------------------------------
					// GUARDAR
					// -------------------------------------------------
					if (recurso != null && gestionRecursos.crearRecurso(recurso)) {

						formulario.dispose();
						mostrarRecursos();
					}

				} catch (Exception ex) {

					ex.printStackTrace();
				}
			});
		}
		
		// =====================================================
		// CREAR PRÉSTAMO
		// =====================================================
		
		else if (modoActual == MODO_PRESTAMOS) {

		    formulario.cargarUsuariosYRecursos(
		            gestionPrestamos.listarUsuarios(),
		            gestionPrestamos.listarRecursos()
		    );

		    formulario.getBotonGuardar().addActionListener(e -> {

		        String usuarioTexto =
		                (String) formulario.getComboUsuario().getSelectedItem();

		        String recursoTexto =
		                (String) formulario.getComboRecurso().getSelectedItem();

		        if (usuarioTexto == null || recursoTexto == null) {
		            return;
		        }

		        String idUsuario =
		                usuarioTexto.substring(
		                        0,
		                        usuarioTexto.indexOf(" · ")
		                );

		        String idRecurso =
		                recursoTexto.substring(
		                        0,
		                        recursoTexto.indexOf(" · ")
		                );

		        boolean creado =
		                gestionPrestamos.prestarRecurso(
		                        idUsuario,
		                        idRecurso
		                );

		        if (creado) {
		            formulario.dispose();
		            mostrarPrestamos();
		        }
		    });
		}

		formulario.setVisible(true);
	}
	
	// =========================================================
	// DATOS TEMPORALES
	// =========================================================
	private void cargarDatos() {

		panelLista.removeAll();

		switch (modoActual) {

		case MODO_USUARIOS:
			cargarUsuarios();
			break;

		case MODO_RECURSOS:
			cargarRecursos();
			break;

		case MODO_PRESTAMOS:
			cargarPrestamos();
			break;
		}

		panelLista.revalidate();
		panelLista.repaint();
	}

	private void cargarUsuarios() {

		for (Usuario usuario : gestionUsuarios.listarUsuarios()) {
			añadirUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail());
		}
	}

	private void cargarRecursos() {

		for (Recurso recurso : gestionRecursos.listarRecursos()) {
			
			String id = recurso.getId();
			String titulo = recurso.getTitulo();
			String ano = recurso.getAno().toString();
			
			String dato1 = "";
		    String dato2 = "";
			
			if(recurso instanceof Libro) {
				
				Libro libro = (Libro) recurso;
				
				dato1 = libro.getAutor();
				dato2 = String.valueOf(libro.getPaginas());
				
			}else if(recurso instanceof Pelicula) {
				
				Pelicula pelicula = (Pelicula) recurso;
				
				dato1 = pelicula.getDirector();
				dato2 = String.valueOf(pelicula.getDuracion());
				
			}else if(recurso instanceof Videojuego) {
				
				Videojuego videojuego = (Videojuego) recurso;
				
				dato1 = videojuego.getPlataforma();
				dato2 = String.valueOf(videojuego.getPEGI());
			}
			
			String estado = (recurso.isEstado() ? "Disponible" : "Prestado"); 
			
			añadirRecurso(recurso.getId(), recurso.getTitulo(), recurso.getAno().toString(), estado, dato1, dato2);
		}
	}

	private void cargarPrestamos() {

		for(Prestamo prestamo : gestionPrestamos.listarPrestamos()) {
			
			System.out.println(prestamo.toString());
			
			String nombreUsuario = prestamo.getUsuario().getNombre();
			String idRecurso = prestamo.getRecurso().getId();
			String tituloRecurso = prestamo.getRecurso().getTitulo();
			String fechaPrestamo =  prestamo.getFechaPrestamo().toString();
			String estado = prestamo.isEstadoPrestamo()? "Activo" : "Finalizado";
			String fechaDevolucion = prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion().toString() : "---";
			
			añadirPrestamo(prestamo, nombreUsuario, idRecurso, tituloRecurso, fechaPrestamo, estado, fechaDevolucion);
		}
	}

	// =========================================================
	// ESTRUCTURA COMÚN DE FILAS
	// =========================================================
	private JPanel crearFila(int altura) {

		JPanel fila = new JPanel(new BorderLayout());
		fila.setBackground(Color.WHITE);
		fila.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)),
				BorderFactory.createEmptyBorder(12, 15, 12, 10)));
		fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, altura));
		
		return fila;
	}

	private JPanel crearPanelDatos() {

		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setOpaque(false);

		return panelDatos;
	}

	private JPanel crearPanelBotones() {

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panelBotones.setOpaque(false);

		return panelBotones;
	}

	private JLabel crearEtiquetaPrincipal(String texto) {

		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 16));

		return etiqueta;
	}

	private JLabel crearEtiquetaSecundaria(String texto) {

		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		return etiqueta;
	}

	// =========================================================
	// FILA DE USUARIO
	// =========================================================
	private void añadirUsuario(String id, String nombre, String email) {

		JPanel fila = crearFila(75);

		JPanel panelDatos = crearPanelDatos();
		panelDatos.add(crearEtiquetaPrincipal(id + " · " + nombre));
		panelDatos.add(crearEtiquetaSecundaria("Email: " + email));

		fila.add(panelDatos, BorderLayout.CENTER);

		JPanel panelBotones = crearPanelBotones();
		JButton botonEditar = crearBotonEditar();
		JButton botonEliminar = crearBotonEliminar();

		panelBotones.add(botonEditar);
		panelBotones.add(botonEliminar);

		fila.add(panelBotones, BorderLayout.EAST);

		// ========================================
		// EDITAR
		// ========================================
		botonEditar.addActionListener(e -> abrirFormularioEdicion(id, nombre, email));

		// ========================================
		// ELIMINAR
		// ========================================
		botonEliminar.addActionListener(e -> abrirConfirmacionEliminacion("Usuario", nombre + " (ID: " + id + ")"));

		// ========================================
		// ABRIR DETALLE
		// ========================================
		añadirEventoDetalle(fila, crearDatosUsuario(id, nombre, email));

		panelLista.add(fila);
	}

	// =========================================================
	// FILA DE RECURSO
	// =========================================================
	private void añadirRecurso(String id, String titulo, String ano, String estado,
			String dato1, String dato2) {

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

		fila.add(panelDatos, BorderLayout.CENTER);

		// ========================================
		// BOTONES
		// ========================================
		JPanel panelBotones = crearPanelBotones();
		JButton botonEditar = crearBotonEditar();
		JButton botonEliminar = crearBotonEliminar();

		panelBotones.add(botonEditar);
		panelBotones.add(botonEliminar);

		fila.add(panelBotones, BorderLayout.EAST);

		// ========================================
		// EDITAR
		// ========================================
		botonEditar.addActionListener(
				e -> abrirFormularioEdicion(id, titulo, ano, estado, dato1, dato2));

		// ========================================
		// ELIMINAR
		// ========================================
		botonEliminar.addActionListener(e -> abrirConfirmacionEliminacion("Recurso", titulo + " (ID: " + id + ")"));

		// ========================================
		// ABRIR DETALLE
		// ========================================
		añadirEventoDetalle(fila, crearDatosRecurso(id, titulo, ano, estado, dato1, dato2));

		panelLista.add(fila);
	}

	// =========================================================
	// FILA DE PRÉSTAMO
	// =========================================================
	private void añadirPrestamo(Prestamo prestamo, String nombreUsuario, String idRecurso, String tituloRecurso,
			String fechaPrestamo, String estado, String fechaDevolucion) {

		JPanel fila = crearFila(75);

		JPanel panelDatos = crearPanelDatos();

		panelDatos.add(crearEtiquetaPrincipal(tituloRecurso + " · " + nombreUsuario));

		panelDatos.add(crearEtiquetaSecundaria("Préstamo: " + fechaPrestamo));

		if (!prestamo.isEstadoPrestamo() && prestamo.getFechaDevolucion() != null) {

			panelDatos.add(crearEtiquetaSecundaria("Devolución: " + prestamo.getFechaDevolucion()));
		}

		fila.add(panelDatos, BorderLayout.CENTER);

		JPanel panelBotones = crearPanelBotones();

		JButton botonEditar = crearBotonEditar();
		panelBotones.add(botonEditar);
		
		if (prestamo.isEstadoPrestamo()) {

		    JButton botonDevolver = new JButton("Devolver");
		    botonDevolver.setBackground(colorRojo);

		    panelBotones.add(botonDevolver);

		    botonDevolver.addActionListener(
		        e -> abrirConfirmacionDevolucion(
		            nombreUsuario,
		            idRecurso,
		            tituloRecurso
		        )
		    );
		}

		fila.add(panelBotones, BorderLayout.EAST);

		botonEditar.addActionListener(
		        e -> abrirFormularioEdicionPrestamo(prestamo)
		);

		// ABRIR DETALLE DEL PRÉSTAMO
		añadirEventoDetalle(fila, crearDatosPrestamo(nombreUsuario, idRecurso, tituloRecurso, fechaPrestamo,
				estado, fechaDevolucion));

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
	private void abrirFormularioEdicion(String id, String nombre, String email) {

	    FormularioContenido formulario = new FormularioContenido(MODO_USUARIOS);

	    formulario.cargarDatos(id, nombre, email);

	    formulario.getBotonGuardar().addActionListener(e -> {

	        String nuevoNombre = formulario.getCampoNombre().getText();
	        String nuevoEmail = formulario.getCampoEmail().getText();

	        if (gestionUsuarios.modificarUsuario(id, nuevoNombre, nuevoEmail)) {

	            formulario.dispose();
	            mostrarUsuarios();
	        }
	    });

	    formulario.setVisible(true);
	}

	private void abrirFormularioEdicion(String id, String titulo, String ano, String estado,
	        String dato1, String dato2) {

	    Recurso recurso = gestionRecursos.buscarRecurso(id);

	    if (recurso == null) {
	        return;
	    }

	    String tipo;

	    if (recurso instanceof Libro) {
	        tipo = "Libro";

	    } else if (recurso instanceof Pelicula) {
	        tipo = "Película";

	    } else if (recurso instanceof Videojuego) {
	        tipo = "Videojuego";

	    } else {
	        return;
	    }

	    FormularioContenido formulario = new FormularioContenido(MODO_RECURSOS);

	    formulario.cargarDatos(
	        id,
	        titulo,
	        ano,
	        estado,
	        dato1,
	        dato2
	    );

	    formulario.getBotonGuardar().addActionListener(e -> {

	        String nuevoTitulo = formulario.getCampoTitulo().getText();
	        String nuevoAnoTexto = formulario.getCampoAno().getText();

	        String nuevoTipo = (String) formulario.getComboTipo().getSelectedItem();

	        String nuevoDato1 = formulario.getCampoEspecifico1().getText();
	        String nuevoDato2 = formulario.getCampoEspecifico2().getText();

	        try {

	            LocalDate nuevoAno = LocalDate.parse(nuevoAnoTexto);

	            if (gestionRecursos.modificarRecurso(
	                    id,
	                    nuevoTitulo,
	                    nuevoAno,
	                    nuevoTipo,
	                    nuevoDato1,
	                    nuevoDato2)) {

	                formulario.dispose();
	                mostrarRecursos();
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    });

	    formulario.setVisible(true);
	}

	private void abrirFormularioEdicionPrestamo(Prestamo prestamo) {

		FormularioContenido formulario = new FormularioContenido(MODO_PRESTAMOS);

		// Cargar usuarios y recursos reales
		formulario.cargarUsuariosYRecursos(gestionPrestamos.listarUsuarios(), gestionPrestamos.listarRecursos());

		// Seleccionar los valores actuales
		formulario.seleccionarUsuario(prestamo.getUsuario().getId());

		String idRecursoActual = prestamo.getRecurso().getId();

		// El recurso actual está prestado, por lo que puede
		// no aparecer entre los recursos disponibles.
		if (!formulario.contieneRecurso(idRecursoActual)) {

			formulario.getComboRecurso()
					.addItem(prestamo.getRecurso().getId() + " · " + prestamo.getRecurso().getTitulo());
		}

		formulario.seleccionarRecurso(idRecursoActual);

		// Guardar cambios
		formulario.getBotonGuardar().addActionListener(e -> {

			String usuarioTexto = (String) formulario.getComboUsuario().getSelectedItem();

			String recursoTexto = (String) formulario.getComboRecurso().getSelectedItem();

			if (usuarioTexto == null || recursoTexto == null) {
				return;
			}

			String idUsuario = usuarioTexto.substring(0, usuarioTexto.indexOf(" · "));

			String idRecurso = recursoTexto.substring(0, recursoTexto.indexOf(" · "));

			boolean modificado = gestionPrestamos.modificarPrestamo(prestamo, idUsuario, idRecurso);

			if (modificado) {

				formulario.dispose();
				mostrarPrestamos();
			}
		});

		formulario.setVisible(true);
	}
	
	private void abrirFormularioEdicionPrestamo(
	        String nombreUsuario,
	        String idRecurso) {

	    for (Prestamo prestamo : gestionPrestamos.listarPrestamos()) {

	        if (prestamo.getUsuario().getNombre().equals(nombreUsuario)
	                && prestamo.getRecurso().getId().equals(idRecurso)) {

	            abrirFormularioEdicionPrestamo(prestamo);
	            return;
	        }
	    }
	}

	// =========================================================
	// ELIMINACIÓN
	// =========================================================
	private void abrirConfirmacionEliminacion(String tipo, String descripcion) {

	    FormularioEliminar formulario = new FormularioEliminar(tipo, descripcion);

	    formulario.getBotonAccion().addActionListener(e -> {

	        if (tipo.equals("Usuario")) {

	            String id = descripcion.substring(
	                    descripcion.indexOf("ID: ") + 4,
	                    descripcion.length() - 1
	            );
	            
	            if (gestionUsuarios.eliminarUsuario(id)) {
	            	
	                formulario.dispose();
	                mostrarUsuarios();
	                
	            } else {

	                JOptionPane.showMessageDialog(
	                    this,
	                    "No se puede eliminar el usuario porque tiene un préstamo activo.",
	                    "No se puede eliminar",
	                    JOptionPane.WARNING_MESSAGE
	                );
	            }
	            
	        }else if (tipo.equals("Recurso")) {

	            String id =
	                descripcion.substring(
	                    descripcion.indexOf("ID: ") + 4,
	                    descripcion.length() - 1
	                );

	            if (gestionRecursos.eliminarRecurso(id)) {

	                formulario.dispose();

	                mostrarRecursos();
	                
	            }else {

	                JOptionPane.showMessageDialog(
	                    this,
	                    "No se puede eliminar el recurso porque tiene un préstamo activo.",
	                    "No se puede eliminar",
	                    JOptionPane.WARNING_MESSAGE
	                );
	            }
	        }
	    });

	    formulario.setVisible(true);
	}

	// =========================================================
	// DEVOLUCIÓN DE PRESTAMOS
	// =========================================================
	private void abrirConfirmacionDevolucion(String nombreUsuario, String idRecurso, String tituloRecurso) {

		FormularioEliminar formulario = new FormularioEliminar("devolución", tituloRecurso + " · " + nombreUsuario, true);

		formulario.getBotonAccion().addActionListener(e -> {

			if (gestionPrestamos.devolverRecurso(idRecurso)) {

				formulario.dispose();
				mostrarPrestamos();
			}
		});

		formulario.setVisible(true);
	}

	// =========================================================
	// DETALLE UNIFICADO
	// =========================================================
	private void añadirEventoDetalle(JPanel fila, String[] datos) {

		fila.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		fila.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1) {

					VentanaDetalleContenido ventana = new VentanaDetalleContenido(modoActual, datos, gestionPrestamos);

					ventana.setAccionEditarPrestamo(
							datosPrestamo -> abrirFormularioEdicionPrestamo(datosPrestamo[0], datosPrestamo[1])
				);

					ventana.setAccionDevolverPrestamo(datosPrestamo -> abrirConfirmacionDevolucion(datosPrestamo[0],
							datosPrestamo[1], datosPrestamo[2]));

					ventana.setVisible(true);
				}
			}
		});
	}

	// =========================================================
	// DATOS PARA EL DETALLE
	// =========================================================
	private String[] crearDatosUsuario(String id, String nombre, String email) {

		return new String[] { id, nombre, email };
	}

	private String[] crearDatosRecurso(String id, String titulo, String ano, String estado,
			String dato1, String dato2) {

		return new String[] { id, titulo, ano, estado, dato1, dato2 };
	}

	// =========================================================
	private String[] crearDatosPrestamo(String nombreUsuario, String idRecurso, String tituloRecurso,
			String fechaPrestamo, String estado, String fechaDevolucion) {

		return new String[] {nombreUsuario, idRecurso, tituloRecurso, fechaPrestamo, estado,
				fechaDevolucion };
	}

	// ACCESO AL MODO ACTUAL
	// =========================================================

	public int getModoActual() {

		return modoActual;
	}
}
