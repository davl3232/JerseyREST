package co.edu.javeriana.webservices.jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class JaxbReader {
	
	public static Object read(Class<?> classType, String path) {
		Object root = null;
		try {
			JAXBContext context = JAXBContext.newInstance(classType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			root = unmarshaller.unmarshal(new FileReader(path));
		}
		catch (JAXBException e) {
			System.err.println("Error while reading the JAXB model in: " + path);
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.err.println("Error while trying to load the file: " + path);
			e.printStackTrace();
		}
		return root;
	}
	
	public static Object readAndValid(Class<?> classType, String xml, String xsd) {
		Object root = null;
		try {
			
			JAXBContext context = JAXBContext.newInstance(classType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(xsd));
			unmarshaller.setSchema(schema);
			root = unmarshaller.unmarshal(new FileReader(xml));
		}
		catch (JAXBException e) {
			System.err.println("Error while reading the JAXB model in: " + xml + " with the schema: " + xsd);
			e.printStackTrace();
			System.exit(1);
		}
		catch (FileNotFoundException e) {
			System.err.println("Error while trying to load the file: " + xml);
			e.printStackTrace();
		}
		catch (SAXException e) {
			System.err.println("Error while processing the file " + xml + " with the schema: " + xsd);
			e.printStackTrace();
			System.exit(1);
		}
		return root;
	}
}
