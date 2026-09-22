package modelo;

import java.time.LocalDate;

public class Videojuego extends Recurso {

	private String plataforma;
	private int PEGI;
	
	public Videojuego(String id, String titulo, LocalDate ano, boolean estado, String plataforma, int pEGI) {
		super(id, titulo, ano, estado);
		this.plataforma = plataforma;
		PEGI = pEGI;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getPEGI() {
		return PEGI;
	}

	public void setPEGI(int pEGI) {
		PEGI = pEGI;
	}

	@Override
	public String toString() {
		return "Videojuego [plataforma=" + plataforma + ", PEGI=" + PEGI + "]";
	}
	
	
	
}
