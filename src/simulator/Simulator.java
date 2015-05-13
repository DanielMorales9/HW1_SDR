package simulator;

import exception.InvalidSignalsException;
import radio.signal.Noise;
import radio.signal.Signal;
import radio.signalprocessing.SignalProcessor;

public class Simulator {

	public static Signal generateSignalWithNoise(int signalLength, double snr) throws InvalidSignalsException {
		Signal simpleSignal = new Signal(signalLength);
		Signal noise = new Noise(snr, signalLength);
		Signal output = SignalProcessor.sum(simpleSignal, noise);
		return output;
	}
}
