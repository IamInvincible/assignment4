/*Critter 1 always tries to fight*/

package assignment4;

public class Critter1 extends Critter {
	@Override
	public String toString() {
		return "1";
	}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		//call reproduce here or in fight()
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		//call reproduce here or in doTimeStep()
		return true;
	}
}
