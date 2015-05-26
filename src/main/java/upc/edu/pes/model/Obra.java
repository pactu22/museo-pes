package upc.edu.pes.model;

public class Obra {

	private Long id;
	private String titulo;
	private Autor autor;
	
	private Long beacon;
	private String informacion;
	private String estilo;
	private String coleccion;
	
	public Obra(){
		
	}
	public Obra(Long id, String titulo, Autor autor){
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
	}
	public Obra(String titulo, Autor autor, Long beacon, String informacion, String estilo){
		this.titulo = titulo;
		this.autor = autor;
		this.beacon = beacon;
		this.informacion = informacion;
		this.estilo = estilo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public Long getBeacon() {
		return beacon;
	}
	public void setBeacon(Long beacon) {
		this.beacon = beacon;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public String getColeccion() {
		return coleccion;
	}
	public void setColeccion(String coleccion) {
		this.coleccion = coleccion;
	}
	@Override
	public String toString() {
		return "Obra [id=" + id + ", titulo=" + titulo + ", autor=" + autor
				+ ", beacon=" + beacon + ", informacion=" + informacion
				+ ", estilo=" + estilo + ", coleccion=" + coleccion + "]";
	}

	
}
