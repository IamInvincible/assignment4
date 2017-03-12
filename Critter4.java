/*Critter4 always tries to reproduce and always tries to fight*/
package assignment4;

public class Critter4 extends Critter {
	@Override
	public String toString() {
		return "4";
	}

	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		reproduce(new Critter4(), Critter.getRandomInt(8));
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}

}
