public class PriorityValueCountRule {

	// Parameter's name
	String id;

	// threshold for id's value
	double thresholdDouble;

	char thresholdChar;
	// string to check contains it or not ( key \'a\')
	String contains;

	// reward given if id value more/less than
	double rewardB; // rewardIfBigger

	double rewardS; // rewardIfSmaler

	double rewardE; // rewardIfEqual

	// e - equal; b - bigger; s - smaller; c - contains;
	char statusKey;

	// Constructor if given just parameter for More / Less

	PriorityValueCountRule(String id_, char statusKey_, double threshold_, double reward_) {
		if ((statusKey_ != 'b' && statusKey_ != 'e' && statusKey_ != 's'))
			System.err.println(
					"error in 1st constructor\\ninput parametrs can be used just for checking bigger/smaller/equals\nshould used with keys 'b' , 's' , 'e'");
		id = id_;
		thresholdDouble = threshold_;
		rewardB = reward_;
		statusKey = statusKey_;

		rewardS = rewardB;
		rewardE = rewardB;
	}

	// Constructor if given just parameter for More and Less
	PriorityValueCountRule(String id_, char statusKey_, double threshold_, double rewardB_, double rewardS_) {
		if (statusKey_ != 'b' && statusKey_ != 'e' && statusKey_ != 's')
			System.err.println(
					"error in 2nd constructor\ninput parametrs can be used just for checking bigger/smaller/equals \nshould used with keys 'b' , 's' , 'e'");
		id = id_;
		thresholdDouble = threshold_;
		rewardB = rewardB_;
		rewardS = rewardS_;
		statusKey = statusKey_;

		rewardE = rewardB; // ? or rewardE = rewardL or rewardE = (rewardB + rewardL) / 2 !!
	}

	// Constructor for all parameters
	PriorityValueCountRule(String id_, char statusKey_, double threshold_, double rewardB_, double rewardS_,
			double rewardE_) {
		if (statusKey_ != 'b' && statusKey_ != 'e' && statusKey_ != 's')
			System.err.println(
					"error in 3d constructor\\ninput parametrs can be used just for checking bigger/smaller/equals \nshould used with keys 'b' , 's' , 'e'");
		id = id_;
		thresholdDouble = threshold_;
		rewardB = rewardB_;
		rewardS = rewardS_;
		rewardE = rewardE_;
		statusKey = statusKey_;
	}

	PriorityValueCountRule(String id_, char statusKey_, double reward) {
		if (statusKey_ != 'c')
			System.err.println(
					"input parameters can be just used for checking  contains given string or not\nshould used with key 'c'");
		id = id_;
		statusKey = statusKey_;
		rewardB = reward;
		rewardS = 0;
	}

	PriorityValueCountRule(String id_, char statusKey_, char thresholdChar, double rewardB_) {
		if (statusKey_ != 'a')
			System.err.println(
					"input parameters can be just used for checking  contains given string or not\nshould used with key 'c'");
		id = id_;
		statusKey = statusKey_;
		rewardB = rewardB_;
		rewardS = 0;
	}

	PriorityValueCountRule(String id_, char statusKey_, char thresholdChar, double rewardB_, double rewardS_) {
		if (statusKey_ != 'a')
			System.err.println(
					"input parameters can be just used for checking  contains given string or not\nshould used with key 'c'");
		id = id_;
		statusKey = statusKey_;
		rewardB = rewardB_;
		rewardS = rewardS_;
	}

	PriorityValueCountRule(String id_, char statusKey_, String contains_, double rewardB_) {
		if (statusKey_ != 'd')
			System.err.println(
					"input parameters can be just used for checking  contains given string or not\nshould used with key 'c'");
		id = id_;
		statusKey = statusKey_;
		contains = contains_;
		rewardB = rewardB_;
		rewardS = 0;
	}

	public double calculateValue(MutationParameter m) {
		
		if (m.containsValue(id))
			if (m.getValue(id).size() != 0) {
				switch (statusKey) {
				case 'a':
					for (int i = 0; i < m.getValue(id).size(); i++)
						if ((char) m.getValue(id).get(i) == thresholdChar)
							return rewardE;
					return 0;
				case 'c':
					return m.containsValue(id) ? rewardB : rewardS;
				case 'e':

					for (int i = 0; i < m.getValue(id).size(); i++)
						if (Double.valueOf(m.getValue(id).get(i).toString()) == thresholdDouble)
							return rewardE;
					return 0;
				case 'b':
					return Double.valueOf(m.getValue(id).get(0).toString()) > thresholdDouble ? rewardB : rewardS;
				case 's':
					return Double.valueOf(m.getValue(id).get(0).toString()) < thresholdDouble ? rewardS : rewardB;
				case 'd':
					for (int i = 0; i < m.getValue(id).size(); i++)
						if (("" + m.getValue(id).get(i)).contains(contains)) {

							return rewardB;
						}
					return 0;
				default:
					System.err.println("invalid key entered");
				}

			}
		return 0;
	}
}
