package restaurantModel;

import restaurantModel.Eating;
import restaurantModel.Restaurant;
import simulationModelling.ConditionalActivity;

public class DeliverFood extends ConditionalActivity  
{
	Party icgParty = new Party();
	Restaurant model; // for referencing the model
	
	
    // constructor of the class DeliverFood
	public DeliverFood(Restaurant model)
	{
		this.model = model;
	}
	
	protected static boolean precondition(Restaurant model)
	{
		boolean retval = false;
		
		if (model.rgWaiter.busyWaiters < model.rgWaiter.numW && model.qPartyDelFoodList.isEmpty()== false 
				&& model.udp.CanStartPaying()== false)
			retval = true;
		return (retval);
		
	}
	
	public void startingEvent() 
	{
		icgParty = model.qPartyDelFoodList.remove(0);
		model.rgWaiter.busyWaiters += 1;
	}
	
	protected double duration() 
	{
		return (model.rvp.uDelFoodTime());
		
	}
	
	protected void terminatingEvent() 
	{
		// Trigger eating Activity
		model.rgWaiter.busyWaiters -= 1;
		Eating eatAct = new Eating(model, icgParty);
		model.spStart(eatAct);
	}
	
}
