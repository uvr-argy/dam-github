package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class DatosPrueba {

	public static void cargarDatos(ArrayList<Usuario> usuarios, ArrayList<Recurso> recursos) {

		// ==================== USUARIOS ====================

		usuarios.add(new Usuario("U01", "Santiago", "santiago@gmail.com"));
		usuarios.add(new Usuario("U02", "Eneko", "eneko@gmail.com"));
		usuarios.add(new Usuario("U03", "Ander", "ander@gmail.com"));
		usuarios.add(new Usuario("U04", "Iker", "iker@gmail.com"));
		usuarios.add(new Usuario("U05", "Unai", "unai@gmail.com"));
		usuarios.add(new Usuario("U06", "Aitor", "aitor@gmail.com"));
		usuarios.add(new Usuario("U07", "Jon", "jon@gmail.com"));
		usuarios.add(new Usuario("U08", "Mikel", "mikel@gmail.com"));
		usuarios.add(new Usuario("U09", "Asier", "asier@gmail.com"));
		usuarios.add(new Usuario("U10", "Julen", "julen@gmail.com"));

		// ==================== LIBROS ====================

		recursos.add(new Libro("L01", "El Quijote", LocalDate.of(1605, 1, 1), true, "Miguel de Cervantes", 863));

		recursos.add(new Libro("L02", "1984", LocalDate.of(1949, 1, 1), true, "George Orwell", 328));

		recursos.add(new Libro("L03", "Harry Potter", LocalDate.of(1997, 1, 1), true, "J.K. Rowling", 309));

		recursos.add(new Libro("L04", "El Hobbit", LocalDate.of(1937, 1, 1), true, "J.R.R. Tolkien", 310));

		recursos.add(new Libro("L05", "Drácula", LocalDate.of(1897, 1, 1), true, "Bram Stoker", 418));

		recursos.add(new Libro("L06", "Frankenstein", LocalDate.of(1818, 1, 1), true, "Mary Shelley", 280));

		recursos.add(new Libro("L07", "La Odisea", LocalDate.of(-700, 1, 1), true, "Homero", 400));

		recursos.add(new Libro("L08", "Dune", LocalDate.of(1965, 1, 1), true, "Frank Herbert", 688));

		recursos.add(new Libro("L09", "It", LocalDate.of(1986, 1, 1), true, "Stephen King", 1138));

		recursos.add(new Libro("L10", "El Principito", LocalDate.of(1943, 1, 1), true, "Antoine de Saint-Exupéry", 96));

		// ==================== PELÍCULAS ====================

		recursos.add(new Pelicula("P01", "Interestelar", LocalDate.of(2014, 1, 1), true, "Christopher Nolan", 169));

		recursos.add(new Pelicula("P02", "El Padrino", LocalDate.of(1972, 1, 1), true, "Francis Ford Coppola", 175));

		recursos.add(new Pelicula("P03", "Matrix", LocalDate.of(1999, 1, 1), true, "Lana y Lilly Wachowski", 136));

		recursos.add(new Pelicula("P04", "Titanic", LocalDate.of(1997, 1, 1), true, "James Cameron", 195));

		recursos.add(new Pelicula("P05", "Gladiator", LocalDate.of(2000, 1, 1), true, "Ridley Scott", 155));

		recursos.add(new Pelicula("P06", "Avatar", LocalDate.of(2009, 1, 1), true, "James Cameron", 162));

		recursos.add(new Pelicula("P07", "Joker", LocalDate.of(2019, 1, 1), true, "Todd Phillips", 122));

		recursos.add(new Pelicula("P08", "Origen", LocalDate.of(2010, 1, 1), true, "Christopher Nolan", 148));

		recursos.add(new Pelicula("P09", "Alien", LocalDate.of(1979, 1, 1), true, "Ridley Scott", 117));

		recursos.add(new Pelicula("P10", "Toy Story", LocalDate.of(1995, 1, 1), true, "John Lasseter", 81));

		// ==================== VIDEOJUEGOS ====================

		recursos.add(new Videojuego("V01", "Minecraft", LocalDate.of(2011, 1, 1), true, "PC", 7));

		recursos.add(new Videojuego("V02", "The Legend of Zelda", LocalDate.of(2017, 1, 1), true, "Nintendo Switch", 12));

		recursos.add(new Videojuego("V03", "GTA V", LocalDate.of(2013, 1, 1), true, "PlayStation 5", 18));

		recursos.add(new Videojuego("V04", "FIFA 25", LocalDate.of(2024, 1, 1), true, "PlayStation 5", 3));

		recursos.add(new Videojuego("V05", "Pokémon Escarlata", LocalDate.of(2022, 1, 1), true, "Nintendo Switch", 7));

		recursos.add(new Videojuego("V06", "The Last of Us", LocalDate.of(2013, 1, 1), true, "PlayStation 5", 18));

		recursos.add(new Videojuego("V07", "God of War", LocalDate.of(2018, 1, 1), true, "PlayStation 5", 18));

		recursos.add(new Videojuego("V08", "Fortnite", LocalDate.of(2017, 1, 1), true, "PC", 12));

		recursos.add(new Videojuego("V09", "Mario Kart 8", LocalDate.of(2014, 1, 1), true, "Nintendo Switch", 3));

		recursos.add(new Videojuego("V10", "Red Dead Redemption 2", LocalDate.of(2018, 1, 1), true, "Xbox Series X", 18));
	}
}