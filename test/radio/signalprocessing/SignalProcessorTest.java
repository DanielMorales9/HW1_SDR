package radio.signalprocessing;

import static org.junit.Assert.*;

import org.junit.Test;

import radio.signal.AbstractSignal;
import radio.signal.Complex;
import radio.signal.Noise;
import radio.signal.Signal;
import radio.signalprocessing.SignalProcessor;

public class SignalProcessorTest {

	@Test
	public void testPower() {
		AbstractSignal sig = new Signal(4);
		double signalPower = SignalProcessor.power(sig);
		assertTrue(1.0 >= signalPower && signalPower > 0.9 );
	}

	@Test
	public void testConvolution() {
		AbstractSignal s1 = new Signal();
		s1.setSamples(new Complex[]{new Complex(3,0), new Complex(2,0), 
				new Complex(1,0)});
		AbstractSignal s2 = new Signal();
		s2.setSamples(new Complex[]{new Complex(1,0), new Complex(1,0), 
				new Complex(2,0), new Complex(1,0)});

		AbstractSignal result = SignalProcessor.convolution(s1, s2);
		Complex[] resultComplex = new Complex[]{new Complex(3,0), new Complex(5,0), 
				new Complex(9,0), new Complex(8,0), new Complex(4,0), new Complex(1,0)};
		assertArrayEquals(resultComplex, result.getSamples());		
	}

	@Test
	public void testSum() throws Exception {
		AbstractSignal s1 = new Signal();
		s1.setSamples(new Complex[] {new Complex(1.0, 2.0), new Complex(1.0, 2.0)});
		AbstractSignal s2 = new Signal();
		s2.setSamples(new Complex[] {new Complex(2.0, 1.0), new Complex(2.0, 1.0)});

		AbstractSignal sSum = SignalProcessor.sum(s1, s2);

		assertArrayEquals(new Complex[] {new Complex(3.0, 3.0), new Complex(3.0, 3.0)}, 
				sSum.getSamples());
	}
	
	@Test
	public void testCalculateSNRFromPower() throws Exception {
		double snr = 1.0/10;
		AbstractSignal s1 = new Signal(999999);
		AbstractSignal s2 = new Noise(snr,999999);
		AbstractSignal sTot = SignalProcessor.sum(s1, s2);
		double power = SignalProcessor.power(sTot);
		double resultSNR = SignalProcessor.calculateSNRFromPower(power);
		System.out.println(resultSNR);
		assertTrue(resultSNR < snr +0.01 && resultSNR > snr -0.01);
		
	}
}
