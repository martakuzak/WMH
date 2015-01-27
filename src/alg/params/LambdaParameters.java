package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj¹ca parametry do testowania dzia³ania algorytmu symulowanego
 * wy¿arzania dla ró¿nych wartoœci parametru wspó³czynnika sch³adzania (lambda)
 * (zmiany w formie ci¹gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 *
 */
public class LambdaParameters {

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
	 * Pocz¹tkowa wartoœæ wspó³czynnika sch³adzania
	 */
	private double lambdaMin;
	/**
	 * Maksymalna wartoœæ wspó³czynnika sch³adzania
	 */
	private double lambdaMax;
	/**
	 * Krok, o jaki zmienia siê lambda (odpowiednik ró¿nicy w ci¹gu
	 * arytmetycznym)
	 */
	private double lambdaStep;
	/**
	 * Schemat sch³adzania
	 */
	private CoolingSchedule coolingSchedule;

	public LambdaParameters(double tmax, double tmin, int nmax,
			double lambdaMin, double lambdaMax, double lambdaStep,
			CoolingSchedule coolingSchedule) {
		super();
		Tmax = tmax;
		Tmin = tmin;
		Nmax = nmax;
		this.lambdaMin = lambdaMin;
		this.lambdaMax = lambdaMax;
		this.lambdaStep = lambdaStep;
		this.coolingSchedule = coolingSchedule;
	}

	/**
	 * 
	 * @return Liczbê lambd w zadanym przedziale <lambdaMin, lambdaMax> o kroku
	 *         lambdaStep
	 */
	public int getNumOfLambdas() {
		Double d = (lambdaMax - lambdaMin) / lambdaStep;
		return d.intValue() + 1;
	}

	/**
	 * 
	 * @param idx
	 *            Numer kolejnej lambdy. Pierwsza to lambdaMin, kolejna to
	 *            lambdaMin + lambdaStep, potem lambdaMin + 2*lambdaStep itd
	 * @return Oczekiwan¹ lambdê lub -1, gdy przekroczymy zadany zakres (wartoœæ
	 *         lambdaMax) 
	 */
	public double getLambda(int idx) {
		double lambda = lambdaMin + idx * lambdaStep;
		return (lambda <= lambdaMax) ? lambda : -1;
	}

	/**
	 * Konwertuje do parametry Parameters lub zwraca null przy zbyt duzym
	 * indeksie lambda.
	 * 
	 * @param lambdaIdx
	 *            Indeks lambdy (w ci¹gu arytmetycznym), która ma byæ ustawiona
	 *            w obiekcie klasy Parameters.
	 * @return Zwraca obiekt klasy Parameters na podstawie zadanych danych
	 * @see Parameters
	 */
	public Parameters getParameters(int lambdaIdx) {
		double lambda = getLambda(lambdaIdx);
		return (lambda != -1) ? new Parameters(Tmax, Tmin, Nmax,
				getLambda(lambdaIdx), coolingSchedule) : null;
	}

}
