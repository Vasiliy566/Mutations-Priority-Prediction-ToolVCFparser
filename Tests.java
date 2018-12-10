
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Tests {
	public static void main(String[] args) throws IOException {

		String test1 = "##INFO=<ID=AC,Number=B,Type=Integer,Description=\"Allele count in genotypes, for each ALT allele, in the same order as listed\">";
		String test2 = "##INFO=<ID=ReadPosRankSum,Number=1,Type=Float,Description=\"Z-score from Wilcoxon rank sum test of Alt vs. Ref read position bias\">";

		// testing that input string equals to string, constructed with
		// InformationParameter.VCF_form
		InformationParameter info = new InformationParameter(test1);
		//System.out.println(info.VcfFormat().equals(test1)); // testing that input string equals to string, constructed
															// with InformationParameter.VCF_form

		info = new InformationParameter(test2);
		//System.out.println(info.VcfFormat().equals(test2));// testing that input string equals to string, constructed
															// with InformationParameter.VCF_form

		InformationParameter info1 = new InformationParameter("TEST_ID", '1', "Integer", "Test_for_constructor");
		//System.out.println(info1.VcfFormat()); // look for console output and watch that string has correct dormat

		
		// test of VCF-class
		/*VCF a = new VCF("test.vcf");
		a.printFile();
		a.addInfoString("TEST ID", '1', "Integer", "Test for constructor"); // test of adding of ##INFO-strings
		a.addInfoValues("test ID", 0.99999); // test of adding new value for all mutations
		a.printFile();
		*/
	   // test of PriorityValueCountRule
		
		VCF a = new VCF("test.vcf");
		a.addCalculateConfig(new PriorityValueCountRule("DP", 'e', 13, 123));
		a.CalculatePriorityMutation();
		a.printFile();
		
	}
}
