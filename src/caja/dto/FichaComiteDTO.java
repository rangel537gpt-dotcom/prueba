package caja.dto;

public class FichaComiteDTO {
	private int id;
	private FichaDatosDTO fichaDatos;
	private String comite;
	private String borrado; // Getters y Setters

	public FichaComiteDTO() {
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

	public String getComite() {
		return comite;
	}

	public void setComite(String comite) {
		this.comite = comite;
	}

	public String getBorrado() {
		return borrado;
	}

	public void setBorrado(String borrado) {
		this.borrado = borrado;
	}

}
