package simulator;

import exception.InvalidDetectionPercentageException;
import exception.InvalidSignalsException;
import radio.signal.AbstractSignal;
import radio.signal.Noise;
import radio.signal.Signal;
import radio.signalprocessing.SignalProcessor;

public class Simulator {

	private final static int CUSTOM_SAMPLES_LENGTH = 1000000;
	
	private double snr;
	private double detectionPercentage;
	
	public Simulator(double detectionPercentage, double snr) throws InvalidDetectionPercentageException {
		if(detectionPercentage>1 || detectionPercentage <0) 
			throw new InvalidDetectionPercentageException();
		this.snr = snr;
		this.detectionPercentage = detectionPercentage;
	}

	public AbstractSignal generateSignalWithNoise() throws InvalidSignalsException {
		AbstractSignal output = null;
		if (detectionPercentage == 1) {
			output = generateSignalAndNoise(snr);
		}
		else {
			AbstractSignal simpleSignal = 
					new Signal(numberOfSignalSamples(detectionPercentage));
			AbstractSignal noise = new Noise(snr, CUSTOM_SAMPLES_LENGTH);
			output = SignalProcessor.sendSignalOnNoise(simpleSignal,
					noise);
		}
		return output;
	}
	
	private AbstractSignal generateSignalAndNoise(double snr) 
			throws InvalidSignalsException {
		AbstractSignal simpleSignal = new Signal(CUSTOM_SAMPLES_LENGTH);
		AbstractSignal noise = new Noise(snr, CUSTOM_SAMPLES_LENGTH);
		AbstractSignal output = SignalProcessor.sum(simpleSignal, noise);
		return output;
	}
	
	private int numberOfSignalSamples(double detectionPercentage) {
		double numberOfTotalSamples = CUSTOM_SAMPLES_LENGTH*detectionPercentage;
		return (int) numberOfTotalSamples; 
		
	}
	
	
}
