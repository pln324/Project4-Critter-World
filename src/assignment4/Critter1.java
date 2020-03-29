package assignment4;

/**
 * This critter will only walk in one direction, when it's energy is below 50, it will start to run in a different direction
 * It will not fight when it is walking; it will always fight when is running
 * @author Kangji Chen
 *
 */
public class Critter1 extends Critter {
	/**
	 * make this critter be represented by "1"
	 */
	@Override
    public String toString() {
        return "1";
    }
	
	private int dirWalk;
	private int dirRun;
	
	/**
	 * constructor to get a unique direction for run and one for walk, for each Critter1
	 */
	public Critter1() {
		dirWalk = Critter.getRandomInt(8);
		dirRun = Critter.getRandomInt(8);
	}
	
	/**
	 * if energy is above 50, walk 
	 * if not, run
	 */
	@Override
	public void doTimeStep() {
		
		if(getEnergy()>50) {
			walk(dirWalk);
		}else {
			run(dirRun);
		}
		
	}
	
	/**
	 * if energy is above 50, no fighting
	 * if not, fight
	 */
	@Override
	public boolean fight(String oponent) {

		if(getEnergy()>50) {
			return false;
		}else {
			return true;
		}
	}
}
