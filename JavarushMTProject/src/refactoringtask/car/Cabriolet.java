package refactoringtask.car;

public class Cabriolet extends Car {

	public Cabriolet(int numberOfPassengers) {
		super(CABRIOLET, numberOfPassengers);
	
	}
	
	public int getMaxSpeed() {
		return MAX_CABRIOLET_SPEED;
	}

}
