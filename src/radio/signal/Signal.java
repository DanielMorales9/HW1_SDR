package radio.signal;

import radio.signalprocessing.SignalProcessor;

public class Signal extends AbstractSignal {

	public Signal(int length) {
		this.samples = new Complex[length];
		for (int i = 0; i < length; i++) { 
			super.setSample(i, new Complex());
			double v = Math.random();
			if (v < 0.5)
				this.samples[i].setpReal(1/Math.sqrt(2)); 
			else
				this.samples[i].setpReal(-1/Math.sqrt(2)); 
			double p = Math.random(); 
			if (p < 0.5)
				this.samples[i].setpImg(1/Math.sqrt(2)); 
			else
				this.samples[i].setpImg(-1/Math.sqrt(2));
		}
	}

	public Signal() {
	}

	public double getSNR() {
		double signalPower = SignalProcessor.power(this);
		return SignalProcessor.calculateSNRFromPower(signalPower);
	}




}
