package radio.signal;

import radio.signalprocessing.SignalProcessor;

/**
 * Classe che modella un segnale nel discreto.
 * @extends AbstractSignal
 * **/

public class Signal extends AbstractSignal {

	/**
	 * Permette di costruire un segnale nel discreto a partire dal numero di campioni.
	 * La genereazione dello stesso sara' casuale. 
	 * I simboli che avro' saranno quelli di una modulazione QPSK e quindi i valori possibili sono: 
	 * 1 + j, 1 - j, -1 + j, -1 - j,
	 *@param int lunghezzaSequenza
	 *@return Signal segnale
	 **/
	public Signal(int length) {
		this.samples = new Complex[length];
		for (int i = 0; i < length; i++) { 
			super.setSample(i, new Complex());
			double v = Math.random();
			if (v < 0.5)
				this.samples[i].setpReal(1/Math.sqrt(2)); 
			else
				this.samples[i].setpReal(-1/Math.sqrt(2)); 
			double p = Math.random(); 
			if (p < 0.5)
				this.samples[i].setpImg(1/Math.sqrt(2)); 
			else
				this.samples[i].setpImg(-1/Math.sqrt(2));
		}
	}

	public Signal() {
	}

	/** OPERAZIONI **/
	
	/**
	 * Medoto che permette di ottenere l'SNR di un dato segnale
	 * Nel particolare l'SNR  ottenuto a partire dalla potenza del segnale
	 * e il calcolo e' effettuato nella classe SignalProcessor.
	 * @See SignalProcessor
	 * @return double SNR
	 **/
	public double getSNR() {
		double signalPower = SignalProcessor.power(this);
		return SignalProcessor.calculateSNRFromPower(signalPower);
	}




}
