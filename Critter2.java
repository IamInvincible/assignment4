/*Critter 2 never moves and always tries to fight*/
package assignment4;

public class Critter2 extends Critter{
	@Override
	public String toString() {
		return "2";
	}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}

}
