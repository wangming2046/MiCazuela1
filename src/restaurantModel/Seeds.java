package restaurantModel;
import cern.jet.random.engine.RandomSeedGenerator;

public class Seeds {
	
	
	int numParty;    // for the number of arriving party each day
	int arrivalParty;  // for party arrival Time
	int sizeOfParty;     // the size of arrival party
	int preServiceT;   // the pre-service time for the party
	int preFoodT;     // the time for the cook preparing food for the party
	int deliverFoodT;  // the time for the waiter delivering food to the party
	int eatingT;      // the time for the party finishing their food
	int postServiceT;  // the time for the waiter collecting fee and clean table
	int bill;          // the bill of each person in the party


	public Seeds(RandomSeedGenerator rsg)
	{
		numParty = rsg.nextSeed();
		arrivalParty = rsg.nextSeed();
		sizeOfParty = rsg.nextSeed();
		preServiceT = rsg.nextSeed();
		preFoodT = rsg.nextSeed();
		deliverFoodT = rsg.nextSeed();
		eatingT = rsg.nextSeed();
		postServiceT = rsg.nextSeed();
		bill = rsg.nextSeed();

	}

}
