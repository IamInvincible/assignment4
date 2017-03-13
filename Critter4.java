/* CRITTERS Critter4.java
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

/**Critter4 always tries to reproduce and always tries to fight*/
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
