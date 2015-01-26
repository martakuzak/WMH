package alg;

public class ResultStats {
	int sumMean;
	double sumStanDev;
	long timeMean;
	double timeStanDev;
	int graphSize;
	
	public ResultStats(int sumMean, double sumStanDev, long timeMean,
			double timeStanDev, int graphSize) {
		super();
		this.sumMean = sumMean;
		this.sumStanDev = sumStanDev;
		this.timeMean = timeMean;
		this.timeStanDev = timeStanDev;
		this.graphSize = graphSize;
	}

	public int getSumMean() {
		return sumMean;
	}

	public double getSumStanDev() {
		return sumStanDev;
	}

	public long getTimeMean() {
		return timeMean;
	}

	public double getTimeStanDev() {
		return timeStanDev;
	}

	public int getGraphSize() {
		return graphSize;
	}
	

}
