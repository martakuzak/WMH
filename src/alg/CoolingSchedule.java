package alg;

import java.awt.Color;

/**
 * 
 * Enum reprezentuj�cy schematy sch�adzania algorytmu symulowanego wy�arzania.
 * 
 * @author Marta Kuzak
 * @author Milena Ma�kowska
 *
 */
public enum CoolingSchedule {
	COOLING_LINEAR(0, Color.RED), COOLING_GEOMETRICAL(1, Color.BLUE), COOLING_LOGARITHMIC(
			2, Color.GREEN);

	private final int coolingSchedule;
	private final Color color;

	CoolingSchedule(int coolSched, Color color) {
		this.coolingSchedule = coolSched;
		this.color = color;
	}

	/**
	 * 
	 * @return warto�� int wybranego schematu sch�adzania
	 */
	public int getValue() {
		return coolingSchedule;
	}

	/**
	 * 
	 * @return Kolor skojarzony ze schematem sch�adzania.
	 */
	public Color getColor() {
		return color;
	}
}
