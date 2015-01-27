package alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import alg.params.Parameters;

/**
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
 * 
 *         Klasa Annealing udostêpnia metodê statyczn¹, która rozwi¹zuje problem
 *         przydzia³u w dwudzielnym symetrycznym grafie wa¿onym za pomoc¹
 *         algorytmu symulowanego wy¿arzania.
 *
 */

public class Annealing {

	/**
	 * 
	 * Funkcja wyznaczaj¹ca rozwi¹zanie problemu przydzia³u w dwudzielnym
	 * symetrycznym grafie wa¿onym za pomoc¹ algorytmu symulowanego wy¿arzania.
	 * 
	 * @param graph
	 *            macierz reprezentuj¹ca graf, dla którego ma byæ dokonany
	 *            przydzia³. Element tablicy graph[i][j] wskazuje na wagê
	 *            krawêdzi pomiêdzy wierzcho³kiem i (z pierwszego przedzia³u) i
	 *            wierzcho³kiem j (z drugiego przedzia³u)
	 * @param params
	 *            parametry algorytmu
	 * @return wektor rozwi¹zañ algorytmu. Zerowym elementem wektora jest
	 *         tymczasowy rezultat uzyskany po pierwszej iteracji temperatury,
	 *         pierwszy dla drugiej iteracji temperatury itd. Ostatni element to
	 *         wynik koñcowy.
	 * @see TempResPair
	 * @see Parameters
	 */

