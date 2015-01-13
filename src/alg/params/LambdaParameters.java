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
			double lambdaMin, double lambdaMax, double lambdaStep,
			CoolingSchedule coolingSchedule) {
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
 * @return liczbê lambd w zadadnym przedziale <lambdaMax, lambdaMin> o kroku lambdaStep
 */
	public int getNumOfLambdas() {
		Double d = (lambdaMax - lambdaMin)/lambdaStep + 1;
		return d.intValue();
	}
	
	/**
	 * 
	 * @param idx - nr kolejnej lambda, pierwsza to lambdaMin, kolejna to lambdaMin + lambdaStep, potem lambdaMin + 2*lambdaStep itd
	 * @return oczekiwana lambde lub -1, gdy juz wyjdziemy z zakresu <lambdaMax, lambdaMin>
	 */
	public double getLambda(int idx) {
		double lambda = lambdaMin + (idx - 1)*lambdaStep;
		return (lambda <= lambdaMax) ? lambda : -1;
	}
	/**
	 * Konwertuje do Paramaters lub zwraca null przy zbyt duzym indeksie
	 * @param lambdaIdx
	 * @return
	 */
	public Parameters getParameters(int lambdaIdx) {
		double lambda = getLambda(lambdaIdx);
		return (lambda != -1) ? new Parameters(Tmax, Tmin, Nmax, getLambda(lambdaIdx), coolingSchedule) : null;
	}
	public double getTmax() {
		return Tmax;
	}

	public void setTmax(double tmax) {
		Tmax = tmax;
	}

	public double getTmin() {
		return Tmin;
	}

	public void setTmin(double tmin) {
		Tmin = tmin;
	}

	public int getNmax() {
		return Nmax;
	}

	public void setNmax(int nmax) {
		Nmax = nmax;
	}

	public double getLambdaMin() {
		return lambdaMin;
	}

	public void setLambdaMin(double lambdaMin) {
		this.lambdaMin = lambdaMin;
	}

	public double getLambdaMax() {
		return lambdaMax;
	}

	public void setLambdaMax(double lambdaMax) {
		this.lambdaMax = lambdaMax;
	}

	public double getLambdaStep() {
		return lambdaStep;
	}

	public void setLambdaStep(double lambdaStep) {
		this.lambdaStep = lambdaStep;
	}

	public CoolingSchedule getCoolingSchedule() {
		return coolingSchedule;
	}

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}

}
