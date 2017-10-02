package experiment;

import restaurantModel.Restaurant;
import restaurantModel.Seeds;
import outputAnalysis.ConfidenceInterval;
import cern.jet.random.engine.RandomSeedGenerator;


public class ResExperiment3 {
	   
	// Constants
	static final int NUMRUNS = 10000;
	static final double CONF_LEVEL = 0.9;
	static final int [] NUM_RUNS_ARRAY = {20, 30, 40, 60, 80, 100, 1000, 10000};
	
	public static void main(String[] args) {
		
		   // Local variables
	       int i; 
	       double START_TIME=0.0, END_TIME=360.0;
	       Seeds[] sds = new Seeds[NUMRUNS];
	       Restaurant model;  // Simulation object
	        
	       // output value for the case one
	       double [] valuesCase1_Profit = new double[NUMRUNS];
	       double [] valuesCase1_WUtilization = new double[NUMRUNS];
	       double [] valuesCase1_CUtilization = new double[NUMRUNS];
	       double [] valuesCase1_PerWaitParty = new double[NUMRUNS];
	       double [] valuesCase1_PerBalkParty = new double[NUMRUNS];
	       
	       // output value for the case two
	       double [] valuesCase2_Profit = new double[NUMRUNS];
	       double [] valuesCase2_WUtilization = new double[NUMRUNS];
	       double [] valuesCase2_CUtilization = new double[NUMRUNS];
	       double [] valuesCase2_PerWaitParty = new double[NUMRUNS];
	       double [] valuesCase2_PerBalkParty = new double[NUMRUNS];
	       
	       // output value for the case three
	       double [] valuesCase3_Profit = new double[NUMRUNS];
	       double [] valuesCase3_WUtilization = new double[NUMRUNS];
	       double [] valuesCase3_CUtilization = new double[NUMRUNS];
	       double [] valuesCase3_PerWaitParty = new double[NUMRUNS];
	       double [] valuesCase3_PerBalkParty = new double[NUMRUNS];
	       
	       // output value for the case four
	       double [] valuesCase4_Profit = new double[NUMRUNS];
	       double [] valuesCase4_WUtilization = new double[NUMRUNS];
	       double [] valuesCase4_CUtilization = new double[NUMRUNS];
	       double [] valuesCase4_PerWaitParty = new double[NUMRUNS];
	       double [] valuesCase4_PerBalkParty = new double[NUMRUNS];
	       
	       
	       // Lets get a set of uncorrelated seeds
	       RandomSeedGenerator rsg = new RandomSeedGenerator();
	       for(i=0 ; i <NUMRUNS ; i ++) sds[i] = new Seeds(rsg);
	       
	       // Loop for NUMRUN simulation runs for each case
	       // Case 1
	       
	       System.out.println("Case 1 - 4 large table, 3 small table, 2 waiters and 2 cooks");
	       for(i=0 ; i < NUMRUNS ; i++)
	       {
	          model = new Restaurant(START_TIME,END_TIME, 4, 2, 2, sds[i], false);
	          model.runSimulation();
	          
	          valuesCase1_Profit[i] = model.getProfit();
	          valuesCase1_WUtilization[i] = model.waiterUtilization(START_TIME, END_TIME);
	          valuesCase1_CUtilization[i] = model.cookUtilization(START_TIME, END_TIME);
	          valuesCase1_PerWaitParty[i] = model.perPartyWait();
	          valuesCase1_PerBalkParty[i] = model.perBalkParty();
	          
	       }
	       // Case 2
	       System.out.println("Case 2 - 5 large table, 1 small table, 2 waiters and 2 cooks");
	       for(i=0 ; i < NUMRUNS ; i++)
	       {
	           model = new Restaurant(START_TIME,END_TIME, 5, 2, 2, sds[i], false);
	           model.runSimulation();
	           
	           valuesCase2_Profit[i] = model.getProfit();
	           valuesCase2_WUtilization[i] = model.waiterUtilization(START_TIME, END_TIME);
	           valuesCase2_CUtilization[i] = model.cookUtilization(START_TIME, END_TIME);
	           valuesCase2_PerWaitParty[i] = model.perPartyWait();
	           valuesCase2_PerBalkParty[i] = model.perBalkParty();
	       }
	       
	       // Case 3
	       System.out.println("Case 3 - 3 large table, 5 small table, 2 waiters and 2 cooks");
	       for(i=0 ; i < NUMRUNS ; i++)
	       {
	           model = new Restaurant(START_TIME,END_TIME, 3, 2, 2, sds[i], false);
	           model.runSimulation();
	           
	           valuesCase3_Profit[i] = model.getProfit();
	           valuesCase3_WUtilization[i] = model.waiterUtilization(START_TIME, END_TIME);
	           valuesCase3_CUtilization[i] = model.cookUtilization(START_TIME, END_TIME);
	           valuesCase3_PerWaitParty[i] = model.perPartyWait();
	           valuesCase3_PerBalkParty[i] = model.perBalkParty();
	       }
	       
	       // Case 4
	       System.out.println("Case 4 - 2 large table, 7 small table, 2 waiters and 2 cooks");
	       for(i=0 ; i < NUMRUNS ; i++)
	       {
	           model = new Restaurant(START_TIME,END_TIME, 2, 2, 2, sds[i], false);
	           model.runSimulation();
	           
	           valuesCase4_Profit[i] = model.getProfit();
	           valuesCase4_WUtilization[i] = model.waiterUtilization(START_TIME, END_TIME);
	           valuesCase4_CUtilization[i] = model.cookUtilization(START_TIME, END_TIME);
	           valuesCase4_PerWaitParty[i] = model.perPartyWait();
	           valuesCase4_PerBalkParty[i] = model.perBalkParty();
	       }
	       
	       displayTable(valuesCase1_Profit,1, "Profit");
	       displayTable(valuesCase1_WUtilization,1, "Waiter_Utilization");	     
	       displayTable(valuesCase1_CUtilization,1, "Cook_Utilization");  
	       displayTable(valuesCase1_PerWaitParty,1, "Percentage of Long Waiting Party");
	       displayTable(valuesCase1_PerBalkParty,1, "Percentage of Balking Party");
	       
	       displayTable(valuesCase2_Profit,2, "Profit");
	       displayTable(valuesCase2_WUtilization,2, "Waiter_Utilization");	     
	       displayTable(valuesCase2_CUtilization,2, "Cook_Utilization");  
	       displayTable(valuesCase2_PerWaitParty,2, "Percentage of Long Waiting Party");
	       displayTable(valuesCase2_PerBalkParty,2, "Percentage of Balking Party");
	       
	       displayTable(valuesCase3_Profit,3, "Profit");
	       displayTable(valuesCase3_WUtilization,3, "Waiter_Utilization");	     
	       displayTable(valuesCase3_CUtilization,3, "Cook_Utilization");  
	       displayTable(valuesCase3_PerWaitParty,3, "Percentage of Long Waiting Party");
	       displayTable(valuesCase3_PerBalkParty,3, "Percentage of Balking Party");
	       
	       displayTable(valuesCase4_Profit,4, "Profit");
	       displayTable(valuesCase4_WUtilization,4, "Waiter_Utilization");	     
	       displayTable(valuesCase4_CUtilization,4, "Cook_Utilization");  
	       displayTable(valuesCase4_PerWaitParty,4, "Percentage of Long Waiting Party");
	       displayTable(valuesCase4_PerBalkParty,4, "Percentage of Balking Party");
	   
	       
	}
	
