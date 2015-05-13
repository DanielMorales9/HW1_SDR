package signalprocessing;

import static org.junit.Assert.*;

import org.junit.Test;

import signal.Complex;
import signal.Signal;

public class SignalProcessorTest {

	@Test
	public void testPower() {
		Signal sig = new Signal(4);
		double signalPower = SignalProcessor.power(sig);
		assertTrue(1.0 >= signalPower && signalPower > 0.9 );
	}

	@Test
	public void testConvolution() {
		Signal s1 = new Signal();
		s1.setSamples(new Complex[]{new Complex(3,0), new Complex(2,0), 
				new Complex(1,0)});
		Signal s2 = new Signal();
		s2.setSamples(new Complex[]{new Complex(1,0), new Complex(1,0), 
				new Complex(2,0), new Complex(1,0)});
		
		Signal result = SignalProcessor.convolution(s1, s2);
		Complex[] resultComplex = new Complex[]{new Complex(3,0), new Complex(5,0), 
			new Complex(9,0), new Complex(8,0), new Complex(4,0), new Complex(1,0)};
		assertArrayEquals(resultComplex, result.getSamples());		
	}

}
