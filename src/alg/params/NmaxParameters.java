package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj�ca parametry do testowania dzia�ania algorytmu symulowanego
 * wy�arzania dla r�nych warto�ci parametru liczby iteracji dla jednej
 * temperatury (Nmax) (zmiany w formie ci�gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class NmaxParameters {
	/**
	 * Temperatura pocz�tkowa
	 */
	private double Tmax;
	/**
	 * Temperatura ko�cowa
	 */
	private double Tmin;
	/**
	 * Maksymalna liczba iteracji
	 */
	private int NmaxMax;
	/**
	 * Minimalna liczba iteracji
	 */
	private int NmaxMin;
	/**
	 * Warto�� wsp�czynnika sch�adzania (lambda)
	 */
	private double lambda;
	/**
	 * Krok, o jaki zmienia si� Nmax (odpowiednik r�nicy w ci�gu arytmetycznym)
	 */
	private int NmaxStep;
	/**
	 * Schemat sch�adzania
	 */
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

	/**
	 * 
	 * @return Liczb� Nmax w zadanym przedziale <NmaxMin, NmaxMax> o kroku
	 *         NmaxStep
	 */
	public int getNmaxNum() {
		return (NmaxMax - NmaxMin) / NmaxStep + 1;
	}

	/**
	 * 
	 * @param idx
	 *            Numer kolejnego Nmax. Pierwszy to NmaxMin, kolejny to NmaxMin
	 *            + NmaxStep, potem NmaxMin + 2*NmaxStep itd
	 * @return Oczekiwan� warto�� Nmax lub -1, gdy przekroczymy zadany zakres
	 *         (warto�� NmaxMax)
	 */
	public int getNmax(int idx) {
		int Nmax = NmaxMin + (idx) * NmaxStep;
		return (Nmax <= NmaxMax) ? Nmax : -1;
	}

	/**
	 * Konwertuje do parametry Parameters lub zwraca null przy zbyt duzym
	 * indeksie Nmax.
	 * 
	 * @param idx
	 *            Indeks Nmax (w ci�gu arytmetycznym), kt�ra ma by� ustawiona w
	 *            obiekcie klasy Parameters.
	 * @return Zwraca obiekt klasy Parameters na podstawie zadanych danych
	 * @see Parameters
	 */
	public Parameters getParameters(int idx) {
		int Nmax = getNmax(idx);
		return (Nmax != -1) ? new Parameters(Tmax, Tmin, getNmax(idx), lambda,
				coolingSchedule) : null;
	}

}
