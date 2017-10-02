package experiment;

import outputAnalysis.ConfidenceInterval;
import restaurantModel.Restaurant;
import restaurantModel.Seeds;
import cern.jet.random.engine.*;

// Main Method: Experiment 1, used to run a single simulation generate 
// The results of 20 simulation runs to show CI's similar to Table 5.2
// from the text book

public class ResExperiment2
{
   public static void main(String[] args)
   {
	   // Constants
	   final int NUMRUNS = 30;
	   final double CONF_LEVEL = 0.9;  // Confidence levels
	   // Local variables
       int i; 
       double START_TIME=0.0, END_TIME=360.0;
       Seeds[] sds = new Seeds[NUMRUNS];
       Restaurant model;  // Simulation object
       // Confidence interval for case 1
       ConfidenceInterval cfIntCase1_Profit;
       ConfidenceInterval cfIntCase1_WUtilization;
       ConfidenceInterval cfIntCase1_CUtilization;
       ConfidenceInterval cfIntCase1_PerWaitParty;
       ConfidenceInterval cfIntCase1_PerBalkParty;
       
       // Confidence interval for case 2
       ConfidenceInterval cfIntCase2_Profit;
       ConfidenceInterval cfIntCase2_WUtilization;
       ConfidenceInterval cfIntCase2_CUtilization;
       ConfidenceInterval cfIntCase2_PerWaitParty;
       ConfidenceInterval cfIntCase2_PerBalkParty;
       
       // Confidence interval for case 3
       ConfidenceInterval cfIntCase3_Profit;
       ConfidenceInterval cfIntCase3_WUtilization;
       ConfidenceInterval cfIntCase3_CUtilization;
       ConfidenceInterval cfIntCase3_PerWaitParty;
       ConfidenceInterval cfIntCase3_PerBalkParty;
       
       // Confidence interval for case 4
       ConfidenceInterval cfIntCase4_Profit;
       ConfidenceInterval cfIntCase4_WUtilization;
       ConfidenceInterval cfIntCase4_CUtilization;
       ConfidenceInterval cfIntCase4_PerWaitParty;
       ConfidenceInterval cfIntCase4_PerBalkParty;      

       
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
       for(i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
       
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
       
       // Define confidence intervals with 90% confidence level
       cfIntCase1_Profit = new ConfidenceInterval(valuesCase1_Profit, CONF_LEVEL);
       cfIntCase1_WUtilization = new ConfidenceInterval(valuesCase1_WUtilization, CONF_LEVEL);
       cfIntCase1_CUtilization = new ConfidenceInterval(valuesCase1_CUtilization, CONF_LEVEL);
       cfIntCase1_PerWaitParty = new ConfidenceInterval(valuesCase1_PerWaitParty, CONF_LEVEL);
       cfIntCase1_PerBalkParty = new ConfidenceInterval(valuesCase1_PerBalkParty, CONF_LEVEL);


       cfIntCase2_Profit = new ConfidenceInterval(valuesCase2_Profit, CONF_LEVEL);
       cfIntCase2_WUtilization = new ConfidenceInterval(valuesCase2_WUtilization, CONF_LEVEL);
       cfIntCase2_CUtilization = new ConfidenceInterval(valuesCase2_CUtilization, CONF_LEVEL);
       cfIntCase2_PerWaitParty = new ConfidenceInterval(valuesCase2_PerWaitParty, CONF_LEVEL);
       cfIntCase2_PerBalkParty = new ConfidenceInterval(valuesCase2_PerBalkParty, CONF_LEVEL);
       
       cfIntCase3_Profit = new ConfidenceInterval(valuesCase3_Profit, CONF_LEVEL);
       cfIntCase3_WUtilization = new ConfidenceInterval(valuesCase3_WUtilization, CONF_LEVEL);
       cfIntCase3_CUtilization = new ConfidenceInterval(valuesCase3_CUtilization, CONF_LEVEL);
       cfIntCase3_PerWaitParty = new ConfidenceInterval(valuesCase3_PerWaitParty, CONF_LEVEL);
       cfIntCase3_PerBalkParty = new ConfidenceInterval(valuesCase3_PerBalkParty, CONF_LEVEL);       
       
       cfIntCase4_Profit = new ConfidenceInterval(valuesCase4_Profit, CONF_LEVEL);
       cfIntCase4_WUtilization = new ConfidenceInterval(valuesCase4_WUtilization, CONF_LEVEL);
       cfIntCase4_CUtilization = new ConfidenceInterval(valuesCase4_CUtilization, CONF_LEVEL);
       cfIntCase4_PerWaitParty = new ConfidenceInterval(valuesCase4_PerWaitParty, CONF_LEVEL);
       cfIntCase4_PerBalkParty = new ConfidenceInterval(valuesCase4_PerBalkParty, CONF_LEVEL);
       
       
       /*------------ Display the resulting confidence intervals --------------*/
       // Header
       System.out.printf("   The output statistic of Case 1  (4 Large Tables)                \n");
       System.out.printf("   Run  Profit WaiterUtil CookUtil PerWaitParty PerBalkParty \n");
       System.out.printf("------------------------------------------------------------------------\n");
       // Simulation values
       for(i = 0; i < NUMRUNS; i++)
           System.out.printf("%7d %8.3f %8.3f %8.3f %8.3f %8.3f\n",i+1, 
        		   valuesCase1_Profit[i], valuesCase1_WUtilization[i],valuesCase1_CUtilization[i],
        		   valuesCase1_PerWaitParty[i], valuesCase1_PerBalkParty[i]);
       // Confidence intervals of the case 1
       System.out.printf("-------------------------------------------------------------------------\n");
       System.out.printf("    PE    %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase1_Profit.getPointEstimate(), 
    		   cfIntCase1_WUtilization.getPointEstimate(), cfIntCase1_CUtilization.getPointEstimate(), 
    		   cfIntCase1_PerWaitParty.getPointEstimate(), cfIntCase1_PerBalkParty.getPointEstimate());
       System.out.printf("    S(n)  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase1_Profit.getStdDev(), 
    		   cfIntCase1_WUtilization.getStdDev(), cfIntCase1_CUtilization.getStdDev(), 
    		   cfIntCase1_PerWaitParty.getStdDev(), cfIntCase1_PerBalkParty.getStdDev());
       System.out.printf("    zeta  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase1_Profit.getZeta(), 
    		   cfIntCase1_WUtilization.getZeta(), cfIntCase1_CUtilization.getZeta(), 
    		   cfIntCase1_PerWaitParty.getZeta(), cfIntCase1_PerBalkParty.getZeta());
       System.out.printf("  CI Min  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase1_Profit.getCfMin(), 
    		   cfIntCase1_WUtilization.getCfMin(), cfIntCase1_CUtilization.getCfMin(), 
    		   cfIntCase1_PerWaitParty.getCfMin(), cfIntCase1_PerBalkParty.getCfMin());	  
       System.out.printf("  CI Max  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase1_Profit.getCfMax(), 
    		   cfIntCase1_WUtilization.getCfMax(), cfIntCase1_CUtilization.getCfMax(), 
    		   cfIntCase1_PerWaitParty.getCfMax(), cfIntCase1_PerBalkParty.getCfMax());	  
       System.out.printf(" zeta/PE  %8.3f %8.3f %8.3f %8.3f %8.3f\n", 
    		   cfIntCase1_Profit.getZeta()/cfIntCase1_Profit.getPointEstimate(), 
    		   cfIntCase1_WUtilization.getZeta()/cfIntCase1_WUtilization.getPointEstimate(), 
    		   cfIntCase1_CUtilization.getZeta()/cfIntCase1_CUtilization.getPointEstimate(), 
    		   cfIntCase1_PerWaitParty.getZeta()/cfIntCase1_PerWaitParty.getPointEstimate(), 
    		   cfIntCase1_PerBalkParty.getZeta()/cfIntCase1_PerBalkParty.getPointEstimate());
       System.out.printf("-----------------------------------------------------------------------------\n");	   
       
       // case 2
       System.out.printf("   The output statistic of Case 2  (5 Large Tables)                \n");
       System.out.printf("   Run  Profit WaiterUtil CookUtil PerWaitParty PerBalkParty \n");
       System.out.printf("------------------------------------------------------------------------\n");
       // Simulation values
       for(i = 0; i < NUMRUNS; i++)
           System.out.printf("%7d %8.3f %8.3f %8.3f %8.3f %8.3f\n",i+1, 
        		   valuesCase2_Profit[i], valuesCase2_WUtilization[i],valuesCase2_CUtilization[i],
        		   valuesCase2_PerWaitParty[i], valuesCase2_PerBalkParty[i]);
       // Confidence intervals of the case 2
       System.out.printf("-------------------------------------------------------------------------\n");
       System.out.printf("    PE    %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase2_Profit.getPointEstimate(), 
    		   cfIntCase2_WUtilization.getPointEstimate(), cfIntCase2_CUtilization.getPointEstimate(), 
    		   cfIntCase2_PerWaitParty.getPointEstimate(), cfIntCase2_PerBalkParty.getPointEstimate());
       System.out.printf("    S(n)  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase2_Profit.getStdDev(), 
    		   cfIntCase2_WUtilization.getStdDev(), cfIntCase2_CUtilization.getStdDev(), 
    		   cfIntCase2_PerWaitParty.getStdDev(), cfIntCase2_PerBalkParty.getStdDev());
       System.out.printf("    zeta  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase2_Profit.getZeta(), 
    		   cfIntCase2_WUtilization.getZeta(), cfIntCase2_CUtilization.getZeta(), 
    		   cfIntCase2_PerWaitParty.getZeta(), cfIntCase2_PerBalkParty.getZeta());
       System.out.printf("  CI Min  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase2_Profit.getCfMin(), 
    		   cfIntCase2_WUtilization.getCfMin(), cfIntCase2_CUtilization.getCfMin(), 
    		   cfIntCase2_PerWaitParty.getCfMin(), cfIntCase2_PerBalkParty.getCfMin());	  
       System.out.printf("  CI Max  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase2_Profit.getCfMax(), 
    		   cfIntCase2_WUtilization.getCfMax(), cfIntCase2_CUtilization.getCfMax(), 
    		   cfIntCase2_PerWaitParty.getCfMax(), cfIntCase2_PerBalkParty.getCfMax());	  
       System.out.printf(" zeta/PE  %8.3f %8.3f %8.3f %8.3f %8.3f\n", 
    		   cfIntCase2_Profit.getZeta()/cfIntCase2_Profit.getPointEstimate(), 
    		   cfIntCase2_WUtilization.getZeta()/cfIntCase2_WUtilization.getPointEstimate(), 
    		   cfIntCase2_CUtilization.getZeta()/cfIntCase2_CUtilization.getPointEstimate(), 
    		   cfIntCase2_PerWaitParty.getZeta()/cfIntCase2_PerWaitParty.getPointEstimate(), 
    		   cfIntCase2_PerBalkParty.getZeta()/cfIntCase2_PerBalkParty.getPointEstimate());
       System.out.printf("-----------------------------------------------------------------------------\n");	 
       
       
       
       // case 3
       System.out.printf("   The output statistic of Case 3  (3 Large Tables)                \n");
       System.out.printf("   Run  Profit WaiterUtil CookUtil PerWaitParty PerBalkParty \n");
       System.out.printf("------------------------------------------------------------------------\n");
       // Simulation values
       for(i = 0; i < NUMRUNS; i++)
           System.out.printf("%7d %8.3f %8.3f %8.3f %8.3f %8.3f\n",i+1, 
        		   valuesCase3_Profit[i], valuesCase3_WUtilization[i],valuesCase3_CUtilization[i],
        		   valuesCase3_PerWaitParty[i], valuesCase3_PerBalkParty[i]);
       // Confidence intervals of the case 3
       System.out.printf("-------------------------------------------------------------------------\n");
       System.out.printf("    PE    %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase3_Profit.getPointEstimate(), 
    		   cfIntCase3_WUtilization.getPointEstimate(), cfIntCase3_CUtilization.getPointEstimate(), 
    		   cfIntCase3_PerWaitParty.getPointEstimate(), cfIntCase3_PerBalkParty.getPointEstimate());
       System.out.printf("    S(n)  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase3_Profit.getStdDev(), 
    		   cfIntCase3_WUtilization.getStdDev(), cfIntCase3_CUtilization.getStdDev(), 
    		   cfIntCase3_PerWaitParty.getStdDev(), cfIntCase3_PerBalkParty.getStdDev());
       System.out.printf("    zeta  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase3_Profit.getZeta(), 
    		   cfIntCase3_WUtilization.getZeta(), cfIntCase3_CUtilization.getZeta(), 
    		   cfIntCase3_PerWaitParty.getZeta(), cfIntCase3_PerBalkParty.getZeta());
       System.out.printf("  CI Min  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase3_Profit.getCfMin(), 
    		   cfIntCase3_WUtilization.getCfMin(), cfIntCase3_CUtilization.getCfMin(), 
    		   cfIntCase3_PerWaitParty.getCfMin(), cfIntCase3_PerBalkParty.getCfMin());	  
       System.out.printf("  CI Max  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase3_Profit.getCfMax(), 
    		   cfIntCase3_WUtilization.getCfMax(), cfIntCase3_CUtilization.getCfMax(), 
    		   cfIntCase3_PerWaitParty.getCfMax(), cfIntCase3_PerBalkParty.getCfMax());	  
       System.out.printf(" zeta/PE  %8.3f %8.3f %8.3f %8.3f %8.3f\n", 
    		   cfIntCase3_Profit.getZeta()/cfIntCase3_Profit.getPointEstimate(), 
    		   cfIntCase3_WUtilization.getZeta()/cfIntCase3_WUtilization.getPointEstimate(), 
    		   cfIntCase3_CUtilization.getZeta()/cfIntCase3_CUtilization.getPointEstimate(), 
    		   cfIntCase3_PerWaitParty.getZeta()/cfIntCase3_PerWaitParty.getPointEstimate(), 
    		   cfIntCase3_PerBalkParty.getZeta()/cfIntCase3_PerBalkParty.getPointEstimate());
       System.out.printf("-----------------------------------------------------------------------------\n");	
       
       
       // case 4
       System.out.printf("   The output statistic of Case 4  (2 Large Tables)                \n");
       System.out.printf("   Run  Profit WaiterUtil CookUtil PerWaitParty PerBalkParty   \n");
       System.out.printf("------------------------------------------------------------------------\n");
       // Simulation values
       for(i = 0; i < NUMRUNS; i++)
           System.out.printf("%7d %8.3f %8.3f %8.3f %8.3f %8.3f\n",i+1, 
        		   valuesCase4_Profit[i], valuesCase4_WUtilization[i],valuesCase4_CUtilization[i],
        		   valuesCase4_PerWaitParty[i], valuesCase4_PerBalkParty[i]);
       // Confidence intervals of the case 4
       System.out.printf("-------------------------------------------------------------------------\n");
       System.out.printf("    PE    %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase4_Profit.getPointEstimate(), 
    		   cfIntCase4_WUtilization.getPointEstimate(), cfIntCase4_CUtilization.getPointEstimate(), 
    		   cfIntCase4_PerWaitParty.getPointEstimate(), cfIntCase4_PerBalkParty.getPointEstimate());
       System.out.printf("    S(n)  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase4_Profit.getStdDev(), 
    		   cfIntCase4_WUtilization.getStdDev(), cfIntCase4_CUtilization.getStdDev(), 
    		   cfIntCase4_PerWaitParty.getStdDev(), cfIntCase4_PerBalkParty.getStdDev());
       System.out.printf("    zeta  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase4_Profit.getZeta(), 
    		   cfIntCase4_WUtilization.getZeta(), cfIntCase4_CUtilization.getZeta(), 
    		   cfIntCase4_PerWaitParty.getZeta(), cfIntCase4_PerBalkParty.getZeta());
       System.out.printf("  CI Min  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase4_Profit.getCfMin(), 
    		   cfIntCase4_WUtilization.getCfMin(), cfIntCase4_CUtilization.getCfMin(), 
    		   cfIntCase4_PerWaitParty.getCfMin(), cfIntCase4_PerBalkParty.getCfMin());	  
       System.out.printf("  CI Max  %8.3f %8.3f %8.3f %8.3f %8.3f\n", cfIntCase4_Profit.getCfMax(), 
    		   cfIntCase4_WUtilization.getCfMax(), cfIntCase4_CUtilization.getCfMax(), 
    		   cfIntCase4_PerWaitParty.getCfMax(), cfIntCase4_PerBalkParty.getCfMax());	  
       System.out.printf(" zeta/PE  %8.3f %8.3f %8.3f %8.3f %8.3f\n", 
    		   cfIntCase4_Profit.getZeta()/cfIntCase4_Profit.getPointEstimate(), 
    		   cfIntCase4_WUtilization.getZeta()/cfIntCase4_WUtilization.getPointEstimate(), 
    		   cfIntCase4_CUtilization.getZeta()/cfIntCase4_CUtilization.getPointEstimate(), 
    		   cfIntCase4_PerWaitParty.getZeta()/cfIntCase4_PerWaitParty.getPointEstimate(), 
    		   cfIntCase4_PerBalkParty.getZeta()/cfIntCase4_PerBalkParty.getPointEstimate());
       System.out.printf("-----------------------------------------------------------------------------\n");	 
       
  }
}


