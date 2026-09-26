package controlador;

import java.io.Serializable;

import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;

public class DatosBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    private Recurso[] recursos;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;

    public DatosBiblioteca(
            Recurso[] recursos,
            Usuario[] usuarios,
            Prestamo[] prestamos) {

        this.recursos = recursos;
        this.usuarios = usuarios;
        this.prestamos = prestamos;
    }

    public Recurso[] getRecursos() {
        return recursos;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public Prestamo[] getPrestamos() {
        return prestamos;
    }
}