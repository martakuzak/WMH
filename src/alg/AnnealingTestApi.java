package alg;

import java.util.Vector;

import alg.params.NmaxParameters;
import alg.params.Parameters;
import alg.params.LambdaParameters;
import alg.params.TminParameters;
import alg.params.TmaxParameters;

public class AnnealingTestApi {
	
	public static final int NUM_OF_COOL_SCHED = 3;

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
	public static Vector<Vector<TempResPair>> findSolMultiGraphsMultiCoolSched(int [][][]graphs, Parameters params) {
		final int numOfGraphs = graphs.length;
		Vector<Vector<TempResPair>> results= new Vector< Vector<TempResPair>>();
		for(int graphIdx = 0; graphIdx < numOfGraphs; ++ graphIdx) //po kolei dla wszystkich grafow
			results.add(findSolOneGraphOneCoolSched(graphs[graphIdx], params));
		return results;
	}
	
	//trzeci tab?
	/**
	 * Zwraca tablice Indeks to nr lambdy. Element tablicy - wynik (czyli suma wag + czas wykonywania)
	 * @param graph - graf
	 * @param params 
	 * @return
	 */
	public static Vector<Vector<TempResPair>> findSolMultiLambdaMultiCoolSched(int [][] graph, LambdaParameters params) {
		final int numOfLambdas = params.getNumOfLambdas();
		Vector<Vector<TempResPair>> results= new Vector< Vector<TempResPair>>();
		for(int lambdaIdx = 0; lambdaIdx < numOfLambdas; ++ lambdaIdx) //po kolei dla wszystkich lambda
			results.add(findSolOneGraphOneCoolSched(graph, params.getParameters(lambdaIdx)));
		return results;
	}
	
	//czwarty tab?
	public static Vector<Vector<TempResPair>> findSolMultiNmaxMultiCoolSched(int [][] graph, NmaxParameters params) {
		final int numOfNmaxs = params.getNmaxNum();
		Vector<Vector<TempResPair>> results= new Vector< Vector<TempResPair>>();
		for(int nmaxIdx = 0; nmaxIdx < numOfNmaxs; ++ nmaxIdx) //po kolei dla wszystkich nmax
			results.add(findSolOneGraphOneCoolSched(graph, params.getParameters(nmaxIdx)));
		return results;
		
	}
	
	//piaty tab?
	public static Vector<Vector<TempResPair>> findSolMultiTempMinMultiCoolSched(int [][] graph, TminParameters params) {
		final int numOfTmins = params.getTminNum();
		Vector<Vector<TempResPair>> results= new Vector< Vector<TempResPair>>();
		for(int tminIdx = 0; tminIdx < numOfTmins; ++ tminIdx) //po kolei dla wszystkich tmin
			results.add(findSolOneGraphOneCoolSched(graph, params.getParameters(tminIdx)));
		return results;
	}
	
	public static Vector<Vector<TempResPair>> findSolMultiTempMaxMultiCoolSched(int [][] graph, TmaxParameters params) {
		final int numOfTmax = params.getTmaxNum();
		Vector<Vector<TempResPair>> results= new Vector< Vector<TempResPair>>();
		for(int tmaxIdx = 0; tmaxIdx < numOfTmax; ++ tmaxIdx) //po kolei dla wszystkich tmax
			results.add(findSolOneGraphOneCoolSched(graph, params.getParameters(tmaxIdx)));
		return results;
	}
}