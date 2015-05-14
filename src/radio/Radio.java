package radio;
import exception.InvalidDetectionPercentageException;
import exception.InvalidSignalsException;
import radio.detector.EnergyDetector;
import radio.signal.AbstractSignal;
import radio.signal.Signal;
import service.fetcher.SignalFetcher;
import service.writer.SignalWriter;
import simulator.Simulator;

public class Radio {
	private static final Double CUSTOM_FALSE_ALLARME_PROBABILITY = Math.pow(10, -4);

	private static final String SIMULATION_PATH = "/Users/Daniel/Desktop/workspace_sdr/HW1/test/mio_output.dat";

	private static final int NOISE_SAMPLE_LENGTH = 10000;

	private Signal signalRead;
	private EnergyDetector detector;
	private double falseAllarm;

	public Radio() {
		this.detector = new EnergyDetector();
		this.falseAllarm = CUSTOM_FALSE_ALLARME_PROBABILITY;
		signalRead = new Signal();
	}
	public String insertPathOfSignalFile(String path) {
		try {
			SignalFetcher fetcher = new SignalFetcher(path);
			fetcher.fetch();
			signalRead.setSamples(fetcher.getSamples());
		} catch (Exception e) {
			return "Il path inserito non è valido";
		}
		return null;
	}

	public double chooseNumberOfTest(int numberOfTest, int noiseSamplesLength) throws Exception {
		double SNR = signalRead.getSNR();
		this.detector.createNoiseEnergies(numberOfTest, noiseSamplesLength, SNR);
		this.detector.determineThereshold(falseAllarm);
		return SNR;
	}

	public double chooseNumberOfTest(int numberOfTest) throws Exception {
		double SNR = signalRead.getSNR();
		this.detector.createNoiseEnergies(numberOfTest, NOISE_SAMPLE_LENGTH, SNR);
		this.detector.determineThereshold(falseAllarm);
		return SNR;
	}

	public double compareWithThreshold(int numberOfTest) {
		double numberOfDetections = 
				this.detector.compareSignalWithThreshold(signalRead, numberOfTest);
		double detectionPercent = numberOfDetections/numberOfTest*100;
		return detectionPercent;
	}	

	public void createNewSimulation(double detectionPercentage, double snr) 
			throws InvalidDetectionPercentageException, InvalidSignalsException {
		double detection = detectionPercentage/100;
		Simulator simulator = new Simulator(detection, snr);
		AbstractSignal signal = simulator.generateSignalWithNoise();

		writeSimulation(signal);
		insertPathOfSignalFile(SIMULATION_PATH);
	}

	private void writeSimulation(AbstractSignal signal) {
		SignalWriter writer = new SignalWriter();
		writer.write(SIMULATION_PATH, signal);
	}
	public double getThreshold() {
		return this.detector.getThreshold();
	}


}