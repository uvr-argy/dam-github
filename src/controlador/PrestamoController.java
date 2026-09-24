package controlador;

import java.util.List;
import modelo.Prestamo;
import funciones.FuncionesPrestamo;

public class PrestamoController {

    private FuncionesPrestamo funcionesPrestamo;

    public PrestamoController(FuncionesPrestamo funcionesPrestamo) {
        this.funcionesPrestamo = funcionesPrestamo;
    }

    //realizacio prestamo
    public boolean realizarPrestamo(String idUsuario, String idRecurso) {
        if (idUsuario == null || idUsuario.isBlank()) {
            return false;
        }
        if (idRecurso == null || idRecurso.isBlank()) {
            return false;
        }
        return funcionesPrestamo.realizarPrestamo(
                idUsuario,
                idRecurso
        );
    }

    //encontar prestamo 
    public Prestamo buscarPrestamo(String idPrestamo) {
        if (idPrestamo == null || idPrestamo.isBlank()) {
            return null;
        }
        return funcionesPrestamo.buscarPrestamo(idPrestamo);
    }

    //listado
    public List<Prestamo> listarPrestamos() {
        return funcionesPrestamo.listarPrestamos();
    }

    //prestamos de un usuario
    public List<Prestamo> prestamosDeUsuario(String idUsuario) {
        if (idUsuario == null || idUsuario.isBlank()) {
            return List.of();
        }
        return funcionesPrestamo.prestamosDeUsuario(idUsuario);
    }

    //prestamo activos
    public List<Prestamo> listarPrestamosActivos() {
        return funcionesPrestamo.listarPrestamosActivos();
    }
}