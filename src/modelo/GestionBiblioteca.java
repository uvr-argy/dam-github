package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionBiblioteca {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Recurso> recursos;
    private ArrayList<Prestamo> prestamos;

    public GestionBiblioteca() {
        usuarios = new ArrayList<>();
        recursos = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    // ==================== RECURSOS ====================
    
    // Crear recurso
    public boolean crearRecurso(Recurso recurso){
    	
    	//Para evitar ids duplicados
    	if(buscarRecurso(recurso.getId()) != null) {
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
    	
    	//busca recurso por id
    	for(Recurso recurso : recursos) {
    		if(recurso.getId().equals(id)) {
    			return recurso;
    		}
    	}
    	//si no encuentra devuelve null
    	return null;
    }
    
    // Modificar recurso
    public boolean modificarRecurso(String id, String titulo, LocalDate ano) {
    	
    	Recurso recurso = buscarRecurso(id);
    	
    	if(recurso == null) {
    	
    		return false;
    	}
    	
    	//No modifico el id por que lo usamos para identificar al recurso
    	recurso.setTitulo(titulo);
    	
    	recurso.setAno(ano);
    	
    	return true;
    }
    
    // Eliminar recurso
    public boolean eliminarRecurso(String id) {

    	Recurso recurso = buscarRecurso(id);
    	
    	if(recurso == null) {
    	
    		return false;
    	}
    	
    	recursos.remove(recurso);
    	
    	return true;
    }
    
    // Consultar disponibilidad
    public boolean disponibilidad(String id) {
    	
    	Recurso recurso = buscarRecurso(id);
    	
    	if(recurso == null) {
    	
    		return false;
    	}
    	
    	//atributo estado de la clase recurso
    	return recurso.isEstado();
    }
    
    // ==================== USUARIOS ====================» 
    
    // Crear usuario
    public boolean crearUsuario(Usuario usuario){
    	
    	//Para evitar ids duplicados
    	if(buscarUsuario(usuario.getId()) != null) {
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
    	
    	for(Usuario usuario : usuarios) {
    		if(usuario.getId().equals(id)) {
    			return usuario;
    		}
    	}
    	
    	return null;
    }
    
    // Modificar usuario
    public boolean modificarUsuario(String id, String nombre, String email) {
    	
    	 Usuario usuario = buscarUsuario(id);
    	
    	if(usuario == null) {
    	
    		return false;
    	}
    	
    	//No modifico el id por que lo usamos para identificar al usuario
    	usuario.setNombre(nombre);
    	
    	usuario.setEmail(email);
    	
    	return true;
    }
    
    // Eliminar recurso
    public boolean eliminarUsuario(String id) {

    	Usuario usuario = buscarUsuario(id);
    	
    	if(usuario == null) {
    	
    		return false;
    	}
    	
    	usuarios.remove(usuario);
    	
    	return true;
    }
    
    // ==================== PRESTAMOS ====================»
    
    //Prestar recurso
    public boolean prestarRecurso(String idUsuario, String idRecurso) {
    	
    	Usuario usuario = buscarUsuario(idUsuario);
    	Recurso recurso = buscarRecurso(idRecurso);
    	
    	//Comprobar que el usuario y el recurso existen
    	if(usuario == null || recurso == null) {
    		return false;
    	}
    	
    	//No permitir prestar un recurso que ya esté prestado
    	if(!recurso.isEstado()) {
    		return false;
    	}
    	
    	Prestamo prestamo = new Prestamo(
    			usuario, //Quien lo pide
    			recurso, //Que recurso piden
    			LocalDate.now(), //Prestamo a fecha de hoy
    			true, //Se activa el prestamo
    			null //Todavia no se ha devuelto		
    	);
    			
    	prestamos.add(prestamo); //Se añade el prestamo
    	
    	recurso.setEstado(false); //Al prestar un recurso debe quedar como no disponible
    	
    	return true;
    }
    
    //Devolver recurso
    public boolean devolverRecurso(String idRecurso) {
    	
    	Recurso recurso = buscarRecurso(idRecurso);
    	 
    	//Comprobar si existe
    	if(recurso == null) {
    		return false;
    	}
    	
    	for(Prestamo prestamo : prestamos) {
    		
    		// el recurso de este prestamo es el que quieres devolver y ademas sigue disponible
    		if(prestamo.getRecurso().getId().equals(idRecurso)
    			&& prestamo.isEstadoPrestamo()){
    				
    			prestamo.setEstadoPrestamo(false); //el recurso ya no esta prestado
    			prestamo.setFechaDevolucion(LocalDate.now()); //ponemos la fecha de hoy como devolucion
    			
    			recurso.setEstado(true); //el recurso vuelve a estar disponible
    			
    			}
    	}
    	return true;
    }
}