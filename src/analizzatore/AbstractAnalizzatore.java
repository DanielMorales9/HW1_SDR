package analizzatore;


import java.io.FileNotFoundException;

import radio.Radio;

/**
 * Classe che si occupa di analizzare e mostrare i dati ottenuti
 * da un operazione di detection di una radio.
 * @author Daniel, Antonio
 * */

public abstract class AbstractAnalizzatore {

	protected Radio radio;
	protected int offlineTests, onlineTests;
	protected String[] paths;

	/**
	 * Semplice metodo che mostra a schermo i risultati dell' operazione di 
	 * detection sui path relativi a sequenze di segnali
	 * @throws Exception
	 */
	public void analizza() {

		double[] snr = new double[paths.length]; 
		double[] thresholds = new double[paths.length];
		double[] detectionPercentages = new double[paths.length];


		for (int i = 0; i< paths.length; i++) {
			try {
				radio.insertPathOfSignalFile(paths[i]);
			} catch (FileNotFoundException f) {
				System.out.println("Path non valido");
				return;
			}
			try {
			snr[i] = radio.chooseNumberOfTest(offlineTests);
			} catch (Exception e) {
				System.out.println("Probabilità di falso allarme non valida");
				return;
			}
			thresholds[i] = radio.getThreshold();
			detectionPercentages[i] = radio.compareWithThreshold(onlineTests);

		}
		System.out.println("Tenendo conto delle costanti: ");
		System.out.println("Probabilita' di Falso Allarme = " + this.radio.getFalseAllarm());
		System.out.println("Lunghezza di rumore (campioni) = " + this.radio.getNoiseLength());
		System.out.println("Con un numero di prove offline pari a: " + this.offlineTests);
		System.out.println("Con un numero di prove online pari a:  " + this.onlineTests);
		System.out.println("I risultati ottenuti sono: ");
		System.out.println("\nSNR \t\t\t Soglia \t\t%DETECTION");
		for (int i = 0; i <paths.length; i++) {
			System.out.println(snr[i]+"\t"+thresholds[i]+"\t"+detectionPercentages[i]);
		}
	}

}
