package funcionalidades;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import java.util.HashMap;
import java.util.Map;

public class GestionPrestamos {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos;
    private ArrayList<Prestamo> prestamos;
    private Map<String, ArrayList<Prestamo>> historial;
    private Map<String, ArrayList<Prestamo>> historialUsuarios;

    public GestionPrestamos(
            ArrayList<Usuario> usuarios,
            ArrayList<Recurso> recursos,
            ArrayList<Prestamo> prestamos) {

        this.usuarios = usuarios;
        this.recursos = recursos;
        this.prestamos = prestamos;
        this.historial = new HashMap<>();
        this.historialUsuarios = new HashMap<>();
    }

    // ==================== PRESTAMOS ====================

    // Prestar recurso
    public boolean prestarRecurso(String idUsuario, String idRecurso) {

        Usuario usuario = buscarUsuario(idUsuario);
        Recurso recurso = buscarRecurso(idRecurso);

        // Comprobar que el usuario y el recurso existen
        if (usuario == null || recurso == null) {
            return false;
        }

        // No permitir prestar un recurso que ya esté prestado
        if (!recurso.isEstado()) {
            return false;
        }

        Prestamo prestamo = new Prestamo(
                usuario,           // Quién lo pide
                recurso,           // Qué recurso piden
                LocalDate.now(),   // Préstamo a fecha de hoy
                true,              // Se activa el préstamo
                null               // Todavía no se ha devuelto
        );

        prestamos.add(prestamo); // Se añade el préstamo
        
        if (!historial.containsKey(idRecurso)) {
            historial.put(idRecurso, new ArrayList<>());
        }
        
        // Añadimos también el préstamo al historial del usuario
        if (!historialUsuarios.containsKey(idUsuario)) {
            historialUsuarios.put(idUsuario, new ArrayList<>());
        }

        historialUsuarios.get(idUsuario).add(prestamo);

        historial.get(idRecurso).add(prestamo);

        recurso.setEstado(false); // Al prestar un recurso debe quedar como no disponible

        return true;
    }
    
	public boolean modificarPrestamo(Prestamo prestamo, String idUsuario, String idRecurso) {

		Usuario nuevoUsuario = buscarUsuario(idUsuario);
		Recurso nuevoRecurso = buscarRecurso(idRecurso);

		if (prestamo == null || nuevoUsuario == null || nuevoRecurso == null) {
			return false;
		}

		Usuario usuarioAnterior = prestamo.getUsuario();
		Recurso recursoAnterior = prestamo.getRecurso();

		// Si se cambia el recurso, el nuevo debe estar disponible
		if (!recursoAnterior.getId().equals(idRecurso) && !nuevoRecurso.isEstado()) {

			return false;
		}

		// =========================
		// CAMBIAR USUARIO
		// =========================

		if (!usuarioAnterior.getId().equals(idUsuario)) {

			ArrayList<Prestamo> historialAnterior = historialUsuarios.get(usuarioAnterior.getId());

			if (historialAnterior != null) {
				historialAnterior.remove(prestamo);
			}

			historialUsuarios.computeIfAbsent(idUsuario, k -> new ArrayList<>()).add(prestamo);
		}

		// =========================
		// CAMBIAR RECURSO
		// =========================

		if (!recursoAnterior.getId().equals(idRecurso)) {

			ArrayList<Prestamo> historialAnterior = historial.get(recursoAnterior.getId());

			if (historialAnterior != null) {
				historialAnterior.remove(prestamo);
			}

			historial.computeIfAbsent(idRecurso, k -> new ArrayList<>()).add(prestamo);

			// Liberar recurso anterior
			recursoAnterior.setEstado(true);

			// Asignar nuevo recurso
			prestamo.setRecurso(nuevoRecurso);

			// Si el préstamo sigue activo, ocupar nuevo recurso
			if (prestamo.isEstadoPrestamo()) {
				nuevoRecurso.setEstado(false);
			}
		}

		// Cambiar usuario
		prestamo.setUsuario(nuevoUsuario);

		return true;
	}

    // Devolver recurso
    public boolean devolverRecurso(String idRecurso) {

        Recurso recurso = buscarRecurso(idRecurso);

        // Comprobar si existe
        if (recurso == null) {
            return false;
        }

        for (Prestamo prestamo : prestamos) {

            // El recurso de este préstamo es el que quieres devolver
            // y además sigue activo
            if (prestamo.getRecurso().getId().equals(idRecurso)
                    && prestamo.isEstadoPrestamo()) {

                prestamo.setEstadoPrestamo(false);
                prestamo.setFechaDevolucion(LocalDate.now());
                recurso.setEstado(true);

                return true;
            }
        }

        // El recurso existe, pero no tiene ningún préstamo activo
        return false;
    }

    // Préstamos de un usuario
    public ArrayList<Prestamo> prestamosUsuario(String idUsuario) {

        ArrayList<Prestamo> resultados = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getUsuario().getId().equals(idUsuario)) {

                resultados.add(prestamo);
            }
        }

        return resultados;
    }

    // Préstamos activos
    public ArrayList<Prestamo> prestamosActivos() {

        ArrayList<Prestamo> resultados = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {

            // Solo añadimos los que están activos
            if (prestamo.isEstadoPrestamo()) {

                resultados.add(prestamo);
            }
        }

        return resultados;
    }

    private Usuario buscarUsuario(String id) {

        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }

        return null;
    }

    private Recurso buscarRecurso(String id) {

        for (Recurso recurso : recursos) {
            if (recurso.getId().equals(id)) {
                return recurso;
            }
        }

        return null;
    }
    
    //listar los prestamos
    public ArrayList<Prestamo> listarPrestamos() {
        return prestamos;
    }
    
    //prestamos devueltos
    public ArrayList<Prestamo> prestamosDevueltos() {
        ArrayList<Prestamo> resultados = new ArrayList<>();
        for (Prestamo prestamo:prestamos) {
            if (!prestamo.isEstadoPrestamo()) {
                resultados.add(prestamo);
            }
        }
        return resultados;
    }
    
    // Devuelve el historial completo de préstamos organizado por recurso
    public Map<String, ArrayList<Prestamo>> getHistorialRecursos() {
        return historial;
    }

    // Devuelve el historial completo de préstamos organizado por usuario
    public Map<String, ArrayList<Prestamo>> getHistorialUsuarios() {
        return historialUsuarios;
    }
    
    // Devuelve el historial de préstamos de un recurso concreto
    public ArrayList<Prestamo> getHistorialRecurso(String idRecurso) {
        return historial.getOrDefault(idRecurso, new ArrayList<>());
    }

    // Devuelve el historial de préstamos de un usuario concreto
    public ArrayList<Prestamo> getHistorialUsuario(String idUsuario) {
        return historialUsuarios.getOrDefault(idUsuario, new ArrayList<>());
    }
    
    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }

    public ArrayList<Recurso> listarRecursos() {
        return recursos;
    }
}