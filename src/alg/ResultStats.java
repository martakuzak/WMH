package alg;

/**
 * 
 * Obiekt klasy ResultStas reprezentuje opis statystyczny szeregu wyników rozwi¹zywania problemu przydzia³u w grafie wa¿onym
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class ResultStats {
	/**
	 * Œrednia sum wag krawêdzi grafu
	 */
	int sumMean;
	/**
	 * Odchylenie standardowe wag krawêdzi grafu
	 */
	double sumStanDev;
	/**
	 * Œredni czas wykonywania algorytmów w ns
	 */
	long timeMean;
	/**
	 * Odchylenie standardowe czasu wykonywania algorytmów
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
