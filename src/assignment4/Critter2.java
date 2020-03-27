package assignment4;

import java.util.List;

/**
 * This Critter will have a different type of sex: Male(1) and Female(0)
 * Male(1) will always run, Female(0) will always walk
 * They will fight everything unless they meet critter2 (same population)
 * Female will reproduce when their energy is above 80
 * @author Kangji Chen
 *
 */
public class Critter2 extends Critter {
	
	@Override
	public String toString() {
		return "2";
	}
	
	private int sex;
	private int dir;
	
	public Critter2() {
		sex = Critter.getRandomInt(2);
		dir = Critter.getRandomInt(8);
	}
	@Override
	public void doTimeStep() {
		if(sex==0) {
			walk(dir);
			if(getEnergy()>80) {
				Critter2 baby = new Critter2();
				baby.sex = Critter.getRandomInt(2);
				reproduce(baby, Critter.getRandomInt(8));
			}
		}
		else {
			run(dir);
		}
		
		dir = Critter.getRandomInt(8);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		if(oponent=="2"){
			return false;
		}
		else {
			return true;
		}
	}
	
	 public static void runStats(List<Critter> critter2) {
		 int M=0;
		 int F=0;
		 for(Object obj: critter2) {
			 Critter2 k = (Critter2) obj;
			 if(k.sex==0) {
				 F++;
			 }
			 if(k.sex==1) {
				 M++;
			 }
		 }
		 System.out.print(critter2.size()+" Total Critters2  ");
		 System.out.print(M+" Total Male Critter2  ");
		 System.out.print(F+" Total Female Critter2");
		 
	 }
	
	
	
	
	
	
	
	
	
	
}






