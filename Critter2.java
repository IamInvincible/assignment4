/*Critter 2 has a 50% of walking in a random direction and a 50% of not moving at all and always tries to fight*/
package assignment4;

public class Critter2 extends Critter{
	@Override
	public String toString() {
		return "2";
	}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		if(this.getEnergy()<(Params.start_energy)/4){
			this.walk(this.getRandomInt(8));
		}
	}
	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return true;
	}

}
