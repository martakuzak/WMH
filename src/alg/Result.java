package alg;

/**
 * 
 * Klasa reprezentuj¹ca rozwi¹zanie problemu przydzia³u w grafie wa¿onym
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class Result {

	/**
	 * Czas wykonywania algorytmu mierzony w ns.
	 */
	private long nanoTime;
	/**
	 * Wyznaczona suma wag krawêdzi.
	 */
	private int sum;
	/**
	 * Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany przydzia³.
	 * Element tablicy graph[i][j] wskazuje na wagê krawêdzi pomiêdzy
	 * wierzcho³kiem i (z pierwszego przedzia³u) i wierzcho³kiem j (z drugiego
	 * przedzia³u)
	 */
	private int[][] graph;

	Result(long nanoTime, int sum, int[][] graph) {
		this.nanoTime = nanoTime;
		this.sum = sum;
		this.graph = graph;
	}

	public long getNanoTime() {
		return this.nanoTime;
	}

	public int getSum() {
		return this.sum;
	}

	public int[][] getGraph() {
		return graph;
	}

}
