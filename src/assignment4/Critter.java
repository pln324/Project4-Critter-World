/*
 * CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Spring 2020
 */

package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.lang.reflect.Constructor;

/* 
 * See the PDF for descriptions of the methods and fields in this
 * class. 
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    private int energy = 0;

    private int x_coord;
    private int y_coord;

    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
        // TODO: Complete this method
    	try {
			//Critter k = (Critter) Class.forName(myPackage+"."+critter_class_name).newInstance();  
    		Class<?> newCri = Class.forName(myPackage+"."+critter_class_name);
    		Critter k = (Critter) newCri.newInstance();
    		k.energy= Params.START_ENERGY;
    		k.x_coord= Critter.getRandomInt(Params.WORLD_WIDTH);
    		k.y_coord= Critter.getRandomInt(Params.WORLD_HEIGHT);
    		population.add(k);
    	}
    	catch(ClassNotFoundException e){
    		throw new InvalidCritterException(critter_class_name);
    	}
    	catch(IllegalAccessException e){
    		throw new InvalidCritterException(critter_class_name);
    	}
    	catch(InstantiationException e){
    		throw new InvalidCritterException(critter_class_name);
    	}
    	catch(IllegalArgumentException e){
    		throw new InvalidCritterException(critter_class_name);
    	}
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *        Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        // TODO: Complete this method
    	List<Critter> sameKind = new ArrayList<Critter>();
    	Class<?> newCri;
    	try {
    		newCri = Class.forName(myPackage+"."+critter_class_name);
    	}
    	catch(ClassNotFoundException e) {
    		throw new InvalidCritterException(critter_class_name);
    	}
    	for(Critter critter : population) {
    		if(newCri.isInstance(critter)) {
    			sameKind.add(critter);
    		}
    	}
        return sameKind;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        // TODO: Complete this method
    	population.clear();
    	babies.clear();
    }

    public static void worldTimeStep() {
        // TODO: Complete this method
    }

    public static void displayWorld() {
        //set up the border
    	String[][] world = new String[Params.WORLD_HEIGHT+2][Params.WORLD_WIDTH+2];
    	world[0][0]="+";
    	world[0][Params.WORLD_WIDTH+1]="+";
    	world[Params.WORLD_HEIGHT+1][0]="+";
    	world[Params.WORLD_HEIGHT+1][Params.WORLD_WIDTH+1]="+";
    	
    	for(int i=1;i<Params.WORLD_HEIGHT+1;i++) {
    		world[i][0]="|";
    		world[i][Params.WORLD_WIDTH+1]="|";
    	}
    	for(int i=1; i<Params.WORLD_WIDTH+1; i++) {
    		world[0][i] = "-";
    		world[Params.WORLD_HEIGHT+1][i]="-";
    	}
    	for(Critter critter: population) {
    		world[critter.x_coord+1][critter.y_coord+1]=critter.toString();
    	}
    	for(int i=1;i<Params.WORLD_HEIGHT+1;i++) {
    		for(int j=1;j<Params.WORLD_WIDTH+1;j++) {
    			world[i][j]=" ";
    		}
    	}
    	for(int i=0;i<population.size();i++) {
    		world[population.get(i).y_coord+1][population.get(i).x_coord+1] = population.get(i).toString();
    	}
    	for(int i=0;i<Params.WORLD_HEIGHT+2;i++) {
    		for(int j=0;j<Params.WORLD_WIDTH+2;j++) {
    			System.out.print(world[i][j]);
    		}
    		System.out.println();
    	}
    }

    /**
     * Prints out how many Critters of each type there are on the
     * board.
     *
     * @param critters List of Critters.
     */
    public static void runStats(List<Critter> critters) {
        System.out.print("" + critters.size() + " critters as follows -- ");
        Map<String, Integer> critter_count = new HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            critter_count.put(crit_string,
                    critter_count.getOrDefault(crit_string, 0) + 1);
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            System.out.print(prefix + s + ":" + critter_count.get(s));
            prefix = ", ";
        }
        System.out.println();
    }

    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    protected final void walk(int direction) {
        // TODO: Complete this method
    }

    protected final void run(int direction) {
        // TODO: Complete this method

    }

    protected final void reproduce(Critter offspring, int direction) {
        // TODO: Complete this method
    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
