package upc.edu.pes.model;

public class Autor {

	private Long id;
	private String nombres;

	public Autor(Long id, String nombres){
		this.id = id;
		this.nombres = nombres;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombre(String nombre) {
		this.nombres = nombre;
	}
}
