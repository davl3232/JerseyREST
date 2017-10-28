package co.edu.javeriana.webservices.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Author {
	
	@XmlAttribute(required = true)
	private long id;
	
	@XmlElement(required = true)
	private String name;
	
	@XmlElement(required = true)
	private String surname;

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	 
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public Author() {
		this.id = 0;
		this.name = "";
		this.surname = "";
	}
	
	public Author(long id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
}
