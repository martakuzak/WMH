package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj�ca parametry do testowania dzia�ania algorytmu symulowanego
 * wy�arzania dla r�nych warto�ci parametru temperatury pocz�tkowej (zmiany w
 * formie ci�gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class TmaxParameters {

	/**
	 * Temperatura ko�cowa
	 */
	private double Tmin;
	/**
	 * Maksymalna temperatura pocz�tkowa
	 */
	private double TmaxMax;
	/**
	 * Minimalna temperatura pocz�tkowa
	 */
	private double TmaxMin;
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
	 * @return Liczb� Tmax w zadanym przedziale <TmaxMin, TmaxMax> o kroku TStep
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
	 * @return Oczekiwan� warto�� Tmax lub -1, gdy przekroczymy zadany zakres
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
	 *            Indeks Tmax (w ci�gu arytmetycznym), kt�ra ma by� ustawiona w
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
