package restaurantModel;

import restaurantModel.Restaurant;


public class UDPs {
	
	Restaurant model; // reference to the whole model
	
	// constructor
	public UDPs(Restaurant model)
	{
		this.model = model;
	}
	// User Defined Procedures
	
	// determine whether waiter can start the service for the party
	protected int CanStartPreService()
	{
		int retval = Constants.NONE;

		if(model.qPartywaitinglist[Constants.LARGE].size() != 0 ||model.qPartywaitinglist[Constants.SMALL].size()!=0)
		{
			if(model.rgWaiter.busyWaiters < model.rgWaiter.numW)
			{
				if(model.qPartywaitinglist[Constants.LARGE].size() >= model.qPartywaitinglist[Constants.SMALL].size()
						&& (model.rgTable[Constants.LARGE].getN() < model.rgTable[Constants.LARGE].numTables))
				{
					retval = Constants.LARGE;
				}
				else if(model.qPartywaitinglist[Constants.LARGE].size() < model.qPartywaitinglist[Constants.SMALL].size()
						&& (model.rgTable[Constants.SMALL].getN() < model.rgTable[Constants.SMALL].numTables))
				{
					 retval = Constants.SMALL;
				}
			}
		}			

		return (retval);
	}
	
	
	// determine whether the party can pay for their food
	protected boolean CanStartPaying()
	{
		boolean retval = false;
		if (CanStartPreService()== Constants.NONE && (model.qPartyPayList.size() != 0) 
				&& (model.rgWaiter.busyWaiters < model.rgWaiter.numW) )
			retval = true;
		return (retval);
		
	}

}
