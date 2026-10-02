package controlador;

import java.util.ArrayList;

import modelo.Libro;
import modelo.Pelicula;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Videojuego;
import persistencia.Guardado;

public class GestionRecursos {

	private ArrayList<Recurso> recursos;
	private ArrayList<Prestamo> prestamos;

	public GestionRecursos(ArrayList<Recurso> recursos, ArrayList<Prestamo> prestamos) {
		this.recursos = recursos;
		this.prestamos = prestamos;
	}

	// ==================== RECURSOS ====================

	// Crear recurso
	public boolean crearRecurso(Recurso recurso) {

		 String prefijo;

		    if (recurso instanceof Libro) {
		        prefijo = "L";
		    } else if (recurso instanceof Pelicula) {
		        prefijo = "P";
		    } else if (recurso instanceof Videojuego) {
		        prefijo = "V";
		    } else {
		        return false;
		    }

		    int siguienteId = 1;
		    String id = String.format("%s%02d", prefijo, siguienteId);

		    while (buscarRecurso(id) != null) {
		        siguienteId++;
		        id = String.format("%s%02d", prefijo, siguienteId);
		    }

		    recurso.setId(id);
		    recursos.add(recurso);

		    return true;
	}

	// Listar recursos
	public ArrayList<Recurso> listarRecursos() {

		return recursos;

	}

	// Buscar recurso
	public Recurso buscarRecurso(String id) {

		// Busca recurso por id
		for (Recurso recurso : recursos) {
			if (recurso.getId().equals(id)) {
				return recurso;
			}
		}

		// Si no encuentra devuelve null
		return null;
	}

	// Modificar recurso
	public boolean modificarRecurso(String id, String titulo, int ano, String tipo, String dato1, String dato2) {

		Recurso recurso = buscarRecurso(id);

		if (recurso == null) {
			return false;
		}

		recurso.setTitulo(titulo);
		recurso.setAno(ano);

		try {

			if ("Libro".equals(tipo) && recurso instanceof Libro) {

				Libro libro = (Libro) recurso;
				libro.setAutor(dato1);
				libro.setPaginas(Integer.parseInt(dato2));

			} else if ("Película".equals(tipo) && recurso instanceof Pelicula) {

				Pelicula pelicula = (Pelicula) recurso;
				pelicula.setDirector(dato1);
				pelicula.setDuracion(Integer.parseInt(dato2));

			} else if ("Videojuego".equals(tipo) && recurso instanceof Videojuego) {

				Videojuego videojuego = (Videojuego) recurso;
				videojuego.setPlataforma(dato1);
				videojuego.setPEGI(Integer.parseInt(dato2));

			} else {

				return false;
			}

		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	// Eliminar recurso
	public boolean eliminarRecurso(String id) {

		Recurso recurso = buscarRecurso(id);

		if (recurso == null) {

			return false;
		}

		// Comprobar si el recurso esta prestado
		for (Prestamo prestamo : prestamos) {

			if (prestamo.getRecurso().getId().equals(id) && prestamo.isEstadoPrestamo()) {

				return false;
			}
		}

		recursos.remove(recurso);

		return true;
	}

	// Consultar disponibilidad
	public boolean disponibilidad(String id) {

		Recurso recurso = buscarRecurso(id);

		if (recurso == null) {

			return false;
		}

		// Atributo estado de la clase recurso
		return recurso.isEstado();
	}

	// ==================== CONSULTAS ====================

	// Recursos disponibles
	public ArrayList<Recurso> recursosDisponibles() {

		ArrayList<Recurso> disponibles = new ArrayList<>();

		for (Recurso recurso : recursos) {

			if (recurso.isEstado()) {

				disponibles.add(recurso);

			}
		}

		return disponibles;
	}

	// Recursos prestados
	public ArrayList<Recurso> recursosPrestados() {

		ArrayList<Recurso> prestados = new ArrayList<>();

		for (Recurso recurso : recursos) {

			if (!recurso.isEstado()) {

				prestados.add(recurso);

			}
		}

		return prestados;
	}

	// Búsqueda por título
	public ArrayList<Recurso> buscarPorTitulo(String titulo) {

		ArrayList<Recurso> resultados = new ArrayList<>();

		for (Recurso recurso : recursos) {

			// Busca los recursos que contengan las letras que hemos puesto
			if (recurso.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {

				resultados.add(recurso);
			}
		}

		return resultados;
	}

	// Recursos filtrados
	public ArrayList<Recurso> recursosPorTipo(String tipo) {

		ArrayList<Recurso> resultados = new ArrayList<>();

		for (Recurso recurso : recursos) {

			// El getSimpleName nos devuelve el nombre del recurso
			// Además ignoramos si está en minúsculas o mayúsculas
			if (recurso.getClass().getSimpleName().equalsIgnoreCase(tipo)) {

				resultados.add(recurso);
			}
		}

		return resultados;
	}

	public ArrayList<Recurso> filtrarRecursos(String texto, String tipo, String disponibilidad) {
		
	    ArrayList<Recurso> resultados = new ArrayList<>();
	    
	    boolean hayTexto = texto != null && !texto.trim().isEmpty();
	    String busqueda = hayTexto ? texto.toLowerCase().trim() : "";
	    
	    boolean hayTipo = tipo != null && !tipo.equals("Todos") && !tipo.trim().isEmpty();
	    boolean hayDisp = disponibilidad != null && !disponibilidad.equals("Todos") && !disponibilidad.trim().isEmpty();

	    for (Recurso recurso : recursos) {
	    	
	    	if (hayTexto && !(recurso.getId().toLowerCase().contains(busqueda) ||
	                  recurso.getTitulo().toLowerCase().contains(busqueda))) {
	    		continue;
	    	}

	        if (hayTipo && !recurso.getClass().getSimpleName().equalsIgnoreCase(tipo)) {
	            continue;
	        }

	        if (hayDisp) {
	            boolean esDisponible = disponibilidad.equals("Disponible");
	            if (recurso.isEstado() != esDisponible) {
	                continue;
	            }
	        }
	        resultados.add(recurso);
	    }
	    return resultados;
	}
}
