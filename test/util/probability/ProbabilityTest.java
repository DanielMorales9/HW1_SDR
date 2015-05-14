package util.probability;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProbabilityTest {

	private Double[] realizations;

	@Before
	public void startUp() {
		realizations = new Double[] {25.0,25.0,25.0,25.0,25.0,25.0,25.0,25.0};
	}
	
	@Test
	public void testSimpleAverage() {
		assertTrue(25.0==Probability.simpleAverage(realizations));
	}

	@Test
	public void testVariance() {
		assertTrue(0.0==Probability.simpleVariance(realizations));
	}

}
