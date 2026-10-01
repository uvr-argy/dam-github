package app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import controlador.GestionPrestamos;
import controlador.GestionRecursos;
import controlador.GestionUsuarios;
import modelo.DatosPrueba;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import persistencia.Guardado;
import vista.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {

		Guardado.cargar();

		ArrayList<Usuario> usuarios = Guardado.getUsuarios();
		ArrayList<Recurso> recursos = Guardado.getRecursos();
		ArrayList<Prestamo> prestamos = Guardado.getPrestamos();

		GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios, prestamos);
		GestionRecursos gestionRecursos = new GestionRecursos(recursos, prestamos);
		GestionPrestamos gestionPrestamos = new GestionPrestamos(usuarios, recursos, prestamos);

		if (usuarios.isEmpty() && recursos.isEmpty()) {
					
				inicializarDatos(usuarios, recursos, gestionPrestamos);
		}
		
		

		VentanaPrincipal ventana = new VentanaPrincipal(gestionUsuarios, gestionRecursos, gestionPrestamos);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				Guardado.guardar(recursos, usuarios, prestamos);
				ventana.dispose();
			}
		});
		
		ventana.setVisible(true);
	}

	public static void inicializarDatos(ArrayList<Usuario> usuarios, ArrayList<Recurso> recursos,
			GestionPrestamos gestionPrestamos) {

		DatosPrueba.cargarDatos(usuarios, recursos);
		
		gestionPrestamos.prestarRecurso("U01", "L01"); // Santiago - El Quijote
		gestionPrestamos.prestarRecurso("U02", "L02"); // Eneko - 1984
		gestionPrestamos.prestarRecurso("U03", "L03"); // Ander - Harry Potter
		gestionPrestamos.prestarRecurso("U04", "L04"); // Iker - El Hobbit
		gestionPrestamos.prestarRecurso("U05", "L05"); // Unai - Drácula

		gestionPrestamos.prestarRecurso("U06", "P01"); // Aitor - Interestelar
		gestionPrestamos.prestarRecurso("U07", "P02"); // Jon - El Padrino
		gestionPrestamos.prestarRecurso("U08", "P03"); // Mikel - Matrix
		gestionPrestamos.prestarRecurso("U09", "P04"); // Asier - Titanic
		gestionPrestamos.prestarRecurso("U10", "P05"); // Julen - Gladiator

		gestionPrestamos.prestarRecurso("U01", "V01"); // Santiago - Minecraft
		gestionPrestamos.prestarRecurso("U03", "V02"); // Ander - Zelda
		gestionPrestamos.prestarRecurso("U05", "V03"); // Unai - GTA V
		gestionPrestamos.prestarRecurso("U07", "V04"); // Jon - FIFA 25
		gestionPrestamos.prestarRecurso("U09", "V05"); // Asier - Pokémon Escarlata
	}
}