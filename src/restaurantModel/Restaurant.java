package restaurantModel;

import java.util.ArrayList;

import restaurantModel.Seeds;
import restaurantModel.Waiters;
import restaurantModel.Cooks;
import restaurantModel.RVPs;
import restaurantModel.Output;
import restaurantModel.Tables;
import restaurantModel.Party;

import simulationModelling.AOSimulationModel;
import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;


public class Restaurant extends AOSimulationModel {
	
	//GAComment:  can declare the input variable uNumOfParties here.
	public int uNumOfParties; 
	//since we can generate the needed number in the rvp, so we do not need this here.
	/*-------------Entity Structures-------------------*/
	/* Group and Queue Entities */
	protected Tables [] rgTable = new Tables[2];   // this is resource group set [2]: tables

    //protected Queue [] qPartywaitinglist = new Queue[2];
	@SuppressWarnings("unchecked")
	protected ArrayList<Party> [] qPartywaitinglist = new ArrayList[2];  // Q.PartyWaitingList[2]
	protected ArrayList<Party> qPartyOrderList = new ArrayList<Party>();   // Q.PartyOrderList
	protected ArrayList<Party>	qPartyPayList = new ArrayList<Party>();   // Q.PartyPayList
	protected ArrayList<Party> qPartyDelFoodList = new ArrayList<Party>(); // Q.PartyDelFoodList
	
	
	// resource group unary
	Waiters rgWaiter = new Waiters();  // GAComment:  If you notis in the examples, names are prefixes to show roles, as in rgWaiter as you did with the iCGParty
	Cooks rgCook = new Cooks();
	
	//Consumer entity
	Party icgParty = new Party();  //GAComment: why is this defined here - theses are entities with scope class?
	
	//Random variate procedures
	RVPs rvp;
	
	// User Defined Procedures
	UDPs udp = new UDPs(this);
	
	// Outputs
	Output output = new Output(this);
	
	
	// Output values
	// DSOV
	public double waiterUtilization(double tstart, double tend) { return output.waiterUtilization(tstart, tend); }
	public double cookUtilization(double tstart, double tend) {return output.cookUtilization(tstart, tend);}
	public void clearTRJRGWaitersBusy() { output.clearTRJRGWaitersBusy(); }
	public void clearTRJRGCooksBusy() { output.clearTRJRGCooksBusy();}
	
	// SSOV	
	public double perBalkParty() {
		
		output.ssovperBalkParty = (double)output.ssovnumBalkParty/output.ssovnumArrivedParties; 
		return (output.ssovperBalkParty);
	}
	public double perPartyWait () {
		
		output.ssovperPartyWait = (double)output.ssovnumLongWait/(output.ssovnumArrivedParties-output.ssovnumBalkParty);
		return (output.ssovperPartyWait);
	}

	public int getProfit() {
		return (output.ssovprofit);
	}
	
	public int getNumberOfParty() {
		return (output.ssovnumArrivedParties);
	}
	public Restaurant()
	{
		
	}
	
	public Restaurant (double t0time, double tftime, int num4T, int numW, int numC, Seeds sd, boolean traceFlag ){
		
		// Turn tracing on if traceFlag is true
		this.traceFlag = traceFlag;
		
		// Set up RVPs
		rvp = new RVPs(this,sd);
		
		// generate the number of parties for generating the inter-arrival time
	 	uNumOfParties = rvp.uNumParty();
		
		// Initialise parameters
		// Need to create the entities/objects here instead of the intialise action
		
		for (int id = 0; id < 2; id++)
		{
			rgTable[id] = new Tables();
			qPartywaitinglist[id] = new ArrayList<Party>();
		}

		rgTable[Constants.LARGE].numTables = num4T;   
		rgWaiter.numW = numW;
		rgCook.numC = numC;
            
            
     	// Initialise the simulation model
    	initAOSimulModel(t0time, tftime+70);  // set stop condition to ensure SBL is not empty.
    	//initAOSimulModel(t0time);  // set stop condition to ensure SBL is not empty.
    	closingTime = tftime; // record the closing time - see
    	                      // implicitStopCondition

	 	
		// Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init); // Should always be first one scheduled.
		PartyArrival pArr = new PartyArrival(this);
		scheduleAction(pArr); // schedule the first arrival	
		
	}
	
	
	
	
	protected double closingTime; // closing time of the restaurant
	
	public boolean implicitStopCondition() // termination explicit
	{
		boolean retVal = false;
        // when the time pass the closing time, and there is not party 
		//in the waiting queue and seating around the table, this is the end time.
		if (getClock()>= closingTime && qPartywaitinglist[Constants.LARGE].size() == 0 
				&& qPartywaitinglist[Constants.SMALL].size() == 0
				&& rgTable[Constants.LARGE].getN() ==0 
				&& rgTable[Constants.SMALL].getN() == 0)
			retVal = true;

		return (retVal);
	}
	

    /************  Updating Trajectory Sets***/
	// Flag for controlling tracing
		boolean traceFlag = false;
		public void eventOccured() {
			// Update the output sequences
			output.updateSequences();	
			if(traceFlag)
			{
				System.out.println("-------------------------------------------------------------------------------------");
				System.out.println("|Clock: "+ getClock() + "| PartywaitingList[Large]: " + qPartywaitinglist[Constants.LARGE].size() +
						"| PartywaitingList[Small]: " + qPartywaitinglist[Constants.SMALL].size());
				System.out.println("| Table[Large] Occupied: " + rgTable[Constants.LARGE].getN() + "| Table[Small] Occupied: " + rgTable[Constants.SMALL].getN());
				System.out.println("| Busy waiters: " + rgWaiter.busyWaiters + "| Busy cooks: "+ rgCook.busyCooks + 
						"| Profit: " + output.ssovprofit);
				System.out.println("| Balk Party : " + output.ssovnumBalkParty + "| Party Long Wait: " + output.ssovnumLongWait);
		        
				showSBL();
			}
		}
	
		
		/*
		 * Testing preconditions
		 */
		protected void testPreconditions(Behaviour behObj)
		{       
			// Bootstrap arrivals
			reschedule(behObj);
			// Check Activity Preconditions
			while(scanPreconditions() == true) /* repeat */;
		}

		// Checks preconditions instantiates Actvities
		// for each true precondition found.
		// Returns true if an activity was instantiated
		// and false otherwise.
		private boolean scanPreconditions()
		{
			boolean statusChanged = false;  
			
			if(PreService.precondition(this) == true)
			{
				PreService act = new PreService(this);
				act.startingEvent();
				scheduleActivity(act);
				statusChanged = true;
			}
			if(PrepareFood.precondition(this) == true)
			{
				PrepareFood act = new PrepareFood(this);
				act.startingEvent();
				scheduleActivity(act);
				statusChanged = true;
			}
			if(DeliverFood.precondition(this) == true)
			{
				DeliverFood act = new DeliverFood(this);
				act.startingEvent();
				scheduleActivity(act);
				statusChanged = true;
			}	
			if(PostService.precondition(this) == true)
			{
				PostService act = new PostService(this);
				act.startingEvent();
				scheduleActivity(act);
				statusChanged = true;
			}
			
			return(statusChanged);
		}		
		
		
	
	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct)
	{
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}	

}
