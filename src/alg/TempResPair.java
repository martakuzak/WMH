package alg;

/**
 * 
 * Para: temperatura + wynik
 *
 */
public class TempResPair {
	double temp;
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
