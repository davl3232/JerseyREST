package co.edu.javeriana.webservices.rest;

import java.math.BigInteger;
import java.util.Vector;

public class Fibbonaci {

	public static Vector<BigInteger> getSequence(int n) {
		Vector<BigInteger> sequence = new Vector<>();
		sequence.add(BigInteger.ZERO);
		sequence.add(BigInteger.ONE);
		
		for(int i = 2; i < n; i++) {
			BigInteger next = sequence.get(i - 1).add(sequence.get(i - 2));
			sequence.add(next);
		}
		
		return sequence;
	}
}
