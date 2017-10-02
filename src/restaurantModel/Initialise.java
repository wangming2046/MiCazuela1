package restaurantModel;
import restaurantModel.Restaurant;
import simulationModelling.ScheduledAction;

public class Initialise extends ScheduledAction{
	
	Restaurant model;
	
	// Constructor
	public Initialise(Restaurant model) { this.model = model; }

	double [] ts = { 0.0, -1.0 }; // -1.0 ends scheduling
	int tsix = 0;  // set index to first entry.
	public double timeSequence() 
	{
		return ts[tsix++];  // only invoked at t=0
	}

	public void actionEvent() 
	{

		
		// System initialisation
		
		model.qPartyDelFoodList.clear();
		model.qPartyOrderList.clear();
		model.qPartyPayList.clear();
		
		model.qPartywaitinglist[Constants.LARGE].clear();  
		model.qPartywaitinglist[Constants.SMALL].clear();
		
		model.rgTable[Constants.LARGE].group.clear();
		model.rgTable[Constants.SMALL].group.clear();
		model.rgTable[Constants.SMALL].numTables = (Constants.TOTAL_TABLE_NUMBER - model.rgTable[Constants.LARGE].numTables*2);

		
		model.rgWaiter.busyWaiters = 0;
		model.rgCook.busyCooks = 0;
		
		model.output.ssovnumArrivedParties = 0;
		model.output.ssovnumBalkParty = 0;  
		model.output.ssovnumLongWait = 0;
		model.output.ssovperBalkParty = 0.0;
		model.output.ssovperPartyWait = 0.0;
		
		model.output.ssovprofit = -(60*model.rgWaiter.numW + 100*model.rgCook.numC + 300);
		

	}

}
