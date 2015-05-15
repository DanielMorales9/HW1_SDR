package radio.signal;

import java.util.List;

/**
 * Classe che modella un segnale e fornisce alcune operazioni di base dei segnali.
 * La classe � astratta in quanto possono esserci diversi tipi di segnale.
 * */

public abstract class AbstractSignal {

	protected Complex[] samples; //valori complessi del segnale
	private int length; //lunghezza del segnale, contestualmente rappresenta anche il numero di campioni!

	/** GETTERS AND SETTERS **/
	public Complex[] getSamples() {
		return samples;
	}

	public void setSamples(Complex[] samples) {
		this.samples = samples;
	}
	public Complex getSample(int j) {
		return this.samples[j];
	}

	public void setSample(int k, Complex sample) {
		this.samples[k] = sample;
	}
	
	public void setSamples(List<Complex> samples) {
		this.samples = samples.toArray(new Complex[samples.size()]);
	}
	public int getLength() {
		return this.samples.length;
	}

	public void setLength(int length) {
		this.length = length;
	}	
	
    /** 
     * Metodo che ruota(inverte) il segnale
     * **/	
	public void rotate() { 
		Complex[] c = new Complex[this.samples.length];
		for(int i = 0; i < this.samples.length; i++) {
			c[this.samples.length -1 -i] = this.samples[i];
		}
		this.samples = c;
	}

	/**
	 * Metodo che permette di ottenere il complesso e coniugato di un segnale.
	 * **/
	public void complexCon() {
		for (Complex sample : samples) {
			sample.complexCon();
		}
	}

	/**
	 * Metodo che permette di sommare a un certo elemento i un determinato numero complesso sample
	 * @param int i, Complex sample
	 * */
	public void sum(int i, Complex sample) {
		setSample(i, Complex.sum(this.samples[i], sample));
	}


}
