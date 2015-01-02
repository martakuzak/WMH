package graph;

import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {
	
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max) throws FileNotFoundException {

			File file = new File("output/"+ilosc_wierz+"_"+wag_min+"_"+wag_max+".txt"); //zmienilam ukosnik na / , bo tu (na Ubuntu) lecial FileNotFoundException. 
																						//A z takim ukosnikiem zadzialalo mi na Windowsie, daj znac jak u Ciebie
			Scanner scanner = new Scanner(file);
			scanner.next(); // VERTEX_PART_NUMBER
			int vpnumber = scanner.nextInt();
			//System.out.println("Liczba wierzcho³ków grafu: " + 2 * vpnumber);
			scanner.next(); //WEIGHT
			int minWeight = scanner.nextInt();
			int maxWeight = scanner.nextInt();
		//	System.out.println("Zakres wag krawêdzi: " + minWeight + " - " + maxWeight);
			//readEdges(scanner, vpnumber);
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
