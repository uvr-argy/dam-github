package controlador;

import java.time.LocalDate;
import modelo.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Recurso> r = new ArrayList<Recurso>();
		r.add(new Recurso("r01", "Recurso", LocalDate.parse("2026-09-28"), false));
		r.add(new Libro("r02", "Libro", LocalDate.parse("2026-09-27"), false, "Autor", 200));
		r.add(new Pelicula("r03", "Película", LocalDate.parse("2026-09-26"), false, "Director", 120));
		r.add(new Videojuego("r04", "Videojuego", LocalDate.parse("2026-09-25"), false, "Super Nintendo", 7));
		
		ArrayList<Usuario> u = new ArrayList<Usuario>();
		u.add(new Usuario(1, "Santiago", "santiago@gmail.com"));
		u.add(new Usuario(2, "Eneko", "eneko@gmail.com"));
		u.add(new Usuario(3, "Ander", "ander@gmail.com"));
		u.add(new Usuario(4, "Iker", "iker@gmail.com"));
		u.add(new Usuario(5, "Unai", "unai@gmail.com"));
		u.add(new Usuario(6, "Aitor", "aitor@gmail.com"));
		u.add(new Usuario(7, "Jon", "jon@gmail.com"));
		u.add(new Usuario(8, "Mikel", "mikel@gmail.com"));
		u.add(new Usuario(9, "Asier", "asier@gmail.com"));
		u.add(new Usuario(0, "Julen", "julen@gmail.com"));
		
		ArrayList<Prestamo> p = new ArrayList<Prestamo>();
		Guardado.guardar(r, u, p);
		Guardado.cargar();
		r = null;
		r = Guardado.getRecursos();
		
		for (int i = 0; i < r.size(); i++) {
			System.out.println(r.get(i).toString());
		}
	}

}
