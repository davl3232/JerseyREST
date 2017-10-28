package co.edu.javeriana.ws.rest.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class FibbonaciNumber {
	
	@XmlAttribute
	private int index;
	
	@XmlValue
	private BigInteger number;
	
	public int getIndex() {
		return this.index;
	}
	
	public BigInteger getNumber() {
		return this.number;
	}
	
	public FibbonaciNumber() {
		this.index = 0;
		this.number = BigInteger.ZERO;
	}
	
	public FibbonaciNumber(int index, BigInteger number) {
		this.index = index;
		this.number = number;
	}

	@Override
	public String toString() {
		return "FibbonaciNumber{" + "index=" + index + ", number=" + number + '}';
	}
}
