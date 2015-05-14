package radio.signal;

import java.util.Random;

public class Noise extends AbstractSignal {

	private double pot_rumore;

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

	public double getPot_rumore() {
		return pot_rumore;
	}

	public void setPot_rumore(double pot_rumore) {
		this.pot_rumore = pot_rumore;
	}
}