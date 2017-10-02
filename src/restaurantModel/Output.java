package restaurantModel;

import simulationModelling.OutputSequence;

public class Output {

	Restaurant model; //  reference to the whole model
	
	/*instance variable */
	protected OutputSequence trjRGCooksBusy; // trajectory set to record the number of busy cooks
	protected OutputSequence trjRGWaitersBusy; // trajectory set to record the number of busy waiters
	
	private int lastbusyCooks;  // detecting changes in RG.Cooks.busyCook attribute
	private int lastbusyWaiters;  // detecting changes in RG.Waiters.busyWaiter attribute
	
	// simple scalar output variable (SSOV) 
	protected int ssovnumArrivedParties;
	protected int ssovprofit;
	protected int ssovnumBalkParty;
	protected int ssovnumLongWait;
	protected double ssovperPartyWait;
	protected double ssovperBalkParty;
	
	// constructor
	public Output(Restaurant model)
	{
		this.model = model;
		// set up trajectory set
		trjRGCooksBusy = new OutputSequence("rgCooksN");
		trjRGWaitersBusy = new OutputSequence("rgWaitersN");
		
		// first point in output sequence - rgCook.busyCooks is empty at time = 0
		// first point in output sequence - rgWaiter.busyWaiters is empty at time = 0;
		lastbusyCooks = 0;
		lastbusyWaiters = 0;
		
		trjRGCooksBusy.put(0.0, 0.0);
		trjRGWaitersBusy.put(0.0, 0.0);
		
	
	}	
	
	// waiter utilization method 
	protected double waiterUtilization(double t0, double tf)
	{
		trjRGWaitersBusy.computeTrjDSOVs(t0, tf);
	    double avgWaiterBusy = trjRGWaitersBusy.getMean();
	    return (avgWaiterBusy/model.rgWaiter.numW);
		
	}
	
	protected void clearTRJRGWaitersBusy() { trjRGWaitersBusy.clearSet(); }
	
	// cooks utilization method
	protected double cookUtilization(double t0, double tf)
	{
		trjRGCooksBusy.computeTrjDSOVs(t0, tf);
	    double avgCookBusy = trjRGCooksBusy.getMean();
	    return (avgCookBusy/model.rgCook.numC);
	}
	
	protected void clearTRJRGCooksBusy() { trjRGCooksBusy.clearSet(); }
		
	// update the trajectory sequence
	void updateSequences()
	{
		// update the  cooks and waiters  trajectory set
		int c = model.rgCook.busyCooks;
		if (lastbusyCooks != c)
		{
			trjRGCooksBusy.put(model.getClock(), (double)c);
			lastbusyCooks = c;
			
		}
		
		int w = model.rgWaiter.busyWaiters;
		if (lastbusyWaiters != w)
		{
			trjRGWaitersBusy.put(model.getClock(), (double)w);
			lastbusyWaiters = w;
		}

	}
}
