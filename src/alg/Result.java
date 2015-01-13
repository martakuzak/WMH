package alg;

public class Result {
	
	private long nanoTime;
	private int sum;
	
	Result(long nanoTime, int sum) {
		this.nanoTime = nanoTime;
		this.sum = sum;
	}
	
	public long getNanoTime() {
		return this.nanoTime;
	}

	public int getSum() {
		return this.sum;
	}
}
