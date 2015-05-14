package radio.signalprocessing;

import exception.InvalidSignalsException;
import radio.signal.AbstractSignal;
import radio.signal.Complex;
import radio.signal.Signal;

public class SignalProcessor {

	public static double power(AbstractSignal signal) {
		double result = 0;
		for (Complex sample : signal.getSamples()) {
			double moduloQuadro = Math.pow(sample.module(), 2);
			result += moduloQuadro;
		}
		return result/signal.getSamples().length;
	}

	public static AbstractSignal sum(AbstractSignal s1, AbstractSignal s2) throws InvalidSignalsException {
		if (s1.getSamples().length != s2.getSamples().length) {
			throw new InvalidSignalsException();
		}
		
		Complex[] samples = new Complex[s1.getSamples().length];
		for (int i = 0; i < s1.getSamples().length; i++) {
			samples[i] = Complex.sum(s1.getSample(i), s2.getSample(i));
		}
		AbstractSignal summedSignal = new Signal();
		summedSignal.setSamples(samples);
		return summedSignal;
	}
	
	public static AbstractSignal convolution(AbstractSignal s1, AbstractSignal s2) {

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
	
	public static double calculateSNRFromPower(double signalPower) {
		double noisePower = signalPower -1;
		double snrDB = 10*Math.log10(1/noisePower);
		return snrDB;
	}
	

}
