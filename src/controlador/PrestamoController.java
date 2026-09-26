package controlador;

import java.util.ArrayList;
import funcionalidades.GestionPrestamos;
import modelo.Prestamo;

public class PrestamoController {

    private GestionPrestamos gestionPrestamos;

    public PrestamoController(GestionPrestamos gestionPrestamos) {
        this.gestionPrestamos = gestionPrestamos;
    }

    //prestarRecurso
    public boolean prestarRecurso(String idUsuario, String idRecurso) {
        if (idUsuario==null || idUsuario.isBlank()) {
            return false;
        }
        if (idRecurso==null || idRecurso.isBlank()) {
            return false;
        }
        return gestionPrestamos.prestarRecurso(idUsuario, idRecurso);
    }


    //devolverRecurso
    public boolean devolverRecurso(String idRecurso) {
        if (idRecurso==null || idRecurso.isBlank()) {
            return false;
        }
        return gestionPrestamos.devolverRecurso(idRecurso);
    }

    //prestamosUsuario
    public ArrayList<Prestamo> prestamosUsuario(String idUsuario) {
        if (idUsuario==null || idUsuario.isBlank()) {
            return new ArrayList<>();
        }
        return gestionPrestamos.prestamosUsuario(idUsuario);
    }

    //prestamosActivos
    public ArrayList<Prestamo> prestamosActivos() {
        return gestionPrestamos.prestamosActivos();
    }

    //prestamosActivos
    public ArrayList<Prestamo> listarPrestamos() {
        return gestionPrestamos.listarPrestamos();
    }

    //prestamosDevueltos
    public ArrayList<Prestamo> prestamosDevueltos() {
        return gestionPrestamos.prestamosDevueltos();
    }
}