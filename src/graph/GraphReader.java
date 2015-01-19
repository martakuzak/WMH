package graph;

import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {
	
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max) throws FileNotFoundException {
		String fileName = new String("output/"+ilosc_wierz+"_"+wag_min+"_"+wag_max+".txt");
		return readFile(fileName);
	}

	public static int[][] readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName); 
		Scanner scanner = new Scanner(file);
		scanner.next(); // VERTEX_PART_NUMBER
		int vpnumber = scanner.nextInt();
		scanner.next(); //WEIGHT
		int minWeight = scanner.nextInt();
		int maxWeight = scanner.nextInt();
		int [][] ttab = readEdges(scanner, vpnumber);
		scanner.close();
		return ttab;
	}
	
	
	public static int[][] readEdges(Scanner scanner, int vpnumber) {
		
        int [][] tab = new int[vpnumber][vpnumber]; 

		
		for (int i = 0; i < vpnumber; ++ i) {
			for(int j=0; j<vpnumber; j++) {
				scanner.nextInt(); //nr wierzcholka A
				scanner.nextInt(); //nr wierzcholka B
				int weight = scanner.nextInt();
				tab[i][j] = weight;
			
		}
	}
            System.out.println(Arrays.deepToString(tab));
            return tab;
	}
	
	
	
	public static String[][] displayGraph(int [][] graph) {           // macierz stringów... suabo... 
		
		String [][] ss = new String [graph.length][graph.length];
		
		for (int i = 0; i < graph.length; ++ i) {
			for(int j=0; j<graph.length; j++) {
			int v1 = i;
			int v2 = j;
			int weight = graph[i][j];
			ss [i][j] = "KrawêdŸ (A" +   (v1+1) + ", B" + (v2+1) +")  waga: " + weight+"\n";
		}
	}
		return ss;
	}
		
	
}
