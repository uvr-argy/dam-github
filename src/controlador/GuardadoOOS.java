package controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;

public class GuardadoOOS {

	private static final String ARCHIVO = "datos/biblioteca.dat";

    private GuardadoOOS() {
    }

    public static void guardar(
            Recurso[] recursos,
            Usuario[] usuarios,
            Prestamo[] prestamos)
            throws IOException {

        DatosBiblioteca datos =
                new DatosBiblioteca(
                    recursos,
                    usuarios,
                    prestamos
                );

        try (ObjectOutputStream oos =
                new ObjectOutputStream(
                    new FileOutputStream(ARCHIVO))) {

            oos.writeObject(datos);
        }
    }

    public static DatosBiblioteca cargar()
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream(ARCHIVO))) {

            return (DatosBiblioteca) ois.readObject();
        }
    }
}