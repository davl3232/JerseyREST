package co.edu.javeriana.webservices.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("class")
public class MyResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}
	
	@GET
	@Path("/saludar") // http://localhost:8080/class/saludar?name=juan
	@Produces(MediaType.TEXT_PLAIN)
	public String saludarQueryParam(@QueryParam("name") String name) {
		return "Hola " + name;
	}
	
	@GET
	@Path("/saludar/{name}") // http://localhost:8080/class/saludar/juan
	@Produces(MediaType.TEXT_PLAIN)
	public String saludarPathParam(@PathParam("name") String name) {
		return "Hola " + name;
	}
	
	@GET
	@Path("/user") // http://localhost:8080/class/user?name=juan
	@Produces(MediaType.TEXT_HTML)
	public String welcomeName(@QueryParam("name") String name) {
		String html = "<html>" + "<head>" + "<title>Rest Server</title>" + "</head>" + "<body>" + "<h1>Bienvenido " + name + "</h1>" + "</body>" + "</html>";
		return html;
	}
	
	@GET
	@Path("/multiplication/{a}") // http://localhost:8080/class/multiplication/3?b=5
	@Produces(MediaType.TEXT_PLAIN)
	public int multiply(@PathParam("a") int a, @QueryParam("b") int b) {
		return a * b;
	}
	
	@GET
	@Path("/article") // http://localhost:8080/class/article
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Article getArticle() {
		Author juan = new Author(1, "Juan Manuel", "Sánchez Lozano");
		Author david = new Author(1, "David", "Villamizar Lizcano");
		Article article = new Article(1, "Titulo del articulaso");
		article.addAuthor(juan);
		article.addAuthor(david);
		return article;
	}
	
	@POST
	@Path("/fibbonaci/{n}") // http://localhost:8080/class/fibbonaci/13
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Fibbonaci fibbonaci(@PathParam("n") int n) {
		Fibbonaci sequence = new Fibbonaci(n);
		return sequence;
	}
}
