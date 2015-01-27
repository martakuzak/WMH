package alg;

/**
 * 
 * Klasa reprezentuj�ca rozwi�zanie problemu przydzia�u w grafie wa�onym
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class Result {

	/**
	 * Czas wykonywania algorytmu mierzony w ns.
	 */
	private long nanoTime;
	/**
	 * Wyznaczona suma wag kraw�dzi.
	 */
	private int sum;
	/**
	 * Macierz reprezentuj�ca graf, dla kt�rego ma by� dokonany przydzia�.
	 * Element tablicy graph[i][j] wskazuje na wag� kraw�dzi pomi�dzy
	 * wierzcho�kiem i (z pierwszego przedzia�u) i wierzcho�kiem j (z drugiego
	 * przedzia�u)
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
