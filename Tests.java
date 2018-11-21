import java.io.IOException;
import java.util.Scanner;

public class Tests {
	public static void main(String[] args) throws IOException {

		String test1 = "##INFO=<ID=AC,Number=B,Type=Integer,Description=\"Allele count in genotypes, for each ALT allele, in the same order as listed\">";
		String test2 = "##INFO=<ID=ReadPosRankSum,Number=1,Type=Float,Description=\"Z-score from Wilcoxon rank sum test of Alt vs. Ref read position bias\">";

		// testing that input string equals to string, constructed with
		// InformationParameter.VCF_form
		InformationParameter info = new InformationParameter(test1);
		System.out.println(info.VcfFormat().equals(test1)); // testing that input string equals to string, constructed
															// with InformationParameter.VCF_form

		info = new InformationParameter(test2);
		System.out.println(info.VcfFormat().equals(test2));// testing that input string equals to string, constructed
															// with InformationParameter.VCF_form

		InformationParameter info1 = new InformationParameter("TEST ID", '1', "Integer", "Test for constructor");
		System.out.println(info1.VcfFormat()); // look for console output and watch that string has correct dormat

		MutationParameter mut = new MutationParameter("20     17330    .          T     A      3     q10     NS=3;DP=11;AF=0.017               GT:GQ:DP:HQ  0|0:49:3:58,50  0|1:3:5:65,3     0/0:41:3");
		System.out.println(mut.VcfFormat());
		
		
		// test of VCF-class
		/*VCF a = new VCF("test.vcf");
		a.addInfoString("TEST ID", '1', "Integer", "Test for constructor"); // test of adding of ##INFO-strings
		a.addInfoValues("test ID", 0.99999); // test of adding new value for all mutations
		a.printFile();
		System.out.println("******************");
		Scanner scan = new Scanner("i	love java much more than c++");
		while(scan.hasNext())
			System.out.println(scan.next());
		
		*/
	}
}
