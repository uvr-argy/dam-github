package controlador;

import java.util.List;
import modelo.Recurso;
import funciones.FuncionesRecurso;

public class RecursoController {

    private FuncionesRecurso funcionesRecurso;

    public RecursoController(FuncionesRecurso funcionesRecurso) {
        this.funcionesRecurso = funcionesRecurso;
    }

    //crear
    public boolean crearRecurso(Recurso recurso) {
        if (recurso == null) {
            return false;
        }
        return funcionesRecurso.crearRecurso(recurso);
    }

    //listar
    public List<Recurso> listarRecursos() {
        return funcionesRecurso.listarRecursos();
    }

    //buscar x id
    public Recurso buscarRecurso(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return funcionesRecurso.buscarRecurso(id);
    }

    //buscar x tittle
    public List<Recurso> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return List.of();
        }
        return funcionesRecurso.buscarPorTitulo(titulo);
    }

    //editar
    public boolean modificarRecurso(Recurso recurso) {
        if (recurso == null) {
            return false;
        }
        return funcionesRecurso.modificarRecurso(recurso);
    }

    //borrar
    public boolean eliminarRecurso(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return funcionesRecurso.eliminarRecurso(id);
    }

    //comprobar disponibilidad
    public boolean estaDisponible(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return funcionesRecurso.estaDisponible(id);
    }

    //listar disponible
    public List<Recurso> listarDisponibles() {
        return funcionesRecurso.listarDisponibles();
    }

    //listar prestado
    public List<Recurso> listarPrestados() {
        return funcionesRecurso.listarPrestados();
    }

    //filtro tipo
    public List<Recurso> filtrarPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            return List.of();
        }
        return funcionesRecurso.filtrarPorTipo(tipo);
    }
}