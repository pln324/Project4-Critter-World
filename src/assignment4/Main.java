/*
 * CRITTERS Main.java
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

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
 * Usage: java <pkg name>.Main <input file> test input file is
 * optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */

public class Main {

    /* Scanner connected to keyboard input, or input file */
    static Scanner kb;

    /* Input file, used instead of keyboard input if specified */
    private static String inputFile;

    /* If test specified, holds all console output */
    static ByteArrayOutputStream testOutputString;

    /* Use it or not, as you wish! */
    private static boolean DEBUG = false;

    /* if you want to restore output to console */
    static PrintStream old = System.out;

    /* Gets the package name.  The usage assumes that Critter and its
       subclasses are all in the same package. */
    private static String myPackage; // package of Critter file.

    /* Critter cannot be in default pkg. */
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     *
     * @param args args can be empty.  If not empty, provide two
     *             parameters -- the first is a file name, and the
     *             second is test (for test output, where all output
     *             to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java <pkg name>.Main OR java <pkg name>.Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java <pkg name>.Main OR java <pkg name>.Main <input file> <test output>");
            }
            if (args.length >= 2) {
                /* If the word "test" is the second argument to java */
                if (args[1].equals("test")) {
                    /* Create a stream to hold the output */
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    /* Save the old System.out. */
                    old = System.out;
                    /* Tell Java to use the special stream; all
                     * console output will be redirected here from
                     * now */
                    System.setOut(ps);
                }
            }
        } else { // If no arguments to main
            kb = new Scanner(System.in); // Use keyboard and console
        }
        commandInterpreter(kb);

        System.out.flush();
    }

    /* Do not alter the code above for your submission. */

    private static void commandInterpreter(Scanner kb){
        //TODO Implement this method
    	boolean done = false;
    	String in = "";
    	while(!done) {
    		System.out.print("critters>");
    		in = kb.nextLine();
    		String[] input = in.split(" "); //separate user input to parts	
    		if(input[0].equals("quit")) {
    			if(input.length>1) {
    				System.out.println("error processing: " + in);
    			}
    			else {
    				done = true;
    			}
    		}
    		else if(input[0].equals("show")){
    			if(input.length>1) {
    				System.out.println("error processing: " + in);
    			}
    			else {
    				Critter.displayWorld();
    			}
    		}
    		else if(input[0].equals("step")) {
    			if(input.length>2) {
    				System.out.println("error processing: "+in);
    			}
    			else {
    				if(input.length==1) {
    					Critter.worldTimeStep();
    				}
    				else {
    					try {
    						int loop = Integer.parseInt(input[1]);
    						for(int i=0;i<loop;i++) {
    						Critter.worldTimeStep();
    						}
    					}
    						catch(Exception e) {
    							System.out.println("error processing: "+in);
    						}
    				}
    			}
    		}
    		else if(input[0].equals("seed")) {
    			if(input.length>2) {
    				System.out.println("error processing: "+ in);
    			}
    			else {
    				try {
    					long seed = Long.parseLong(input[1]);
    					Critter.setSeed(seed);
    				}
    				catch(Exception e) {
    					System.out.println("error processing: "+in);
    				}
    			}
    		}
    		else if(input[0].equals("create")) {
    			if(input.length>3|input.length<2) {
    				System.out.println("error processing: "+in);
    			}
    			else {
    				try {
    					if(input.length==2) {
    						Critter.createCritter(input[1]);
    					}
    					else if(input.length == 3) {
    						int loop = Integer.parseInt(input[2]);
    						for(int i=0;i<loop;i++) {
    							Critter.createCritter(input[1]);
    						}
    					}
    				}
    				catch(InvalidCritterException |NumberFormatException|NoClassDefFoundError e) {
    					System.out.println("error processing: "+in);
    				}
    			}
    		}
    		else if(input[0].equals("stats")) {
    			if(input.length==2) {
    				String inClass = input[1];
    				java.util.List<Critter> lCritter = null;
    				Class<?> statClass = null;
    			

    				try {
    					lCritter = Critter.getInstances(inClass);
    					statClass = Class.forName(myPackage+"."+inClass);
    					System.out.println(statClass);
    					System.out.println("haha"+statClass.getMethod("runStats", List.class));
    					java.lang.reflect.Method runStats = statClass.getMethod("runStats", List.class);
    					runStats.invoke(statClass, lCritter);
    					System.out.println("haha3");
    				}
    				catch(InvalidCritterException|NullPointerException|SecurityException|IllegalAccessException|InvocationTargetException |ClassNotFoundException e) {
    					System.out.println("error processing: "+in);
    					System.out.println("haha");
    			}
    				catch(NoSuchMethodException e) {
    					Critter.runStats(lCritter);
    				}
    		}
    		    else {
    		    	System.out.println("error processing: "+in);
    			}
    		}
    		else if(input[0].equals("clear")) {
    			if(input.length!=1) {
    				System.out.println("error processing: "+in);
    			}
    			else {
    				Critter.clearWorld();
    			}
    		}
    		else {
    			System.out.println("error processing: "+in);
    		}
    	}
    }
}
