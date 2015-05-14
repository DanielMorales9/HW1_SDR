package radio.signal;

import java.util.Random;

/**Classe che permette di modellare segnali di tipo Rumore.
 * @see AbstractSignal
 * */

public class Noise extends AbstractSignal {

	private double pot_rumore;
    
	/**
	 * Costruttore che permette di creare un rumore gaussiano bianco additivo a partire da
	 * un dato snr e da un certo numero di campioni. Il segnale sarà descritto da una 
	 * varianza pari a 1 e una media nulla.
	 * */
	public Noise(double snr, int length) {
		Random campione = null;
		double snr_linearizzato = Math.pow(10, (snr/10)); 
		this.setPot_rumore((1/snr_linearizzato));
		this.samples = new Complex[length];

		for (int i = 0; i < length; i++) {
			campione = new Random();
			this.setSample(i, new Complex());
			this.getSample(i).setpReal(campione.nextGaussian() *
					Math.sqrt(getPot_rumore()/2));
		}

		for (int i = 0; i < length; i++) {
			campione = new Random();
			this.getSample(i).setpImg(campione.nextGaussian() *
					Math.sqrt(getPot_rumore()/2));
		}
	}

	public Noise() {
		
	}

	
	/** SETTERS & GETTERS **/
	
	public double getPot_rumore() {
		return pot_rumore;
	}

	public void setPot_rumore(double pot_rumore) {
		this.pot_rumore = pot_rumore;
	}
}