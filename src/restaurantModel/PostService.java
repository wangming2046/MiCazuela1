package restaurantModel;

import restaurantModel.Restaurant;
import simulationModelling.ConditionalActivity;

public class PostService extends ConditionalActivity {
	
	Restaurant model; // reference to the whole model
	private Party icgParty = new Party(); // reference to the party entity

	// constructor
	public PostService(Restaurant model)
	{
		this.model = model;
	}
	
	protected static boolean precondition(Restaurant model)
	{
		boolean retval = false;
		if(model.udp.CanStartPaying()== true)
			retval = true;
		return(retval);
	}
	
	public void startingEvent() 
	{
		//Output output = model.output; // Create local reference to output object
		model.rgWaiter.busyWaiters += 1;
		icgParty = model.qPartyPayList.remove(0);

		// collect the fee 
		model.output.ssovprofit += model.rvp.uPartyProfit(icgParty);
	}
	
	protected double duration() 
	{
		return (model.rvp.uPostServiceTime());	
	}
	
	
	protected void terminatingEvent() 
	{
		
		if(model.rgTable[icgParty.partyScale].spRemoveGrp(icgParty) == false)
		{  
			System.out.println("No such Party!");
		}; 
		model.rgWaiter.busyWaiters -= 1;		
	}

}