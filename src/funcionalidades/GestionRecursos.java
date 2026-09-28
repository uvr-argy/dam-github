package funcionalidades;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Prestamo;
import modelo.Recurso;

public class GestionRecursos {

    private ArrayList<Recurso> recursos;

    public GestionRecursos(ArrayList<Recurso> recursos, ArrayList<Prestamo> prestamos) {
        this.recursos = recursos;
    }

    // ==================== RECURSOS ====================

    // Crear recurso
    public boolean crearRecurso(Recurso recurso) {

        // Para evitar ids duplicados
        if (buscarRecurso(recurso.getId()) != null) {
            return false;
        }

        recursos.add(recurso);

        return true;
    }

    // Listar recursos
    public ArrayList<Recurso> listarRecursos() {

        return recursos;

    }

    // Buscar recurso
    public Recurso buscarRecurso(String id) {

        // Busca recurso por id
        for (Recurso recurso : recursos) {
            if (recurso.getId().equals(id)) {
                return recurso;
            }
        }

        // Si no encuentra devuelve null
        return null;
    }

    // Modificar recurso
    public boolean modificarRecurso(String id, String titulo, LocalDate ano) {

        Recurso recurso = buscarRecurso(id);

        if (recurso == null) {

            return false;
        }

        // No modifico el id porque lo usamos para identificar al recurso
        recurso.setTitulo(titulo);

        recurso.setAno(ano);

        return true;
    }

    // Eliminar recurso
    public boolean eliminarRecurso(String id) {

        Recurso recurso = buscarRecurso(id);

        if (recurso == null) {

            return false;
        }

        recursos.remove(recurso);

        return true;
    }

    // Consultar disponibilidad
    public boolean disponibilidad(String id) {

        Recurso recurso = buscarRecurso(id);

        if (recurso == null) {

            return false;
        }

        // Atributo estado de la clase recurso
        return recurso.isEstado();
    }

    // ==================== CONSULTAS ====================

    // Recursos disponibles
    public ArrayList<Recurso> recursosDisponibles() {

        ArrayList<Recurso> disponibles = new ArrayList<>();

        for (Recurso recurso : recursos) {

            if (recurso.isEstado()) {

                disponibles.add(recurso);

            }
        }

        return disponibles;
    }

    // Recursos prestados
    public ArrayList<Recurso> recursosPrestados() {

        ArrayList<Recurso> prestados = new ArrayList<>();

        for (Recurso recurso : recursos) {

            if (!recurso.isEstado()) {

                prestados.add(recurso);

            }
        }

        return prestados;
    }

    // Búsqueda por título
    public ArrayList<Recurso> buscarPorTitulo(String titulo) {

        ArrayList<Recurso> resultados = new ArrayList<>();

        for (Recurso recurso : recursos) {

            // Busca los recursos que contengan las letras que hemos puesto
            if (recurso.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {

                resultados.add(recurso);
            }
        }

        return resultados;
    }

    // Recursos filtrados
    public ArrayList<Recurso> recursosPorTipo(String tipo) {

        ArrayList<Recurso> resultados = new ArrayList<>();

        for (Recurso recurso : recursos) {

            // El getSimpleName nos devuelve el nombre del recurso
            // Además ignoramos si está en minúsculas o mayúsculas
            if (recurso.getClass().getSimpleName().equalsIgnoreCase(tipo)) {

                resultados.add(recurso);
            }
        }

        return resultados;
    } 
}
