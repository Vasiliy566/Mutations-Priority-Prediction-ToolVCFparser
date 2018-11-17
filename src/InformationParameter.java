public class InformationParameter {
	private String ID;
	// Parameter's ID
	// example: NS
	// meaning: "NS"(Number of Samples With Data) ; "DP" (Total Depth), etc
	// most popular is 2 chars for ID
	// But can be much more: ##INFO=<ID=VQSLOD,Number=1,Type=Float,Description="Lo

	private char number;
	// ??
	// Type 'char' beacuse of wikipedia's example
	// "https://en.wikipedia.org/wiki/Variant_Call_Format"
	// included ##INFO=<ID=AF,Number=A,.. , but 'A' isn't a number
	// example " : 1
	// meaning: ???

	private String type;
	// Data type
	// example: String
	// meaning: Type of included data

	private String description;
	// Description of parameter
	// example: "Total Depth"
	// meaning: Description this parameter's meaning

	public String VcfFormat() { // Return INFO string in VCF-format
		return "##INFO=<ID=" + ID + ",Number=" + number + ",Type=" + type + ",Description=\"" + description + "\">";
	}

	// constructor, parsed InformationParameter form VCF line
	// will be use for parsing lines from VCF-file
	InformationParameter(String infoFromVCF) {
		ID = infoFromVCF.substring("##INFO=<ID=".length(), infoFromVCF.indexOf(','));
		number = infoFromVCF.charAt(("##INFO=<ID=,Number=" + ID).length());
		type = infoFromVCF.substring(("##INFO=<ID=,Number=A,Type=" + ID).length(),
				infoFromVCF.indexOf(',', ("##INFO=<ID=,Number=A,Type=" + ID).length()));
		description = infoFromVCF.substring(("##INFO=<ID=,Number=A,Type=,Description=\"" + ID + type).length(),
				infoFromVCF.indexOf('>') - 1);
	}

	// common parameters-depended constructor
	// will be use for make personal setting
	InformationParameter(String ID_, char number_, String type_, String description_) {
		ID = ID_;
		number = number_;
		type = type_;
		description = description_;
	}

	// functions that returns private elements
	// done to save class parameters form user's access
	public String getID() {
		return ID;
	}

	public char getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

}