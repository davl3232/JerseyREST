package co.edu.javeriana.webservices.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {
	
	private String title;
	private String author;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Article() {
		this.title = "";
		this.author = "";
	}

	public Article(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", author=" + author + "]";
	}
}
