package service.writer;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import radio.signal.Complex;
import radio.signal.Signal;
import service.fetcher.Fetcher;
import service.writer.DataWriter;

public class DataWriterTest {
	private DataWriter writer;
	private Signal signal;
	@Before
	public void startUp() {
		signal = new Signal();
		signal.setSamples(new Complex[] {new Complex(1.0, 1.0), new Complex(1.0, 1.0)});
		writer = new DataWriter();
	}
	@Test
	public void testWrite() {
		String path = "/Users/Daniel/Desktop/workspace_sdr/HW1/test/writerTest.dat";
		writer.write(path, signal);
		Fetcher fetcher = new Fetcher(path);
		fetcher.fetch();
		assertEquals(2, fetcher.getCountReal());
		assertEquals(2, fetcher.getCountImg());
		assertEquals(2, fetcher.getLength());
		
		Complex c = new Complex(1.0, 1.0);
		
		assertEquals(c,fetcher.getSamples().get(0));
		assertEquals(c,fetcher.getSamples().get(1));
	}

}
