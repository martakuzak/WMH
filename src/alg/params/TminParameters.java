package alg.params;

import alg.CoolingSchedule;

public class TminParameters {

	private double Tmax;
	private double TminMax;
	private double TminMin;
	private double TStep;
	private int Nmax;
	private double lambda;
	private CoolingSchedule coolingSchedule;

	public TminParameters(double tmax, 
			double tminMax, double tminMin,
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
		Double d = (TminMax - TminMin) / TStep;
		return d.intValue();
	}

	public double getTmin(int idx) {
		double Tmin = TminMin + (idx) * TStep;
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

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}

}
