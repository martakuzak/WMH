package alg;

/**
 * 
 * Klasa pozwalaj�ca na rozwi�zanie problem przydzia�u w dwudzielnym
 * symetrycznym grafie wa�onym za pomoc� metody pe�nego przegl�du grafu.
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class FullSearch {

	/**
	 * Funkcja znajduj�ca najlepsze rozwi�zanie problemu przydzia�u w grafie
	 * wa�onym za pomoc� pe�nego przegl�du grafu.
	 * 
	 * @param graph
	 *            Macierz reprezentuj�ca graf, dla kt�rego ma by� dokonany
	 *            przydzia�. Element tablicy graph[i][j] wskazuje na wag�
	 *            kraw�dzi pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *            wierzcho�kiem j (z drugiego przedzia�u)
	 * @return Najlepsze rozwi�zanie (czas nie jest mierzony, jego warto�� jest
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
	 * Funkcja wyznaczaj�ca sum� aktualnego rozwi�zania (opisywanego przez
	 * parametry funkcji).
	 * 
	 * @param graph
	 *            Macierz reprezentuj�ca graf, dla kt�rego ma by� dokonany
	 *            przydzia�. Element tablicy graph[i][j] wskazuje na wag�
	 *            kraw�dzi pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *            wierzcho�kiem j (z drugiego przedzia�u)
	 * @param permutation
	 *            Tablica wskazuj�ca na s�siad�w wierzcho�k�w grafu po danym
	 *            przydziale. i-ty element tablicy wskazuje na indeks s�siada (z
	 *            drugiego przedzia�u) i-tego wierzcho�ka pierwszego przedzia�u.
	 * @return Sum� kraw�dzi grafu b�d�cego aktualnym rozwi�zaniem.
	 */
	private static int getSum(int[][] graph, int[] permutation) {
		int sum = 0;
		for (int i = 0; i < graph.length; ++i) {
			sum += graph[i][permutation[i]];
		}
		return sum;
	}

	/**
	 * Funkcja wyznaczaj�ca graf aktualnego rozwi�zania (opisywanego przez
	 * parametry funkcji).
	 * 
	 * @param graph
	 *            Macierz reprezentuj�ca graf, dla kt�rego ma by� dokonany
	 *            przydzia�. Element tablicy graph[i][j] wskazuje na wag�
	 *            kraw�dzi pomi�dzy wierzcho�kiem i (z pierwszego przedzia�u) i
	 *            wierzcho�kiem j (z drugiego przedzia�u)
	 * @param permutation
	 *            Tablica wskazuj�ca na s�siad�w wierzcho�k�w grafu po danym
	 *            przydziale. i-ty element tablicy wskazuje na indeks s�siada (z
	 *            drugiego przedzia�u) i-tego wierzcho�ka pierwszego przedzia�u.
	 * @return Graf b�d�cy aktualnym rozwi�zaniem.
	 */
	private static int[][] getGraph(int[][] graph, int[] permutation) {
		int[][] output = new int[graph.length][graph.length];

		for (int i = 0; i < permutation.length; ++i)
			output[i][permutation[i]] = graph[i][permutation[i]];
		return output;
	}

	/**
	 * Odwr�cenie kolejno�ci element�w tablicy input od indeksu idx1 do indeksu
	 * idx2.
	 * 
	 * @param input
	 *            Tablica, kt�rej kolejno�� element�w ma by� odwracana.
	 * @param idx1
	 *            Indeks pocz�tkowy elementu tablicy.
	 * @param idx2
	 *            Indeks ko�cowy elementu tablicy.
	 * @return Przekszta�con� tablic�.
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
