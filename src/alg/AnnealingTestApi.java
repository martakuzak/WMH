package alg;

import java.util.Vector;

import alg.params.NmaxParameters;
import alg.params.Parameters;
import alg.params.LambdaParameters;
import alg.params.TminParameters;
import alg.params.TmaxParameters;

/**
 * 
 * Klasa AnnealingTestApi to API udostêpniaj¹ce szereg funkcji pozwalaj¹cych na
 * rozwi¹zanie problemu przydzia³u w grafie wa¿onym za pomoc¹ algorytmu
 * symulowanego wy¿arzania oraz na zbadanie w³aœciwoœci tego algorytmu.
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 * 
 *
 */
public class AnnealingTestApi {

	/**
	 * Liczba iteracji dla jednego rozwi¹zania - do uœredniania wykresów.
	 */
	private static final int NUM_OF_ITERATIONS = 50;

	// pierwszy tab
	/**
	 * Funkcja rozwi¹zuj¹ca problem przydzia³u w zadanym grafie za pomoc¹
	 * algorytmu symulowanego wy¿arzania.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param params
	 *            Parametry algorytmu
	 * @return Wektor rozwi¹zañ algorytmu. Zerowym elementem wektora jest
	 *         tymczasowy rezultat uzyskany po pierwszej iteracji temperatury,
	 *         pierwszy dla drugiej iteracji temperatury itd. Ostatni element to
	 *         wynik koñcowy.
	 */
	public static Vector<TempResPair> findSolOneGraphOneCoolSched(
			int[][] graph, Parameters params) {
		return Annealing.findSol(graph, params);
	}

	// drugi tab
	/**
	 * Funkcja rozwi¹zuj¹ca problem przydzia³u w grafie wa¿onym za pomoc¹
	 * algorytmu symulowanego wy¿arzania dla szeregu grafów. Dla ka¿dego grafu
	 * algorytm jest wykonywany NUM_OF_ITERATIONS razy a nastêpnie wyniki
	 * (uzyskana suma oraz czas wykonywania) s¹ reprezentowane za pomoc¹ wyników
	 * œrednich i ich odchylenia standardowego.
	 * 
	 * @param graphs
	 *            Tablica macierzy reprezentujacych grafy. Element tablicy
	 *            graph[k][i][j] wskazuje na wagê krawêdzi pomiêdzy
	 *            wierzcho³kiem i (z pierwszego przedzia³u) i wierzcho³kiem j (z
	 *            drugiego przedzia³u) k-tego grafu.
	 * @param params
	 *            Parametry algorytmu
	 * @return Wektor rozwi¹zañ (wartoœci œrednie i odchylenia standardowe sumy
	 *         wag oraz czasu wykonywania algorytmu)
	 * @see ResultStats
	 */
	public static Vector<ResultStats> findSolMultiGraphsMultiCoolSched(
			int[][][] graphs, Parameters params) {
		final int numOfGraphs = graphs.length;
		Vector<ResultStats> results = new Vector<>();
		for (int graphIdx = 0; graphIdx < numOfGraphs; ++graphIdx) {// po kolei
																	// dla
																	// wszystkich
																	// grafow
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS];
			for (int i = 0; i < NUM_OF_ITERATIONS; ++i)
				tmpResults[i] = findSolOneGraphOneCoolSched(graphs[graphIdx],
						params).lastElement().getResult();

			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}

	// trzeci tab
	/**
	 * Funkcja rozwi¹zuj¹ca problem przydzia³u w grafie wa¿onym za pomoc¹
	 * algorytmu symulowanego wy¿arzania dla jednego grafu i dla zmieniaj¹cych
	 * siê wartoœci wspó³czynnika sch³adzania (lambda). Dla ka¿dego grafu
	 * algorytm jest wykonywany NUM_OF_ITERATIONS razy a nastêpnie wyniki
	 * (uzyskana suma oraz czas wykonywania) s¹ reprezentowane za pomoc¹ wyników
	 * œrednich i ich odchylenia standardowego.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param params
	 *            Parametry algorytmu
	 * @return Wektor rozwi¹zañ (wartoœci œrednie i odchylenia standardowe sumy
	 *         wag oraz czasu wykonywania algorytmu)
	 * @see ResultStats
	 * @see LambdaParameters
	 */
	public static Vector<ResultStats> findSolMultiLambdaMultiCoolSched(
			int[][] graph, LambdaParameters params) {
		final int numOfLambdas = params.getNumOfLambdas();
		Vector<ResultStats> results = new Vector<>();
		for (int lambdaIdx = 0; lambdaIdx < numOfLambdas; ++lambdaIdx) {// po
																		// kolei
																		// dla
																		// wszystkich
																		// lambda
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS];
			for (int i = 0; i < NUM_OF_ITERATIONS; ++i)
				tmpResults[i] = findSolOneGraphOneCoolSched(graph,
						params.getParameters(lambdaIdx)).lastElement()
						.getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}

