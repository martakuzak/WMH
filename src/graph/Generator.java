package graph;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {
	final static String OUT_DIR = "output";

	public static void generateGraph (int VERTEX_PART_NUMBER, int MIN_WEIGHT, int MAX_WEIGHT) {
		generateGraph(VERTEX_PART_NUMBER, MIN_WEIGHT, MAX_WEIGHT, OUT_DIR);
	}

	public static void generateGraph (int VERTEX_PART_NUMBER, int MIN_WEIGHT, int MAX_WEIGHT, String outputDir) {
		if (MAX_WEIGHT < MIN_WEIGHT) {
			System.err
					.println("MAX_WEIGHT must not be smaller than MIN_WEIGHT");
			return;
		} else if (MAX_WEIGHT < 0 || MIN_WEIGHT < 0) {
			System.err.println("Weights must not be negative");
			return;
		} else if (VERTEX_PART_NUMBER <= 0) {
			System.err.println("VERTEX_PART_NUMBER must be positive");
			return;
		}

		new File(outputDir).mkdir();
		final File file = new File(outputDir + "/" + VERTEX_PART_NUMBER + "_" + MIN_WEIGHT + "_"
				+ MAX_WEIGHT + ".txt");

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
			bw.write("VERTEX_PART_NUMBER\t" + VERTEX_PART_NUMBER + "\n");
			bw.write("WEIGHT\t" + MIN_WEIGHT + " " + MAX_WEIGHT + "\n");
			for (int firstV = 1; firstV < VERTEX_PART_NUMBER + 1; ++ firstV) {
				for (int secondV = 1; secondV < VERTEX_PART_NUMBER + 1; ++ secondV) {
					final Random generator = new Random();
					final int weight = generator.nextInt(MAX_WEIGHT
							- MIN_WEIGHT + 1)
							+ MIN_WEIGHT;
					bw.write(firstV + "\t" + secondV + "\t" + weight + "\n");
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void generateGraphs(int MIN_VERTEX_PART_NUMBER, int MAX_VERTEX_PART_NUMBER, int MIN_WEIGHT, int MAX_WEIGHT, String outputDir)  {
		for (int vpnumber = MIN_VERTEX_PART_NUMBER; vpnumber < MAX_VERTEX_PART_NUMBER; ++ vpnumber)
			generateGraph(vpnumber, MIN_WEIGHT, MAX_WEIGHT, outputDir);
		
	}
	
	public static void generateGraphs(int MIN_VERTEX_PART_NUMBER, int MAX_VERTEX_PART_NUMBER, int MIN_WEIGHT, int MAX_WEIGHT)  {
		generateGraphs(MIN_VERTEX_PART_NUMBER, MAX_VERTEX_PART_NUMBER, MIN_WEIGHT, MAX_WEIGHT, OUT_DIR);
	}

/*	static String getFileName() {
		return new String(VERTEX_PART_NUMBER + "_" + MIN_WEIGHT + "_"
				+ MAX_WEIGHT + ".txt");
	}*/
	
	int  MAX_WEIGHT, MIN_WEIGHT, VERTEX_PART_NUMBER;
}
