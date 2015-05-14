package main;

import java.util.Scanner;

import radio.Radio;

public class Main {

	public static void main(String[] args) throws Exception {
		Radio radio = new Radio();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ipotesi H0: Ascolto il segnale.");
		System.out.println("Inserisci il path del segnale.");
		String path = scanner.nextLine();
		String validationPathMessage = radio.insertPathOfSignalFile(path);
		if (validationPathMessage != null) {
			System.out.println(validationPathMessage);
			scanner.close();
			return;
		}
		System.out.println("Stabilisci numero di prove per le quali viene "
				+ "generato di volta in volta un nuovo rumore avente le "
				+ "stesse caratteristiche di valor atteso e varianza");
		int numberOfTest = scanner.nextInt();
		double snr = radio.chooseNumberOfTest(numberOfTest);
		System.out.println("L'SNR calcolato è: "+snr);
		System.out.println("Sto calcolando per ogni prova l'energia "
				+ "del rumore generato e lo sto memorizzando in un vettore."
				+ "\nSto calcolando la soglia...");
		double threshold = radio.getThreshold();
		System.out.println("La soglia è: "+threshold + "\n");
		System.out.println("Ascolto il segnale ricevuto...");
		System.out.println("Stabilisci il numero di prove da compiere online.");
		int tests = scanner.nextInt();
		double detectionPercentage = radio.compareWithThreshold(tests);
		System.out.println("\nSto calcolando le energie di ciascuna prova...");
		System.out.println("\nSto confrontando ciascuna energia con la soglia...");
		System.out.println("\nLa percentuale di detection è pari al: " + detectionPercentage+"%");
		scanner.close();
		System.out.println("Facciamo una simulazione");
		//		System.out.println("Scegli un valore di detection:");
		//		double detectionPercentage = scanner.nextDouble();
		//		System.out.println("Scegli un valore di SNR:");
		//		double snr = scanner.nextDouble();
		//		radio.createNewSimulation(detectionPercentage, snr);
		//		System.out.println("Stabilisci numero di prove per le quali viene "
		//				+ "generato di volta in volta un nuovo rumore avente le "
		//				+ "stesse caratteristiche di valor atteso e varianza");
		//		int numberOfTest = scanner.nextInt();
		//		snr = radio.chooseNumberOfTest(numberOfTest);
		//		System.out.println("L'SNR calcolato è: "+snr);
		//		System.out.println("Sto calcolando per ogni prova l'energia "
		//				+ "del rumore generato e lo sto memorizzando in un vettore."
		//				+ "\nSto calcolando la soglia...");
		//		double threshold = radio.getThreshold();
		//		System.out.println("La soglia è: "+threshold + "\n");
		//		System.out.println("Ascolto il segnale ricevuto...");
		//		System.out.println("Stabilisci il numero di prove da compiere online.");
		//		int tests = scanner.nextInt();
		//		detectionPercentage = radio.compareWithThreshold(tests);
		//		System.out.println("\nSto calcolando le energie di ciascuna prova...");
		//		System.out.println("\nSto confrontando ciascuna energia con la soglia...");
		//		System.out.println("\nLa percentuale di detection è pari al: " + detectionPercentage+"%");
		//		scanner.close();

	}


}
