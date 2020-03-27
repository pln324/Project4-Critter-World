package assignment4;

/**
 * This critter will only walk in one direction, when it's energy is below 50, it will start to run in a different direction
 * It will not fight when it is walking; it will always fight when is running
 * @author Kangji Chen
 *
 */
public class Critter1 extends Critter {
	@Override
    public String toString() {
        return "1";
    }
	
	private int dirWalk;
	private int dirRun;
	
	public Critter1() {
		dirWalk = Critter.getRandomInt(8);
		dirRun = Critter.getRandomInt(8);
	}

	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		if(getEnergy()>50) {
			walk(dirWalk);
		}else {
			run(dirRun);
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		if(getEnergy()>50) {
			return false;
		}else {
			return true;
		}
	}
}
