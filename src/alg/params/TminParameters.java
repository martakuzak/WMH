package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj�ca parametry do testowania dzia�ania algorytmu symulowanego
 * wy�arzania dla r�nych warto�ci parametru temperatury ko�cowej (zmiany w
 * formie ci�gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class TminParameters {
	/**
	 * Temperatura pocz�tkowa
	 */
	private double Tmax;
	/**
	 * Maksymalna temperatura ko�cowa
	 */
	private double TminMax;
	/**
	 * Minimalna temperatura ko�cowa
	 */
	private double TminMin;
	/**
	 * Krok temperatury (odpowiednik r�nicy ci�gu arytmetycznego)
	 */
	private double TStep;
	/**
	 * Liczba iteracji dla zadanej temperatury
	 */
	private int Nmax;
	/**
	 * Wsp�czynnik sch�adzania
	 */
	private double lambda;
	/**
	 * Schemat sch�adzania
	 */
	private CoolingSchedule coolingSchedule;

	public TminParameters(double tmax, double tminMax, double tminMin,
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

	/**
	 * 
	 * @return Liczb� Tmin w zadanym przedziale <TminMin, TminMax> o kroku TStep
	 */
	public int getTminNum() {
		Double d = (TminMax - TminMin) / TStep;
		return d.intValue() + 1;
	}

	/**
	 * 
	 * @param idx
	 *            Numer kolejnego Tmin. Pierwszy to TminMin, kolejny to TminMin
	 *            + TStep, potem TminMin + 2*TStep itd
	 * @return Oczekiwan� warto�� Tmin lub -1, gdy przekroczymy zadany zakres
	 */
	public double getTmin(int idx) {
		double Tmin = TminMin + (idx) * TStep;
		return (Tmin <= TminMax) ? Tmin : -1;
	}

	/**
	 * Konwertuje do parametry Parameters lub zwraca null przy zbyt duzym
	 * indeksie Tmin.
	 * 
	 * @param idx
	 *            Indeks Tmin (w ci�gu arytmetycznym), kt�ra ma by� ustawiona w
	 *            obiekcie klasy Parameters.
	 * @return Zwraca obiekt klasy Parameters na podstawie zadanych danych
	 * @see Parameters
	 */
	public Parameters getParameters(int idx) {
		double Tmin = getTmin(idx);
		return (Tmin != -1) ? new Parameters(Tmax, Tmin, Nmax, lambda,
				coolingSchedule) : null;
	}

}
