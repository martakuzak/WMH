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
		int Nmax = NmaxMin + (idx) * NmaxStep;
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

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}

}
