package alg;

import java.awt.Color;

/**
 * 
 * Enum reprezentuj¹cy schematy sch³adzania algorytmu symulowanego wy¿arzania.
 * 
 * @author Marta Kuzak
 * @author Milena Maækowska
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
	 * @return wartoœæ int wybranego schematu sch³adzania
	 */
	public int getValue() {
		return coolingSchedule;
	}

	/**
	 * 
	 * @return Kolor skojarzony ze schematem sch³adzania.
	 */
	public Color getColor() {
		return color;
	}
}
