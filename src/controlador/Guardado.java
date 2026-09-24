package controlador;

import modelo.*;

public class Guardado {
	
	public static String sanitizarString(String s) {
		return s.replace('\t', ' ');
	}
	
	public static String serializar(Recurso[] recursos, Usuario[] usuarios, Prestamo[] prestamos) {
		return "";
	}
	
	//TODO a implementar en las clases
	public static String serializarRecurso(Recurso recurso) {
		return "";
	}
}
