
package service.fetcher;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import radio.signal.Complex;
import service.fetcher.Fetcher;

public class FetcherTest {
	
	private Fetcher fetcher;
	
	@Before
	public void startUp() {
		fetcher = new Fetcher("/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_1/output_1.dat");
	}
	
	@Test
	public void testFetch() {
		fetcher.fetch();
		assertEquals(1000000, fetcher.getCountReal());
		assertEquals(1000000, fetcher.getCountImg());
		assertEquals(1000000, fetcher.getLength());
		assertEquals(new Complex(-0.35596, -2.7268) ,fetcher.getSamples().get(0));
	}

}
