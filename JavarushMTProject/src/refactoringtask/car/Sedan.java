package refactoringtask.car;

public class Sedan extends Car {

	public Sedan(int numberOfPassengers) {
		super(SEDAN,numberOfPassengers);
	}
	public int getMaxSpeed() {
		return MAX_SEDAN_SPEED;
	}

}
