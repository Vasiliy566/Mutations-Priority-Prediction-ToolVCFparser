import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class MutationParameter {

	private int chrom;
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

	private HashMap<String, Double> info = new HashMap<String, Double>();
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
	MutationParameter(String infoFromVCF) {// CAN BE UPGRATE BY
											// REFLECTIONS(http://qaru.site/questions/95345/how-to-loop-over-a-class-attributes-in-java)
		Scanner scan = new Scanner(infoFromVCF);

		chrom = Integer.valueOf(scan.next());

		pos = Integer.valueOf(scan.next());

		ID = scan.next();

		ref = scan.next().charAt(0);

		alt = scan.next().charAt(0);

		qual = Double.valueOf(scan.next());

		filter = scan.next();

		String s = scan.next();

		String key = "";
		String value = "";
	
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '=' && i + 1 >= s.length()) {
					for (int j = i + 1; j < s.length() || s.charAt(j) != ';'; j++)
						value += s.charAt(j);
					i += value.length() + 1;
					info.put(key, Double.valueOf(value));
					value = "";
					key = "";

				} else {
					key += s.charAt(i);
				}
			}
	
		s = scan.next();
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
	}

	public String VcfFormat() { // Return mutation's string in VCF-format
		// return simple values
		String ans = (chrom + "\t" + pos + "\t" + ID + "\t" + ref + "\t" + alt + "\t" + qual + "\t" + filter + "\t");

		// return INFO-values
		for (Map.Entry entry : info.entrySet())
			ans += entry.getKey() + "=" + entry.getValue() + ";";
		ans += "\t";

		// return FORMAT values
		while (!format.isEmpty())
			ans += format.poll() + ":";
		ans = ans.substring(0, ans.length() - 1);

		return ans;
	}

	// add values [INFO ID] , [value] to values HashMap
	void addInfoValue(String key, double value) {
		info.put(key, value);
	}
}
