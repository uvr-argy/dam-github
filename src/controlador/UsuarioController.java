package controlador;

import java.util.ArrayList;
import funcionalidades.GestionUsuarios;
import modelo.Usuario;

public class UsuarioController {

    private GestionUsuarios gestionUsuarios;

    public UsuarioController(GestionUsuarios gestionUsuarios) {
        this.gestionUsuarios = gestionUsuarios;
    }

    //crearUsuario
    public boolean crearUsuario(Usuario usuario) {
        if (usuario==null) {
            return false;
        }
        if (usuario.getId()==null || usuario.getId().isBlank()) {
            return false;
        }
        if (usuario.getNombre()==null || usuario.getNombre().isBlank()) {
            return false;
        }
        if (usuario.getEmail()==null || usuario.getEmail().isBlank()) {
            return false;
        }
        return gestionUsuarios.crearUsuario(usuario);
    }

    //listarUsuarios
    public ArrayList<Usuario> listarUsuarios() {
        return gestionUsuarios.listarUsuarios();
    }

    //buscarUsuario
    public Usuario buscarUsuario(String id) {
        if (id==null || id.isBlank()) {
            return null;
        }
        return gestionUsuarios.buscarUsuario(id);
    }

    //modificarUsuario
    public boolean modificarUsuario(String id, String nombre, String email) {

        if (id==null || id.isBlank()) {
            return false;
        }
        if (nombre==null || nombre.isBlank()) {
            return false;
        }
        if (email==null || email.isBlank()) {
            return false;
        }
        return gestionUsuarios.modificarUsuario(id, nombre, email);
    }

    //eliminarUsuario
    public boolean eliminarUsuario(String id) {
        if (id==null || id.isBlank()) {
            return false;
        }
        return gestionUsuarios.eliminarUsuario(id);
    }

    //eliminarUsuario
    public ArrayList<Usuario> usuariosSinPrestamos() {
        return gestionUsuarios.usuarioSinPrestamos();
    }

    //buscarUsuarioPorEmail
    public Usuario buscarUsuarioPorEmail(String email) {
        if (email==null || email.isBlank()) {
            return null;
        }
        return gestionUsuarios.buscarUsuarioPorEmail(email);
    }

    //usuariosConPrestamosActivos
    public ArrayList<Usuario> usuariosConPrestamosActivos() {
        return gestionUsuarios.usuariosConPrestamosActivos();
    }
}