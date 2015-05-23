package radio.signalprocessing;

import exception.InvalidSignalsException;
import radio.signal.AbstractSignal;
import radio.signal.Complex;
import radio.signal.Signal;

/**
 * Classe che astrae una componente hardware di una Radio SDR
 * @author Daniel, Antonio
 **/

public class SignalProcessor {

	/**
	 * Metodo che permette di ottenere la potenza di un certo segnale.
	 * In particolare la potenza di un segnale discreto � pari alla somma del modulo quadro di ogni campione
	 * tutto fratto il numero di campioni.
	 * @param AbstractSignal signal
	 * @return double potenza del Segnale
	 **/
	public static double power(AbstractSignal signal) {
		double result = 0;
		for (Complex sample : signal.getSamples()) {
			double moduloQuadro = Math.pow(sample.module(), 2);
			result += moduloQuadro;
		}
		return result/signal.getSamples().length; 
	}

	/**
	 * Metodo che permette di calcolare la somma di due Segnali aventi la stessa lunghezza,
	 * (stesso numero di campioni). 
	 * @param AbstractSignal signal1, AbstractSignal signal2
	 * @throws InvalidSignalsException
	 * @return AbstractSignal signal1 + signal2 
	 **/
	public static AbstractSignal sum(AbstractSignal s1, AbstractSignal s2) throws InvalidSignalsException {
		if (s1.getSamples().length != s2.getSamples().length) { //i due segnali devono avere la stessa lunghezza!
			throw new InvalidSignalsException();
		}

		Complex[] samples = new Complex[s1.getSamples().length];
		for (int i = 0; i < s1.getSamples().length; i++) {
			samples[i] = Complex.sum(s1.getSample(i), s2.getSample(i)); //Sommo campione per campione
		}
		AbstractSignal summedSignal = new Signal();
		summedSignal.setSamples(samples);
		return summedSignal;
	}

	/**
	 * Metodo che restituisce la convoluzione tra due segnali s1 e s2.
	 * @param AbstractSignal s1, AbstractSignal s2
	 * @return AbstractSignal s1 convoluto s2 
	 * **/
	public static AbstractSignal convolution(AbstractSignal s1, AbstractSignal s2) {

		//la convoluzione avr� lunghezza pari alla somma del numero dei campioni dei segnali
		int finalLength = s1.getSamples().length + s2.getSamples().length-1; 
		AbstractSignal result = new Signal();
		result.setSamples(new Complex[finalLength]);

		for(int k=0; k<finalLength; k++) {
			int upperBound = Math.min(k, s2.getSamples().length -1); 
			int lowerBound = Math.max(0, k - s1.getSamples().length +1);

			for(int j=lowerBound; j<=upperBound; j++) {
				Complex product = Complex.product(s1.getSample(k-j), 
						s2.getSample(j));
				if (result.getSample(k) == null) 
					result.setSample(k, product);
				else {
					Complex sum = Complex.sum(result.getSample(k), product);  
					result.setSample(k, sum);
				}
			}
		}
		return result;
	}

	/**
	 * Metodo che permette di calcolare l'SNR a partire dalla potenza di un segnale.
	 * @param double signalPower
	 * @return double snr
	 **/
	public static double calculateSNRFromPower(double signalPower) {
		double noisePower = signalPower -1;
		double snrDB;

		snrDB = 10*Math.log10(1/noisePower);
		return snrDB;
	}
	/**
	 * Metodo che somma il rumore a un segnale 
	 * @param AbstractSignal simpleSignal, AbstractSignal noise
	 * @return AbstractSignal noise
	 * **/
	public static AbstractSignal sendSignalOnNoise(AbstractSignal simpleSignal,
			AbstractSignal noise) {
		for (int i = 0; i < simpleSignal.getSamples().length; i++) {
			noise.sum(i, simpleSignal.getSample(i));
		}
		return noise;
	}


}
