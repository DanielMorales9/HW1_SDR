package analizzatore;

import radio.Radio;

/**
 * Classe analizzatore che incapsula i parametri operativi relativi 
 * a operazioni di detection in un ricevitore radio.
 * @see AbstractAnalizzatore
 * @author Daniel, Antonio
 * **/

public class AnalizzatoreDefault extends AbstractAnalizzatore {
	
	private static final String[] PATHS ={
			"Sequenza_1/output_1.dat", 
			"Sequenza_1/output_3.dat", 
			"Sequenza_1/output_4.dat",
			"Sequenza_2/output_1.dat", 
			"Sequenza_2/output_3.dat", 
			"Sequenza_2/output_4.dat",
			"Sequenza_3/output_1.dat", 
			"Sequenza_3/output_3.dat", 
			"Sequenza_3/output_4.dat",
	};
	
	public AnalizzatoreDefault(){
		this.radio = new Radio();
		this.offlineTests = 10000; 
		this.onlineTests = 1000; 
        this.paths = PATHS;
	}
}
