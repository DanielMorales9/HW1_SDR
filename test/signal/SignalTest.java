package signal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import radio.signal.Complex;
import radio.signal.Signal;

public class SignalTest {
	private Signal signal;
	@Before
	public void startUp() {
		signal = new Signal();
		signal.setSamples(new Complex[] {new Complex(1.0, 2.0), new Complex(2.0, 1.0)});
	}

	@Test
	public void testComplexCon() {
		signal.complexCon();
		assertEquals(new Complex(1.0, -2.0), signal.getSample(0));
		assertEquals(new Complex(2.0, -1.0), signal.getSample(1));	
	}

	@Test
	public void testRotate() {
		signal.rotate();
		assertEquals(new Complex(2.0, 1.0), signal.getSample(0));
		assertEquals(new Complex(1.0, 2.0), signal.getSample(1));
		
	}

}
