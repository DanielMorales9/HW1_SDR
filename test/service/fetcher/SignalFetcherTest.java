
package service.fetcher;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import radio.signal.Complex;
import service.fetcher.SignalFetcher;

public class SignalFetcherTest {
	
	private SignalFetcher fetcher;
	
	@Before
	public void startUp() {
		fetcher = new SignalFetcher("Sequenza_1/output_1.dat");
	}
	
	@Test
	public void testFetch() throws FileNotFoundException {
		fetcher.fetch();
		assertEquals(1000000, fetcher.getCountReal());
		assertEquals(1000000, fetcher.getCountImg());
		assertEquals(1000000, fetcher.getLength());
		assertEquals(new Complex(-0.35596, -2.7268) ,fetcher.getSamples().get(0));
	}

}
