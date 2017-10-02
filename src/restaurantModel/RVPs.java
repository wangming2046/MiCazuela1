package restaurantModel;

import cern.jet.random.Exponential;
import cern.jet.random.Uniform;
import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister;
import restaurantModel.Restaurant;
import restaurantModel.Seeds;

public class RVPs {
	
	Restaurant model; // reference to the complete model
	Party icgParty = new Party();

	
	// Constructor
	
	public RVPs(Restaurant model, Seeds sd) 
	{ 
		this.model = model; 
		// Initialise Internal modules, user modules and input variables
		numParty = new Uniform(NUMPARTY_MIN,NUMPARTY_MAX,sd.numParty);
		partySize = new Uniform(PARTYSIZE_MIN,PARTYSIZE_MAX, sd.sizeOfParty);
		bill = new Uniform(BILL_MIN, BILL_MAX, sd.bill);
		
		interArrival = new Exponential(1.0/MEAN1, new MersenneTwister(sd.arrivalParty));

		preServiceTime = new Normal(PRESERVICE_MEAN, PRESERVICE_VARIANCE,new MersenneTwister(sd.preServiceT));
		preFoodTime = new Normal(PREFOOD_MEAN, PREFOOD_VARIANCE, new MersenneTwister(sd.preFoodT));
		delFoodTime = new Normal(DELFOOD_MEAN, DELFOOD_VARIANCE, new MersenneTwister(sd.deliverFoodT));
		eatingTime = new Normal(EATING_MEAN, EATING_VARIANCE, new MersenneTwister(sd.eatingT));
		postServiceTime = new Normal(POSTSERVICE_MEAN,POSTSERVICE_VARIANCE, new MersenneTwister(sd.postServiceT));

	}
	
	// number of party will come in one day
	private final int NUMPARTY_MIN = 30;
	private final int NUMPARTY_MAX = 50;
	protected Uniform numParty; // Random variate generator for the number of parties each day
	protected int uNumParty(){
		
		return (numParty.nextInt());
	}
	
	//size of each arrival party
	private final int PARTYSIZE_MIN = 1;
	private final int PARTYSIZE_MAX = 4;
	protected Uniform partySize; //Random variate generator for the party size;
	//method of implementing the uPartySize -- the number of people in one party
	protected int uPartySize()
	{
		return(partySize.nextInt());
	}
	
	// bill of each customers in one party
	private final int BILL_MIN = 10;
	private final int BILL_MAX = 16;
	private Uniform bill; //Radom variate generator for the bill of each customer in the party
	// method of implementing the uBill -- the bill of each customer
	protected int uBill()
	{
		return(bill.nextInt());
	}
	
	// number of inter-arrival time of the party in each time period
	
	// Party arrival times
	
	
	
	final static double MEAN1 = 15; // for initialize the object
   

	protected Exponential interArrival;  // party inter-arrival time
	
	protected double DuParty ()
	{
		double nxtTime;
		double mean;
	
		int number = model.uNumOfParties;   // GAComment:  This looks odd given that iCG.Parties are of scope Class.   Need to use an input variable uNumOfParty as recommended in the CM.	
		double MEAN1 = 60/(0.1*number);
		double MEAN2 = 60/(0.2*number);
		double MEAN3 = 120/(0.55*number);
		double MEAN4 = 60/(0.1*number);
		double MEAN5 = 60/(0.1*number);
	    
		if (model.getClock() <= 60) mean = MEAN1;
		else if(model.getClock()<= 120) mean = MEAN2;
		else if(model.getClock()<= 240) mean = MEAN3;
		else if(model.getClock()<= 300) mean = MEAN4;
		else mean = MEAN5;
			
		nxtTime = model.getClock() + interArrival.nextDouble(1.0/mean);
		
		if (nxtTime > model.closingTime)
			nxtTime = -1.0; // Ends Time sequence
		
		return (nxtTime);
			
	}   // GAComment: careful with indentation - does not look like the end of the method.

	// RVP.uPreServiceTime
	final static double PRESERVICE_MEAN = 7.0;
	final static double PRESERVICE_VARIANCE = 1.7;
	private Normal preServiceTime; // Random variate generator for the pre-service time	
	
	// method of implementing the uPreServiceTime -- the pre-service time of the waiter;
	protected double uPreServiceTime()
	{
		return(preServiceTime.nextDouble());
	}
	
	//RVP.uPreFoodTime
	final static double PREFOOD_MEAN = 7.0;
	final static double PREFOOD_VARIANCE = 1.5;
	private Normal preFoodTime;  // random variate generator for preparing food time
	
	// method of implementing the uPreFoodTime -- the preparing food time of the cook;
	protected double uPreFoodTime()
	{
		return(preFoodTime.nextDouble());
	}
	
	//RVP.uDelFoodTime
	final static double DELFOOD_MEAN = 2.0;
	final static double DELFOOD_VARIANCE = 0.5;
	private Normal delFoodTime;  // random variate generator for delivering food time
	
	// method of implementing the uDelFoodTime -- the delivering food time of the waiter;
	protected double uDelFoodTime()
	{
		return(delFoodTime.nextDouble());
	}
	
	//RVP.uEatingTime
	final static double EATING_MEAN = 10.0;
	final static double EATING_VARIANCE = 2.0;
	private Normal eatingTime;  // random variate generator for eating time
	
	// method of implementing the uEatingTime -- party eating food time
	protected double uEatingTime()
	{
		return(eatingTime.nextDouble());
	}
	
	//RVP.uPostServiceTime
	final static double POSTSERVICE_MEAN = 3.0;
	final static double POSTSERVICE_VARIANCE = 0.8;
	private Normal postServiceTime;  // random variate generator for cleaning the table and collecting the fee
		
	// method of implementing the uPostServiceTime --- the time for the waiter cleaning the table and collecting the fee
	protected double uPostServiceTime()
	{
		return(postServiceTime.nextDouble());
	}
	
	//RVP.uPartyProfit (uPartySize)
	//method of implementing the uPartyProfit
	protected int uPartyProfit(Party icgParty)
	{
		int partyProfit = 0; // summing the bill of each customer
		for (int index = 0; index < icgParty.uSize; index++)
		{
			partyProfit += (this.uBill() - 1);	
		}
		return (partyProfit);
	}
}


