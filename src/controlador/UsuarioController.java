package controlador;

import java.util.List;
import modelo.Usuario;
import funciones.FuncionesUsuario;

public class UsuarioController {

    private FuncionesUsuario funcionesUsuario;

    public UsuarioController(FuncionesUsuario funcionesUsuario) {
        this.funcionesUsuario = funcionesUsuario;
    }

    //crear usuario
    public boolean crearUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        return funcionesUsuario.crearUsuario(usuario);
    }

    //listar usuarios
    public List<Usuario> listarUsuarios() {
        return funcionesUsuario.listarUsuarios();
    }

    //buscar por id
    public Usuario buscarUsuario(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return funcionesUsuario.buscarUsuario(id);
    }

    //editar usuario
    public boolean modificarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        return funcionesUsuario.modificarUsuario(usuario);
    }

    //borrar usuario
    public boolean eliminarUsuario(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return funcionesUsuario.eliminarUsuario(id);
    }

    //comprobar si existe
    public boolean existeUsuario(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return funcionesUsuario.existeUsuario(id);
    }
}