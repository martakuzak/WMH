package graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Generator dwudzielnych symetrycznych pe³nych grafów wa¿onych.
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class Generator {
	/**
	 * Domyœlny katalog, do którego zapisywane s¹ pliki z utworzonymi grafami
	 */
	final private static String OUT_DIR = "output";

	/**
	 * Tworzy graf o zadanych parametrach i zapisuje go w domyœlnej lokalizacji
	 * (OUT_DIR).
	 * 
	 * @param vertexPartNumber
	 *            Liczba wierzcho³ków jednego przedzia³u grafu
	 * @param minWeight
	 *            Minimalna waga krawêdzi
	 * @param maxWeight
	 *            Maksymalna waga krawêdzi
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
	 *            Liczba wierzcho³ków jednego przedzia³u grafu
	 * @param minWeight
	 *            Minimalna waga krawêdzi
	 * @param maxWeight
	 *            Maksymalna waga krawêdzi
	 * @param outputDir
	 *            Œcie¿ka do katalogu, w którym bêdzie znajdowa³ siê plik
	 *            wyjœciowy.
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
	 * Tworz¹ grafy o liczbie wierzcho³ków od 2*minVertexPartNumber do
	 * 2*maxVertexPartNumber o zadanym zakresie wag
	 * 
	 * @param minVertexPartNumber
	 *            Minimalna liczba wierzcho³ków jednego przedzia³u grafu
	 * @param maxVertexPartNumber
	 *            Maksymalna wierzcho³ków jednego przedzia³u grafu
	 * @param minWeight
	 *            Minimalna waga krawêdzi
	 * @param maxWeight
	 *            Maksymalna waga krawêdzi
	 */
	public static void generateGraphs(int minVertexPartNumber,
			int maxVertexPartNumber, int minWeight, int maxWeight) {
		generateGraphs(minVertexPartNumber, maxVertexPartNumber, minWeight,
				maxWeight, OUT_DIR);
	}

	/**
	 * Tworz¹ grafy o liczbie wierzcho³ków od 2*minVertexPartNumber do
	 * 2*maxVertexPartNumber o zadanym zakresie wag
	 * 
	 * @param minVertexPartNumber
	 *            Minimalna liczba wierzcho³ków jednego przedzia³u grafu
	 * @param maxVertexPartNumber
	 *            Maksymalna wierzcho³ków jednego przedzia³u grafu
	 * @param minWeight
	 *            Minimalna waga krawêdzi
	 * @param maxWeight
	 *            Maksymalna waga krawêdzi
	 * @param outputDir
	 *            Œcie¿ka do katalogu, w którym bêdzie znajdowa³ siê plik
	 *            wyjœciowy.
	 */
	public static void generateGraphs(int minVertexPartNumber,
			int maxVertexPartNumber, int minWeight, int maxWeight,
			String outputDir) {
		for (int vpnumber = minVertexPartNumber; vpnumber < maxVertexPartNumber; ++vpnumber)
			generateGraph(vpnumber, minWeight, maxWeight, outputDir);

	}

}
