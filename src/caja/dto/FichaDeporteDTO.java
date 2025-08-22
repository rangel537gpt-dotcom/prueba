package caja.dto;

public class FichaDeporteDTO {
	private int id;
	private DeporteDTO deporteDTO;
	private FichaDatosDTO fichaDatos;
	private String borrado;

	public FichaDeporteDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DeporteDTO getDeporteDTO() {
		return deporteDTO;
	}

	public void setDeporteDTO(DeporteDTO deporte) {
		this.deporteDTO = deporte;
	}

	public FichaDatosDTO getFichaDatos() {
		return fichaDatos;
	}

	public void setFichaDatos(FichaDatosDTO fichaDatos) {
		this.fichaDatos = fichaDatos;
	}

	public String getBorrado() {
		return borrado;
	}

	public void setBorrado(String borrado) {
		this.borrado = borrado;
	}

}
