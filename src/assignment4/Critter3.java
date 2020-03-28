package assignment4;

import assignment4.Critter.TestCritter;

/**
 * "Dora"
 * An exploring critter
 * Will methodically try to walk on every space in the entire world.
 * Will walk down until all coordinates in the vertical line have been walked on.
 * Then it will move one block to the right and repeat.
 * If the critter does not have enough energy to walk, it will stand still until it does.
 * In an encounter, the critter will always fight.
 * When the world has been entirely explored, it will attempt to reproduce and try to die.
 * It will try to die by standing still and not fighting during any encounters after it has explored everything.
 * 
 * @author Pierce Nguyen
 */
public class Critter3 extends Critter{

	private int blocksDiscovered = 1;
	private int flag = 0;
	
	/**
	 * Walks south until entire column is walked upon. Then
	 * takes one step to the right and repeats. Will stop
	 * moving if it doesn't have enough energy to walk or
	 * it has walked on every space
	 */
	@Override
	public void doTimeStep() {
		int area = Params.WORLD_HEIGHT * Params.WORLD_WIDTH;
		if (blocksDiscovered >= area) {
			if (flag == 0) {
				Critter3 child = new Critter3();
				reproduce(child, Critter.getRandomInt(8));
				flag = 1;
				return;
			}
			else {
				return;
			}
			
		}
		if (this.getEnergy() <= Params.WALK_ENERGY_COST) {
			return;
		}
		if (blocksDiscovered == 0) {
			walk(6);
			blocksDiscovered++;
			return;
		}
		else if (blocksDiscovered%Params.WORLD_HEIGHT == 0) {
			walk(0);
			blocksDiscovered++;
			return;
		}
		else {
			walk(6);
			blocksDiscovered++;
			return;
		}
		
	}

	/**
	 * Will always fight unless it is done exploring
	 */
	@Override
	public boolean fight(String oponent) {
		if (flag == 1) return false;
		return true;
	}

	@Override
	public String toString() {
		return "3";
	}
}
