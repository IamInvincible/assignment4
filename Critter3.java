/*Critter 3 always tries to run away from fights.*/
package assignment4;

public class Critter3 extends Critter {
	@Override
	public String toString() {
		return "3";
	}

	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		this.walk(this.getRandomInt(8));
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		this.walk(this.getRandomInt(8));
		return false;
	}

}
