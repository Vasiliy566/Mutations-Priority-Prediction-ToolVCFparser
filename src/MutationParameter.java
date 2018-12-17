
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class MutationParameter {

	private char chrom;
	// Chromosome's number
	// example: 20
	// meaning: The name of the sequence (typically a chromosome) on which the
	// variation is being called.
	// This sequence is usually known as 'the reference sequence', i.e. the sequence
	// against which the given sample varies.

	private int pos;
	// Position in chromosome
	// example: 14370
	// meaning: The 1-based position of the variation on the given sequence.

	private String ID;
	// Identifier of the variation
	// example: rs6054257
	// meaning: The identifier of the variation, e.g. a dbSNP rs identifier, or if
	// unknown a ".".
	// Multiple identifiers should be separated by semi-colons without white-space.

	private char ref;
	// Reference base
	// example: A
	// meaning: The reference base (or bases in the case of an index) at the given
	// position on the given reference sequence.

	private char alt;
	// Alternative bases
	// example: G
	// meaning: The list of alternative alleles at this position.

	private double qual;
	// Quality score
	// example: 47
	// meaning: A quality score associated with the inference of the given alleles.

	private String filter;
	// Filter's flag
	// example: PASS
	// example: q10
	// meaning: A flag indicating which of a given set of filters the variation has
	// passed.
	private ArrayList<InformationParameter> parameters = new ArrayList<InformationParameter>();

	private HashMap<String, List<Object>> info = new HashMap<String, List<Object>>();
	// List of key-value pairs
	// example: NS=3;DP=11;AF=0.017
	// meaning : An extensible list of key-value pairs (fields) describing the
	// variation.
	// See below for some common fields.
	// Multiple fields are separated by semicolons with optional values in the
	// format: "<key>=[,data]".

	private Queue<String> format = new LinkedList<String>();

	// List of fields
	// example: GT:GQ:DP:HQ
	// meaning: An (optional) extensible list of fields for describing the samples.
	// See below for some common fields.

	// Initialize MutationParameter object with VCF-format string
	// !!! DANGEROUS! MONKEY CODING DETECTED!!!
	MutationParameter(String infoFromVCF, ArrayList<InformationParameter> param) {// CAN BE UPGRATE BY
		// REFLECTIONS(http://qaru.site/questions/95345/how-to-loop-over-a-class-attributes-in-java)
		parameters = param;
		Scanner scan = new Scanner(infoFromVCF);
		try {
			chrom = scan.next().charAt(0);

			pos = scan.nextInt();

			ID = scan.next();

			ref = scan.next().charAt(0);

			alt = scan.next().charAt(0);

			qual = scan.nextDouble();

			filter = scan.next();

			String s = scan.next();

			String key = "";
			String value = "";

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '=' && i + 1 < s.length()) {
					ArrayList possibleValues = new ArrayList();
					for (int j = i + 1; j < s.length(); j++) {
						value += s.charAt(j);
						char flag = value.charAt(value.length() - 1);

						if (flag == ';' || flag == '|' || flag == ',') {

							int k = 0;

							while (k < parameters.size() && !parameters.get(k).getName().equals(key))
								k++;

							if (k < parameters.size() && value.length() - 2 >= 0) { // !!

								if (parameters.get(k).getType().equals("Integer"))
									possibleValues.add(Integer.valueOf(value.substring(0, value.length() - 1)));
								else if (parameters.get(k).getType().equals("Double")
										|| parameters.get(k).getType().equals("Float"))
									possibleValues.add(Double.valueOf(value.substring(0, value.length() - 1)));
								else if (parameters.get(k).getType().equals("String"))
									possibleValues.add(value.substring(0, value.length() - 1));
								else if (parameters.get(k).getType().equals("Character"))
									possibleValues.add(value.substring(0, value.length() - 1).charAt(0));
								else if (parameters.get(k).getType().equals("Flag"))
									possibleValues.add(value.substring(0, value.length() - 1));
								value = "";
							}

						}
						if (flag == ';') {

							info.put(key, possibleValues);

							value = "";
							key = "";
							i = j;

							break;
						}

					}
				} else if (s.charAt(i) == ';') {
					ArrayList possibleValues = new ArrayList();
					possibleValues.add("");
					info.put(key, possibleValues);
					key = "";
				} else {
					key += s.charAt(i);
				}
			}

			String ans = "";
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) != ':') {
					ans += s.charAt(i);
				} else {
					format.add(ans);
					ans = "";
				}
			}
			format.add(ans);

			scan.close();
		} catch (

		InputMismatchException e1) {
			System.err.println("INVALID  NUMERICAL MUTATION VCF-STRING PARAMETR");
			throw e1;
		} catch (NoSuchElementException e2) {
			System.err.println("INVALID MUTATION VCF-STRING FORMAT");
			throw e2;
		}
	}

	public String VcfFormat() { // Return mutation's string in VCF-format
		// return simple values
		String ans = (chrom + "\t" + pos + "\t" + ID + "\t" + ref + "\t" + alt + "\t" + qual + "\t" + filter + "\t");

		// return INFO-values
		for (Map.Entry<String, List<Object>> entry : info.entrySet()) {
			ans += entry.getKey();
			ans += "=";
			if (entry.getValue().size() != 0)
				ans += entry.getValue().get(0);

			for (int i = 1; i < entry.getValue().size(); i++)
				ans += entry.getValue().get(i) + "|";

			ans += ';';

		}

		ans += "\t";

		// return FORMAT values
		while (!format.isEmpty())
			ans += format.poll() + ":";
		ans = ans.substring(0, ans.length() - 1);

		return ans;
	}

	// add values [INFO ID] , [value] to values HashMap
	void addInfoValue(String key, Object value) {
		if (info.get(key) != null) {
			if (key.equals("MP")) {
				double tmp = Double.valueOf(info.get("MP").get(0).toString());
				info.get("MP").clear();
				info.get("MP").add(tmp + Double.valueOf(value.toString()));

			}

		} else {
			List values = new ArrayList();
			values.add(value);
			info.put(key, values);
		}
	}

	public boolean containsValue(String paramName) {
		if (info.containsKey(paramName))
			return true;
		return false;
	}

	List getValue(String paramName) {
		try {
			return info.get(paramName);
		} catch (Exception e) {
			System.err.println("No such parametr as <" + paramName + "> in mutation " + ID);
			return new ArrayList();
		}
	}

	String getId() {
		return ID;
	}

	int getPos() {
		return pos;
	}
}
