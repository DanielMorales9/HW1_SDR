package radio.signal;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexTest {

	@Test
	public void testSum() {
		Complex c1 = new Complex(3, 4);
		Complex c2 = new Complex(7, 6);
		assertEquals(new Complex(10, 10), Complex.sum(c1, c2));
	}
	
	@Test
	public void testProduct() {
		Complex c1 = new Complex(3, 4);
		Complex c2 = new Complex(7, 6);
		assertEquals(new Complex(-3, 46), Complex.product(c1, c2));
	}

	@Test
	public void testDiff() {
		Complex c1 = new Complex(3, 4);
		Complex c2 = new Complex(7, 6);
		assertEquals(new Complex(-4, -2), Complex.diff(c1, c2));
	}
	
	@Test
	public void testDivide() {
		Complex c1 = new Complex(1, 2);
		Complex c2 = new Complex(4, 6);
		assertEquals(new Complex(2.2188007849009166, 0.2773500981126146), Complex.div(c1, c2));
	}
	
	@Test
	public void testPhase() {
		Complex c1 = new Complex(1, 0);
		assertTrue(0 == c1.phase());
		Complex c2 = new Complex(0,1);
		assertTrue(c2.phase() == 1.5707963267948966);
	}
	
	@Test
	public void testModule() {
		Complex c1 = new Complex(0, 3);
		assertTrue(c1.module() == 3);
	}
	
	@Test 
	public void testComplessoCon() {
		Complex c1 = new Complex(3, -4);
		c1.complexCon();
		assertEquals(new Complex(3, 4), c1);
	}
	
	@Test
	public void testReciprocal() {
		Complex c1 = new Complex(0, -1);
		c1.reciprocal();
		assertEquals(new Complex(0, 1), c1);
	}
}
