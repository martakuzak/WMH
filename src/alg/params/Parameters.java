package alg.params;

import alg.CoolingSchedule;

public class Parameters {

	private double Tmax;
	private double Tmin;
	private int Nmax;
	private double lambda;
	private CoolingSchedule coolingSchedule;

	public Parameters(double tmax, double tmin, int nmax, double lambda,
			CoolingSchedule coolingSchedule) {
		Tmax = tmax;
		Tmin = tmin;
		Nmax = nmax;
		this.lambda = lambda;
		this.coolingSchedule = coolingSchedule;
	}

	public double getTmax() {
		return Tmax;
	}

	public double getTmin() {
		return Tmin;
	}

	public int getNmax() {
		return Nmax;
	}

	public double getLambda() {
		return lambda;
	}

	public CoolingSchedule getCoolingSchedule() {
		return coolingSchedule;
	}

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}
}
