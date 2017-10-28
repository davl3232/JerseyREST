package co.edu.javeriana.webservices.rest;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Article {
	
	@XmlAttribute(required = true)
	private long id;
	
	@XmlElement(required = true)
	private String title;
	
	@XmlElementWrapper(name = "authors", required = true)
	@XmlElement(name = "author")
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
