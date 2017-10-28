package co.edu.javeriana.ws.rest.model;

import java.math.BigInteger;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fibbonaci {
	
	@XmlElement(name = "number")
	private Vector<FibbonaciNumber> sequence;
	
	public Vector<FibbonaciNumber> getSequence() {
		return this.sequence;
	}
	
	public Fibbonaci() {
		this.sequence = new Vector<>();
	}
	
	public Fibbonaci(int n) {
		this.sequence = new Vector<>();
		this.getSequence(n);
	}
	
	private void getSequence(int n) {
		for (int i = 0; i < n; i++) {
			FibbonaciNumber number;
			if (i < 2) {
				number = new FibbonaciNumber(i, new BigInteger(i + ""));
			} else {
				BigInteger a = this.sequence.get(i - 1).getNumber();
				BigInteger b = this.sequence.get(i - 2).getNumber();
				BigInteger c = a.add(b);
				number = new FibbonaciNumber(i, c);
			}
			this.sequence.add(number);
		}
	}
}
