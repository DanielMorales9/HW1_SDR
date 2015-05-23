package analizzatore;

import radio.Radio;

/**
 * Classe che crea un esecutore e permette di mostrare gli output di una sequenza di test.
 * COME SEQUENZE DI TEST SONO STATE USATE QUELLE DELL'HOMEWORK
 * **/

public class AnalizzatoreDefault extends AbstractAnalizzatore {
	
	private final String[] PATHS ={
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_1/output_1.dat", 
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_1/output_3.dat", 
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_1/output_4.dat",
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_2/output_1.dat", 
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_2/output_3.dat", 
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_2/output_4.dat",
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_3/output_1.dat", 
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_3/output_3.dat", 
			"/Users/Daniel/Desktop/workspace_sdr/HW1/Sequenza_3/output_4.dat",
	};
	
	public AnalizzatoreDefault(){
		this.radio = new Radio();
		this.offlineTests = 10000; //ipotesi h0
		this.onlineTests = 1000; //ipotesi h1
        this.paths = PATHS;
//		this.paths = new String[] { 
//				"C:/Users/Antonio/git/HW1_SDRD/Sequenza_1/output_1.dat",
//				};
	}
}
