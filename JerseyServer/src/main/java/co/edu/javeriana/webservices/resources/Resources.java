package co.edu.javeriana.webservices.resources;

import java.net.URL;

public class Resources {
	
	public static URL getArticles() {
		return Resources.class.getResource("articles.xml");
	}
	
	public static URL getArticlesSchema() {
		return Resources.class.getResource("articlesSchema.xsd");
	}
}
