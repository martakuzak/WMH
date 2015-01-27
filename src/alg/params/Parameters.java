package alg.params;

import alg.CoolingSchedule;

public class Parameters {
	
	/**
	 * Temperatura pocz¹tkowa
	 */
	private double Tmax;
	/**
	 * Temperatura koñcowa
	 */
	private double Tmin;
	/**
	 * Liczba iteracji dla zadanej temperatury
	 */
	private int Nmax;
	/**
	 * Wspó³czynnik sch³adzania
	 */
	private double lambda;
	/**
	 * Schemat sch³adzania
	 */
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
}
