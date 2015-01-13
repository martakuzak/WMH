package alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;

import alg.params.Parameters;




public class Annealing {
	
	
	
	public static Result findSol (int[][] graph, Parameters params) {
		
		long start_time = System.nanoTime();
		
		int ver = graph.length;
		int sumX = 0;  int sumY = 0;
		//int selWeights = 0 ; 

			
		
		double Tmax = params.getTmax();;                      // temperatura poczatk
		double Tmin = params.getTmin();							//MARTA: temperatura koncowa
		double T = Tmax;		
		int Nmax = params.getNmax();						// max ilosc iteracji
		double lambda = params.getLambda();				//potrzebne dla schladzania liniowego i geometrycznego
		CoolingSchedule coolingSchedule = params.getCoolingSchedule();
		
		int [][] X = new int[ver][ver]; int [][] Y = new int[ver][ver];
		int [] aNeighbours = new int[ver]; // MARTA: wektor, dla ktorego i-ty element zawiera numer wierzcholka ze zbioru B, z ktorym polaczony jest i-ty wierzcholek ze zbioru A
		 										
		if(ver == 1) {
			long end_time = System.nanoTime();
			sumX = graph[0][0];
			return new Result(end_time - start_time, sumX);
		}
		 
		 
		ArrayList<Integer> list = new ArrayList<Integer>();  //lista indeksow (potrzebna do wybierania losowo wierzcholkow B dla kazdego A)
        for (int i = 0;  i < ver;  i++) { //MARTA: zlozonosc O(ver) - komentarz pomocniczy
            list.add(i);
        }
        Collections.shuffle(list); //MARTA: zlozonosc O(ver) - komentarz pomocniczy
        

		//wybieranie losowego rozwiazania X:
		for (int a = 0; a < ver; ++ a) { //MARTA: zlozonosc O(ver) - komentarz pomocniczy
			int b = list.get(a);
			int selWeights = graph[a][b];
			sumX +=  selWeights;             // rozwiazanie X (suma wag)
			
			//System.out.println(Arrays.deepToString(tab));
			System.out.println("krawedzie rozw X: (A"+ (a+1)+ ", B" + (b+1) +")" ) ;
						
			X[a][b] = graph[a][b];                           // zapisujemy rozw X
			aNeighbours[a] = b;
					
		}
		System.out.println("sumX : " +sumX);
		
		while(T >= Tmin) { //MARTA: zlozonosc w sprawku - komentarz pomocniczy
			
			int i = 1;
			while(i < Nmax) { //MARTA: zlozonosc O(Nmax) - komentarz pomocniczy
				//losujemy dwa wezly ze zbioru A i zamieniamy je ich sasiadami ze zbioru B(na poczatek 2, a potem moze 3, ale po co?)
				final Random randGen = new Random(); //MARTA: zlozonosc O(1) - komentarz pomocniczy
				int a1, a2 = 0;
				a1 = randGen.nextInt(ver);
				a2 = randGen.nextInt(ver - 1);
				if(a2 >= a1) //MARTA: wylosowanie dwoch liczb - zlozonosc O(1) :)
					++ a2; 
					
				//z sumX odejmujemy wagi usunietych krawedzi ..
				sumY = sumX - (graph[a1][aNeighbours[a1]] + graph[a2][aNeighbours[a2]]);
				//... i dodajemy sumy nowych krawedzi
				sumY += (graph[a1][aNeighbours[a2]] + graph[a2][aNeighbours[a1]]);
				
				if(sumX >= sumY) {
					//usuwamy krawedzie
					X[a1][aNeighbours[a1]] = 0;
					X[a2][aNeighbours[a2]] = 0;
					//dodajemy nowe
					X[a1][aNeighbours[a2]] = graph[a1][aNeighbours[a2]];
					X[a2][aNeighbours[a1]] = graph[a2][aNeighbours[a1]];
					
					//aktualizujemy wektor z glupia nazwa - dwa wierzcholki zamieniaja sie sasiadami!
					final int oldTmpA1 = aNeighbours[a1];
					aNeighbours[a1] = aNeighbours[a2];
					aNeighbours[a2] = oldTmpA1;
					
					sumX = sumY;
					

					System.out.println("Temp : " + T + " it : " + i + " sumX : " +sumX);
				} else {
					double probability = 1/(1 + Math.exp((sumY - sumX)/T));
					double tmp = randGen.nextDouble();
					if (probability > tmp) { 
						//usuwamy krawedzie
						X[a1][aNeighbours[a1]] = 0;
						X[a2][aNeighbours[a2]] = 0;
						//dodajemy nowe
						X[a1][aNeighbours[a2]] = graph[a1][aNeighbours[a2]];
						X[a2][aNeighbours[a1]] = graph[a2][aNeighbours[a1]];
						
						//aktualizujemy wektor z glupia nazwa - dwa wierzcholki zamieniaja sie sasiadami!
						final int oldTmpA1 = aNeighbours[a1];
						aNeighbours[a1] = aNeighbours[a2];
						aNeighbours[a2] = oldTmpA1;		
						
						sumX = sumY;
						System.out.println("Temp : " + T + " it : " + i + " sumX : " +sumX);
					}	
					
				}	

				++ i;
			}
			T = updateTemp(T, coolingSchedule, lambda, i, Tmax);
			
		}
		
		long end_time = System.nanoTime();
		
		System.out.println("Algortym pracowal przez " + (end_time - start_time) + " ns.");
		int [] results = new int [Nmax]; //
		
		
		
		// iteracje
		
		int i = 1;
		results [i-1] = sumX;
		/*while (i < Nmax) {
			
				Collections.shuffle(list);
				int aa = 0; int bb = 0;
				
				
				// wybieranie losowego rozw Y
				for ( aa = 0; aa < ver; ++ aa) {
					//int bb = 0;
					bb = list.get(aa);
					int selWeights = graph[aa][bb];
					sumY +=  selWeights;             // rozwiazanie Y (suma wag)

					Y[aa][bb] = graph[aa][bb];          // rozw Y
				}
			//	System.out.println("Y: " + Arrays.deepToString(Y) + "sumY : "+sumY);
				
				
				
				
				
				if( sumX > sumY ) {
					  X = Y;                         // przyjmij Y jako aktualne rozw
					  sumX = sumY;
					  results[i] = sumX;
					  
					} else {
						
						int p =0;
						if (T != 0 ){
							 p = (int) (100*(1/(1 + java.lang.Math.exp((sumY-sumX)/T))));      // p-stwo w procentach
						}
						else {
							 p = 0;
						}
						
						// lopatologiczna implementacja fragmentu "przyjmij Y z prawdopodobie�stwem p" ...
						int [] array = new int [100];
						for (int r = 0;  r < p; ++r) {
							array[r]=1;
						}
						
						Random generator = new Random();
						int indx = generator.nextInt(array.length);
						
						
						if (array[indx]==1){
							X = Y;
							sumX = sumY;
							results[i] = sumX;
							}
						else {
							results[i] = sumX;
						}
										
						}
						
				
				
				
				sumY = 0;
				
				//zerowanie tablicy Y
				for (int f=0; f<ver; ++f) {
					for (int g=0; g<ver; ++g) {
						Y[f][g] = 0;
					}
				}

				// ustalamy temperature
				T = (int) (0.8*T);                 // chwilowo tak o
				
				
				i++;	
			
				
		}*/
		return new Result(end_time - start_time, sumX);	
	}

	
	/**
	 * 
	 * @param T obecna temperatura
	 * @param coolingSchedule schemat chlodzenia
	 * @param lambda 
	 * @param k numer iteracji
	 * @param Tmax maksymalna temperatura
	 * @return
	 */
	private static double updateTemp(double T, CoolingSchedule coolingSchedule, double lambda, int k, double Tmax) {
		
		//System.out.print("Update temp " + T + " -> ");
		switch(coolingSchedule) {
		case COOLING_LINEAR:
			T = T - lambda;
			break;
		case COOLING_GEOMETRICAL:
			T = lambda * T;
			break;
		case COOLING_LOGARITHMIC:
			T = Tmax/Math.log(k);
			break;
		default:
			break; //to sie nie moze zdarzyc
		}
		
		//System.out.println(T);
		return T;
	}
	
	public static String chooseRes (int [] results) {
		
	    Arrays.sort(results);
        int min =results[0];
        String ss = "Najmniejsza znaleziona suma wag: " + min +"\n";	//MARTA: ściezka to trochę co innego ;)
        						//to taki ciąg krawędzi, z których każde dwie są ze sobą połączone (i chyba w ramach ścieżki nie moga sie powtarzac krawedzie)
        						//tak ze mozna nimi przejsc (to taka jakby droga)
        return ss;
		
	}
	
	

}