	 /*------------ Display the confidence intervals for various number of simulations --------------*/
		private static void displayTable(double [] allValues, int caseNum, String category)
		{
		       System.out.printf("------------------------------------------------------------------\n");
		       System.out.println("                    case " + caseNum + " " + category          );
		       System.out.printf("------------------------------------------------------------------\n");
		       System.out.printf("n        y(n)     s(n)     zeta(n)  CI Min   CI Max   zeta(n)/y(n)\n");
		       System.out.printf("------------------------------------------------------------------\n");
		       for(int ix1 = 0; ix1 < NUM_RUNS_ARRAY.length; ix1++)
		       {
		    	   int numruns = NUM_RUNS_ARRAY[ix1];
		    	   double [] values = new double[numruns];
		    	   for(int ix2 = 0 ; ix2 < numruns; ix2++) values[ix2] = allValues[ix2];
		    	   ConfidenceInterval ci = new ConfidenceInterval(values, CONF_LEVEL);
		    	   System.out.printf("%5d %8.3f %8.3f %8.3f %8.3f %8.3f %8.3f\n",
		    			               numruns, ci.getPointEstimate(), ci.getStdDev(), ci.getZeta(),
		    			               ci.getCfMin(), ci.getCfMax(), ci.getZeta()/ci.getPointEstimate());
		       }
		       System.out.printf("------------------------------------------------------------------\n");
		}

}
