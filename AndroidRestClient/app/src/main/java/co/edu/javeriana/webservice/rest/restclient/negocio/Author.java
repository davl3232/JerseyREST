package co.edu.javeriana.webservice.rest.restclient.negocio;

public class Author {

	private long id;

	private String name;

	private String surname;
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Author() {
		this.id = 0;
		this.name = "";
		this.surname = "";
	}
	
	public Author(long id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
}
