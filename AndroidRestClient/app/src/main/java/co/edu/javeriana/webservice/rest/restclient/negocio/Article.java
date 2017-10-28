package co.edu.javeriana.webservice.rest.restclient.negocio;

import java.util.Vector;

public class Article {

	private long id;

	private String title;
	
	private Vector<Author> authors;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Vector<Author> getAuthors() {
		return this.authors;
	}
	
	public void setAuthors(Vector<Author> authors) {
		this.authors = authors;
	}
	
	public Article() {
		this.id = 0;
		this.title = "";
		this.authors = new Vector<>();
	}
	
	public Article(long id, String title) {
		this.id = id;
		this.title = title;
		this.authors = new Vector<>();
	}
	
	public void addAuthor(long id, String name, String surname) {
		Author author = new Author(id, name, surname);
		this.addAuthor(author);
	}
	
	public void addAuthor(Author author) {
		this.authors.add(author);
	}
}
