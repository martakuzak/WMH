package alg.params;

import alg.CoolingSchedule;

public class NmaxParameters {

	private double Tmax;
	private double Tmin;
	private int NmaxMax;
	private int NmaxMin;
	private double lambda;
	private int NmaxStep;
	private CoolingSchedule coolingSchedule;

	public NmaxParameters(double tmax, double tmin, int nmaxMax, int nmaxMin,
			double lambda, int nmaxStep, CoolingSchedule coolingSchedule) {
		super();
		Tmax = tmax;
		Tmin = tmin;
		NmaxMax = nmaxMax;
		NmaxMin = nmaxMin;
		this.lambda = lambda;
		NmaxStep = nmaxStep;
		this.coolingSchedule = coolingSchedule;
	}

	public int getNmaxNum() {
		return (NmaxMax - NmaxMin) / NmaxStep + 1;
	}

	public int getNmax(int idx) {
		int Nmax = NmaxMin + (idx - 1) * NmaxStep;
		return (Nmax <= NmaxMax) ? Nmax : -1;
	}

	/**
	 * Konwertuje do Paramaters lub zwraca null przy zbyt duzym indeksie
	 * 
	 * @param idx
	 * @return
	 */
	public Parameters getParameters(int idx) {
		int Nmax = getNmax(idx);
		return (Nmax != -1) ? new Parameters(Tmax, Tmin, getNmax(idx), lambda,
				coolingSchedule) : null;
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

	public int getNmaxMax() {
		return NmaxMax;
	}

	public void setNmaxMax(int nmaxMax) {
		NmaxMax = nmaxMax;
	}

	public int getNmaxMin() {
		return NmaxMin;
	}

	public void setNmaxMin(int nmaxMin) {
		NmaxMin = nmaxMin;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getNmaxStep() {
		return NmaxStep;
	}

	public void setNmaxStep(int nmaxStep) {
		NmaxStep = nmaxStep;
	}

	public CoolingSchedule getCoolingSchedule() {
		return coolingSchedule;
	}

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}

}
