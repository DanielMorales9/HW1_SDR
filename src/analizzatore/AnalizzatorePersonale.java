package analizzatore;

import radio.Radio;

/**
 * Classe che implementa una sessione per l'ascolto di un segnale con dei parametri inseriti dall
 * utente.
 * */

public class AnalizzatorePersonale extends AbstractAnalizzatore {

	public AnalizzatorePersonale(String[] path, double falsoAllarme, int noiseSample,int numberOfTest, int tests){
		this.radio = new Radio(falsoAllarme, noiseSample);
		this.offlineTests = numberOfTest;
		this.onlineTests=tests;
		this.paths = path;
	}
	
}
