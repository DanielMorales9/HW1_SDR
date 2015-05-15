package radio;
import exception.InvalidDetectionPercentageException;
import exception.InvalidSignalsException;
import radio.detector.EnergyDetector;
import radio.signal.AbstractSignal;
import radio.signal.Signal;
import service.fetcher.SignalFetcher;
import service.writer.SignalWriter;
import simulator.Simulator;

/**
 * Radio è una classe che rappresenta l'intero sistema in questione.
 * Si occupa di offrire tramite la sua interfaccia tutte le operazioni di energy detection
 * @author Daniel
 *
 */
public class Radio {
	private static final Double CUSTOM_FALSE_ALLARME_PROBABILITY = Math.pow(10, -3);

	private static final String SIMULATION_PATH = "/Users/Daniel/Desktop/workspace_sdr/HW1/test/mio_output.dat";

	private static final int NOISE_SAMPLES_LENGTH = 10000;

	private Signal signalRead;
	private EnergyDetector detector;
	private double falseAllarm;

	public Radio() {
		this.detector = new EnergyDetector();
		this.falseAllarm = CUSTOM_FALSE_ALLARME_PROBABILITY;
		signalRead = new Signal();
	}
	
	/**
	 * Dato un percorso di un file, ne fa il fetche 
	 * e crea un segnale
	 * @param path - percorso del file
	 * @return
	 */
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

	/**
	 * Semplice metodo che dato un numero di prove e la lunghezza dei campioni del 
	 * rumore, crea un vettore di energie calcolate su 
	 * un rumore Gaussiano e ne determina una soglia.
	 * @param numberOfTest - numero di prove
	 * @param noiseSamplesLength - numero di campioni del rumore
	 * @return il valore in decibel dell'SNR del segnale letto.
	 * @throws Exception
	 */
	public double chooseNumberOfTest(int numberOfTest, int noiseSamplesLength) throws Exception {
		double SNR = signalRead.getSNR();
		this.detector.createNoiseEnergies(numberOfTest, noiseSamplesLength, SNR);
		this.detector.determineThereshold(falseAllarm);
		return SNR;
	}

	/**
	 * Dato un numero di prove,
	 * crea un vettore di energie calcolate su 
	 * un rumore Gaussiano e ne determina una soglia.
	 * @param numberOfTest - numero di prove
	 * @return il valore in decibel dell'SNR del segnale letto.
	 * @throws Exception
	 */
	public double chooseNumberOfTest(int numberOfTest) throws Exception {
		double SNR = signalRead.getSNR();
		this.detector.createNoiseEnergies(numberOfTest, NOISE_SAMPLES_LENGTH, SNR);
		this.detector.determineThereshold(falseAllarm);
		return SNR;
	}

	/**
	 * Calcola la percentuale di detection sul numero di prove date
	 * @param numberOfTest - numero di prove del segnale con la soglia
	 * @return percentuale di detection
	 */
	public double compareWithThreshold(int numberOfTest) {
		double numberOfDetections = 
				this.detector.compareSignalWithThreshold(signalRead, numberOfTest);
		double detectionPercent = numberOfDetections/numberOfTest*100;
		return detectionPercent;
	}	

	/**
	 * Data una percentuale di detection e un snr genera una simulazione
	 * in cui su una trasmissione di un milione di campioni di rumore (a snr dato)
	 * va a sommare un numero di campioni di segnali a potenza unitaria pari alla 
	 * percentuale di detection per il numero di campioni
	 * @param detectionPercentage - percentuale di detection
	 * @param snr
	 * @throws InvalidDetectionPercentageException
	 * @throws InvalidSignalsException
	 */
	public void createNewSimulation(double detectionPercentage, double snr) 
			throws InvalidDetectionPercentageException, InvalidSignalsException {
		double detection = detectionPercentage/100;
		Simulator simulator = new Simulator(detection, snr);
		AbstractSignal signal = simulator.generateSignalWithNoise();

		writeSimulation(signal);
		insertPathOfSignalFile(SIMULATION_PATH);
	}

	/**
	 * Dato un segnale lo scrive su un file
	 * @param signal
	 */
	private void writeSimulation(AbstractSignal signal) {
		SignalWriter writer = new SignalWriter();
		writer.write(SIMULATION_PATH, signal);
	}
	
	
	public double getThreshold() {
		return this.detector.getThreshold();
	}


}