package modelo;

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

		recursos.add(new Libro("L01", "El Quijote", 1605, true, "Miguel de Cervantes", 863));
		recursos.add(new Libro("L02", "1984", 1949, true, "George Orwell", 328));
		recursos.add(new Libro("L03", "Harry Potter", 1997, true, "J.K. Rowling", 309));
		recursos.add(new Libro("L04", "El Hobbit", 1937, true, "J.R.R. Tolkien", 310));
		recursos.add(new Libro("L05", "Drácula", 1897, true, "Bram Stoker", 418));
		recursos.add(new Libro("L06", "Frankenstein", 1818, true, "Mary Shelley", 280));
		recursos.add(new Libro("L07", "La Odisea", -700, true, "Homero", 400));
		recursos.add(new Libro("L08", "Dune", 1965, true, "Frank Herbert", 688));
		recursos.add(new Libro("L09", "It", 1986, true, "Stephen King", 1138));
		recursos.add(new Libro("L10", "El Principito", 1943, true, "Antoine de Saint-Exupéry", 96));

		// ==================== PELÍCULAS ====================

		recursos.add(new Pelicula("P01", "Interestelar", 2014, true, "Christopher Nolan", 169));
		recursos.add(new Pelicula("P02", "El Padrino", 1972, true, "Francis Ford Coppola", 175));
		recursos.add(new Pelicula("P03", "Matrix", 1999, true, "Lana y Lilly Wachowski", 136));
		recursos.add(new Pelicula("P04", "Titanic", 1997, true, "James Cameron", 195));
		recursos.add(new Pelicula("P05", "Gladiator", 2000, true, "Ridley Scott", 155));
		recursos.add(new Pelicula("P06", "Avatar", 2009, true, "James Cameron", 162));
		recursos.add(new Pelicula("P07", "Joker", 2019, true, "Todd Phillips", 122));
		recursos.add(new Pelicula("P08", "Origen", 2010, true, "Christopher Nolan", 148));
		recursos.add(new Pelicula("P09", "Alien", 1979, true, "Ridley Scott", 117));
		recursos.add(new Pelicula("P10", "Toy Story", 1995, true, "John Lasseter", 81));

		// ==================== VIDEOJUEGOS ====================

		recursos.add(new Videojuego("V01", "Minecraft", 2011, true, "PC", 7));
		recursos.add(new Videojuego("V02", "The Legend of Zelda", 2017, true, "Nintendo Switch", 12));
		recursos.add(new Videojuego("V03", "GTA V", 2013, true, "PlayStation 5", 18));
		recursos.add(new Videojuego("V04", "FIFA 25", 2024, true, "PlayStation 5", 3));
		recursos.add(new Videojuego("V05", "Pokémon Escarlata", 2022, true, "Nintendo Switch", 7));
		recursos.add(new Videojuego("V06", "The Last of Us", 2013, true, "PlayStation 5", 18));
		recursos.add(new Videojuego("V07", "God of War", 2018, true, "PlayStation 5", 18));
		recursos.add(new Videojuego("V08", "Fortnite", 2017, true, "PC", 12));
		recursos.add(new Videojuego("V09", "Mario Kart 8", 2014, true, "Nintendo Switch", 3));
		recursos.add(new Videojuego("V10", "Red Dead Redemption 2", 2018, true, "Xbox Series X", 18));
	}
}