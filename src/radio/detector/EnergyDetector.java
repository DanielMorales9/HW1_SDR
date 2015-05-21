package radio.detector;

import radio.signal.AbstractSignal;
import radio.signal.Complex;
import radio.signal.Noise;
import radio.signal.Signal;
import radio.signalprocessing.SignalProcessor;
import util.probability.Probability;

/**
 * Classe che modella la componente di Energy Detector di una Radio SCR.
 * @author Daniel
 *
 */
public class EnergyDetector {
	private Double[] noiseEnergies;
	private double threshold;
	
	/**
	 * Dato un numero di prove, la lunghezza dei campioni di rumore e un snr 
	 * genera un vettore diEnergie di Rumore
	 * @param numberOfTest
	 * @param noiseSamplesLength
	 * @param snr
	 */
	public void createNoiseEnergies(int numberOfTest, int noiseSamplesLength, double snr) {
		noiseEnergies = new Double[numberOfTest];
		for (int i = 0; i < noiseEnergies.length; i++) {			
			noiseEnergies[i] = SignalProcessor.power(new Noise(snr, noiseSamplesLength));
		}
	}
	
	/**
	 * Data una probabilità di falso allarme calcola la soglia come la somma
	 * della media delle energie di rumore e il prodotto fra la radice del doppio
	 * della varianza delle energie e la eft^-1 di 1-2 per la probabilità di falso allarme
	 * @param falseAllarmeProbability
	 * @throws Exception
	 */
	public void determineThereshold(Double falseAllarmeProbability) throws Exception {
		this.threshold = Probability.simpleAverage(noiseEnergies);
		double variance = Probability.simpleVariance(noiseEnergies);
		threshold += Math.sqrt(2*variance) * Probability.invErf(1-2*falseAllarmeProbability);
	}

	/**
	 * Dato un segnale e un numero di prove compara l'energie delle prove del segnale 
	 * con la soglia 
	 * @param signal
	 * @param numberOfTest
	 * @return percentuale di detection - numero di volte in cui si è verificata la 
	 * presenza dell'utente primario
	 */
	public double compareSignalWithThreshold(AbstractSignal signal, int numberOfTest) {
		Double[] powers = calculateSignalsPower(signal, numberOfTest);
		int count = 0;
		for(int i = 0; i < numberOfTest; i++) {
			if(powers[i] >= threshold) {
				count++;
			}
		}
		return (double) count;
	}

	/**
	 * A partire da un segnale di ingresso genera le energie delle prove
	 * @param signalRead
	 * @param numberOfTest
	 * @return vettore di energie delle prove
	 */
	private Double[] calculateSignalsPower(AbstractSignal signalRead,
			int numberOfTest) {
		int numberOfTotalSamples = signalRead.getSamples().length;
		int numberOfSamples = numberOfTotalSamples/numberOfTest;
		Double[] powers = new Double[numberOfTest]; 
		
		for (int i = 0; i < numberOfTest; i++) {
			AbstractSignal signal = new Signal();
			setSamplesToSignal(signal, signalRead, numberOfSamples, i);
			double signalPower = SignalProcessor.power(signal);
			powers[i] = signalPower;
		}
		return powers;
	}
	
	/**
	 * Data una prova, un segnale d'ingresso, un numero di campioni e un numero di prova
	 * Scrive i campioni del segnale nella prova
	 * @param sig - prova
	 * @param signal - segnale d'ingresso
	 * @param numberOfSamples
	 * @param test
	 */
	private void setSamplesToSignal(AbstractSignal sig, AbstractSignal signal, 
			int numberOfSamples, int test) {
		sig.setSamples(new Complex[numberOfSamples]);
		for (int i = 0; i < numberOfSamples; i++) {
			int signalIndex= i+numberOfSamples*test;
			sig.setSample(i, signal.getSample(signalIndex));
		}
	}

	public double getThreshold() {
		return this.threshold;
	}
}
