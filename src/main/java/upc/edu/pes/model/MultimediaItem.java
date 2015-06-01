package upc.edu.pes.model;


public class MultimediaItem {

	private String id;
	private String type; // para indicar img, sound, video
	private byte[] content;  // Este tipo de dato Java (array of byte) creara en Postgres una columna BYTEA que sirve para almacenar imagenes

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}


}
