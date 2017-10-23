package co.edu.javeriana.webservices.rest;

import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Peercheck {
	
	@XmlElement(name = "article", required = true)
	private Vector<Article> articles;
	
	public Vector<Article> getArticles() {
		return this.articles;
	}
	
	public Peercheck() {
		this.articles = new Vector<>();
	}
	
	public void addArticle(String title) {
		int id = this.articles.size();
		Article article = new Article(id, title);
		this.addArticle(article);
	}
	
	public void addArticle(Article article) {
		this.articles.add(article);
	}
	
	public void addAuthorToArticle(int articleIndex, long id, String name, String surname) {
		Article article = this.articles.get(articleIndex);
		article.addAuthor(id, name, surname);
		this.articles.set(articleIndex, article);
	}
	
	public void addAuthorToArticle(int articleIndex, Author author) {
		Article article = this.articles.get(articleIndex);
		article.addAuthor(author);
		this.articles.set(articleIndex, article);
	}
}
