package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj¹ca parametry do testowania dzia³ania algorytmu symulowanego
 * wy¿arzania dla ró¿nych wartoœci parametru temperatury koñcowej (zmiany w
 * formie ci¹gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class TminParameters {
	/**
	 * Temperatura pocz¹tkowa
	 */
	private double Tmax;
	/**
	 * Maksymalna temperatura koñcowa
	 */
	private double TminMax;
	/**
	 * Minimalna temperatura koñcowa
	 */
	private double TminMin;
	/**
	 * Krok temperatury (odpowiednik ró¿nicy ci¹gu arytmetycznego)
	 */
	private double TStep;
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
	 * @return Liczbê Tmin w zadanym przedziale <TminMin, TminMax> o kroku TStep
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
	 * @return Oczekiwan¹ wartoœæ Tmin lub -1, gdy przekroczymy zadany zakres
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
	 *            Indeks Tmin (w ci¹gu arytmetycznym), która ma byæ ustawiona w
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
