package alg;

public enum CoolingSchedule {
	COOLING_LINEAR(0), COOLING_GEOMETRICAL(1), COOLING_LOGARITHMIC(2);

	private final int coolingSchedule;

	CoolingSchedule(int coolSched) {
		this.coolingSchedule = coolSched;
	}

	public int getValue() {
		return coolingSchedule;
	}
}
