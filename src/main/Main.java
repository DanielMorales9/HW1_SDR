package main;

import java.util.Scanner;

import analizzatore.AbstractAnalizzatore;
import analizzatore.AnalizzatoreDefault;
import analizzatore.AnalizzatorePersonale;

/**
 * Classe Main da cui è possibile mostrare l'output dell'analisi sulle sequenze di test (HOMEWORK)
 * oppure ascoltare una propria sequenza, definire i parametri operativi e visualizzare i risultati.
 * **/

public class Main {

	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		String choose;
		AbstractAnalizzatore esecutore;
		do {
			System.out.println("Benvenuto, cosa vuoi fare?\n"
					+ "digita 't' se vuoi visualizzare i risultati sulle sequenze dell'Homework;\n"
					+ "digita 'l' se vuoi ascoltare segnale personale.\n");
			choose = scanner.nextLine();
			choose.toLowerCase();

			if (choose.equals("t")) {
				System.out.println("Sto calcolando i risultati delle sequenze...");
				esecutore = new AnalizzatoreDefault();
			}
			else {
				System.out.println("Hai scelto di analizzare un segnale personale...");

				System.out.println("Inserisci il path del segnale.");
				String[] path = {scanner.nextLine()};

				System.out.println("Inserisci il numero di campioni di rumore da sommare al tuo segnale:");
				int noiseSamples = scanner.nextInt();

				System.out.println("Stabilisci la probabilità di falso allarme da rispettare, ad esempio 0,001:");
				double falsoAllarme = scanner.nextDouble();

				System.out.println("Stabilisci il numero di prove da compiere offline (H0):");
				int numberOfTest = scanner.nextInt();

				System.out.println("Stabilisci il numero di prove da compiere online (H1):");
				int tests = scanner.nextInt();

				esecutore = new AnalizzatorePersonale(path,falsoAllarme,noiseSamples,numberOfTest,tests);
				
				System.out.println("\n Calcolo i risultati. . .\n");
			}

			try {
				esecutore.analizza();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Vuoi Riprovare? s/n");
			scanner.nextLine();
			choose = scanner.nextLine();
			choose.toLowerCase();

		} while (choose.equals("s")); 
		scanner.close();	
	    System.out.println("\n Arrivederci! \n");
	}
}
