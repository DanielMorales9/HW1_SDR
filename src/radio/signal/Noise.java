package radio.signal;

import java.util.Random;

public class Noise extends Signal {

	private double pot_rumore;

	public Noise(double snr, int length) {
		Random campione = null;
		double snr_linearizzato = Math.pow(10, (snr/10)); 
		this.pot_rumore = (1/snr_linearizzato);
		this.samples = new Complex[length];

		for (int i = 0; i < length; i++) {
			campione = new Random();
			this.setSample(i, new Complex());
			this.getSample(i).setpReal(campione.nextGaussian() *
					Math.sqrt(pot_rumore/2));
		}

		for (int i = 0; i < length; i++) {
			campione = new Random();
			this.getSample(i).setpImg(campione.nextGaussian() *
					Math.sqrt(pot_rumore/2));
		}
	}
}