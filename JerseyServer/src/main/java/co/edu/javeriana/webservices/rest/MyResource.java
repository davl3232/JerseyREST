package co.edu.javeriana.webservices.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.javeriana.webservices.jaxb.JaxbReader;
import co.edu.javeriana.webservices.jaxb.JaxbWriter;
import co.edu.javeriana.webservices.resources.Resources;

@Path("class")
public class MyResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}
	
	@GET
	@Path("/saludar")
	@Produces(MediaType.TEXT_PLAIN)
	public String saludarQueryParam(@QueryParam("name") String name) {
		return "Hola " + name;
	}
	
	@GET
	@Path("/saludar/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String saludarPathParam(@PathParam("name") String name) {
		return "Hola " + name;
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.TEXT_HTML)
	public String welcomeName(@QueryParam("name") String name) {
		String html = "<html>" + "<head>" + "<title>Rest Server</title>" + "</head>" + "<body>" + "<h1>Bienvenido " + name + "</h1>" + "</body>" + "</html>";
		return html;
	}
	
	@GET
	@Path("/multiplication/{a}")
	@Produces(MediaType.TEXT_PLAIN)
	public int multiply(@PathParam("a") int a, @QueryParam("b") int b) {
		return a * b;
	}
	
	@POST
	@Path("/fibbonaci/{n}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Fibbonaci fibbonaci(@PathParam("n") int n) {
		Fibbonaci sequence = new Fibbonaci(n);
		return sequence;
	}
	
	@GET
	@Path("/fecha")
	@Produces(MediaType.APPLICATION_JSON)
	public Date getFecha() {
		return new Date();
	}
	
	@GET
	@Path("/articles")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Peercheck findAllArticles() {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		return peercheck;
	}
	
	@POST
	@Path("/article")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article postArticle(Article article) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		peercheck.addArticle(article);
		JaxbWriter.writeAndValid(peercheck, articles, schema);
		
		return article;
	}
	
	@GET
	@Path("/article/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article getArticle(@PathParam("id") int idArticle) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		Article article = peercheck.findArticle(idArticle);
		
		return article;
	}
	
	@PUT
	@Path("/article/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article updateArticle(@PathParam("id") int id, Article article) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		Article articleModified = peercheck.updateArticle(id, article);
		JaxbWriter.writeAndValid(peercheck, articles, schema);
		
		return articleModified;
	}
	
	@DELETE
	@Path("/article/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article deleteArticle(@PathParam("id") int idArticle) {
		String articles = Resources.getArticles().getFile();
		String schema = Resources.getArticlesSchema().getFile();
		
		Peercheck peercheck = (Peercheck) JaxbReader.readAndValid(Peercheck.class, articles, schema);
		
		Article article = peercheck.removeArticle(idArticle);
		JaxbWriter.writeAndValid(peercheck, articles, schema);
		
		return article;
	}
}
