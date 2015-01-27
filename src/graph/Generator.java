package graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Generator dwudzielnych symetrycznych pe�nych graf�w wa�onych.
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class Generator {
	/**
	 * Domy�lny katalog, do kt�rego zapisywane s� pliki z utworzonymi grafami
	 */
	final private static String OUT_DIR = "output";

	/**
	 * Tworzy graf o zadanych parametrach i zapisuje go w domy�lnej lokalizacji
	 * (OUT_DIR).
	 * 
	 * @param vertexPartNumber
	 *            Liczba wierzcho�k�w jednego przedzia�u grafu
	 * @param minWeight
	 *            Minimalna waga kraw�dzi
	 * @param maxWeight
	 *            Maksymalna waga kraw�dzi
	 */
	public static void generateGraph(int vertexPartNumber, int minWeight,
			int maxWeight) {
		generateGraph(vertexPartNumber, minWeight, maxWeight, OUT_DIR);
	}

	/**
	 * Tworzy graf o zadanych parametrach i zapisuje go w zadanej lokalizacji.
	 * Graf tworzony jest w pliku
	 * outputDir/$vertexPartNumber$_$minWeight$_$maxWeight$.txt Np. dla
	 * outputDir = "D://" , vertexPartNumber = 5, minWeight = 3, maxWeight = 10
	 * nazwa pliku to: "D://5_3_10.txt"
	 * 
	 * @param vertexPartNumber
	 *            Liczba wierzcho�k�w jednego przedzia�u grafu
	 * @param minWeight
	 *            Minimalna waga kraw�dzi
	 * @param maxWeight
	 *            Maksymalna waga kraw�dzi
	 * @param outputDir
	 *            �cie�ka do katalogu, w kt�rym b�dzie znajdowa� si� plik
	 *            wyj�ciowy.
	 */
	public static void generateGraph(int vertexPartNumber, int minWeight,
			int maxWeight, String outputDir) {
		if (maxWeight < minWeight) {
			System.err
					.println("MAX_WEIGHT must not be smaller than MIN_WEIGHT");
			return;
		} else if (maxWeight < 0 || minWeight < 0) {
			System.err.println("Weights must not be negative");
			return;
		} else if (vertexPartNumber <= 0) {
			System.err.println("VERTEX_PART_NUMBER must be positive");
			return;
		}

		new File(outputDir).mkdir();
		final File file = new File(outputDir + "/" + vertexPartNumber + "_"
				+ minWeight + "_" + maxWeight + ".txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			final FileWriter fw = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter bw = new BufferedWriter(fw);
			bw.write("VERTEX_PART_NUMBER\t" + vertexPartNumber + "\n");
			bw.write("WEIGHT\t" + minWeight + " " + maxWeight + "\n");
			for (int firstV = 1; firstV < vertexPartNumber + 1; ++firstV) {
				for (int secondV = 1; secondV < vertexPartNumber + 1; ++secondV) {
					final Random generator = new Random();
					final int weight = generator.nextInt(maxWeight - minWeight
							+ 1)
							+ minWeight;
					bw.write(firstV + "\t" + secondV + "\t" + weight + "\n");
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tworz� grafy o liczbie wierzcho�k�w od 2*minVertexPartNumber do
	 * 2*maxVertexPartNumber o zadanym zakresie wag
	 * 
	 * @param minVertexPartNumber
	 *            Minimalna liczba wierzcho�k�w jednego przedzia�u grafu
	 * @param maxVertexPartNumber
	 *            Maksymalna wierzcho�k�w jednego przedzia�u grafu
	 * @param minWeight
	 *            Minimalna waga kraw�dzi
	 * @param maxWeight
	 *            Maksymalna waga kraw�dzi
	 */
	public static void generateGraphs(int minVertexPartNumber,
			int maxVertexPartNumber, int minWeight, int maxWeight) {
		generateGraphs(minVertexPartNumber, maxVertexPartNumber, minWeight,
				maxWeight, OUT_DIR);
	}

	/**
	 * Tworz� grafy o liczbie wierzcho�k�w od 2*minVertexPartNumber do
	 * 2*maxVertexPartNumber o zadanym zakresie wag
	 * 
	 * @param minVertexPartNumber
	 *            Minimalna liczba wierzcho�k�w jednego przedzia�u grafu
	 * @param maxVertexPartNumber
	 *            Maksymalna wierzcho�k�w jednego przedzia�u grafu
	 * @param minWeight
	 *            Minimalna waga kraw�dzi
	 * @param maxWeight
	 *            Maksymalna waga kraw�dzi
	 * @param outputDir
	 *            �cie�ka do katalogu, w kt�rym b�dzie znajdowa� si� plik
	 *            wyj�ciowy.
	 */
	public static void generateGraphs(int minVertexPartNumber,
			int maxVertexPartNumber, int minWeight, int maxWeight,
			String outputDir) {
		for (int vpnumber = minVertexPartNumber; vpnumber < maxVertexPartNumber; ++vpnumber)
			generateGraph(vpnumber, minWeight, maxWeight, outputDir);

	}

}
