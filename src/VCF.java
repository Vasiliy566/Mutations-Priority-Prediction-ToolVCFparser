import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VCF {
	ArrayList<MutationParameter> mutations = new ArrayList<MutationParameter>(); // all mutations in one list
	ArrayList<InformationParameter> parameters = new ArrayList<InformationParameter>(); // all parameters in one list

	String defaultStringtemplate = "#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT"; // template for mutation
																								// string

	// constructor, that takes VCF-class from VCF-file, filling all fields
	VCF(String filePath) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		String curString = "";
		while ((curString = buf.readLine()) != null) {
			if (curString.substring(0, 6).equals("##INFO"))
				parameters.add(new InformationParameter(curString));
			else if (curString.charAt(0) == '#')
				defaultStringtemplate = curString;
			else
				mutations.add(new MutationParameter(curString));
		}
		buf.close();
	}

	// function that print all VCF-class info to VCF-file template and create
	// correctly VCF-file
	void printFile() throws IOException {
		FileWriter writer = new FileWriter("out.vcf");
		for (InformationParameter p : parameters)
			writer.write(p.VcfFormat() + '\n');
		writer.write(defaultStringtemplate + '\n');
		for (MutationParameter p : mutations)
			writer.write(p.VcfFormat() + '\n');
		writer.flush();
	}

	// function that add new INFO-string, all parameters should be given manually
	void addInfoString(String ID_, char number_, String type_, String description_) {
		parameters.add(new InformationParameter(ID_, number_, type_, description_));
	}

	// function that add given values 'input' for all mutations in filed 'info'
	void addInfoValues(String info, double input) {
		for (MutationParameter p : mutations)
			p.addInfoValue(info, input);
	}

}
