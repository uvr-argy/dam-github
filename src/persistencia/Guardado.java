package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;

public class Guardado {
	
	private static final String ARCHIVO = "datos/biblioteca.txt";
	
	private static ArrayList<Recurso> recursos = new ArrayList<>();
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<Prestamo> prestamos = new ArrayList<>();
	
	//TODO funciones para revisar si el archivo existe y para crear el archivo
	public static boolean guardar(ArrayList<Recurso> r, ArrayList<Usuario> u, ArrayList<Prestamo> p) {
		
		Recurso[] recursos = new Recurso[r.size()];
		Usuario[] usuarios = new Usuario[u.size()];
		Prestamo[] prestamos = new Prestamo[p.size()];
		
		for (int i = 0; i < recursos.length; i++) {
			recursos[i] = r.get(i);
		}
		
		for (int i = 0; i < usuarios.length; i++) {
			usuarios[i] = u.get(i);
		}
		
		for (int i = 0; i < prestamos.length; i++) {
			prestamos[i] = p.get(i);
		}
		
		return guardar(recursos, usuarios, prestamos);
	}

    public static boolean guardar(Recurso[] recursos, Usuario[] usuarios, Prestamo[] prestamos) {
    	
        DatosBiblioteca datos = new DatosBiblioteca(recursos, usuarios, prestamos);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(datos);
        } catch (IOException e) {
        	return false;
        }
        return true;
    }

    public static void cargar() {
    	
    	DatosBiblioteca datos;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            datos = (DatosBiblioteca) ois.readObject();
            
            recursos.addAll(Arrays.asList(datos.getRecursos()));
            usuarios.addAll(Arrays.asList(datos.getUsuarios()));
            prestamos.addAll(Arrays.asList(datos.getPrestamos()));
            
        } catch (IOException e) {
        	return;
        } catch (ClassNotFoundException e) {
        	return;
        }
    }
    
    public static ArrayList<Recurso> getRecursos() {
    	return recursos;
    }
    
    public static ArrayList<Usuario> getUsuarios() {
    	return usuarios;
    }
    
    public static ArrayList<Prestamo> getPrestamos() {
    	return prestamos;
    }
}