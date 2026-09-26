package controlador;

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

    // Crear usuario
    public boolean crearUsuario(Usuario usuario) {

        // Para evitar ids duplicados
        if (buscarUsuario(usuario.getId()) != null) {
            return false;
        }

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

    // Eliminar usuario
    public boolean eliminarUsuario(String id) {

        Usuario usuario = buscarUsuario(id);

        if (usuario == null) {

            return false;
        }

        usuarios.remove(usuario);

        return true;
    }

    // Usuarios que nunca han realizado un préstamo
    public ArrayList<Usuario> usuarioSinPrestamos() {

        ArrayList<Usuario> resultados = new ArrayList<>();

        for (Usuario usuario : usuarios) {

            boolean tienePrestamo = false;

            for (Prestamo prestamo : prestamos) {

                // Buscamos al usuario
                if (prestamo.getUsuario().getId().equals(usuario.getId())) {

                    // Si tiene al menos un préstamo dejamos de buscar
                    tienePrestamo = true;
                    break;
                }
            }

            // Si no tiene préstamos añadimos a los resultados
            if (!tienePrestamo) {
                resultados.add(usuario);
            }
        }

        return resultados;
    }
}