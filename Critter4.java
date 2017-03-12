/*Critter4 reproduces only if it has more than 25% of Params.start_energy (rounded down) and only walks in random directions and always fights*/
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
