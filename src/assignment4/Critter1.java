package assignment4;

import assignment4.Critter.TestCritter;

public class Critter1 extends TestCritter {
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
		return false;
	}
}