	public static Vector<TempResPair> findSol(int[][] graph, Parameters params) {

		long start_time = System.nanoTime();

		int ver = graph.length; // liczba wierzcholkow grafu w pojedynczym
								// przedziale (zbiorze)
		int sumX = 0;
		int sumY = 0;

		double Tmax = params.getTmax();
		double Tmin = params.getTmin(); // temperatura koncowa
		double T = Tmax;
		int Nmax = params.getNmax(); // max ilosc iteracji dla jednej
										// temperatury
		double lambda = params.getLambda(); // potrzebne dla schladzania
											// liniowego i geometrycznego
		CoolingSchedule coolingSchedule = params.getCoolingSchedule(); // schemat
																		// chlodzenia

		int[][] X = new int[ver][ver];
		int[] aNeighbours = new int[ver]; // wektor, dla ktorego i-ty element
											// zawiera numer wierzcholka ze
											// zbioru B, z ktorym polaczony jest
											// i-ty wierzcholek ze zbioru A

		Vector<TempResPair> tempResPairs = new Vector<>(); // wektor rozwiazan

		if (ver == 1) {
			long end_time = System.nanoTime();
			sumX = graph[0][0];
			Result result = new Result(end_time - start_time, sumX, X);
			tempResPairs.add(new TempResPair(Tmax, result));
			return tempResPairs;
		}

		ArrayList<Integer> list = new ArrayList<Integer>(); // lista indeksow
															// (potrzebna do
															// wybierania losowo
															// wierzcholkow B
															// dla kazdego A)
		for (int i = 0; i < ver; i++) { // zlozonosc O(ver) - komentarz
										// pomocniczy
			list.add(i);
		}
		Collections.shuffle(list); // zlozonosc O(ver) - komentarz pomocniczy

		// wybieranie losowego rozwiazania X:
		for (int a = 0; a < ver; ++a) { // zlozonosc O(ver) - komentarz
										// pomocniczy
			int b = list.get(a);
			int selWeights = graph[a][b];
			sumX += selWeights; // rozwiazanie X (suma wag)
			X[a][b] = graph[a][b]; // zapisujemy rozw X
			aNeighbours[a] = b;

		}

		int tempIdx = 2;

		while (T >= Tmin) { // zlozonosc w sprawku - komentarz pomocniczy

			int it = 1;
			while (it < Nmax) { // zlozonosc O(Nmax) - komentarz pomocniczy
				// losujemy dwa wezly ze zbioru A i zamieniamy je ich sasiadami
				// ze zbioru B
				final Random randGen = new Random(); // zlozonosc O(1) -
														// komentarz pomocniczy
				int a1, a2 = 0;
				a1 = randGen.nextInt(ver);
				a2 = randGen.nextInt(ver - 1);
				if (a2 >= a1) // wylosowanie dwoch liczb - zlozonosc O(1)
					++a2;

				// z sumX odejmujemy wagi usunietych krawedzi ..
				sumY = sumX
						- (graph[a1][aNeighbours[a1]] + graph[a2][aNeighbours[a2]]);
				// ... i dodajemy sumy nowych krawedzi
				sumY += (graph[a1][aNeighbours[a2]] + graph[a2][aNeighbours[a1]]);

				if (sumX >= sumY) {
					// usuwamy krawedzie
					X[a1][aNeighbours[a1]] = 0;
					X[a2][aNeighbours[a2]] = 0;
					// dodajemy nowe
					X[a1][aNeighbours[a2]] = graph[a1][aNeighbours[a2]];
					X[a2][aNeighbours[a1]] = graph[a2][aNeighbours[a1]];

					// aktualizujemy wektor sasiadow - dwa wierzcholki
					// zamieniaja sie sasiadami!
					final int oldNeighbourA1 = aNeighbours[a1];
					aNeighbours[a1] = aNeighbours[a2];
					aNeighbours[a2] = oldNeighbourA1;

					sumX = sumY;

				} else {
					double probability = 1 / (1 + Math.exp((sumY - sumX) / T));
					double tmp = randGen.nextDouble();
					if (probability > tmp) {
						// usuwamy krawedzie
						X[a1][aNeighbours[a1]] = 0;
						X[a2][aNeighbours[a2]] = 0;
						// dodajemy nowe
						X[a1][aNeighbours[a2]] = graph[a1][aNeighbours[a2]];
						X[a2][aNeighbours[a1]] = graph[a2][aNeighbours[a1]];

						// aktualizujemy wektor sasiadow - dwa wierzcholki
						// zamieniaja sie sasiadami!
						final int oldTmpA1 = aNeighbours[a1];
						aNeighbours[a1] = aNeighbours[a2];
						aNeighbours[a2] = oldTmpA1;

						sumX = sumY;
					}

				}

				++it;
			}
			long tmpEndTime = System.nanoTime();
			Result tmpResult = new Result(tmpEndTime - start_time, sumX, X);
			tempResPairs.add(new TempResPair(T, tmpResult));
			T = updateTemp(T, coolingSchedule, lambda, ++tempIdx, Tmax);

		}
		
		System.out.println("Algorytm pracowal przez " + tempResPairs.lastElement().getResult().getNanoTime() + " ns");

		return tempResPairs;
	}

	/**
	 * 
	 * Funkcja uaktualniaj¹ca temperaturê zgodnie z zadanym schematem
	 * ch³odzenia.
	 * 
	 * @param T
	 *            obecna temperatura
	 * @param coolingSchedule
	 *            schemat ch³odzenia
	 * @param lambda
	 *            (wspó³czynnik sch³adzania, nie dotyczy schematu
	 *            logarytmicznego)
	 * @param k
	 *            numer iteracji
	 * @param Tmax
	 *            maksymalna temperatura (temperatura pocz¹tkowa)
	 * @return uaktualnion¹ wartoœæ temperatury
	 */
	private static double updateTemp(double T, CoolingSchedule coolingSchedule,
			double lambda, int k, double Tmax) {
		switch (coolingSchedule) {
		case COOLING_LINEAR:
			T = T - lambda;
			break;
		case COOLING_GEOMETRICAL:
			T = lambda * T;
			break;
		case COOLING_LOGARITHMIC:
			T = Tmax / Math.log(k);
			break;
		default:
			break; // to sie nie moze zdarzyc
		}
		return T;
	}

}
