/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ws.rest.util;

import co.edu.javeriana.ws.rest.view.FXMLDocumentController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author davl3232
 */
public class Formatter {
	public static String formatXML(String xml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException, TransformerConfigurationException, TransformerException {
		// Turn xml string into a document
		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder()
				.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(document);
		transformer.transform(source, result);
		xml = result.getWriter().toString();
		
		return xml;
	}

	public static String formatJSON(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object jsonObj = mapper.readValue(json, Object.class);
		String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
		
		return indented;
	}
}
