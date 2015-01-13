package alg;

import alg.params.NmaxParameters;
import alg.params.Parameters;
import alg.params.LambdaParameters;
import alg.params.TParamaters;

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
	public static Result findSolOneGraphOneCoolSched(int[][] graph, Parameters params) {
		return Annealing.findSol(graph, params);
	}

	//drugi tab ?
	/**
	 * Zwraca tablicê 2D. Pierwszy indeks - numer schematu chlodzenia. Drugi indeks - nr grafu. Element tablicy - wynik (czyli suma wag + czas wykonywania)
	 * @param graphs - pierwszy indeks to nr grafu
	 * @param params
	 * @return
	 */
	public static Result[][] findSolMultiGraphsMultiCoolSched(int [][][]graphs, Parameters params) {
		final int numOfGraphs = graphs.length;
		Result[][] results = new Result[NUM_OF_COOL_SCHED] [numOfGraphs];
		
		for (int coolSched = 0; coolSched < NUM_OF_COOL_SCHED; ++ coolSched) { //po kolei dla wszystkich schematow chlodzenia
			params.setCoolingSchedule(CoolingSchedule.values()[coolSched]); //schematy chlodzenia sa w enumie ustalone po kolei: Lin - 0, Geo - 1, Log - 2
			for(int graphIdx = 0; graphIdx < numOfGraphs; ++ graphIdx) //po kolei dla wszystkich grafow
				results[coolSched][graphIdx] = findSolOneGraphOneCoolSched(graphs[graphIdx], params);
		}
		return results;
	}
	
	//trzeci tab?
	/**
	 * Zwraca tablice 2D. Pierwszy indeks to nr schematu chlodzenia. Drugi to nr lambdy. Element tablicy - wynik (czyli suma wag + czas wykonywania)
	 * @param graph - graf
	 * @param params 
	 * @return
	 */
	public static Result[][] findSolMultiLambdaMultiCoolSched(int [][] graph, LambdaParameters params) {
		final int numOfLambdas = params.getNumOfLambdas();
		Result[][] results = new Result[NUM_OF_COOL_SCHED][numOfLambdas];
		for (int coolSched = 0; coolSched < NUM_OF_COOL_SCHED; ++ coolSched) { //po kolei dla wszystkich schematow chlodzenia
			params.setCoolingSchedule(CoolingSchedule.values()[coolSched]); //schematy chlodzenia sa w enumie ustalone po kolei: Lin - 0, Geo - 1, Log - 2
			for(int lambdaIdx = 0; lambdaIdx < numOfLambdas; ++ lambdaIdx) //po kolei dla wszystkich lambda
				results[coolSched][lambdaIdx] = findSolOneGraphOneCoolSched(graph, params.getParameters(lambdaIdx));
		}
		return results;
	}
	
	//czwarty tab?
	public static Result[][] findSolMultiNmaxMultiCoolSched(int [][] graph, NmaxParameters params) {
		final int numOfNmaxs = params.getNmaxNum();
		Result[][] results = new Result[NUM_OF_COOL_SCHED][numOfNmaxs];
		for (int coolSched = 0; coolSched < NUM_OF_COOL_SCHED; ++ coolSched) { //po kolei dla wszystkich schematow chlodzenia
			params.setCoolingSchedule(CoolingSchedule.values()[coolSched]); //schematy chlodzenia sa w enumie ustalone po kolei: Lin - 0, Geo - 1, Log - 2
			for(int nmaxIdx = 0; nmaxIdx < numOfNmaxs; ++ nmaxIdx) //po kolei dla wszystkich nmax
				results[coolSched][nmaxIdx] = findSolOneGraphOneCoolSched(graph, params.getParameters(nmaxIdx));
		}
		return results;
		
	}
	
	//piaty tab?
	public static Result[][] findSolMultiTempMultiCoolSched(int [][] graph, TParamaters params) {
		final int numOfTmins = params.getTminNum();
		Result[][] results = new Result[NUM_OF_COOL_SCHED][numOfTmins];
		for (int coolSched = 0; coolSched < NUM_OF_COOL_SCHED; ++ coolSched) { //po kolei dla wszystkich schematow chlodzenia
			params.setCoolingSchedule(CoolingSchedule.values()[coolSched]); //schematy chlodzenia sa w enumie ustalone po kolei: Lin - 0, Geo - 1, Log - 2
			for(int tminIdx = 0; tminIdx < numOfTmins; ++ tminIdx) //po kolei dla wszystkich tmin
				results[coolSched][tminIdx] = findSolOneGraphOneCoolSched(graph, params.getParameters(tminIdx));
		}
		return results;
	}
}