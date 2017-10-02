package restaurantModel;

import restaurantModel.Restaurant;
import restaurantModel.Party;
import simulationModelling.ConditionalActivity;

public class PreService extends ConditionalActivity {

	Party icgParty = new Party();
	Restaurant model; // for referencing the model
	
	
    // constructor of the class DeliverFood
	public PreService(Restaurant model)
	{
		this.model = model;
	}
	
	protected static boolean precondition(Restaurant model)
	{
		boolean retval = false;
	    if (model.udp.CanStartPreService() != Constants.NONE)
	    	retval = true;
	    return(retval);
	}
	
	public void startingEvent() 
	{
		// pass the size of the party to the partyScale, and use this attribute to determine to operate which queue
		icgParty.partyScale = model.udp.CanStartPreService();
		icgParty = model.qPartywaitinglist[icgParty.partyScale].remove(0);
		model.rgTable[icgParty.partyScale].spInsertGrp(icgParty);
    	if (model.getClock() - icgParty.startWait >= 20)
    	{
    		model.output.ssovnumLongWait += 1;
    		
    	}
    	
    	model.rgWaiter.busyWaiters += 1;	
    
	}
	
	protected double duration() 
	{
		return (model.rvp.uPreServiceTime());
	}
	
	
	protected void terminatingEvent() 
	{	
		model.qPartyOrderList.add(icgParty);
		model.rgWaiter.busyWaiters -= 1;
	}
}
