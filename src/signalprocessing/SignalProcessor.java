package signalprocessing;

import exception.InvalidSignalsException;
import signal.Complex;
import signal.Signal;

public class SignalProcessor {

	public static double power(Signal signal) {
		double result = 0;
		for (Complex sample : signal.getSamples()) {
			double moduloQuadro = Math.pow(sample.module(), 2);
			result += moduloQuadro;
		}
		return result/signal.getSamples().length;
	}

	public static Signal sum(Signal s1, Signal s2) throws InvalidSignalsException {
		if (s1.getSamples().length != s2.getSamples().length) {
			throw new InvalidSignalsException();
		}
		
		Complex[] samples = new Complex[s1.getSamples().length];
		for (int i = 0; i < s1.getSamples().length; i++) {
			samples[i] = Complex.sum(s1.getSample(i), s2.getSample(i));
		}
		Signal summedSignal = new Signal();
		summedSignal.setSamples(samples);
		return summedSignal;
	}
	
	public static Signal convolution(Signal v1, Signal v2) {

		int finalLength = v1.getSamples().length + v2.getSamples().length-1;
		Signal result = new Signal();
		result.setSamples(new Complex[finalLength]);

		for(int k=0; k<finalLength; k++) {
			int upperBound = Math.min(k, v2.getSamples().length -1);
			int lowerBound = Math.max(0, k - v1.getSamples().length +1);

			for(int j=lowerBound; j<=upperBound; j++) {
				Complex product = Complex.product(v1.getSample(k-j), 
						v2.getSample(j));
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
	
	public static void main(String[] args) {
		Signal s = new Signal(4);
		System.out.println(SignalProcessor.power(s));
	}

}
