/*Critter4 always tries to reproduce and always tries to flee*/
package assignment4;

public class Critter4 extends Critter {
	@Override
	public String toString() {
		return "4";
	}

	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		if(this.getEnergy() > (Params.start_energy/4)){
			reproduce(new Critter4(), this.getRandomInt(8));
		}
		this.walk(this.getRandomInt(8));
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		if (this.getEnergy() >= (Params.start_energy) / 10) {
			return true;
		}
		return false;
	}

}
