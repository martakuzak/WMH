package alg;

import java.util.Vector;

public class Result {
	
	private long nanoTime;
	private int sum;
	private int [][] graph;
	
	Result(long nanoTime, int sum, int [][] graph) {
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