	// czwarty tab
	/**
	 * Funkcja rozwi¹zuj¹ca problem przydzia³u w grafie wa¿onym za pomoc¹
	 * algorytmu symulowanego wy¿arzania dla jednego grafu i dla zmieniaj¹cej
	 * siê liczby iteracji dla jednej temperatury (Nmax). Dla ka¿dego grafu
	 * algorytm jest wykonywany NUM_OF_ITERATIONS razy a nastêpnie wyniki
	 * (uzyskana suma oraz czas wykonywania) s¹ reprezentowane za pomoc¹ wyników
	 * œrednich i ich odchylenia standardowego.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param params
	 *            Parametry algorytmu
	 * @return Wektor rozwi¹zañ (wartoœci œrednie i odchylenia standardowe sumy
	 *         wag oraz czasu wykonywania algorytmu)
	 * @see ResultStats
	 * @see NmaxParameters
	 */
	public static Vector<ResultStats> findSolMultiNmaxMultiCoolSched(
			int[][] graph, NmaxParameters params) {
		final int numOfNmaxs = params.getNmaxNum();
		Vector<ResultStats> results = new Vector<>();
		for (int nmaxIdx = 0; nmaxIdx < numOfNmaxs; ++nmaxIdx) {// po kolei dla
																// wszystkich
																// nmax
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS];
			for (int i = 0; i < NUM_OF_ITERATIONS; ++i)
				tmpResults[i] = findSolOneGraphOneCoolSched(graph,
						params.getParameters(nmaxIdx)).lastElement()
						.getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;

	}

	// piaty tab
	/**
	 * Funkcja rozwi¹zuj¹ca problem przydzia³u w grafie wa¿onym za pomoc¹
	 * algorytmu symulowanego wy¿arzania dla jednego grafu i dla zmieniaj¹cej
	 * siê wartoœci temperatury koñcowej algorytmu. Dla ka¿dego grafu algorytm
	 * jest wykonywany NUM_OF_ITERATIONS razy a nastêpnie wyniki (uzyskana suma
	 * oraz czas wykonywania) s¹ reprezentowane za pomoc¹ wyników œrednich i ich
	 * odchylenia standardowego.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param params
	 *            Parametry algorytmu
	 * @return Wektor rozwi¹zañ (wartoœci œrednie i odchylenia standardowe sumy
	 *         wag oraz czasu wykonywania algorytmu)
	 * @see ResultStats
	 * @see TminParameters
	 */
	public static Vector<ResultStats> findSolMultiTempMinMultiCoolSched(
			int[][] graph, TminParameters params) {
		final int numOfTmins = params.getTminNum();
		Vector<ResultStats> results = new Vector<>();
		for (int tminIdx = 0; tminIdx < numOfTmins; ++tminIdx) {// po kolei dla
																// wszystkich
																// tmin
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS];
			for (int i = 0; i < NUM_OF_ITERATIONS; ++i)
				tmpResults[i] = findSolOneGraphOneCoolSched(graph,
						params.getParameters(tminIdx)).lastElement()
						.getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}

	// piaty tab
	/**
	 * Funkcja rozwi¹zuj¹ca problem przydzia³u w grafie wa¿onym za pomoc¹
	 * algorytmu symulowanego wy¿arzania dla jednego grafu i dla zmieniaj¹cej
	 * siê wartoœci temperatury pocz¹tkowej algorytmu. Dla ka¿dego grafu
	 * algorytm jest wykonywany NUM_OF_ITERATIONS razy a nastêpnie wyniki
	 * (uzyskana suma oraz czas wykonywania) s¹ reprezentowane za pomoc¹ wyników
	 * œrednich i ich odchylenia standardowego.
	 * 
	 * @param graph
	 *            Macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param params
	 *            Parametry algorytmu
	 * @return Wektor rozwi¹zañ (wartoœci œrednie i odchylenia standardowe sumy
	 *         wag oraz czasu wykonywania algorytmu)
	 * @see ResultStats
	 * @see TminParameters
	 */
	public static Vector<ResultStats> findSolMultiTempMaxMultiCoolSched(
			int[][] graph, TmaxParameters params) {
		final int numOfTmax = params.getTmaxNum();
		Vector<ResultStats> results = new Vector<>();
		for (int tmaxIdx = 0; tmaxIdx < numOfTmax; ++tmaxIdx) {// po kolei dla
																// wszystkich
																// tmax
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS];
			for (int i = 0; i < NUM_OF_ITERATIONS; ++i)
				tmpResults[i] = findSolOneGraphOneCoolSched(graph,
						params.getParameters(tmaxIdx)).lastElement()
						.getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}

	/**
	 * Funkcja wyznacza œredni¹ sumê wag, czas wykonywania algorytmu i ich
	 * odchylenia standardowe.
	 * 
	 * @param results
	 *            Wyniki dzia³ania algorytmu dla jednego z grafów i wybranej
	 *            jednej konfiguracji algorytmu
	 * @return Obiekt klasy ResultStats przechowuj¹cy œredni¹ sumê wag, czas
	 *         wykonywania algorytmu i ich odchylenia standardowe.
	 * @see ResultStats
	 */

	private static ResultStats getStats(Result[] results, int graphSize) {
		ResultStats stats;
		int sumSum = 0;
		long timeSum = 0;
		for (int i = 0; i < NUM_OF_ITERATIONS; ++i) {
			Result tmpResult = results[i];
			sumSum += tmpResult.getSum();
			timeSum += tmpResult.getNanoTime();
		}
		// obliczenie sredniej
		int meanSum = (int) Math.round(sumSum / NUM_OF_ITERATIONS);
		long meanTime = Math.round(timeSum / NUM_OF_ITERATIONS);
		// liczenie odchylenia standardowe
		int sumStandDevSum = 0;
		int timeStandDevSum = 0;
		for (int i = 0; i < NUM_OF_ITERATIONS; ++i) {
			sumStandDevSum += Math.pow(results[i].getSum() - meanSum, 2);
			timeStandDevSum += Math.pow(results[i].getNanoTime() - meanTime, 2);
		}
		double sumStandDev = Math.pow(sumStandDevSum / NUM_OF_ITERATIONS, 0.5);
		double timeStandDev = Math
				.pow(timeStandDevSum / NUM_OF_ITERATIONS, 0.5);
		stats = new ResultStats(meanSum, sumStandDev, meanTime, timeStandDev,
				graphSize);
		return stats;
	}
}