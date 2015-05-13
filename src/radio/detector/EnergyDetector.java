package detector;

import probability.Probability;
import signal.Complex;
import signal.Noise;
import signal.Signal;
import signalprocessing.SignalProcessor;

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
		double variance = Probability.variance(noiseEnergies);
		threshold += Math.sqrt(2*variance) * Probability.invErf(falseAllarmeProbability);
	}

	public double compareSignalWithThreshold(Signal signal, int numberOfTest) {
		int numberOfTotalSamples = signal.getSamples().length;
		int numberOfSamples = numberOfTotalSamples/numberOfTest;
		Double[] powers = new Double[numberOfTest]; 
		for (int i = 0; i < numberOfTest; i++) {
			Signal sig = new Signal();
			setSamplesToSignal(sig, signal, numberOfSamples, i);
			double signalPower = SignalProcessor.power(sig);
			powers[i] = signalPower;
		}
		int count = 0;
		for(int i = 0; i < numberOfTest; i++) {
			if(powers[i] >= threshold) {
				count++;
			}
		}
		return count;
	}

	private void setSamplesToSignal(Signal sig, Signal signal2, int numberOfSamples, int test) {
		sig.setSamples(new Complex[numberOfSamples]);
		for (int i = 0; i < numberOfSamples; i++) {
			sig.setSample(i, signal2.getSample(i*test));
		}
	}
}
