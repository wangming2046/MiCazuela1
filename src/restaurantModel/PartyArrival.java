package restaurantModel;

import restaurantModel.Party;
import restaurantModel.Restaurant;
import simulationModelling.ScheduledAction;

public class PartyArrival extends ScheduledAction  {

	Restaurant model; // to access the complete model
	
	
	public PartyArrival (Restaurant model)
	{
		this.model = model;
	}
	
	public double timeSequence()
	{
	    return (model.rvp.DuParty());
	}
	

	public void actionEvent() {
	     
	     // party arrival action sequence SCS
		 Party icgParty = new Party();
	     icgParty.uSize = model.rvp.uPartySize();
	     model.output.ssovnumArrivedParties += 1;
	     
	     // determine the party scale
	     if (icgParty.uSize > 2)
	     {
	    	 // which means it is a large party, and will insert into the list for large party and use large table
	    	 icgParty.partyScale = Constants.LARGE;  // GAComment: please use the contants, not magic numbers.
	     }
	     else
	     {
	    	// which means it is a small party, and will insert into the list for small party and use small table
	    	 icgParty.partyScale = Constants.SMALL;
	     }
	     
	     // determine whether the waiting list is full
	     if(model.qPartywaitinglist[icgParty.partyScale].size() == Constants.PARTYWAITINGLIST_CAP)
	     {
	    	 model.output.ssovnumBalkParty += 1;
	     }
	     else
	     {
	    	 model.qPartywaitinglist[icgParty.partyScale].add(icgParty);
	    	 icgParty.startWait = model.getClock();	     
	     }
	}
}
