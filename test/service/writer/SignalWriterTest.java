package service.writer;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import radio.signal.Complex;
import radio.signal.Signal;
import service.fetcher.SignalFetcher;
import service.writer.SignalWriter;

public class SignalWriterTest {
	private SignalWriter writer;
	private Signal signal;
	@Before
	public void startUp() {
		signal = new Signal();
		signal.setSamples(new Complex[] {new Complex(1.0, 1.0), new Complex(1.0, 1.0)});
		writer = new SignalWriter();
	}
	@Test
	public void testWrite() {
		String path = "/Users/Daniel/Desktop/workspace_sdr/HW1/test/writerTest.dat";
		writer.write(path, signal);
		SignalFetcher fetcher = new SignalFetcher(path);
		fetcher.fetch();
		assertEquals(2, fetcher.getCountReal());
		assertEquals(2, fetcher.getCountImg());
		assertEquals(2, fetcher.getLength());
		
		Complex c = new Complex(1.0, 1.0);
		
		assertEquals(c,fetcher.getSamples().get(0));
		assertEquals(c,fetcher.getSamples().get(1));
	}

}
