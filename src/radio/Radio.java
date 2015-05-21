package radio;
import radio.detector.EnergyDetector;
import radio.signal.Signal;
import service.fetcher.SignalFetcher;

/**
 * Radio è una classe che rappresenta l'intero sistema in questione.
 * Si occupa di offrire tramite la sua interfaccia tutte le operazioni di energy detection
 * @author Daniel
 *
 */
public class Radio {
	private static final Double CUSTOM_FALSE_ALLARME_PROBABILITY = Math.pow(10, -3);

	private static final int NOISE_SAMPLES_LENGTH = 1000;

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
		double detectionPercent = numberOfDetections/ (double) (numberOfTest)*100;
		return detectionPercent;
	}
	
	
	public double getThreshold() {
		return this.detector.getThreshold();
	}


}