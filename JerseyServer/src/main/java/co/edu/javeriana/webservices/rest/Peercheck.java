package co.edu.javeriana.webservices.rest;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Peercheck {
	
	@XmlElement(name = "article")
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
	
	public Article findArticle(int id) {
		for (int i = 0; i < this.articles.size(); i++) {
			Article article = this.articles.get(i);
			if (article.getId() == id) { return article; }
		}
		
		return null;
	}
	
	public Article removeArticle(int id) {
		Article article = this.findArticle(id);
		
		if (article != null) {
			this.articles.remove(article);
		}
		
		return article;
	}
	
	public Article updateArticle(int id, Article article) {
		Article articleFounded = this.findArticle(id);
		
		if (articleFounded != null) {
			articleFounded.setTitle(article.getTitle());
			articleFounded.setId(article.getId());
			articleFounded.setAuthors(article.getAuthors());
		}
		return articleFounded;
	}
}
