package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {
	public static void readFile(File file) {
		try { // analizowanie pliku wejsciowego
			Scanner scanner = new Scanner(file);
			scanner.next(); // VERTEX_PART_NUMBER
			int vpnumber = scanner.nextInt();
			System.out.println("Liczba wierzcho³ków grafu: " + 2 * vpnumber);
			scanner.next(); //WEIGHT
			int minWeight = scanner.nextInt();
			int maxWeight = scanner.nextInt();
			System.out.println("Zakres wag krawêdzi: " + minWeight + " - " + maxWeight);
			readEdges(scanner, vpnumber);
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static void readEdges(Scanner scanner, int vpnumber) {
		int edgeNumber = vpnumber * vpnumber;
		for (int i = 0; i < edgeNumber; ++ i) {
			int v1 = scanner.nextInt();
			int v2 = scanner.nextInt();
			int weight = scanner.nextInt();
			System.out.println("KrawêdŸ (" +   v1 + ", " + v2 +") waga: " + weight);
		}
	}

}
