package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Libro;
import modelo.Pelicula;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Videojuego;

/**
 * Cosas que antes estaban repetidas en varias clases de la vista: colores,
 * fuentes, textos "mágicos" (Libro, Disponible...), y métodos para crear filas,
 * etiquetas y botones con el mismo aspecto en toda la aplicación.
 * 
 * Si hay que cambiar un color o un texto, se cambia aquí y listo.
 */
public final class UtilVista {

	// Clase de utilidades: no se instancia
	private UtilVista() {
	}

	// =========================================================
	// COLORES
	// =========================================================
	public static final Color COLOR_FONDO = new Color(249, 247, 242);
	public static final Color COLOR_AZUL = new Color(52, 80, 154);
	public static final Color COLOR_VERDE = new Color(123, 220, 99);
	public static final Color COLOR_AMARILLO = new Color(236, 206, 92);
	public static final Color COLOR_ROJO = new Color(230, 100, 100);
	public static final Color COLOR_BORDE = new Color(220, 220, 220);

	// =========================================================
	// FUENTES
	// =========================================================
	private static final String FAMILIA = "Segoe UI";

	public static final Font FUENTE_TITULO = new Font(FAMILIA, Font.BOLD, 24);
	public static final Font FUENTE_SUBTITULO = new Font(FAMILIA, Font.BOLD, 20);
	public static final Font FUENTE_NAVEGACION = new Font(FAMILIA, Font.BOLD, 18);
	public static final Font FUENTE_PRINCIPAL = new Font(FAMILIA, Font.BOLD, 16);
	public static final Font FUENTE_DATO = new Font(FAMILIA, Font.PLAIN, 15);
	public static final Font FUENTE_TEXTO = new Font(FAMILIA, Font.PLAIN, 14);
	public static final Font FUENTE_TEXTO_NEGRITA = new Font(FAMILIA, Font.BOLD, 14);
	public static final Font FUENTE_SECUNDARIA = new Font(FAMILIA, Font.PLAIN, 13);

	// =========================================================
	// TEXTOS COMUNES
	// =========================================================
	public static final String TODOS = "Todos";

	public static final String TIPO_LIBRO = "Libro";

	public static final String TIPO_PELICULA = "Película";
	public static final String TIPO_VIDEOJUEGO = "Videojuego";

	public static final String DISPONIBLE = "Disponible";
	public static final String PRESTADO = "Prestado";

	public static final String ACTIVO = "Activo";
	public static final String FINALIZADO = "Finalizado";

	public static final String SIN_FECHA = "---";

	// =========================================================
	// TEXTOS SEGÚN EL TIPO DE RECURSO
	// =========================================================
	public static String tipoDe(Recurso recurso) {

		if (recurso instanceof Libro) {
			return TIPO_LIBRO;
		}
		if (recurso instanceof Pelicula) {
			return TIPO_PELICULA;
		}
		if (recurso instanceof Videojuego) {
			return TIPO_VIDEOJUEGO;
		}
		return "Recurso";
	}

	public static String etiquetaDato1(String tipo) {

		if (TIPO_LIBRO.equals(tipo)) {
			return "Autor";
		}
		if (TIPO_PELICULA.equals(tipo)) {
			return "Director";
		}
		if (TIPO_VIDEOJUEGO.equals(tipo)) {
			return "Plataforma";
		}
		return "";
	}

	public static String etiquetaDato2(String tipo) {

		if (TIPO_LIBRO.equals(tipo)) {
			return "Páginas";
		}
		if (TIPO_PELICULA.equals(tipo)) {
			return "Duración";
		}
		if (TIPO_VIDEOJUEGO.equals(tipo)) {
			return "PEGI";
		}
		return "";
	}

	public static String dato1(Recurso recurso) {

		if (recurso instanceof Libro) {
			return ((Libro) recurso).getAutor();
		}
		if (recurso instanceof Pelicula) {
			return ((Pelicula) recurso).getDirector();
		}
		if (recurso instanceof Videojuego) {
			return ((Videojuego) recurso).getPlataforma();
		}
		return "";
	}

	public static String dato2(Recurso recurso) {

		if (recurso instanceof Libro) {
			return String.valueOf(((Libro) recurso).getPaginas());
		}
		if (recurso instanceof Pelicula) {
			return String.valueOf(((Pelicula) recurso).getDuracion());
		}
		if (recurso instanceof Videojuego) {
			return String.valueOf(((Videojuego) recurso).getPEGI());
		}
		return "";
	}

	// =========================================================
	// TEXTOS DE ESTADO Y FECHAS
	// =========================================================
	public static String estadoDe(Recurso recurso) {

		return recurso.isEstado() ? DISPONIBLE : PRESTADO;
	}

	public static String estadoDe(Prestamo prestamo) {

		return prestamo.isEstadoPrestamo() ? ACTIVO : FINALIZADO;
	}

	public static String fechaDevolucion(Prestamo prestamo) {

		return prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion().toString() : SIN_FECHA;
	}

	// =========================================================
	//AVISOS
	// =========================================================
	public static void mostrarAviso(Component padre, String mensaje) {

		JOptionPane.showMessageDialog(padre, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
	}

	// =========================================================
	// FILAS DE LAS LISTAS
	// =========================================================
	public static JPanel crearFila() {

		JPanel fila = new JPanel(new BorderLayout());
		fila.setBackground(Color.WHITE);
		fila.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE),
				BorderFactory.createEmptyBorder(12, 15, 12, 10)));

		return fila;
	}

	public static JPanel crearPanelDatos() {

		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setOpaque(false);

		return panelDatos;
	}

	public static JPanel crearPanelBotones() {

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panelBotones.setOpaque(false);

		return panelBotones;
	}

	public static JLabel crearEtiquetaPrincipal(String texto) {

		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(FUENTE_PRINCIPAL);

		return etiqueta;
	}

	public static JLabel crearEtiquetaSecundaria(String texto) {

		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(FUENTE_SECUNDARIA);

		return etiqueta;
	}

	// Hace que un clic simple sobre el componente ejecute la acción. 
	public static void hacerClicable(JComponent componente, Runnable accion) {

		componente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		componente.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1) {
					accion.run();
				}
			}
		});
	}

	// =========================================================
	// BOTONES
	// =========================================================
	public static JButton crearBoton(String texto, Color fondo) {

		JButton boton = new JButton(texto);
		boton.setBackground(fondo);

		return boton;
	}

	public static JButton crearBotonEditar() {

		return crearBoton("Editar", COLOR_AMARILLO);
	}

	public static JButton crearBotonEliminar() {

		return crearBoton("Eliminar", COLOR_ROJO);
	}

	public static JButton crearBotonDevolver() {

		return crearBoton("Devolver", COLOR_ROJO);
	}
}