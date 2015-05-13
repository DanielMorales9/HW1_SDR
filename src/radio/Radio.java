package radio;

import signal.Signal;
import detector.EnergyDetector;
import fetcher.Fetcher;

public class Radio {
	private static final Double CUSTOM_FALSE_ALLARME_PROBABILITY = Math.pow(10, -4);
	
	private Signal signalRead;
	private EnergyDetector detector;
	
	public Radio() {
		this.detector = new EnergyDetector();
	}
	public void insertPathOfSignalFile(String path) {
		Fetcher fetcher = new Fetcher(path);
		fetcher.fetch();
		signalRead = new Signal();
		signalRead.setSamples(fetcher.getSamples());
	}
	
	public void chooseNumberOfTest(int numberOfTest, int noiseSamplesLength) throws Exception {
		double SNR = signalRead.getSNR();
		this.detector.createNoiseEnergies(numberOfTest, noiseSamplesLength, SNR);
		this.detector.determineThereshold(CUSTOM_FALSE_ALLARME_PROBABILITY);
	}
	
	public double compareWithThreshold(int numberOfTest) {
		double numberOfDetections = 
				this.detector.compareSignalWithThreshold(signalRead, numberOfTest);
		return numberOfDetections;
	}	
}