/** 
 * Class information parameter in VCF-files format 
 *  <b>ID</b> , <b>number</b> , <b>type</b> , <b>description</b>.
 * @autor Isaev Vasily, Lonishin Lubov
 * @version 2.1
*/
public class InformationParameter {
	
	/** Parameter's ID
	* example: NS
	* meaning: "NS"(Number of Samples With Data) ; "DP" (Total Depth), etc
	* most popular is 2 chars for ID
	* But can be much more: ##INFO=<ID=VQSLOD,Number=1,Type=Float,Description="Lo"
	*/
	private String ID;
	
	/**
	 * Numerical parameter's value // ЧТО ЭТО ?!?!? Type 'char' beacuse of
	 * wikipedia's example "https://en.wikipedia.org/wiki/Variant_Call_Format"
	 * included ##INFO=<ID=AF,Number=A,.. , but 'A' isn't a number example " : 1
	 * meaning: ???
	 */
	private char number;
	
	/** Data type
	* example: String
	* meaning: Type of included data
	*/
	private String type;
	
	/** Description of parameter
	* example: "Total Depth"
	* meaning: Description this parameter's meaning
	*/
	private String description;
	
	 /**
     * Function that forms INFO-string from all parameters
     * @return VCF-formated INFO string
     */
	public String VcfFormat() { 
		return "##INFO=<ID=" + ID + ",Number=" + number + ",Type=" + type + ",Description=\"" + description + "\">";
	}

	/** 
     * <br /> <b>Constructor: make new object from list by string from VCF-file</b>
     * <br /> <b>Usage: to parse VCF-files</b>
     * @param infoFromVCF - string, taken from VCF-file
     * @see InformationParameter#InformationParameter()
     */
	InformationParameter(String infoFromVCF) {
		ID = infoFromVCF.substring("##INFO=<ID=".length(), infoFromVCF.indexOf(','));
		number = infoFromVCF.charAt(("##INFO=<ID=,Number=" + ID).length());
		type = infoFromVCF.substring(("##INFO=<ID=,Number=A,Type=" + ID).length(),
				infoFromVCF.indexOf(',', ("##INFO=<ID=,Number=A,Type=" + ID).length()));
		description = infoFromVCF.substring(("##INFO=<ID=,Number=A,Type=,Description=\"" + ID + type).length(),
				infoFromVCF.indexOf('>') - 1);
	}

	/** 
     * <br /> <b>Constructor: make new object from list of all the parameters</b>
     * <br /> <b>Usage: to create new own VCF-parameters </b>
     * @param ID_ - ID
     * @param number_ - number
     * @param type_ - type
     * @param descreption_ - descreption
     * @see InformationParameter#InformationParameter()
     */
	InformationParameter(String ID_, char number_, String type_, String description_) {
		ID = ID_;
		number = number_;
		type = type_;
		description = description_;
	}

}