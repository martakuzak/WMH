package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj¹ca parametry do testowania dzia³ania algorytmu symulowanego
 * wy¿arzania dla ró¿nych wartoœci parametru temperatury pocz¹tkowej (zmiany w
 * formie ci¹gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class TmaxParameters {

	/**
	 * Temperatura koñcowa
	 */
	private double Tmin;
	/**
	 * Maksymalna temperatura pocz¹tkowa
	 */
	private double TmaxMax;
	/**
	 * Minimalna temperatura pocz¹tkowa
	 */
	private double TmaxMin;
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

	public TmaxParameters(double tmin, double tmaxMax, double tmaxMin,
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

	/**
	 * 
	 * @return Liczbê Tmax w zadanym przedziale <TmaxMin, TmaxMax> o kroku TStep
	 */
	public int getTmaxNum() {
		Double d = (TmaxMax - TmaxMin) / TStep;
		return d.intValue() + 1;
	}

	/**
	 * 
	 * @param idx
	 *            Numer kolejnego Tmax. Pierwszy to TmaxMin, kolejny to TmaxMin
	 *            + TStep, potem TmaxMin + 2*TStep itd
	 * @return Oczekiwan¹ wartoœæ Tmax lub -1, gdy przekroczymy zadany zakres
	 */
	public double getTmax(int idx) {
		double Tmin = TmaxMin + (idx) * TStep;
		return (Tmin <= TmaxMax) ? Tmin : -1;
	}

	/**
	 * Konwertuje do parametry Parameters lub zwraca null przy zbyt duzym
	 * indeksie Tmax.
	 * 
	 * @param idx
	 *            Indeks Tmax (w ci¹gu arytmetycznym), która ma byæ ustawiona w
	 *            obiekcie klasy Parameters.
	 * @return Zwraca obiekt klasy Parameters na podstawie zadanych danych
	 * @see Parameters
	 */
	public Parameters getParameters(int idx) {
		double Tmax = getTmax(idx);
		return (Tmax != -1) ? new Parameters(Tmax, Tmin, Nmax, lambda,
				coolingSchedule) : null;
	}

}
