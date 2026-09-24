package controlador;

import funciones.FuncionesPrestamo;

public class DevolucionController {

    private FuncionesPrestamo funcionesPrestamo;

    public DevolucionController(FuncionesPrestamo funcionesPrestamo) {
        this.funcionesPrestamo = funcionesPrestamo;
    }

    //devolver recurso
    public boolean devolverRecurso(String idPrestamo) {
        if (idPrestamo == null || idPrestamo.isBlank()) {
            return false;
        }
        return funcionesPrestamo.devolverRecurso(idPrestamo);
    }
}