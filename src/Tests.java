
import java.io.IOException;
import java.util.Scanner;

public class Tests {
	public static void main(String[] args) throws IOException {
		VCF a = new VCF("src/resources/test.vcf");
		//22	18905964	rs2904552	C	T	11385.86	PASS	AF=0.125;FS=1.120;MQ=55.19;NEGATIVE_TRAIN_SITE;QD=13.41;VQSLOD=-1.877e+00;CLNDBN=Proline_dehydrogenase_deficiency|Schizophrenia_4;CLNREVSTAT=no_criteria|no_criteria;CLNSIG=255;OM;PM;X1000Gp3_AF=0.0429313;ExAC_AF=0.075;ESP6500_AF=0.0815903;BIOBANK_AF_v20171101=0.068;dbNSFP_PROVEAN_pred=D,D;dbNSFP_Polyphen2_HVAR_pred=B,P,B;dbNSFP_SIFT_pred=D,T;ANN=T|missense_variant|MODERATE|PRODH|PRODH|transcript|NM_016335|protein_coding|12/15|c.1292G>A|p.Arg431His|1496/2408|1292/1803|431/600||,T|missense_variant|MODERATE|PRODH|PRODH|transcript|NM_001195226|protein_coding|11/14|c.968G>A|p.Arg323His|1081/1993|968/1479|323/492||	GT:AD:DP:GQ:PL:PP	0/0:40,0:40:99:0,111,1665:0,121,1692	0/0:65,0:65:99:0,120,1800:0,130,1827	0/0:73,0:73:99:0,120,1800:0,130,1827	0/0:44,0:44:99:0,120,1800:0,130,1827	0/0:54,0:54:99:0,120,1800:0,130,1827	1/1:0,99:99:99:2675,297,0:2648,281,0	0/0:71,0:71:99:0,120,1800:0,130,1827	0/0:59,0:59:99:0,118,1424:0,128,1451

		/*String test1 = "##INFO=<ID=AC,Number=B,Type=Integer,Description=\"Allele count in genotypes, for each ALT allele, in the same order as listed\">";
		String test2 = "##INFO=<ID=ReadPosRankSum,Number=1,Type=Float,Description=\"Z-score from Wilcoxon rank sum test of Alt vs. Ref read position bias\">";

		// testing that input string equals to string, constructed with
		// InformationParameter.VCF_form
		InformationParameter info = new InformationParameter(test1);
		// System.out.println(info.VcfFormat().equals(test1)); // testing that input
		// string equals to string, constructed
		// with InformationParameter.VCF_form

		info = new InformationParameter(test2);
		// System.out.println(info.VcfFormat().equals(test2));// testing that input
		// string equals to string, constructed
		// with InformationParameter.VCF_form

		InformationParameter info1 = new InformationParameter("TEST_ID", '1', "Integer", "Test_for_constructor");
		// System.out.println(info1.VcfFormat()); // look for console output and watch
		// that string has correct format

		// test of VCF-class

		VCF a = new VCF("src/resources/test.vcf");
		a.printFile();
		a.addInfoString("TEST_ID", '1', "Integer", "Test for constructor"); // test of adding of ##INFO-strings
		a.addInfoValues("test_ID", 0.99999); // test of adding new value for all
		// mutations
		a.printFile();

		// test of PriorityValueCountRule

		// VCF a = new VCF("src/resources/test.vcf");
		// a.addCalculateConfig(new PriorityValueCountRule("DP", 's', 130, 0.33333));
		// a.CalculatePriorityMutation();
		// a.printFile();*/
	}
}
