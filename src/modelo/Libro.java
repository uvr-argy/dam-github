package modelo;

import java.time.LocalDate;

public class Libro extends Recurso {

	private String autor;
	private int paginas;
	
	public Libro(String id, String titulo, LocalDate ano, boolean estado, String autor, int paginas) {
		super(id, titulo, ano, estado);
		this.autor = autor;
		this.paginas = paginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		return "Libro [autor=" + autor + ", paginas=" + paginas + "]";
	}

	

	

}
