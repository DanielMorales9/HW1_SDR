package simulator;

import exception.InvalidSignalsException;
import radio.signal.AbstractSignal;
import radio.signal.Noise;
import radio.signal.Signal;
import radio.signalprocessing.SignalProcessor;

public class Simulator {

	public static AbstractSignal generateSignalWithNoise(int signalLength, double snr) throws InvalidSignalsException {
		AbstractSignal simpleSignal = new Signal(signalLength);
		AbstractSignal noise = new Noise(snr, signalLength);
		AbstractSignal output = SignalProcessor.sum(simpleSignal, noise);
		return output;
	}
}
