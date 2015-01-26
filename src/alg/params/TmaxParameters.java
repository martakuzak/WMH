package alg.params;

import alg.CoolingSchedule;

public class TmaxParameters {

	private double Tmin;
	private double TmaxMax;
	private double TmaxMin;
	private double TStep;
	private int Nmax;
	private double lambda;
	private CoolingSchedule coolingSchedule;

	public TmaxParameters(double tmin, 
			double tmaxMax, double tmaxMin,
			double tstep, int nmax, double lambda,
			CoolingSchedule coolingSchedule) {
		super();
		Tmin = tmin;
		TmaxMax = tmaxMax;
		TmaxMin = tmaxMin;
		TStep = tstep;
		Nmax = nmax;
		this.lambda = lambda;
		this.coolingSchedule = coolingSchedule;
	}

	public int getTmaxNum() {
		Double d = (TmaxMax - TmaxMin) / TStep;
		return d.intValue();
	}

	public double getTmax(int idx) {
		double Tmin = TmaxMin + (idx) * TStep;
		return (Tmin <= TmaxMax) ? Tmin : -1;
	}

	/**
	 * Konwertuje do Paramaters lub zwraca null przy zbyt duzym indeksie
	 * 
	 * @param idx
	 * @return
	 */
	public Parameters getParameters(int idx) {
		double Tmax = getTmax(idx);
		return (Tmax != -1) ? new Parameters(Tmax, Tmin, Nmax, lambda,
				coolingSchedule) : null;
	}

	public void setCoolingSchedule(CoolingSchedule coolingSchedule) {
		this.coolingSchedule = coolingSchedule;
	}

}
