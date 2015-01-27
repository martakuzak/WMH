package graph;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Klasa s³u¿¹ca do czytania z pliku dwudzielnych symetrycznych pe³nych grafów
 * wa¿onych.
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class GraphReader {
	/**
	 * Domyœlny katalog, z którego czytane s¹ pliki z grafami
	 */
	final private static String OUT_DIR = "output";

	/**
	 * Zwraca liczbê wierzcho³ków jednego przedzia³u grafu.
	 * 
	 * @param fileName
	 *            Nazwa pliku, w którym opisywany jest graf.
	 * @return Liczbê wierzcho³ków jednego przedzia³u grafu.
	 * @throws FileNotFoundException
	 */
	public static int getVpNum(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		scanner.next(); // VERTEX_PART_NUMBER
		int vpnumber = scanner.nextInt();
		scanner.close();
		return vpnumber;
	}

	/**
	 * Wczytuje graf z pliku znajduj¹cym siê w domyœlnym katalogu (OUT_DIR) o
	 * domyœlnej nazwie "$ilosc_wierzch$_$wag_min$_$wag_max$.txt" (np. dla grafu
	 * o ilosc_wierzch = 3, wag_min = 4, wag_max = 6 nazwa pliku to "3_4_6.txt"
	 * 
	 * @param ilosc_wierz
	 *            Liczba wierzcho³ków jednego przedzia³u grafu.
	 * @param wag_min
	 *            Minimalna wartoœæ wagi krawêdzi.
	 * @param wag_max
	 *            Maksymalna wartoœæ wagi krawêdzi.
	 * @return Macierz reprezentuj¹c¹ graf, dla którego ma byæ dokonany
	 *         przydzia³. Element tablicy graph[i][j] wskazuje na wagê krawêdzi
	 *         pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *         wierzcho³kiem j (z drugiego przedzia³u)
	 * @throws FileNotFoundException
	 */
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max)
			throws FileNotFoundException {
		String fileName = new String(OUT_DIR + "/" + ilosc_wierz + "_"
				+ wag_min + "_" + wag_max + ".txt");
		return readFile(fileName);
	}

	/**
	 * Wczytuje graf z pliku znajduj¹cym siê w zadanym katalogu o domyœlnej
	 * nazwie "$ilosc_wierzch$_$wag_min$_$wag_max$.txt" (np. dla grafu o
	 * ilosc_wierzch = 3, wag_min = 4, wag_max = 6 nazwa pliku to "3_4_6.txt"
	 * 
	 * @param ilosc_wierz
	 *            Liczba wierzcho³ków jednego przedzia³u grafu.
	 * @param wag_min
	 *            Minimalna wartoœæ wagi krawêdzi.
	 * @param wag_max
	 *            Maksymalna wartoœæ wagi krawêdzi.
	 * @param outDir
	 *            Œcie¿ka do katalogu, w którym bêdzie znajdowa³ siê plik
	 *            wyjœciowy.
	 * @return Macierz reprezentuj¹c¹ graf, dla którego ma byæ dokonany
	 *         przydzia³. Element tablicy graph[i][j] wskazuje na wagê krawêdzi
	 *         pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *         wierzcho³kiem j (z drugiego przedzia³u)
	 * @throws FileNotFoundException
	 */
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max,
			String outDir) throws FileNotFoundException {
		String allFileName = new String(outDir + "/" + ilosc_wierz + "_"
				+ wag_min + "_" + wag_max + ".txt");
		return readFile(allFileName);
	}

	/**
	 * Wczytuje graf z pliku o zadanej nazwie.
	 * 
	 * @param fileName
	 *            Pe³na nazwa pliku, w którym znajduje siê opis grafu (ze
	 *            œcie¿k¹).
	 * @return Macierz reprezentuj¹c¹ graf, dla którego ma byæ dokonany
	 *         przydzia³. Element tablicy graph[i][j] wskazuje na wagê krawêdzi
	 *         pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *         wierzcho³kiem j (z drugiego przedzia³u)
	 * @throws FileNotFoundException
	 */
	public static int[][] readFile(String fileName)
			throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		scanner.next(); // VERTEX_PART_NUMBER
		int vpnumber = scanner.nextInt();
		scanner.next(); // WEIGHT
		scanner.nextInt(); // minimalna waga
		scanner.nextInt(); // maksymalna waga
		int[][] ttab = readEdges(scanner, vpnumber);
		scanner.close();
		return ttab;
	}

	/**
	 * Wczytuje grafy ze wszystkich plików z zadanej lokalizacji.
	 * 
	 * @param fileName
	 *            Pe³na œcie¿ka do katalogu, w którym znajduj¹ siê pliki
	 *            opisuj¹ce grafy.
	 * @return Tablica macierzy reprezentujacych grafy. Element tablicy
	 *         graph[k][i][j] wskazuje na wagê krawêdzi pomiêdzy wierzcho³kiem i
	 *         (z pierwszego przedzia³u) i wierzcho³kiem j (z drugiego
	 *         przedzia³u) k-tego grafu.
	 * @throws FileNotFoundException
	 */
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
		// sortowanie grafów od najmniejszego do najwiekszego
		HashMap<Integer, int[][]> map = new HashMap<Integer, int[][]>();
		int[] vpnums = new int[fileNum];

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile()) {
				int vp = getVpNum(fileEntry.getAbsolutePath());
				map.put(vp, readFile(fileEntry.getAbsolutePath()));
				vpnums[idx] = vp;
				++idx;
			}
		}

		Arrays.sort(vpnums);

		for (int i = 0; i < idx; ++i)
			graphs[i] = map.get(vpnums[i]);
		return graphs;
	}

	/**
	 * Wczytuje wagi krawêdzi grafu.
	 * 
	 * @param scanner
	 *            Obiekt, który skanuje plik opisuj¹cy zadany graf (ju¿ z
	 *            rozpoczêtym skanowaniem).
	 * @param vpnumber
	 *            Liczba wierzcho³ków jednego przedzia³u grafu.
	 * @return Macierz reprezentuj¹c¹ graf, dla którego ma byæ dokonany
	 *         przydzia³. Element tablicy graph[i][j] wskazuje na wagê krawêdzi
	 *         pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *         wierzcho³kiem j (z drugiego przedzia³u)
	 */
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
		return tab;
	}

	/**
	 * Zwraca tablicê obiektów typu String, która zawiera opis grafu.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf. Element tablicy graph[i][j]
	 *            wskazuje na wagê krawêdzi pomiêdzy wierzcho³kiem i (z
	 *            pierwszego przedzia³u) i wierzcho³kiem j (z drugiego
	 *            przedzia³u)
	 * @return tablicê obiektów typu String, która zawiera opis grafu.
	 */
	public static String[][] displayGraph(int[][] graph) {

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

	/**
	 * Zwraca tablicê obiektów typu String, która zawiera opis rozwi¹zania
	 * grafu.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf. Element tablicy graph[i][j]
	 *            wskazuje na wagê krawêdzi pomiêdzy wierzcho³kiem i (z
	 *            pierwszego przedzia³u) i wierzcho³kiem j (z drugiego
	 *            przedzia³u)
	 * @return tablicê obiektów typu String, która zawiera opis grafu
	 *         rozwi¹zania grafu.
	 */
	public static String[] displaySol(int[][] graph) {

		int weight = 0;
		String s[] = new String[graph.length];

		for (int i = 0; i < graph.length; ++i) {
			for (int j = 0; j < graph.length; j++) {
				int v1 = i;
				int v2 = j;
				weight = graph[i][j];
				if (weight != 0) {
					s[i] = "(A" + (v1 + 1) + ",B" + (v2 + 1) + ")="
							+ graph[i][j] + "; ";
				}
			}
		}

		return s;
	}

}
