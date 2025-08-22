package caja.dto;

public class FichaInstitucionDTO {
	private int id;
	private FichaDatosDTO fichaDatos;
	private String nombre;
	private String fechaIngreso;
	private String borrado; // Getters y Setters

	public FichaInstitucionDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FichaDatosDTO getFichaDatos() {
		return fichaDatos;
	}

	public void setFichaDatos(FichaDatosDTO fichaDatos) {
		this.fichaDatos = fichaDatos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getBorrado() {
		return borrado;
	}

	public void setBorrado(String borrado) {
		this.borrado = borrado;
	}

}
