/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ws.rest.view;

import co.edu.javeriana.ws.rest.client.RestClientMain;
import co.edu.javeriana.ws.rest.model.Fibbonaci;
import co.edu.javeriana.ws.rest.model.FibbonaciNumber;
import co.edu.javeriana.ws.rest.util.Formatter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
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
public class FXMLDocumentController implements Initializable {
	
	@FXML
	private TextArea txtXml;
	
	@FXML
	private TextArea txtJson;
	
	@FXML
	private TextArea txtLista;
	
	@FXML
	private Spinner spnFib;
	
	@FXML
	private void pedir(ActionEvent event) {
		String xml = RestClientMain.getFibonacciXML((Integer)spnFib.getValue());
		try {
			xml = Formatter.formatXML(xml);
		} catch (Exception ex) {
			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		txtXml.setText(xml);
		
		String json = RestClientMain.getFibonacciJSON((Integer)spnFib.getValue());
		try {
			json = Formatter.formatJSON(json);
		} catch (Exception ex) {
			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		txtJson.setText(json);
		
		String lista = "[\n";
		Vector<FibbonaciNumber> seq = RestClientMain.getFibonacciLista((Integer)spnFib.getValue()).getSequence();
		for (FibbonaciNumber fib : seq) {
			lista += fib.toString() + ",\n";
		}
		lista += "]";
		txtLista.setText(lista);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		spnFib.setValueFactory(new IntegerSpinnerValueFactory(0, 100));
	}
	
}
