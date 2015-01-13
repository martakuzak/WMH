package alg.params;

import alg.CoolingSchedule;

public class TParamaters {

	private double Tmax;
	private double TminMax;
	private double TminMin;
	private double TStep;
	private int Nmax;
	private double lambda;
	private CoolingSchedule coolingSchedule;

	public TParamaters(double tmax, double tminMax, double tminMin,
			double tstep, int nmax, double lambda,
			CoolingSchedule coolingSchedule) {
		super();
		Tmax = tmax;
		TminMax = tminMax;
		TminMin = tminMin;
		TStep = tstep;
		Nmax = nmax;
		this.lambda = lambda;
		this.coolingSchedule = coolingSchedule;
	}

	public int getTminNum() {
		Double d = (TminMax - TminMin) / TStep + 1;
		return d.intValue();
	}

	public double getTmin(int idx) {
		double Tmin = TminMin + (idx - 1) * TStep;
		return (Tmin <= TminMax) ? Tmin : -1;
	}

	/**
	 * Konwertuje do Paramaters lub zwraca null przy zbyt duzym indeksie
	 * 
	 * @param idx
	 * @return
	 */
	public Parameters getParameters(int idx) {
		double Tmin = getTmin(idx);
		return (Tmin != -1) ? new Parameters(Tmax, Tmin, Nmax, lambda,
				coolingSchedule) : null;
	}

	public double getTmax() {
		return Tmax;
	}

	public void setTmax(double tmax) {
		Tmax = tmax;
	}

	public double getTminMax() {
		return TminMax;
	}

	public void setTminMax(double tminMax) {
		TminMax = tminMax;
	}

	public double getTminMin() {
		return TminMin;
	}

	public void setTminMin(double tminMin) {
		TminMin = tminMin;
	}

	public double getTstep() {
		return TStep;
	}

	public void setTstep(double tstep) {
		TStep = tstep;
	}

	public int getNmax() {
		return Nmax;
	}

	public void setNmax(int nmax) {
		Nmax = nmax;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public CoolingSchedule getCoolingSchedule() {
		return coolingSchedule;
	}

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}

}
