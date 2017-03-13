/* CRITTERS Critter3.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Jason Fang
 * jhf649
 * 16238
 * Cejay Zhu
 * cz4723
 * 16238
 * Slip days used: 1
 * Fall 2016
 */
/**Critter 3 always runs in random directions and has a 50% of attempting to fight.*/
package assignment4;

public class Critter3 extends Critter {
	@Override
	public String toString() {
		return "3";
	}

	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		this.run(Critter.getRandomInt(8));
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		int x = Critter.getRandomInt(1);
		if (x == 1) {
			return true;
		}
		return false;
	}

}
