package alg.params;

import alg.CoolingSchedule;

/**
 * Klasa reprezentuj�ca parametry do testowania dzia�ania algorytmu symulowanego
 * wy�arzania dla r�nych warto�ci parametru wsp�czynnika sch�adzania (lambda)
 * (zmiany w formie ci�gu arytmetycznego)
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public class LambdaParameters {

	/**
	 * Temperatura pocz�tkowa
	 */
	private double Tmax;
	/**
	 * Temperatura ko�cowa
	 */
	private double Tmin;
	/**
	 * Liczba iteracji dla zadanej temperatury
	 */
	private int Nmax;
	/**
	 * Pocz�tkowa warto�� wsp�czynnika sch�adzania
	 */
	private double lambdaMin;
	/**
	 * Maksymalna warto�� wsp�czynnika sch�adzania
	 */
	private double lambdaMax;
	/**
	 * Krok, o jaki zmienia si� lambda (odpowiednik r�nicy w ci�gu
	 * arytmetycznym)
	 */
	private double lambdaStep;
	/**
	 * Schemat sch�adzania
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
	 * @return Liczb� lambd w zadanym przedziale <lambdaMin, lambdaMax> o kroku
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
	 * @return Oczekiwan� lambd� lub -1, gdy przekroczymy zadany zakres (warto��
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
	 *            Indeks lambdy (w ci�gu arytmetycznym), kt�ra ma by� ustawiona
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
