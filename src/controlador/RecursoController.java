package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import funcionalidades.GestionRecursos;
import modelo.Recurso;

public class RecursoController {

    private GestionRecursos gestionRecursos;

    public RecursoController(GestionRecursos gestionRecursos) {
        this.gestionRecursos = gestionRecursos;
    }

    //crearRecurso
    public boolean crearRecurso(Recurso recurso) {
        if (recurso==null) {
            return false;
        }
        if (recurso.getId()==null || recurso.getId().isBlank()) {
            return false;
        }
        if (recurso.getTitulo()==null || recurso.getTitulo().isBlank()) {
            return false;
        }
        if (recurso.getAno()==null) {
            return false;
        }
        return gestionRecursos.crearRecurso(recurso);
    }

    //listarRecursos
    public ArrayList<Recurso> listarRecursos() {
        return gestionRecursos.listarRecursos();
    }

    //buscarRecurso ID
    public Recurso buscarRecurso(String id) {
        if (id==null || id.isBlank()) {
            return null;
        }
        return gestionRecursos.buscarRecurso(id);
    }

    //buscarRecurso
    public boolean modificarRecurso(String id,String titulo,LocalDate ano) {
        if (id==null || id.isBlank()) {
            return false;
        }
        if (titulo==null || titulo.isBlank()) {
            return false;
        }
        if (ano==null) {
            return false;
        }
        return gestionRecursos.modificarRecurso(id,titulo,ano);
    }

    //eliminarRecurso
    public boolean eliminarRecurso(String id) {
        if (id==null || id.isBlank()) {
            return false;
        }
        return gestionRecursos.eliminarRecurso(id);
    }

    //disponibilidad
    public boolean disponibilidad(String id) {
        if (id==null || id.isBlank()) {
            return false;
        }
        return gestionRecursos.disponibilidad(id);
    }

    //recursosDisponibles
    public ArrayList<Recurso> recursosDisponibles() {
        return gestionRecursos.recursosDisponibles();
    }

    //recursosPrestados
    public ArrayList<Recurso> recursosPrestados() {
        return gestionRecursos.recursosPrestados();
    }

    //recursosPrestados
    public ArrayList<Recurso> buscarPorTitulo(String titulo) {
        if (titulo==null || titulo.isBlank()) {
            return new ArrayList<>();
        }
        return gestionRecursos.buscarPorTitulo(titulo);
    }

    //recursosPorTipo
    public ArrayList<Recurso> recursosPorTipo(String tipo) {
        if (tipo==null || tipo.isBlank()) {
            return new ArrayList<>();
        }
        return gestionRecursos.recursosPorTipo(tipo);
    }

    //recursosMasPrestados
    public ArrayList<Recurso> recursosMasPrestados(int cantidad) {
        if (cantidad<1) {
            return new ArrayList<>();
        }
        return gestionRecursos.recursosMasPrestados(cantidad);
    }

    //recursosPorAno
    public ArrayList<Recurso> recursosPorAno(int ano) {
        if (ano<=0) {
            return new ArrayList<>();
        }
        return gestionRecursos.recursosPorAno(ano);
    }

    //recursosNuncaPrestados
    public ArrayList<Recurso> recursosNuncaPrestados() {
        return gestionRecursos.recursosNuncaPrestados();
    }
}