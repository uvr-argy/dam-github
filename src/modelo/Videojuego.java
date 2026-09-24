package modelo;

import java.time.LocalDate;

public class Videojuego extends Recurso {

    private String plataforma;
    private int pegi;

    public Videojuego(String id, String titulo, LocalDate ano, boolean estado,
                      String plataforma, int pegi) {

        super(id, titulo, ano, estado);

        this.plataforma = plataforma;
        this.pegi = pegi;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }
}