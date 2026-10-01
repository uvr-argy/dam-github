package app;

import java.util.ArrayList;

import funcionalidades.GestionPrestamos;
import funcionalidades.GestionRecursos;
import funcionalidades.GestionUsuarios;

import modelo.DatosPrueba;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
    	
    	
        // ==========================================
        // 1. CREAR LAS LISTAS
        // ==========================================

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Recurso> recursos = new ArrayList<>();
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        // ==========================================
        // 2. CARGAR DATOS DE PRUEBA
        // ==========================================

        DatosPrueba.cargarDatos(usuarios, recursos);

        // ==========================================
        // 3. CREAR LAS GESTIONES
        // ==========================================

		GestionUsuarios gestionUsuarios = new GestionUsuarios(usuarios, prestamos);

		GestionRecursos gestionRecursos = new GestionRecursos(recursos, prestamos);

		GestionPrestamos gestionPrestamos = new GestionPrestamos(usuarios, recursos, prestamos);
		
		// ==================== PRÉSTAMOS ====================

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
		
        /* PRUEBAS DE CONTROLADORES
        // ==========================================
        // 4. MOSTRAR USUARIOS
        // ==========================================

        System.out.println("=================================");
        System.out.println("          USUARIOS");
        System.out.println("=================================");

        for (Usuario usuario : gestionUsuarios.listarUsuarios()) {
            System.out.println(usuario);
        }


        // ==========================================
        // 5. MOSTRAR RECURSOS
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("          RECURSOS");
        System.out.println("=================================");

        for (Recurso recurso : gestionRecursos.listarRecursos()) {
            System.out.println(recurso);
        }


        // ==========================================
        // 6. BUSCAR USUARIO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       BUSCAR USUARIO");
        System.out.println("=================================");

        Usuario usuarioEncontrado =
                gestionUsuarios.buscarUsuario("U01");

        if (usuarioEncontrado != null) {
            System.out.println("Usuario encontrado:");
            System.out.println(usuarioEncontrado);
        } else {
            System.out.println("Usuario no encontrado.");
        }


        // ==========================================
        // 7. BUSCAR RECURSO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       BUSCAR RECURSO");
        System.out.println("=================================");

        Recurso recursoEncontrado =
                gestionRecursos.buscarRecurso("L01");

        if (recursoEncontrado != null) {
            System.out.println("Recurso encontrado:");
            System.out.println(recursoEncontrado);
        } else {
            System.out.println("Recurso no encontrado.");
        }


        // ==========================================
        // 8. REALIZAR PRÉSTAMO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("          PRÉSTAMO");
        System.out.println("=================================");

        boolean prestamoRealizado =
                gestionPrestamos.prestarRecurso("U01", "L01");

        if (prestamoRealizado) {
            System.out.println("Préstamo realizado correctamente.");
        } else {
            System.out.println("No se ha podido realizar el préstamo.");
        }


        // ==========================================
        // 9. COMPROBAR DISPONIBILIDAD
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       DISPONIBILIDAD");
        System.out.println("=================================");

        boolean disponible =
                gestionRecursos.disponibilidad("L01");

        System.out.println(
                "¿L01 está disponible? " + disponible);


        // ==========================================
        // 10. INTENTAR PRESTAR EL MISMO RECURSO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("     PRÉSTAMO DUPLICADO");
        System.out.println("=================================");

        boolean segundoPrestamo =
                gestionPrestamos.prestarRecurso("U02", "L01");

        if (segundoPrestamo) {
            System.out.println(
                    "El segundo préstamo se ha realizado.");
        } else {
            System.out.println(
                    "No se puede prestar L01 porque ya está prestado.");
        }


        // ==========================================
        // 11. MOSTRAR PRÉSTAMOS ACTIVOS
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       PRÉSTAMOS ACTIVOS");
        System.out.println("=================================");

        for (Prestamo prestamo :
                gestionPrestamos.prestamosActivos()) {

            System.out.println(prestamo);
        }


        // ==========================================
        // 12. MOSTRAR HISTORIAL DEL RECURSO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       HISTORIAL L01");
        System.out.println("=================================");

        for (Prestamo prestamo :
                gestionPrestamos.getHistorialRecurso("L01")) {

            System.out.println(prestamo);
        }


        // ==========================================
        // 13. MOSTRAR HISTORIAL DEL USUARIO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       HISTORIAL U01");
        System.out.println("=================================");

        for (Prestamo prestamo :
                gestionPrestamos.getHistorialUsuario("U01")) {

            System.out.println(prestamo);
        }


        // ==========================================
        // 14. DEVOLVER RECURSO
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("        DEVOLUCIÓN");
        System.out.println("=================================");

        boolean devolucion =
                gestionPrestamos.devolverRecurso("L01");

        if (devolucion) {
            System.out.println(
                    "Recurso devuelto correctamente.");
        } else {
            System.out.println(
                    "No se ha podido devolver el recurso.");
        }


        // ==========================================
        // 15. COMPROBAR DISPONIBILIDAD FINAL
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("     DISPONIBILIDAD FINAL");
        System.out.println("=================================");

        disponible =
                gestionRecursos.disponibilidad("L01");

        System.out.println(
                "¿L01 está disponible? " + disponible);


        // ==========================================
        // FIN
        // ==========================================

        System.out.println("\n=================================");
        System.out.println("       FIN DE LAS PRUEBAS");
        System.out.println("=================================");
        */
    	
    	//cargar persistencia AQUÍ
    	
    	VentanaPrincipal ventana = new VentanaPrincipal(gestionUsuarios, gestionRecursos, gestionPrestamos);
    	ventana.setVisible(true);
    }
}