package restaurantModel;

import restaurantModel.Restaurant;
import restaurantModel.Party;
import simulationModelling.SequelActivity;

public class Eating extends SequelActivity  {
	
	Restaurant model;  // reference to the whole model
	// Party that is eating
	Party icgParty; 
	
	//constructor
	public Eating(Restaurant model, Party icgParty){
		this.model = model;
		this.icgParty = icgParty;
	}
	
	
	public void startingEvent() 
	{
		/* Empty event */
	}

	public double duration() 
	{
		return model.rvp.uEatingTime();
	}

	public void terminatingEvent() 
	{
     	// eating terminating Event SCS 
        model.qPartyPayList.add(icgParty);  // insert into the party paying list waiting for an available waiter

	}

}
