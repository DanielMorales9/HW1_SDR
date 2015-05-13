package signal;

import java.util.List;

import signalprocessing.SignalProcessor;


public class Signal {

	protected Complex[] samples;

	public Signal(int length) {
		this.samples = new Complex[length];
		for (int i = 0; i < length; i++) { 
			this.setSample(i, new Complex());
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

	public Complex[] getSamples() {
		return samples;
	}
	
	public void setSamples(Complex[] samples) {
		this.samples = samples;
	}
	public Complex getSample(int j) {
		return this.samples[j];
	}

	public void setSample(int k, Complex sample) {
		this.samples[k] = sample;
	}
	
	public void rotate() { 
		Complex[] c = new Complex[this.samples.length];
		for(int i = 0; i < this.samples.length; i++) {
			c[this.samples.length -1 -i] = this.samples[i];
		}
		this.samples = c;
	}

	public void complexCon() {
		for (Complex sample : samples) {
			sample.complexCon();
		}
	}

	public void setSamples(List<Complex> samples) {
		this.samples = samples.toArray(new Complex[samples.size()]);
	}

	public double getSNR() {
		double signalPower = SignalProcessor.power(this);
		return SignalProcessor.getSNRFromPower(signalPower);
	}




}
