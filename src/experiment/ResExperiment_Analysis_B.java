package experiment;

import outputAnalysis.ConfidenceInterval;
import restaurantModel.Restaurant;
import restaurantModel.Seeds;

import java.util.Arrays;

import cern.jet.random.engine.*;

// Main Method: Experiment 1, used to run a single simulation generate 
// The results of 5000 simulation runs to show CI's similar to Table 5.2
// from the text book

public class ResExperiment_Analysis_B
{
   public static void main(String[] args)
   {
	   // Constants
	   final int NUMRUNS = 10000;
	   
	   // Confidence levels - first one is the overall confidence level. 
	   // The other are Ck's and one more must be calculated to ensure the overall CF
	   double [] cfLevels= { 0.85, 0.9999, 0.9999 };
	   
	   // Local variables
       int i; 
       double START_TIME=0.0, END_TIME=360.0;
       Seeds[] sds = new Seeds[NUMRUNS];
       Restaurant model;  // Simulation object
        
       // output value for the case one
       double [] valuesCase1_Profit = new double[NUMRUNS];
       // output value for the case two
       double [] valuesCase2_Profit = new double[NUMRUNS];
       // output value for the case three
       double [] valuesCase3_Profit = new double[NUMRUNS];       
       // output value for the case four
       double [] valuesCase4_Profit = new double[NUMRUNS];

       
       // calculate the difference between two cases
       double [] valuesDiff21_Profit = new double[NUMRUNS];
       double [] valuesDiff31_Profit = new double[NUMRUNS];
       double [] valuesDiff41_Profit = new double[NUMRUNS];
          
       double [] CONF_LEVEL = computeLastCFLevel(cfLevels);
       if(CONF_LEVEL == null) return;  // Error in computations
       System.out.println("Using confidence levels (first value is overall level: "+Arrays.toString(CONF_LEVEL));
       
       
       // Lets get a set of uncorrelated seeds
       RandomSeedGenerator rsg = new RandomSeedGenerator();
       for(i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
       
       // Loop for NUMRUN simulation runs for each case
       // Case 1
       
       System.out.println("Case A - 4 large tables, 3 small tables, 2 waiters and 2 cooks (Base Case)");
       for(i=0 ; i < NUMRUNS ; i++)
       {
          model = new Restaurant(START_TIME,END_TIME, 4, 2, 2, sds[i], false);
          model.runSimulation();
          
          valuesCase1_Profit[i] = model.getProfit();
          
       }
       // Case 2
       System.out.println("Case B - 3 large tables, 5 small tables, 2 waiters and 2 cooks");
       for(i=0 ; i < NUMRUNS ; i++)
       {
           model = new Restaurant(START_TIME,END_TIME, 3, 2, 2, sds[i], false);
           model.runSimulation();
           
           valuesCase2_Profit[i] = model.getProfit();         
           valuesDiff21_Profit[i] = valuesCase2_Profit[i]-valuesCase1_Profit[i];
       }
       
       // Case 3
       System.out.println("Case C - 4 large tables, 3 small tables, 3 waiters and 2 cooks");
       for(i=0 ; i < NUMRUNS ; i++)
       {
           model = new Restaurant(START_TIME,END_TIME, 4, 3, 2, sds[i], false);
           model.runSimulation();
           
           valuesCase3_Profit[i] = model.getProfit();      
           valuesDiff31_Profit[i] = valuesCase3_Profit[i]-valuesCase1_Profit[i];

       }
       
       // Case 4
       System.out.println("Case D - 3 large tables, 5 small tables, 3 waiters and 2 cooks");
       for(i=0 ; i < NUMRUNS ; i++)
       {
           model = new Restaurant(START_TIME,END_TIME, 3, 3, 2, sds[i], false);
           model.runSimulation();
           
           valuesCase4_Profit[i] = model.getProfit();
           valuesDiff41_Profit[i] = valuesCase4_Profit[i]-valuesCase1_Profit[i];

       }
           
       // Get the conficence intervals
       ConfidenceInterval cfDiff21_Profit = new ConfidenceInterval(valuesDiff21_Profit, CONF_LEVEL[1]);
       // this is determined by the program
       ConfidenceInterval cfDiff31_Profit = new ConfidenceInterval(valuesDiff31_Profit, CONF_LEVEL[3]); 
       ConfidenceInterval cfDiff41_Profit = new ConfidenceInterval(valuesDiff41_Profit, CONF_LEVEL[2]);

       
    // Create the table
       System.out.printf("-------------------------------------------------------------------------------------\n");
       System.out.printf("------------------------------Profit Comparison--------------------------------------\n");
       System.out.printf("Comparison    Point estimate(ybar(n))  s(n)     zeta   CI Min   CI Max |zeta/ybar(n)|\n");
       System.out.printf("-------------------------------------------------------------------------------------\n");
       System.out.printf("Diff21_Profit %13.3f %14.3f %8.3f %8.3f %8.3f %11.3f\n",
       	         cfDiff21_Profit.getPointEstimate(), cfDiff21_Profit.getVariance(), cfDiff21_Profit.getZeta(), 
    	         cfDiff21_Profit.getCfMin(), cfDiff21_Profit.getCfMax(),
    	         Math.abs(cfDiff21_Profit.getZeta()/cfDiff21_Profit.getPointEstimate()));
       System.out.printf("Diff31_Profit %13.3f %14.3f %8.3f %8.3f %8.3f %11.3f\n", 
	             cfDiff31_Profit.getPointEstimate(), cfDiff31_Profit.getVariance(), cfDiff31_Profit.getZeta(), 
	             cfDiff31_Profit.getCfMin(), cfDiff31_Profit.getCfMax(),
	             Math.abs(cfDiff31_Profit.getZeta()/cfDiff31_Profit.getPointEstimate()));
       System.out.printf("Diff41_Profit %13.3f %14.3f %8.3f %8.3f %8.3f %11.3f\n", 
	             cfDiff41_Profit.getPointEstimate(), cfDiff41_Profit.getVariance(), cfDiff41_Profit.getZeta(), 
	             cfDiff41_Profit.getCfMin(), cfDiff41_Profit.getCfMax(),
	             Math.abs(cfDiff41_Profit.getZeta()/cfDiff41_Profit.getPointEstimate()));
       System.out.printf("-------------------------------------------------------------------------------------\n"); 
              
  }
   
   
   /*
    * The last level must satisfy the following equation
    *     C = (1-K) + sum_k_1_K(Ck)     (sum_k_1_K is sum over terms for k = 1 to K)
    *     where C = levels[0], 
    *           Ck are the values of levels[i] for i = 1 to levels.length-1
    *           K = levels.length  (note that we add one element, i.e. K = newLevels.length-1)
    *     Can use alternative:  C = 1 - sum_k_1_K(alpha_k)  where alpha_k = 1 - Ck
    *     Thus C = 1 - sum_k_1_K-1(alpha_k) - alpha_K, and thus
    *          alpha_K = 1 - C - sum_k_1_K-1(alpha_k)   (note that alpha_k for k = 1 to K-1 are known)
    *          and CK = 1 - alpha_K
    */
   public static double [] computeLastCFLevel(double [] levels)
   {
	   double [] newLevels = new double[levels.length+1];
	   double alphaK = 1.0 - levels[0];   // Assign C - 1 to alpha K
	   newLevels[0] = levels[0];  // Save C in new array
	   // Sum 1 - Ck to alphaK for k = 1 to K - 1
	   // At the sam time save values in new array
	   for(int k = 1 ; k<levels.length; k++)
	   {
		   alphaK = alphaK - (1 - levels[k]);
		   newLevels[k] = levels[k];
	   }
	   newLevels[newLevels.length - 1] = 1.0 - alphaK;
	   // Double check the values
	   double sum = 0.0;
	   for(int k = 1 ; k < newLevels.length ; k++) sum = sum +(1 - newLevels[k]);
	   if(Math.abs(newLevels[0] - (1.0 - sum)) > 0.001) 
	   {
		   System.out.println("Last value invalid in Confidence Levels: "+Arrays.toString(newLevels));
		   newLevels = null;  // return null value to flag error.
	   }
	   return(newLevels);		   
   }
   
}