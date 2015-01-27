package alg;

/**
 * 
 * Obiekt klasy TempRes reprezentuje tymczasowe rozwi¹zanie problemu przydzia³u
 * w grafie wa¿onym wykonywanego algorytmem symulowanego wy¿arzania dla zadanej
 * temperatury.
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class TempResPair {
	/**
	 * Temperatura
	 */
	double temp;
	/**
	 * Wynik algorytmu
	 */
	Result result;

	public TempResPair(double temp, Result result) {
		super();
		this.temp = temp;
		this.result = result;
	}

	public double getTemp() {
		return temp;
	}

	public Result getResult() {
		return result;
	}

}
