package alg;

/**
 * 
 * Klasa pozwalaj¹ca na rozwi¹zanie problem przydzia³u w dwudzielnym
 * symetrycznym grafie wa¿onym za pomoc¹ metody pe³nego przegl¹du grafu.
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class FullSearch {

	/**
	 * Funkcja znajduj¹ca najlepsze rozwi¹zanie problemu przydzia³u w grafie
	 * wa¿onym za pomoc¹ pe³nego przegl¹du grafu.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @return Najlepsze rozwi¹zanie (czas nie jest mierzony, jego wartoœæ jest
	 *         ustawiana arbitralnie na -1).
	 */
	public static Result getBestResult(int[][] graph) {
		// inicjalizacja zmiennych rezultatu
		int bestSum = Integer.MAX_VALUE;
		int[] bestPermutation = new int[graph.length];
		// szukanie wszystkich permutacji
		int n = graph.length;
		int[] t = new int[n];
		for (int i = 0; i < n; ++i)
			t[i] = i;
		bestPermutation = t.clone();
		while (true) {
			int sum = getSum(graph, t);
			if (sum < bestSum) {
				bestPermutation = t.clone();
				bestSum = sum;
			}
			/* Szukamy najdalszego elementu do zmiany */
			int i = n - 1;
			while (i > 0 && t[i - 1] >= t[i])
				i--;
			if (i == 0)
				break; /*
						 * Ciag posortowany nierosnaco /* Wykonujemy zamiane
						 */
			int j = i;
			while (j < n && t[j] > t[i - 1])
				j++;
			j--;

			int tmp1 = t[i - 1];
			t[i - 1] = t[j];
			t[j] = tmp1;
			t = reverse(t, i, n);

		}

		return new Result(-1, bestSum, getGraph(graph, bestPermutation));
	}

	/**
	 * Funkcja wyznaczaj¹ca sumê aktualnego rozwi¹zania (opisywanego przez
	 * parametry funkcji).
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param permutation
	 *            Tablica wskazuj¹ca na s¹siadów wierzcho³ków grafu po danym
	 *            przydziale. i-ty element tablicy wskazuje na indeks s¹siada (z
	 *            drugiego przedzia³u) i-tego wierzcho³ka pierwszego przedzia³u.
	 * @return Sumê krawêdzi grafu bêd¹cego aktualnym rozwi¹zaniem.
	 */
	private static int getSum(int[][] graph, int[] permutation) {
		int sum = 0;
		for (int i = 0; i < graph.length; ++i) {
			sum += graph[i][permutation[i]];
		}
		return sum;
	}

	/**
	 * Funkcja wyznaczaj¹ca graf aktualnego rozwi¹zania (opisywanego przez
	 * parametry funkcji).
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param permutation
	 *            Tablica wskazuj¹ca na s¹siadów wierzcho³ków grafu po danym
	 *            przydziale. i-ty element tablicy wskazuje na indeks s¹siada (z
	 *            drugiego przedzia³u) i-tego wierzcho³ka pierwszego przedzia³u.
	 * @return Graf bêd¹cy aktualnym rozwi¹zaniem.
	 */
	private static int[][] getGraph(int[][] graph, int[] permutation) {
		int[][] output = new int[graph.length][graph.length];

		for (int i = 0; i < permutation.length; ++i)
			output[i][permutation[i]] = graph[i][permutation[i]];
		return output;
	}

	/**
	 * Odwrócenie kolejnoœci elementów tablicy input od indeksu idx1 do indeksu
	 * idx2.
	 * 
	 * @param input
	 *            Tablica, której kolejnoœæ elementów ma byæ odwracana.
	 * @param idx1
	 *            Indeks pocz¹tkowy elementu tablicy.
	 * @param idx2
	 *            Indeks koñcowy elementu tablicy.
	 * @return Przekszta³con¹ tablicê.
	 */
	private static int[] reverse(int[] input, int idx1, int idx2) {
		int[] output = new int[input.length];
		for (int i = 0; i < idx1; ++i)
			output[i] = input[i];
		for (int i = 0; i < idx2 - idx1; ++i)
			output[idx1 + i] = input[idx2 - i - 1];
		return output;
	}

}
