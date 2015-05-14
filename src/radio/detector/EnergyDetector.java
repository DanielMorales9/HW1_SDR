package radio.detector;

import radio.signal.AbstractSignal;
import radio.signal.Complex;
import radio.signal.Noise;
import radio.signal.Signal;
import radio.signalprocessing.SignalProcessor;
import util.probability.Probability;

public class EnergyDetector {
	private Double[] noiseEnergies;
	private double threshold;
	
	public void createNoiseEnergies(int numberOfTest, int noiseSamplesLength, double snr) {
		noiseEnergies = new Double[numberOfTest];
		for (int i = 0; i < noiseEnergies.length; i++) {			
			noiseEnergies[i] = SignalProcessor.power(new Noise(snr, noiseSamplesLength));
		}
	}

	public void determineThereshold(Double falseAllarmeProbability) throws Exception {
		this.threshold = Probability.simpleAverage(noiseEnergies);
		double variance = Probability.simpleVariance(noiseEnergies);
		threshold += Math.sqrt(2*variance) * Probability.invErf(1-2*falseAllarmeProbability);
	}

	public double compareSignalWithThreshold(AbstractSignal signal, int numberOfTest) {
		Double[] powers = calculateSignalsPower(signal, numberOfTest);
		int count = 0;
		for(int i = 0; i < numberOfTest; i++) {
			if(powers[i] >= threshold) {
				count++;
			}
		}
		return count;
	}

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
