package radio.signal;

import static org.junit.Assert.*;

import org.junit.Test;

import radio.signalprocessing.SignalProcessor;

public class NoiseTest {

	@Test
	public void testNoisePower() {
		Noise noise = new Noise(0.1, 10000);
		double pot = SignalProcessor.power(noise);
		System.out.println(pot + " " + noise.getPot_rumore());
		assertTrue(noise.getPot_rumore() <pot +0.02 && noise.getPot_rumore() > pot - 0.02 );
	}
}
