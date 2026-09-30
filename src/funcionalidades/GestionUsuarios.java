package funcionalidades;

import java.util.ArrayList;

import modelo.Prestamo;
import modelo.Usuario;

public class GestionUsuarios {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    public GestionUsuarios(ArrayList<Usuario> usuarios, ArrayList<Prestamo> prestamos) {
        this.usuarios = usuarios;
        this.prestamos = prestamos;
    }

    // ==================== USUARIOS ====================

    public boolean crearUsuario(Usuario usuario) {

        int siguienteId = usuarios.size() + 1;
        String id = String.format("U%02d", siguienteId);

        while (buscarUsuario(id) != null) {
            siguienteId++;
            id = String.format("U%02d", siguienteId);
        }

        usuario.setId(id);
        usuarios.add(usuario);

        return true;
    }

    // Listar usuarios
    public ArrayList<Usuario> listarUsuarios() {

        return usuarios;

    }

    // Buscar usuario
    public Usuario buscarUsuario(String id) {

        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }

        return null;
    }

    // Modificar usuario
    public boolean modificarUsuario(String id, String nombre, String email) {

        Usuario usuario = buscarUsuario(id);

        if (usuario == null) {

            return false;
        }

        // No modifico el id porque lo usamos para identificar al usuario
        usuario.setNombre(nombre);

        usuario.setEmail(email);

        return true;
    }

    public boolean eliminarUsuario(String id) {

        Usuario usuario = buscarUsuario(id);

        if (usuario == null) {

            return false;
        }

        // Comprobar si el usuario tiene algún préstamo activo
        for (Prestamo prestamo : prestamos) {

            if (prestamo.getUsuario().getId().equals(id)
                    && prestamo.isEstadoPrestamo()) {

                return false;
            }
        }

        usuarios.remove(usuario);

        return true;
    }
}