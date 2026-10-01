package modelo;

import java.time.LocalDate;

public class Prestamo {

	private Usuario usuario;
	private Recurso recurso;
	private LocalDate fechaPrestamo;
	private boolean estadoPrestamo;
	private LocalDate fechaDevolucion;

	public Prestamo(Usuario usuario, Recurso recurso, LocalDate fechaPrestamo, boolean estadoPrestamo,
			LocalDate fechaDevolucion) {
		this.usuario = usuario;
		this.recurso = recurso;
		this.fechaPrestamo = fechaPrestamo;
		this.estadoPrestamo = estadoPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public boolean isEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(boolean estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public String toString() {
		return "Prestamo [usuario=" + usuario + ", recurso=" + recurso + ", fechaPrestamo=" + fechaPrestamo
				+ ", estadoPrestamo=" + estadoPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
	}
}
