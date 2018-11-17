import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;



class VCF_parser {

	static void vcfToStringArray() throws IOException {
		FileInputStream s = new FileInputStream("src/test.vcf");// создаем поток из файла
		BufferedReader buf = new BufferedReader(new InputStreamReader(s));// создаем буфер от входного потока
		FileWriter writer = new FileWriter("out.vcf");
		
		ArrayList<String> propertiesFileStrokes = new ArrayList<String>();
		ArrayList<String> mutationFileStrokes = new ArrayList<String>();

		String rStr = "";
		while ((rStr = buf.readLine()) != null) 
		{
			if (rStr.substring(0, 2).equals("##"))
				propertiesFileStrokes.add(rStr);
			else {
				mutationFileStrokes.add(rStr);
			}
		}
		for (int i = 0; i < propertiesFileStrokes.size(); i++)
			writer.write(propertiesFileStrokes.get(i));

		for (int i = 0; i < mutationFileStrokes.size(); i++)
			writer.write(mutationFileStrokes.get(i));

	}
	static String dataByColumnName(String property) {
		
		return "";
	}
	public static void main(String[] args) throws IOException {
		//TESTS FOR VCFHeader
			//TEST FOR INFORMATION PARAMETER
				//TEST DATA
			String test1 = "##INFO=<ID=AC,Number=B,Type=Integer,Description=\"Allele count in genotypes, for each ALT allele, in the same order as listed\">";
			String test2 = "##INFO=<ID=ReadPosRankSum,Number=1,Type=Float,Description=\"Z-score from Wilcoxon rank sum test of Alt vs. Ref read position bias\">";

			    // testing that input string equals to string, constructed with InformationParameter.VCF_form
			    InformationParameter info = new InformationParameter(test1);
			    System.out.println(info.VcfFormat().equals(test1)); // testing that input string equals to string, constructed with InformationParameter.VCF_form
			
			    
			    info = new InformationParameter(test2);
			    System.out.println(info.VcfFormat().equals(test2));
			    
			    InformationParameter info1 = new InformationParameter( "TEST ID" , '1' , "Integer" , "Test for constructor" );
			    System.out.println( info1.VcfFormat()  );
			  //end constructions
			 //END TEST FOR INFORMATION PARAMETER		
		//TEST END
			  
			    
			   
			   VCF a = new VCF("SimpleTest1.txt"); 
			   a.printFile();
	}
}
