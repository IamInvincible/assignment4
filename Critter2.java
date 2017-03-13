/* CRITTERS Critter2.java
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
/**Critter 2 never moves and always tries to fight*/
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
