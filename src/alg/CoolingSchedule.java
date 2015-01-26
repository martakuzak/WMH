package alg;

import java.awt.Color;

public enum CoolingSchedule {
	COOLING_LINEAR(0, Color.RED), COOLING_GEOMETRICAL(1, Color.BLUE), COOLING_LOGARITHMIC(2, Color.GREEN);

	private final int coolingSchedule;
	private final Color color;

	CoolingSchedule(int coolSched, Color color) {
		this.coolingSchedule = coolSched;
		this.color = color;
	}

	public int getValue() {
		return coolingSchedule;
	}
	
	public Color getColor() {
		return color;
	}
}
