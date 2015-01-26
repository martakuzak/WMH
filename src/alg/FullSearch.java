package alg;

import java.util.Arrays;
import java.util.Vector;

public class FullSearch {
	
	
	public static Result getBestResult(int[][] graph) {
		int bestSum = Integer.MAX_VALUE;
		Vector<int[]> permutations = allPermutations(graph.length);
		int bestIdx = 0;
		for (int i = 0; i < permutations.size(); ++ i) {
			int sum = getSum(graph, permutations.get(i));
			if(sum < bestSum) {
				bestSum = sum;
				bestIdx = i;
			}
		}
		
		Result r = new Result(-1, bestSum, getGraph(graph, permutations.get(bestIdx)));
		return r;
	}

	public static int getSum(int[][] graph, int[] permutation) {
		int sum = 0;
		for(int i = 0; i < graph.length; ++ i) {
			sum += graph[i][permutation[i]];
		}
		return sum;
	}
	
	public static int[][] getGraph (int[][] graph, int[] permutation) {
		int[][] output = new int[graph.length][graph.length];
		
		for (int i = 0; i < permutation.length; ++ i)
			output[i][permutation[i]] = graph[i][permutation[i]];
		return output;
	}
	
	public static Vector<int[]> allPermutations(int n) {
		int[] t = new int[n];
		for (int i = 0; i < n; ++ i)
			t[i] = i;
		
		Vector<int[]> permutations = new Vector<>();
		
		while(true) {
			permutations.add(t.clone());
			/* Szukamy najdalszego elementu do zmiany */
			int i = n - 1;
			while(i > 0 && t[i -1] >= t[i])
				i--;
			if(i == 0)
				break; /* Ciag posortowany nierosnaco
			/* Wykonujemy zamiane */
			int j = i;
			while(j < n && t[j] >t[i -1])
				j++;
			j--;
			
			int tmp1 = t[i-1];
			t[i-1] = t[j];
			t[j] = tmp1;
			t = reverse(t, i, n);
			
		}
		
		return permutations;
	}
	
	private static void printTab(int[] tab) {
		for(int i = 0; i < tab.length; ++i )
			System.out.print(tab[i] + "\t");
		System.out.println();
	}
	
	private static int[] reverse(int[] input, int idx1, int idx2) {
		int[] output = new int[input.length];
		for(int i = 0; i < idx1; ++ i)
			output[i] = input[i];
		for (int i = 0; i < idx2 - idx1; ++i)
			output[idx1 + i] = input[idx2 - i - 1];
		return output;
	}


}

