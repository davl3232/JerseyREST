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
		Author david = new Author(2, "David", "Villamizar Lizcano");
		Author sergio = new Author(3, "Sergio", "Forero Gómez");
		Author sebas = new Author(4, "Sebastian", "Bobadilla");
		Article article1 = new Article(1, "Titulo del articulaso");
		Article article2 = new Article(2, "Titulo del articulaso");
		Article article3 = new Article(3, "Titulo del articulaso");
		Article article4 = new Article(4, "Titulo del articulaso");
		article1.addAuthor(juan);
		article1.addAuthor(david);
		article2.addAuthor(juan);
		article2.addAuthor(david);
		article2.addAuthor(sergio);
		article3.addAuthor(sebas);
		article4.addAuthor(juan);
		article4.addAuthor(david);
		article4.addAuthor(sebas);
		article4.addAuthor(sergio);
		return article4;
	}
	
	@POST
	@Path("/fibbonaci/{n}") // http://localhost:8080/class/fibbonaci/13
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Fibbonaci fibbonaci(@PathParam("n") int n) {
		Fibbonaci sequence = new Fibbonaci(n);
		return sequence;
	}
}
