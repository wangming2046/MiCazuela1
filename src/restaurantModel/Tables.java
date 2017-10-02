package restaurantModel;

import java.util.HashSet;
import restaurantModel.Party;

public class Tables {
	
	// Attributes
	protected int numTables;  // the maximum number of tables in each table set that is available
	
	// Implement the queue using an ArrayList object
	protected HashSet<Party> group = new HashSet<Party>();  // Size is initialised to 0
	
	// getters/setters and standard procedures
	protected int getN() { return(group.size()); }
	protected void spInsertGrp(Party icgParty) { 
		group.add(icgParty);
	}
	protected boolean spRemoveGrp(Party icgParty) 
	{ 
		return(group.remove(icgParty));
	}
	

}
