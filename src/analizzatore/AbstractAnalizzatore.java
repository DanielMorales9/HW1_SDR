package analizzatore;

import radio.Radio;

/**
 * Classe che modella l'ascolto di una sequenza generica, che sia presa in input con dei parametri specifici
 * o che faccia riferimento a valori costanti definiti nel programma
 * */

public abstract class AbstractAnalizzatore {

	protected Radio radio;
	protected int offlineTests, onlineTests;
	protected String[] paths;
	
	public void analizza() throws Exception {

		double[] snr = new double[paths.length]; 
		double[] thresholds = new double[paths.length];
		double[] detectionPercentages = new double[paths.length];
		
 
		for (int i = 0; i< paths.length; i++) {
			radio.insertPathOfSignalFile(paths[i]);
			snr[i] = radio.chooseNumberOfTest(offlineTests);
			thresholds[i] = radio.getThreshold();
			detectionPercentages[i] = radio.compareWithThreshold(onlineTests);
			
		}
		System.out.println("Tenendo conto delle costanti: ");
		System.out.println("Probabilità di Errore = " + this.radio.getFalseAllarm());
		System.out.println("Lunghezza di rumore (campioni) = " + this.radio.getLunghezzaRumore());
		System.out.println("Con un numero di prove offline pari a: " + this.offlineTests);
		System.out.println("Con un numero di prove online pari a:  " + this.onlineTests);
		System.out.println("I risultati ottenuti sono: ");
		System.out.println("\nSNR \t\t\t Soglia \t\t%DETECTION");
		for (int i = 0; i <paths.length; i++) {
			System.out.println(snr[i]+"\t"+thresholds[i]+"\t"+detectionPercentages[i]);
		}
	}
	
}
