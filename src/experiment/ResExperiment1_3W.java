package experiment;

import restaurantModel.Restaurant;
import restaurantModel.Seeds;
import cern.jet.random.engine.RandomSeedGenerator;


public class ResExperiment1_3W {
	
	// Some constants for experimentation
		public static final double START_TIME=0.0;
		public static final double END_TIME= 360.0 ;   // using 1 day observation interval

	  /** main method **/
	   public static void main(String[] args)
	   {
	       // Lets get a set of uncorrelated seeds
	       RandomSeedGenerator rsg = new RandomSeedGenerator();
	       Seeds sds = new Seeds(rsg);
	       System.out.println("Restaurant Experiment Begin!");
	       // Case 1
	       runRestaurant1Sim("Case 1_3" , 4, 3, 2, sds);
	       // Case 2
	       runRestaurant1Sim("Case 2_3" , 5, 3, 2, sds);
	       // Case 3
	       runRestaurant1Sim("Case 3_3" , 3, 3, 2, sds);
	       // Case 4
	       runRestaurant1Sim("Case 4_3" , 2, 3, 2, sds);

	       
	       // Code to use different seeds to find various cases of the case 1
	       int numRuns = 5;
	       Seeds [] sdsArr = new Seeds[numRuns];
	       for(int i = 0 ; i< numRuns; i++) sdsArr[i] = new Seeds(rsg);
	       for(int i = 0 ; i< numRuns; i++) 
	    	   {
	    	   		runRestaurant1Sim("Case 1_3" , 4, 3, 2, sdsArr[i]);
	    	   }
	   }
	
	   private static void runRestaurant1Sim(String caseID, int num4T, int numW, int numC, Seeds sds)
	   {
	      System.out.printf("%s: Number of large table = %d: Number of Waiters = %d: Number of Cooks = %d \r\n",
	    		  caseID, num4T, numW, numC);
	      Restaurant restSys = new Restaurant(START_TIME, END_TIME, num4T, numW, numC, sds, true);
	      restSys.runSimulation();
	      System.out.println("Simulation Done time to output the results");
	      // compute scalar output
	      System.out.println("Waiter Utilization : " + restSys.waiterUtilization(START_TIME, END_TIME));
	      System.out.println("Cook Utilization : " + restSys.cookUtilization(START_TIME, END_TIME)); 
	      System.out.println("The percentage of the long waiting party: " + restSys.perPartyWait());
	      System.out.println("The percentage of the balk party: " + restSys.perBalkParty());
	      System.out.println("The daily profit of the restaurant: " + restSys.getProfit());
	      System.out.println("The total number of arrived parties: " + restSys.getNumberOfParty());
	   }
}