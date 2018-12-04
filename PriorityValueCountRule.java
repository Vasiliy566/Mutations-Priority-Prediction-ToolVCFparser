
public class PriorityValueCountRule {
	// Parameter's name
	String id;

	// threshold for id's value
	int threshold;

	// reward given if id value more/less than
	double rewardB; // rewardIfBigger

	double rewardL; // rewardIfLess

	double rewardE; // rewardIfEqual

	// if true, than parameter's value should be bigger than threshold, else smaller
	boolean more;

	// Constructor if given just parameter for More/less 
	PriorityValueCountRule(String id_, int threshold_, boolean more_, double reward_) {
		id = id_;
		threshold = threshold_;
		rewardB = reward_;
		more = more_;

		rewardL = rewardB;
		rewardE = rewardB;
	}
	
	// Constructor if given just parameter for More and Less
	PriorityValueCountRule(String id_, int threshold_, boolean more_, double rewardB_, double rewardL_) {
		id = id_;
		threshold = threshold_;
		rewardB = rewardB_;
		rewardL = rewardL_;
		more = more_; 
		
		rewardE = 0; // ? or rewardE = rewardL or rewardE = (rewardB + rewardL) / 2 !!
	}

	// Constructor for all parameters
	PriorityValueCountRule(String id_, int threshold_, boolean more_, double rewardB_, double rewardL_,
			double rewardE_) {
		id = id_;
		threshold = threshold_;
		rewardB = rewardB_;
		rewardL = rewardL_;
		rewardE = rewardE_;
		more = more_;
	}

	public double calculateValue(MutationParameter m) {
		if ((more && m.getValue(id) > threshold))
			return rewardB;
		if ((!more && m.getValue(id) < threshold))
			return rewardL;
		if (m.getValue(id) == threshold)
			return rewardE;
		return 0;
	}
}
