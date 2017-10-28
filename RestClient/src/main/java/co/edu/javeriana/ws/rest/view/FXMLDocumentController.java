/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ws.rest.view;

import co.edu.javeriana.ws.rest.client.RestClientMain;
import co.edu.javeriana.ws.rest.model.Article;
import co.edu.javeriana.ws.rest.model.Author;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Spinner spnIdArticulo;
	
	@FXML
	private TextField txtTituloArticulo;
	
	@FXML
	private Spinner spnIdAutor;
	
	@FXML
	private TextField txtNombreAutor;
	
	@FXML
	private TextField txtApellidoAutor;
	
	@FXML
	private TableView<Article> tblArticulos;
	
	@FXML
	private TableColumn<Article, String> colIdArticulo;
	
	@FXML
	private TableColumn<Article, String> colTituloArticulo;
	
	@FXML
	private TableView<Author> tblAutores;
	
	@FXML
	private TableColumn<Author, String> colIdAutor;
	
	@FXML
	private TableColumn<Author, String> colNombreAutor;
	
	@FXML
	private TableColumn<Author, String> colApellidoAutor;
	private Article articuloSel;
	private Author autorSel;
	
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
	
	@FXML
	private void crearArticulo(ActionEvent event) {
		Article art = new Article((long) (int) spnIdArticulo.getValue(), txtTituloArticulo.getText().toString());
		RestClientMain.createArticle(art);
		
		tblArticulos.getItems().setAll(RestClientMain.getAllArticles());
	}
	
	@FXML
	private void editarArticulo(ActionEvent event) {
		articuloSel.setId((long) (int) spnIdArticulo.getValue());
		articuloSel.setTitle(txtTituloArticulo.getText().toString());
		RestClientMain.updateArticle(articuloSel.getId(), articuloSel);
	}
	
	@FXML
	private void borrarArticulo(ActionEvent event) {
		RestClientMain.deleteArticle(articuloSel.getId());
		
		tblArticulos.getItems().remove(articuloSel);
	}
	
	@FXML
	private void crearAutor(ActionEvent event) {
		articuloSel.addAuthor((long) (int) spnIdArticulo.getValue(), txtNombreAutor.getText().toString(), txtApellidoAutor.getText().toString());
		RestClientMain.updateArticle(articuloSel.getId(), articuloSel);
		
		tblAutores.getItems().setAll(articuloSel.getAuthors());
	}
	
	@FXML
	private void editarAutor(ActionEvent event) {
		articuloSel.updateAuthor((long) (int) spnIdArticulo.getValue(), txtNombreAutor.getText().toString(), txtApellidoAutor.getText().toString());
		RestClientMain.updateArticle(articuloSel.getId(), articuloSel);
		
		tblAutores.getItems().setAll(articuloSel.getAuthors());
	}
	
	@FXML
	private void borrarAutor(ActionEvent event) {
		RestClientMain.updateArticle(articuloSel.getId(), articuloSel);
		
		Vector<Author> autores = articuloSel.getAuthors();
		autores.remove(autorSel);
		tblAutores.getItems().setAll(autores);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		spnFib.setValueFactory(new IntegerSpinnerValueFactory(0, 100));
		spnIdArticulo.setValueFactory(new IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
		spnIdAutor.setValueFactory(new IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
		
		colIdArticulo.setCellValueFactory(new PropertyValueFactory<>("id"));
		colTituloArticulo.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		colIdAutor.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombreAutor.setCellValueFactory(new PropertyValueFactory<>("name"));
		colApellidoAutor.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
		tblArticulos.getItems().setAll(RestClientMain.getAllArticles());
		
		tblArticulos.getSelectionModel()
			.selectedItemProperty()
			.addListener((obs, oldSelection, newSelection) -> {
				if (newSelection != null) {
					articuloSel = tblArticulos.getSelectionModel().getSelectedItem();
					tblAutores.getItems().setAll(articuloSel.getAuthors());
					
					spnIdArticulo.getValueFactory().setValue((int) (long) articuloSel.getId());
					txtTituloArticulo.setText(articuloSel.getTitle());
				}
			});
		
		tblAutores.getSelectionModel()
			.selectedItemProperty()
			.addListener((obs, oldSelection, newSelection) -> {
				if (newSelection != null) {
					autorSel = tblAutores.getSelectionModel().getSelectedItem();
					
					spnIdAutor.getValueFactory().setValue((int) (long) autorSel.getId());
					txtNombreAutor.setText(autorSel.getName());
					txtApellidoAutor.setText(autorSel.getSurname());
				}
			});
	}
	
}
