package radio.signal;

import java.util.List;

public abstract class AbstractSignal {

	protected Complex[] samples;

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
}
