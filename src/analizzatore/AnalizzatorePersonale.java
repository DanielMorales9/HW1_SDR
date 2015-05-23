package analizzatore;

import radio.Radio;

/**
 * Classe che astrae il concetto di analizzatore con i parametri operativi 
 * settati dall'utente.
 * @see AbstractAnalizzatore
 * @author Daniel, Antonio
 * */

public class AnalizzatorePersonale extends AbstractAnalizzatore {

	public AnalizzatorePersonale(String[] path, double falsoAllarme, 
			int noiseSample,int numberOfTest, int tests){
		this.radio = new Radio(falsoAllarme, noiseSample);
		this.offlineTests = numberOfTest;
		this.onlineTests=tests;
		this.paths = path;
	}
	
}
