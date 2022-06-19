package ippodromGame;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
	
	public static Hippodrome game;
	
	private List<Horse> horses;
	

	public static void main(String[] args) {
		// a) Создать список лошадей (horses).
		List<Horse> horses = new ArrayList<>();
		horses.add(new Horse("Vasya",3.0,0.0));
		horses.add(new Horse("Petya",3.0,0.0));
		horses.add(new Horse("Valera",3.0,0.0));
		Hippodrome hp = new Hippodrome(horses);
		Hippodrome.game = hp;
		game.run();
		game.printWinner();
		

	}
	

	public Hippodrome(List<Horse> horses) {
		
		this.horses = horses;
	}
	public void run() {
		for (int i = 0; i < 100; i++) {
			move();
			print();
			try {
				Thread.sleep(200);//чтобы цикл не отработал за долю секунды
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public Horse getWinner() {
		double max=0;
		Horse winner =null;
		for(Horse h: horses) {
			if(h.getDistance()>=max) {
				max = h.getDistance();
				winner = h;
			}
		}
		return winner;
	}
	
	public void printWinner() {
		System.out.println("Winner is "+getWinner().getName()+"!");
	}
	
	public void move() {
		for(Horse h: horses) {
			h.move();
		}
		
	}
	public void print() {
		for(Horse h: horses) {
			h.print();

		}
		for(int i=0; i<10; i++) {
				System.out.println();
			}
		
	}


	public List<Horse> getHorses() {
		return horses;
	}
	
	
	

}
