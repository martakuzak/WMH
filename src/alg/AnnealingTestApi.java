package alg;

import java.util.Vector;

import alg.params.NmaxParameters;
import alg.params.Parameters;
import alg.params.LambdaParameters;
import alg.params.TminParameters;
import alg.params.TmaxParameters;

public class AnnealingTestApi {
	
	public static final int NUM_OF_ITERATIONS = 10;

	//pierwszy tab?
	/**
	 * Zwraca pojedyncze rozwiazanie - sprawdzamy jeden graf i jeden schemat
	 * chlodzenia
	 * 
	 * @param graph
	 * @param coolingSchedule
	 * @return
	 */
	public static Vector<TempResPair> findSolOneGraphOneCoolSched(int[][] graph, Parameters params) {
		return Annealing.findSol(graph, params);
	}

	//drugi tab ?
	/**
	 * Zwraca tablicê indeks - nr grafu. Element tablicy - wynik (czyli suma wag + czas wykonywania)
	 * @param graphs - pierwszy indeks to nr grafu
	 * @param params
	 * @return
	 */
	public static Vector<ResultStats> findSolMultiGraphsMultiCoolSched(int [][][]graphs, Parameters params) {
		final int numOfGraphs = graphs.length;
		Vector<ResultStats> results= new Vector<>();
		for(int graphIdx = 0; graphIdx < numOfGraphs; ++ graphIdx) {//po kolei dla wszystkich grafow 
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS]; 
			for(int i = 0; i < NUM_OF_ITERATIONS; ++ i) 
				tmpResults[i] = findSolOneGraphOneCoolSched(graphs[graphIdx], params).lastElement().getResult();

			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}

	
	//trzeci tab?
	/**
	 * Zwraca tablice Indeks to nr lambdy. Element tablicy - wynik (czyli suma wag + czas wykonywania)
	 * @param graph - graf
	 * @param params 
	 * @return
	 */
	public static Vector<ResultStats> findSolMultiLambdaMultiCoolSched(int [][] graph, LambdaParameters params) {
		final int numOfLambdas = params.getNumOfLambdas();
		Vector<ResultStats> results= new Vector<>();
		for(int lambdaIdx = 0; lambdaIdx < numOfLambdas; ++ lambdaIdx) {//po kolei dla wszystkich lambda
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS]; 
			for(int i = 0; i < NUM_OF_ITERATIONS; ++ i) 
				tmpResults[i] =	findSolOneGraphOneCoolSched(graph, params.getParameters(lambdaIdx)).lastElement().getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}
	
	//czwarty tab?
	public static Vector<ResultStats> findSolMultiNmaxMultiCoolSched(int [][] graph, NmaxParameters params) {
		final int numOfNmaxs = params.getNmaxNum();
		Vector<ResultStats> results= new Vector<>();
		for(int nmaxIdx = 0; nmaxIdx < numOfNmaxs; ++ nmaxIdx) {//po kolei dla wszystkich nmax
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS]; 
			for(int i = 0; i < NUM_OF_ITERATIONS; ++ i) 
				tmpResults[i] = findSolOneGraphOneCoolSched(graph, params.getParameters(nmaxIdx)).lastElement().getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
		
	}
	
	//piaty tab?
	public static Vector<ResultStats> findSolMultiTempMinMultiCoolSched(int [][] graph, TminParameters params) {
		final int numOfTmins = params.getTminNum();
		Vector<ResultStats> results= new Vector<>();
		for(int tminIdx = 0; tminIdx < numOfTmins; ++ tminIdx) {//po kolei dla wszystkich tmin
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS]; 
			for(int i = 0; i < NUM_OF_ITERATIONS; ++ i) 
				tmpResults[i] = findSolOneGraphOneCoolSched(graph, params.getParameters(tminIdx)).lastElement().getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}
	
	public static Vector<ResultStats> findSolMultiTempMaxMultiCoolSched(int [][] graph, TmaxParameters params) {
		final int numOfTmax = params.getTmaxNum();
		Vector<ResultStats> results= new Vector<>();
		for(int tmaxIdx = 0; tmaxIdx < numOfTmax; ++ tmaxIdx) {//po kolei dla wszystkich tmax
			Result[] tmpResults = new Result[NUM_OF_ITERATIONS]; 
			for(int i = 0; i < NUM_OF_ITERATIONS; ++ i) 
				tmpResults[i] = findSolOneGraphOneCoolSched(graph, params.getParameters(tmaxIdx)).lastElement().getResult();
			results.add(getStats(tmpResults, tmpResults[0].getGraph().length));
		}
		return results;
	}
	
	/**
	 * 
	 * @param results
	 * @return 
	 */
	
	private static ResultStats getStats(Result[] results, int graphSize) {
		ResultStats stats;
		int sumSum = 0;
		long timeSum = 0;
		for(int i = 0; i < NUM_OF_ITERATIONS; ++ i) {
			Result tmpResult = results[i];
			sumSum += tmpResult.getSum();
			timeSum += tmpResult.getNanoTime();
		}
		//obliczenie sredniej
		int meanSum = (int) Math.round(sumSum / NUM_OF_ITERATIONS);
		long meanTime = Math.round(timeSum / NUM_OF_ITERATIONS);
		//liczenie odchylenia standardowe
		int sumStandDevSum = 0;
		int timeStandDevSum = 0;
		for (int i = 0; i < NUM_OF_ITERATIONS; ++ i) { 
			sumStandDevSum += Math.pow(results[i].getSum() - meanSum, 2);
			timeStandDevSum += Math.pow(results[i].getNanoTime() - meanTime, 2);
		}
		double sumStandDev =  Math.pow(sumStandDevSum/NUM_OF_ITERATIONS, 0.5);
		double timeStandDev = Math.pow(timeStandDevSum/NUM_OF_ITERATIONS, 0.5);
		stats = new ResultStats(meanSum, sumStandDev, meanTime, timeStandDev, graphSize);
		return stats;
	}
}