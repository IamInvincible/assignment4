/*Critter 2 runs in random directions and only walks if it has less than 50% of its starting energy (rounded down)*/
package assignment4;

public class Critter2 extends Critter{
	@Override
	public String toString() {
		return "2";
	}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		if(this.getEnergy()<(Params.start_energy)/2){
			this.run(this.getRandomInt(8));
		}
	}
	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return false;
	}

}
