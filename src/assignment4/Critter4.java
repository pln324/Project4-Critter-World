package assignment4;

import assignment4.Critter.TestCritter;

/**
 * "Mama"
 * Tries to reproduce as often as possible.
 * If this critter gets into a fight, it will either run or reproduce.
 * If the critter does not have enough energy to reproduce, it will fight until it does.
 * Will also fight Goblins to protect offspring
 * 
 * @author Pierce Nguyen
 */
public class Critter4 extends Critter{

	/**
	 * Will attempt to reproduce every time step
	 */
	@Override
	public void doTimeStep() {
		Critter4 child = new Critter4();
		reproduce(child, Critter.getRandomInt(8));
	}

	/**
	 * Will either run or reproduce. If not enough energy 
	 * to reproduce or if enemy is a Goblin, Critter will 
	 * fight
	 * 
	 * @param oponent Critter currently being fought
	 */
	@Override
	public boolean fight(String oponent) {
		int decision = Critter.getRandomInt(2);
		if (oponent == "G") {
			return true;
		}
		if (this.getEnergy()<Params.MIN_REPRODUCE_ENERGY) {
			return true;
		}
		else {
			switch(decision) {
			case 0:
				run(Critter.getRandomInt(8));
			case 1:
				Critter4 child = new Critter4();
				reproduce(child, Critter.getRandomInt(8));
			}
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "4";
	}
	
}
