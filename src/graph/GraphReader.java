package graph;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {

	public static int getVpNum(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		scanner.next(); // VERTEX_PART_NUMBER
		int vpnumber = scanner.nextInt();
		return vpnumber;
	}
	
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max)
			throws FileNotFoundException {
		String fileName = new String("output/" + ilosc_wierz + "_" + wag_min
				+ "_" + wag_max + ".txt");
		return readFile(fileName);
	}
	
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max, String outDir) 
			throws FileNotFoundException {
		String allFileName = new String(outDir + "/"+ ilosc_wierz + "_" + wag_min
				+ "_" + wag_max + ".txt");
		System.out.println(allFileName);
		return readFile(allFileName);
	}

	public static int[][] readFile(String fileName)
			throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		scanner.next(); // VERTEX_PART_NUMBER
		int vpnumber = scanner.nextInt();
		scanner.next(); // WEIGHT
		int minWeight = scanner.nextInt();
		int maxWeight = scanner.nextInt();
		int[][] ttab = readEdges(scanner, vpnumber);
		scanner.close();
		return ttab;
	}

	public static int[][][] readAllFilesInDir(String dir)
			throws FileNotFoundException {
		int[][][] graphs;

		int fileNum = 0;
		final File folder = new File(dir);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile()) 
				++fileNum;
		}
		graphs = new int[fileNum][][];

		int idx = 0; 
		HashMap<Integer,int[][]> map = new HashMap<Integer,int[][]>(); //te cuda sa po to, zeby posortowac grafy od najmniejszego do najwiekszego :D`
		int[] vpnums = new int[fileNum];
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile()) {
				int vp = getVpNum(fileEntry.getAbsolutePath());
				map.put(vp, readFile(fileEntry.getAbsolutePath()));
				vpnums[idx] = vp;
				//graphs[idx] = readFile(fileEntry.getAbsolutePath());
				++idx;
			}
		}
		
		Arrays.sort(vpnums);
		
		for(int i = 0; i < idx; ++i)
			graphs[i] = map.get(vpnums[i]);
		return graphs;
	}

	public static int[][] readEdges(Scanner scanner, int vpnumber) {

		int[][] tab = new int[vpnumber][vpnumber];

		for (int i = 0; i < vpnumber; ++i) {
			for (int j = 0; j < vpnumber; j++) {
				scanner.nextInt(); // nr wierzcholka A
				scanner.nextInt(); // nr wierzcholka B
				int weight = scanner.nextInt();
				tab[i][j] = weight;

			}
		}
		//System.out.println(Arrays.deepToString(tab));
		return tab;
	}

	public static String[][] displayGraph(int[][] graph) { // macierz
															// stringów...
															// suabo...

		String[][] ss = new String[graph.length][graph.length];

		for (int i = 0; i < graph.length; ++i) {
			for (int j = 0; j < graph.length; j++) {
				int v1 = i;
				int v2 = j;
				int weight = graph[i][j];
				ss[i][j] = "KrawêdŸ (A" + (v1 + 1) + ", B" + (v2 + 1)
						+ ")  waga: " + weight + "\n";
			}
		}
		return ss;
	}
	


	public static String [] displaySol(int[][] graph) { // macierz
		// stringów...
		// suabo...

		int weight = 0;
		String[][] ss = new String[graph.length][graph.length];
		String s [] = new String [graph.length];

		for (int i = 0; i < graph.length; ++i) {
			for (int j = 0; j < graph.length; j++) {
				int v1 = i;
				int v2 = j;
				 weight = graph[i][j];
				if (weight != 0) {
				//System.out.print("(A" + v1 + ",B" + v2 +") waga: " +graph[i][j] + ", " + "\n");
				s [i]= "(A" + (v1+1) + ",B" + (v2+1) +")=" + graph[i][j] +"; " ;
				}
			}
		}

		return s;
}	
	
	

}
