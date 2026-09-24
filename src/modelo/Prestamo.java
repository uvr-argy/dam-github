package modelo;

import java.time.LocalDate;

public class Prestamo {

    private Usuario usuario;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private boolean estado;
    private LocalDate fechaDevolucion;

    public Prestamo(Usuario usuario, Recurso recurso,
                    LocalDate fechaPrestamo, boolean estado,
                    LocalDate fechaDevolucion) {

        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.estado = estado;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
    public String toString() {
        return "Prestamo [usuario=" + usuario
                + ", recurso=" + recurso
                + ", fechaPrestamo=" + fechaPrestamo
                + ", estado=" + estado
                + ", fechaDevolucion=" + fechaDevolucion + "]";
    }
}