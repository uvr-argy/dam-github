package modelo;

import java.time.LocalDate;

public class Recurso {

	private String id;
	private String titulo;
	private LocalDate ano;
	private boolean estado;
	
	
	public Recurso(String id, String titulo, LocalDate ano, boolean estado) {
		this.id = id;
		this.titulo = titulo;
		this.ano = ano;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getAno() {
		return ano;
	}

	public void setAno(LocalDate ano) {
		this.ano = ano;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Recurso [id=" + id + ", titulo=" + titulo + ", ano=" + ano + ", estado=" + estado + "]";
	}
	
	
	
}
