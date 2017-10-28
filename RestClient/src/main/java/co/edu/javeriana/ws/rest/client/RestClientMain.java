package co.edu.javeriana.ws.rest.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import co.edu.javeriana.ws.rest.model.Article;
import co.edu.javeriana.ws.rest.model.Author;
import co.edu.javeriana.ws.rest.model.Fibbonaci;
import co.edu.javeriana.ws.rest.model.Peercheck;

public class RestClientMain {
	
	private static InetAddress myIP = null;
	public static String MY_SERVER_URL = "http://localhost:8080";
	public static WebTarget baseWebTarget;
	public static Client client;
	
	static {
		try {
			RestClientMain.myIP = InetAddress.getLocalHost();
			RestClientMain.MY_SERVER_URL = "http://" + RestClientMain.myIP.getHostAddress() + ":8080";
			System.out.println(RestClientMain.MY_SERVER_URL);
		}
		catch (UnknownHostException event) {
			System.out.println("Error: [" + event.getMessage() + "]");
		}
	}
	
	public static void init() {
		RestClientMain.client = ClientBuilder.newClient();
		RestClientMain.client.register(JacksonJaxbJsonProvider.class);
		RestClientMain.baseWebTarget = RestClientMain.client.target(RestClientMain.MY_SERVER_URL);
	}
	
	public static void main(String args[]) {
		RestClientMain.init();
		
		RestClientMain.getFibonacciJSON(10); // http://localhost:8080/class/fibbonaci/{n}
		
		RestClientMain.getFibonacciXML(10); // http://localhost:8080/class/fibbonaci/{n}
		
		RestClientMain.getAllArticles(); // http://localhost:8080/class/articles
		
		RestClientMain.deleteArticle(1); // http://localhost:8080/class/article/{id}
		RestClientMain.getAllArticles(); // http://localhost:8080/class/articles
		
		Author aut = new Author(5, "Carlos", "Parra");
		Article art = RestClientMain.getArticle(2);
		if (art != null) {
			art.setTitle("Nuevo título");
			art.getAuthors().add(aut);
			RestClientMain.updateArticle(2, art); // http://localhost:8080/class/article/{id}
		}
		
		art = new Article(4, "Nuevo artículo");
		art.getAuthors().add(aut);
		RestClientMain.createArticle(art); // http://localhost:8080/class/article/{id}
	}
	
	public static void createArticle(Article article) {
		// http://localhost:8080/class/article
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/article");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		
		Response response = invocationBuilder.post(Entity.xml(article));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
	}
	
	public static void updateArticle(long id, Article article) {
		// http://localhost:8080/class/article/{id}
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/article/" + id);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.put(Entity.xml(article));
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
	}
	
	public static Article getArticle(long id) {
		// http://localhost:8080/class/article/{id}
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/article/" + id);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
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
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/article/" + id);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.delete();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
	}
	
	public static Vector<Article> getAllArticles() {
		// http://localhost:8080/class/articles
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/articles");
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		Peercheck res = response.readEntity(Peercheck.class);
		System.out.println("Content: " + res);
		
		return res.getArticles();
	}
	
	public static String getFibonacciXML(int n) {
		// http://localhost:8080/class/fibbonaci/{n}
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/fibbonaci/" + n);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		String respuestaTexto = response.readEntity(String.class);
		System.out.println("Content: " + respuestaTexto);
		
		return respuestaTexto;
	}
	
	public static String getFibonacciJSON(int n) {
		// http://localhost:8080/class/fibbonaci/{n}
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/fibbonaci/" + n);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		String respuestaTexto = response.readEntity(String.class);
		System.out.println("Content: " + respuestaTexto);
		
		return respuestaTexto;
	}
	
	public static Fibbonaci getFibonacciLista(int n) {
		// http://localhost:8080/class/fibbonaci/{n}
		WebTarget webTarget = RestClientMain.baseWebTarget.path("class/fibbonaci/" + n);
		System.out.println("Requesting from server URI: " + webTarget.getUri());
		
		// Concatena servidor y el path al recurso
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		System.out.println("Response from server code: " + response.getStatus() + " - " + response.getStatusInfo());
		System.out.println("Media type: " + response.getMediaType().toString());
		Fibbonaci res = response.readEntity(Fibbonaci.class);
		System.out.println("Content: " + res);
		
		return res;
	}
}
