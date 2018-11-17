import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VCF {
	ArrayList<MutationParameter> mutations = new ArrayList<MutationParameter>();
	ArrayList<InformationParameter> parameters = new ArrayList<InformationParameter>();

	String defaultStringtemplate = "#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT";

	VCF(String filePath) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		String curString = "";
		while ((curString = buf.readLine()) != null) {
			if (curString.substring(0, 2).equals("##"))
				parameters.add(new InformationParameter(curString));
			else if (curString.charAt(0) == '#')
				defaultStringtemplate = curString;
			else
				mutations.add(new MutationParameter(curString));

		}

	}
	void printFile() throws IOException {
		FileWriter writer = new FileWriter("out.vcf");
		for(InformationParameter p : parameters)
			writer.write(p.VcfFormat());
		writer.write(defaultStringtemplate);
		for(MutationParameter p : mutations)
			writer.write(p.VcfFormat());
	}

}
