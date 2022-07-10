package refactoringtask.car;

public class Truck extends Car {

	public Truck( int numberOfPassengers) {
		super(TRUCK, numberOfPassengers);
	}
	public int getMaxSpeed() {
		return MAX_TRUCK_SPEED;
	}

}
