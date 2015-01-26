package alg.params;

import alg.CoolingSchedule;

public class LambdaParameters {

	private double Tmax;
	private double Tmin;
	private int Nmax;
	private double lambdaMin;
	private double lambdaMax;
	private double lambdaStep;
	private CoolingSchedule coolingSchedule;

	public LambdaParameters(double tmax, double tmin, int nmax,
			double lambdaMin, double lambdaMax, double lambdaStep,  CoolingSchedule coolingSchedule) {
		super();
		Tmax = tmax;
		Tmin = tmin;
		Nmax = nmax;
		this.lambdaMin = lambdaMin;
		this.lambdaMax = lambdaMax;
		this.lambdaStep = lambdaStep;
		this.coolingSchedule = coolingSchedule;
	}

	/**
	 * 
	 * @return liczbê lambd w zadadnym przedziale <lambdaMax, lambdaMin> o kroku
	 *         lambdaStep
	 */
	public int getNumOfLambdas() {
		Double d = (lambdaMax - lambdaMin) / lambdaStep;
		return d.intValue();
	}

	/**
	 * 
	 * @param idx
	 *            - nr kolejnej lambda, pierwsza to lambdaMin, kolejna to
	 *            lambdaMin + lambdaStep, potem lambdaMin + 2*lambdaStep itd
	 * @return oczekiwana lambde lub -1, gdy juz wyjdziemy z zakresu <lambdaMax,
	 *         lambdaMin>
	 */
	public double getLambda(int idx) {
		double lambda = lambdaMin + idx * lambdaStep;
		return (lambda <= lambdaMax) ? lambda : -1;
	}

	/**
	 * Konwertuje do Paramaters lub zwraca null przy zbyt duzym indeksie
	 * 
	 * @param lambdaIdx
	 * @return
	 */
	public Parameters getParameters(int lambdaIdx) {
		double lambda = getLambda(lambdaIdx);
		return (lambda != -1) ? new Parameters(Tmax, Tmin, Nmax,
				getLambda(lambdaIdx), coolingSchedule) : null;
	}

	public boolean setCoolingSchedule(CoolingSchedule coolingSchedule) {
		if(coolingSchedule.equals(CoolingSchedule.COOLING_LOGARITHMIC))
			return false;
		this.coolingSchedule = coolingSchedule;
		return true;
	}

}
