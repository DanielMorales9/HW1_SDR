package main;

import java.util.Scanner;

import analizzatore.AbstractAnalizzatore;
import analizzatore.AnalizzatoreDefault;
import analizzatore.AnalizzatorePersonale;

/**
 * Classe Main da cui e' possibile mostrare l'output dell'analisi sulle sequenze di test
 * oppure ascoltare una propria sequenza definiti i parametri operativi.
 * Infine visualizza i risultati.
 **/

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String choose;
		AbstractAnalizzatore esecutore;
		do {
			System.out.println("Benvenuto, cosa vuoi fare?\n"
					+ "digita 'h' se vuoi visualizzare i risultati ottenuti dalle sequenze dell'Homework;\n"
					+ "digita 's' se vuoi ascoltare un segnale personale.\n");
			choose = scanner.nextLine();
			choose.toLowerCase();

			if (choose.equals("h")) {
				System.out.println("Sto calcolando i risultati delle sequenze...");
				esecutore = new AnalizzatoreDefault();
			}
			else {
				System.out.println("Hai scelto di analizzare un segnale personale...");

				System.out.println("Inserisci il path del segnale.");
				String[] path = {scanner.nextLine()};

				System.out.println("Inserisci il numero di campioni di rumore con cui fare le prove:");
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

			esecutore.analizza();
			
			System.out.println("Vuoi Riprovare? s/n");
			choose = scanner.nextLine();
			choose = scanner.nextLine();
			choose.toLowerCase();

		} while (choose.equals("s")); 
		scanner.close();	
		System.out.println("\n Arrivederci! \n");
	}
}
