package controlador;

import modelo.*;
import java.time.LocalDate;

public class Guardado {
	public static final char SEPARADOR = '\t';
	public static final char LINEA = '\n';
	
	public static String sanitizarString(String s) {
		return s.replace(Guardado.SEPARADOR, ' ');
	}
	
	public static void anadirSerializado(String serial, String datos) {
		serial += Guardado.sanitizarString(datos);
		serial += Guardado.SEPARADOR;
	}
	
	public static void anadirSerializado(String serial, LocalDate datos) {
		serial += Guardado.sanitizarString("" + datos);
		serial += Guardado.SEPARADOR;
	}

	public static void anadirSerializado(String serial, boolean datos) {
		serial += Guardado.sanitizarString("" + datos);
		serial += Guardado.SEPARADOR;
	}
	
	public static void anadirSerializado(String serial, int datos) {
		serial += Guardado.sanitizarString("" + datos);
		serial += Guardado.SEPARADOR;
	}
	
	public static String serializarRecurso(Recurso recurso) {
		String s = "";
		Guardado.anadirSerializado(s, recurso.getId());
		Guardado.anadirSerializado(s, recurso.getTitulo());
		Guardado.anadirSerializado(s, recurso.getAno());
		Guardado.anadirSerializado(s, recurso.isEstado());
		return s;
	}
	
	//funciones para serializar ÚNICAMENTE atributos especificos de subclases
	public static String serializarLibro(Libro libro) {
		String s = "";
		s = Guardado.serializarRecurso(libro);
		Guardado.anadirSerializado(s, libro.getAutor());
		Guardado.anadirSerializado(s, libro.getPaginas());
		return s;
	}
	
	public static String serializarPelicula(Pelicula pelicula) {
		String s = "";
		s = Guardado.serializarRecurso(pelicula);
		Guardado.anadirSerializado(s, pelicula.getDirector());
		Guardado.anadirSerializado(s, pelicula.getDuracion());
		return s;
	}
	
	public static String serializarVideojuego(Videojuego videojuego) {
		String s = "";
		s = Guardado.serializarRecurso(videojuego);
		Guardado.anadirSerializado(s, videojuego.getPlataforma());
		Guardado.anadirSerializado(s, videojuego.getPEGI());
		return s;
	}
	
	public static String serializarRecursos(Recurso[] recursos) {
		String s = "";
		return s;
	}
	
	/*public static String serializar(Recurso[] recursos, Usuario[] usuarios, Prestamo[] prestamos) {
		return "";
	}*/
}
