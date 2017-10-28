package co.edu.javeriana.ws.rest.client;

import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import co.edu.javeriana.ws.rest.model.Article;
import co.edu.javeriana.ws.rest.model.Author;
import co.edu.javeriana.ws.rest.model.Fibbonaci;
import co.edu.javeriana.ws.rest.model.Peercheck;
import java.util.Vector;

public class RestClientMain {
	public static final String MY_SERVER_URL = "http://localhost:8080";
	public static WebTarget baseWebTarget;
	public static Client client;
	
	public static void init() {
		client = ClientBuilder.newClient();
		client.register(JacksonJaxbJsonProvider.class);
		baseWebTarget = client.target(MY_SERVER_URL);
	}
	
	public static void main(String args[]) {
		init();
		
		getFibonacciJSON(10); // http://localhost:8080/class/fibbonaci/{n}
		
		getFibonacciXML(10); // http://localhost:8080/class/fibbonaci/{n}
		
		getAllArticles(); // http://localhost:8080/class/articles
		
		deleteArticle(1); // http://localhost:8080/class/article/{id}
		getAllArticles(); // http://localhost:8080/class/articles
		
		Author aut = new Author(5, "Carlos", "Parra");
		Article art = getArticle(2);
		if (art != null) {
			art.setTitle("Nuevo título");
			art.getAuthors().add(aut);
			updateArticle(2, art); // http://localhost:8080/class/article/{id}
		}
		
		art = new Article(4, "Nuevo artículo");
		art.getAuthors().add(aut);
		createArticle(art); // http://localhost:8080/class/article/{id}
	}

	public static void createArticle(Article article) {
		// http://localhost:8080/class/article
		WebTarget webTarget = baseWebTarget.path("class/article");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.post(Entity.xml(article));
		System.out
			.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
	}

	public static void updateArticle(long id, Article article) {
		// http://localhost:8080/class/article/{id}
		WebTarget webTarget = baseWebTarget.path("class/article/" + id);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.put(Entity.xml(article));
		System.out
			.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
	}

	public static Article getArticle(long id) {
		// http://localhost:8080/class/article/{id}
		WebTarget webTarget = baseWebTarget.path("class/article/" + id);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();

		System.out
			.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		if (response.getLength() > 0) {
			System.out.println("Media type: " + response.getMediaType().toString());
			Article res = response.readEntity(Article.class);
			System.out.println("Content: " + res);
			return res;
		} else {
			return null;
		}
		
	}

	public static void deleteArticle(long id) {
		// http://localhost:8080/class/article/{id}
		WebTarget webTarget = baseWebTarget.path("class/article/" + id);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.delete();
		System.out
				.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
	}

	public static Vector<Article> getAllArticles() {
		// http://localhost:8080/class/articles
		WebTarget webTarget = baseWebTarget.path("class/articles");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		System.out
				.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		Peercheck res = response.readEntity(Peercheck.class);
		System.out.println("Content: " + res);
		
		return res.getArticles();
	}
	
	public static String getFibonacciXML(int n) {
		// http://localhost:8080/class/fibbonaci/{n}
		WebTarget webTarget = baseWebTarget.path("class/fibbonaci/" + n);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		System.out
				.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		String respuestaTexto = response.readEntity(String.class);
		System.out.println("Content: " + respuestaTexto);
		
		return respuestaTexto;
	}
	
	public static String getFibonacciJSON(int n) {
		// http://localhost:8080/class/fibbonaci/{n}
		WebTarget webTarget = baseWebTarget.path("class/fibbonaci/" + n);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out
				.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		String respuestaTexto = response.readEntity(String.class);
		System.out.println("Content: " + respuestaTexto);
		
		return respuestaTexto;
	}
	
	public static Fibbonaci getFibonacciLista(int n) {
		// http://localhost:8080/class/fibbonaci/{n}
		WebTarget webTarget = baseWebTarget.path("class/fibbonaci/" + n);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		System.out
				.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		Fibbonaci res = response.readEntity(Fibbonaci.class);
		System.out.println("Content: " + res);
		
		return res;
	}
}
