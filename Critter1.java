/* CRITTERS Critter1.java
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

/**Critter 1 only walks in random directions and always tries to fight*/

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
		this.walk(Critter.getRandomInt(8));
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		//call reproduce here or in doTimeStep()
		return true;
	}
}
