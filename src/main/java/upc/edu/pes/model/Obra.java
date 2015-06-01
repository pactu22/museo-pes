package upc.edu.pes.model;

import org.springframework.web.multipart.MultipartFile;

public class Obra {

	private Long id;
	private String titulo;
	private Autor autor;
	
	private Long beacon;
	private String informacion;
	private String estilo;
	private String coleccion;
	
	// Un campo extra para facilitar el manejo de id el autor
	private Long autorId;
	
	private MultipartFile item1;
	private MultipartFile item2;
	private MultipartFile item3;
	
	
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
	
	
	public Long getAutorId() {
		return autorId;
	}
	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	
	public MultipartFile getItem1() {
		return item1;
	}
	public void setItem1(MultipartFile item1) {
		this.item1 = item1;
	}
	public MultipartFile getItem2() {
		return item2;
	}
	public void setItem2(MultipartFile item2) {
		this.item2 = item2;
	}
	public MultipartFile getItem3() {
		return item3;
	}
	public void setItem3(MultipartFile item3) {
		this.item3 = item3;
	}
	
	@Override
	public String toString() {
		return "Obra [id=" + id + ", titulo=" + titulo + ", autor=" + autor
				+ ", beacon=" + beacon + ", informacion=" + informacion
				+ ", estilo=" + estilo + ", coleccion=" + coleccion + "]";
	}

	
}
