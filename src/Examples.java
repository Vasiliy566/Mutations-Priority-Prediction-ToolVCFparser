import java.io.IOException;

public class Examples {

	public static void main(String[] args) throws IOException {
		VCF a = new VCF("src/resources/test.vcf"); // распарсили VCF в класс
		
		// Способы создания параметров для рассчета приоритета мутации
		//1.1  - параметры для ключей 'e'/'b'/'s'
		
		// если eQUALS, то +1.5, если нет, то +0
		PriorityValueCountRule ClinVar_1 = new PriorityValueCountRule("CLNSIG", 'e', 4, 1.5);
		PriorityValueCountRule ClinVar_2 = new PriorityValueCountRule("CLNSIG", 'e', 5, 2.5); 
		
		// если больше чем 1.5, то +3, если меньше, то -5, если равны, то + 3
		PriorityValueCountRule frequency_1 = new PriorityValueCountRule("X1000Gp3_AF", 'b', 0.01, 0, 1.0); 
		PriorityValueCountRule frequency_2 = new PriorityValueCountRule("ExAC_AF", 'b', 0.01, 0, 1.0);
		PriorityValueCountRule frequency_3 = new PriorityValueCountRule("ESP6500_AF", 'b', 0.01, 0, 1.0);
		
		// если больше, то +3, меньше -5, одинаково +0
		PriorityValueCountRule prediction_1 = new PriorityValueCountRule("dbNSFP_Polyphen2_HVAR_pred", 'e', 'D', 0.5);
		PriorityValueCountRule prediction_2 = new PriorityValueCountRule("dbNSFP_Polyphen2_HVAR_pred", 'e', 'P', 0.25);
		PriorityValueCountRule prediction_3 = new PriorityValueCountRule("dbNSFP_PROVEAN_pred", 'e', 'D', 0.5);
		PriorityValueCountRule prediction_4 = new PriorityValueCountRule("dbNSFP_SIFT_pred", 'e', 'D', 0.5);
		
		PriorityValueCountRule annotation_1 = new PriorityValueCountRule("OM", 'c', 1.0);
		
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "nonsense", 1.5);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "splic", 1.0);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "+1", 0.5);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "+2", 0.5);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "+3", 0.5);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "-1", 0.5);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "-2", 0.5);
		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 's', "-3", 0.5);
		
	
		
		

	}

}
