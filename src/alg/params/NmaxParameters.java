package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj¹ca parametry do testowania dzia³ania algorytmu symulowanego
 * wy¿arzania dla ró¿nych wartoœci parametru liczby iteracji dla jednej
 * temperatury (Nmax) (zmiany w formie ci¹gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class NmaxParameters {
	/**
	 * Temperatura pocz¹tkowa
	 */
	private double Tmax;
	/**
	 * Temperatura koñcowa
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
	 * Wartoœæ wspó³czynnika sch³adzania (lambda)
	 */
	private double lambda;
	/**
	 * Krok, o jaki zmienia siê Nmax (odpowiednik ró¿nicy w ci¹gu arytmetycznym)
	 */
	private int NmaxStep;
	/**
	 * Schemat sch³adzania
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
	 * @return Liczbê Nmax w zadanym przedziale <NmaxMin, NmaxMax> o kroku
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
	 * @return Oczekiwan¹ wartoœæ Nmax lub -1, gdy przekroczymy zadany zakres
	 *         (wartoœæ NmaxMax)
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
	 *            Indeks Nmax (w ci¹gu arytmetycznym), która ma byæ ustawiona w
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
