package alg;

/**
 * 
 * Obiekt klasy ResultStas reprezentuje opis statystyczny szeregu wynik�w rozwi�zywania problemu przydzia�u w grafie wa�onym
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class ResultStats {
	/**
	 * �rednia sum wag kraw�dzi grafu
	 */
	int sumMean;
	/**
	 * Odchylenie standardowe wag kraw�dzi grafu
	 */
	double sumStanDev;
	/**
	 * �redni czas wykonywania algorytm�w w ns
	 */
	long timeMean;
	/**
	 * Odchylenie standardowe czasu wykonywania algorytm�w
	 */
	double timeStanDev;
	/**
	 * Rozmiar grafu
	 */
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
