package co.edu.javeriana.webservices.rest;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {
	
	@XmlAttribute(required = true)
	private long id;
	
	@XmlElement(required = true)
	private String title;
	
	@XmlElementWrapper(name = "authors", required = true)
	@XmlElement(name = "author", required = true)
	private Vector<Author> authors;
	
	public long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Vector<Author> getAuthors() {
		return this.authors;
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
	
	public void addAuthor(Author author) {
		this.authors.add(author);
	}
}
