package restaurantModel;

import restaurantModel.Restaurant;
import simulationModelling.ConditionalActivity;

public class PrepareFood extends ConditionalActivity {

	private Party icgParty;
	public Restaurant model; // for referencing the model
	
	
    // constructor of the class DeliverFood
	public PrepareFood(Restaurant model)
	{
		this.model = model;
	}
	
	protected static boolean precondition(Restaurant model)
	{
		boolean retval = false;
		
		if ((model.rgCook.busyCooks < model.rgCook.numC) && (model.qPartyOrderList.isEmpty()== false))
			retval = true;
		
		return (retval);
	}
	
	public void startingEvent() 
	{
		icgParty = model.qPartyOrderList.remove(0);
		model.rgCook.busyCooks += 1;
		
	}
	
	protected double duration() 
	{
		return (model.rvp.uPreFoodTime());
	}
	
	
	protected void terminatingEvent() 
	{
		model.rgCook.busyCooks -= 1;
		model.qPartyDelFoodList.add(icgParty);
	}
}
