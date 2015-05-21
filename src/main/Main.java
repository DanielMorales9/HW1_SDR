package main;

import radio.Radio;

public class Main {

	
	public static void main(String[] args) throws Exception {
		String[] paths = new String[] { 
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
		Radio radio = new Radio();
		int numberOfTest = 10000, tests=100;
		double[] snr = new double[paths.length]; 
		double[] thresholds = new double[paths.length];
		double[] detectionPercentages = new double[paths.length];
		
 
		for (int i = 0; i< paths.length; i++) {
			radio.insertPathOfSignalFile(paths[i]);
			snr[i] = radio.chooseNumberOfTest(numberOfTest);
			thresholds[i] = radio.getThreshold();
			detectionPercentages[i] = radio.compareWithThreshold(tests);
			
		}
		System.out.println("SNR\tSoglia\tDETECTION");
		for (int i = 0; i <paths.length; i++) {
			System.out.println(snr[i]+"\t"+thresholds[i]+"\t"+detectionPercentages[i]);
		}
	}
}
