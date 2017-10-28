package co.edu.javeriana.webservices.rest;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

@Path("")
public class Main {
	
	public static final String BASE_URI = "http://localhost:8080/";
	
	public static HttpServer startServer() {
		ResourceConfig rc = new ResourceConfig().packages("co.edu.javeriana.webservices.rest");
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(Main.BASE_URI), rc);
	}
	
	public static void main(String[] args) throws IOException {
		final HttpServer server = Main.startServer();
		System.out.println(String.format("-------------------------------\n" + "\tJersey app started with WADL available at\n" + "\t%sapplication.wadl\n" + "\tHit enter to stop it...", Main.BASE_URI));
		System.in.read();
		server.shutdown();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String index() {
		return "Hola desde index";
	}
}
