package modelo;

import java.time.LocalDate;

public class Pelicula extends Recurso {

    private String director;
    private int duracion;

    public Pelicula(String id, String titulo, LocalDate ano, boolean estado,
                    String director, int duracion) {

        super(id, titulo, ano, estado);

        this.director = director;
        this.duracion = duracion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}