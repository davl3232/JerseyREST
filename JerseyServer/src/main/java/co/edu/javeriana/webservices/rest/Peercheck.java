package co.edu.javeriana.webservices.rest;

import java.util.Vector;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import co.edu.javeriana.webservices.jaxb.JaxbReader;
import co.edu.javeriana.webservices.jaxb.JaxbWriter;
import co.edu.javeriana.webservices.resources.Resources;

@Path("Peercheck")
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
	
	public Article renameArticle(int id, String name) {
		Article article = this.findArticle(id);
		
		if (article != null) {
			article.setTitle(name);
		}
		return article;
	}
	
	@GET
	@Path("/articles") // http://localhost:8080/Peercheck/articles
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Peercheck findAllArticles() {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		System.out.println(articles + "\n" + schema);
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		return peercheck;
	}
	
	@POST
	@Path("/article/{id}") // http://localhost:8080/class/Peercheck/2?id=4&name=Articulaso
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article createArticle(@QueryParam("id") int idArticle, @QueryParam("name") String name) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		System.out.println(articles + "\n" + schema);
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		Article article = peercheck.createArticle(idArticle, name);
		JaxbWriter.writeAndValid(peercheck, articles, schema);
		
		return article;
	}
	
	@GET
	@Path("/article/{id}") // http://localhost:8080/Peercheck/article/2
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article getArticle(@PathParam("id") int idArticle) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		System.out.println(articles + "\n" + schema);
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		Article article = peercheck.findArticle(idArticle);
		
		return article;
	}
	
	@PUT
	@Path("/article") // http://localhost:8080/Peercheck/article?name=perros_feos
	public void updateArticle(@QueryParam("name") String name) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		System.out.println(articles + "\n" + schema);
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		peercheck.addArticle(name);
		JaxbWriter.writeAndValid(peercheck, articles, schema);
	}
	
	@DELETE
	@Path("/article/{id}") // http://localhost:8080/Peercheck/article/2
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article deleteArticle(@PathParam("id") int idArticle) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		System.out.println(articles + "\n" + schema);
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		Article article = peercheck.removeArticle(idArticle);
		JaxbWriter.writeAndValid(peercheck, articles, schema);
		
		return article;
	}
}
