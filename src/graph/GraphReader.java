package graph;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Klasa s�u��ca do czytania z pliku dwudzielnych symetrycznych pe�nych graf�w
 * wa�onych.
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class GraphReader {
	/**
	 * Domy�lny katalog, z kt�rego czytane s� pliki z grafami
	 */
	final private static String OUT_DIR = "output";

	/**
	 * Zwraca liczb� wierzcho�k�w jednego przedzia�u grafu.
	 * 
	 * @param fileName
	 *            Nazwa pliku, w kt�rym opisywany jest graf.
	 * @return Liczb� wierzcho�k�w jednego przedzia�u grafu.
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
	 * Wczytuje graf z pliku znajduj�cym si� w domy�lnym katalogu (OUT_DIR) o
	 * domy�lnej nazwie "$ilosc_wierzch$_$wag_min$_$wag_max$.txt" (np. dla grafu
	 * o ilosc_wierzch = 3, wag_min = 4, wag_max = 6 nazwa pliku to "3_4_6.txt"
	 * 
	 * @param ilosc_wierz
	 *            Liczba wierzcho�k�w jednego przedzia�u grafu.
	 * @param wag_min
	 *            Minimalna warto�� wagi kraw�dzi.
	 * @param wag_max
	 *            Maksymalna warto�� wagi kraw�dzi.
	 * @return Macierz reprezentuj�c� graf, dla kt�rego ma by� dokonany
	 *         przydzia�. Element tablicy graph[i][j] wskazuje na wag� kraw�dzi
	 *         pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *         wierzcho�kiem j (z drugiego przedzia�u)
	 * @throws FileNotFoundException
	 */
	public static int[][] readFile(int ilosc_wierz, int wag_min, int wag_max)
			throws FileNotFoundException {
		String fileName = new String(OUT_DIR + "/" + ilosc_wierz + "_"
				+ wag_min + "_" + wag_max + ".txt");
		return readFile(fileName);
	}

	/**
	 * Wczytuje graf z pliku znajduj�cym si� w zadanym katalogu o domy�lnej
	 * nazwie "$ilosc_wierzch$_$wag_min$_$wag_max$.txt" (np. dla grafu o
	 * ilosc_wierzch = 3, wag_min = 4, wag_max = 6 nazwa pliku to "3_4_6.txt"
	 * 
	 * @param ilosc_wierz
	 *            Liczba wierzcho�k�w jednego przedzia�u grafu.
	 * @param wag_min
	 *            Minimalna warto�� wagi kraw�dzi.
	 * @param wag_max
	 *            Maksymalna warto�� wagi kraw�dzi.
	 * @param outDir
	 *            �cie�ka do katalogu, w kt�rym b�dzie znajdowa� si� plik
	 *            wyj�ciowy.
	 * @return Macierz reprezentuj�c� graf, dla kt�rego ma by� dokonany
	 *         przydzia�. Element tablicy graph[i][j] wskazuje na wag� kraw�dzi
	 *         pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *         wierzcho�kiem j (z drugiego przedzia�u)
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
	 *            Pe�na nazwa pliku, w kt�rym znajduje si� opis grafu (ze
	 *            �cie�k�).
	 * @return Macierz reprezentuj�c� graf, dla kt�rego ma by� dokonany
	 *         przydzia�. Element tablicy graph[i][j] wskazuje na wag� kraw�dzi
	 *         pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *         wierzcho�kiem j (z drugiego przedzia�u)
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
	 * Wczytuje grafy ze wszystkich plik�w z zadanej lokalizacji.
	 * 
	 * @param fileName
	 *            Pe�na �cie�ka do katalogu, w kt�rym znajduj� si� pliki
	 *            opisuj�ce grafy.
	 * @return Tablica macierzy reprezentujacych grafy. Element tablicy
	 *         graph[k][i][j] wskazuje na wag� kraw�dzi pomi�dzy wierzcho�kiem i
	 *         (z pierwszego przedzia�u) i wierzcho�kiem j (z drugiego
	 *         przedzia�u) k-tego grafu.
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
		// sortowanie graf�w od najmniejszego do najwiekszego
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
	 * Wczytuje wagi kraw�dzi grafu.
	 * 
	 * @param scanner
	 *            Obiekt, kt�ry skanuje plik opisuj�cy zadany graf (ju� z
	 *            rozpocz�tym skanowaniem).
	 * @param vpnumber
	 *            Liczba wierzcho�k�w jednego przedzia�u grafu.
	 * @return Macierz reprezentuj�c� graf, dla kt�rego ma by� dokonany
	 *         przydzia�. Element tablicy graph[i][j] wskazuje na wag� kraw�dzi
	 *         pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *         wierzcho�kiem j (z drugiego przedzia�u)
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
	 * Zwraca tablic� obiekt�w typu String, kt�ra zawiera opis grafu.
	 * 
	 * @param graph
	 *            Macierz reprezentuj�ca graf. Element tablicy graph[i][j]
	 *            wskazuje na wag� kraw�dzi pomi�dzy wierzcho�kiem i (z
	 *            pierwszego przedzia�u) i wierzcho�kiem j (z drugiego
	 *            przedzia�u)
	 * @return tablic� obiekt�w typu String, kt�ra zawiera opis grafu.
	 */
	public static String[][] displayGraph(int[][] graph) {

		String[][] ss = new String[graph.length][graph.length];

		for (int i = 0; i < graph.length; ++i) {
			for (int j = 0; j < graph.length; j++) {
				int v1 = i;
				int v2 = j;
				int weight = graph[i][j];
				ss[i][j] = "Kraw�d� (A" + (v1 + 1) + ", B" + (v2 + 1)
						+ ")  waga: " + weight + "\n";
			}
		}
		return ss;
	}

	/**
	 * Zwraca tablic� obiekt�w typu String, kt�ra zawiera opis rozwi�zania
	 * grafu.
	 * 
	 * @param graph
	 *            Macierz reprezentuj�ca graf. Element tablicy graph[i][j]
	 *            wskazuje na wag� kraw�dzi pomi�dzy wierzcho�kiem i (z
	 *            pierwszego przedzia�u) i wierzcho�kiem j (z drugiego
	 *            przedzia�u)
	 * @return tablic� obiekt�w typu String, kt�ra zawiera opis grafu
	 *         rozwi�zania grafu.
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
