import java.io.IOException;
import java.util.HashMap;

public class Examples {

	public static void main(String[] args) throws IOException {

		VCF a = new VCF("src/resources/test.vcf"); // распарсили VCF в класс

		// Способы создания параметров для рассчета приоритета мутации
		// 1.1 - параметры для ключей 'e'/'b'/'s'

		// если eQUALS, то +1.5, если нет, то +0
		PriorityValueCountRule ClinVar_1 = new PriorityValueCountRule("CLNSIG", 'e', 5, 1.5);
		PriorityValueCountRule ClinVar_2 = new PriorityValueCountRule("CLNSIG", 'e', 4, 1.0);
		PriorityValueCountRule ClinVar_3 = new PriorityValueCountRule("CLNSIG", 'e', 1, -0.5);
		PriorityValueCountRule ClinVar_4 = new PriorityValueCountRule("CLNSIG", 'e', 2, -1.0);
		PriorityValueCountRule ClinVar_5 = new PriorityValueCountRule("CLNSIG", 'e', 3, -0.5);

		// если больше чем 1.5, то +3, если меньше, то -5, если равны, то + 3
		PriorityValueCountRule frequency_1 = new PriorityValueCountRule("X1000Gp3_AF", 'b', 0.01, 0, 1.0);
		PriorityValueCountRule frequency_2 = new PriorityValueCountRule("ExAC_AF", 'b', 0.01, 0, 1.0);
		PriorityValueCountRule frequency_3 = new PriorityValueCountRule("ESP6500_AF", 'b', 0.01, 0, 1.0);

		// если больше, то +3, меньше -5, одинаково +0
		PriorityValueCountRule prediction_1 = new PriorityValueCountRule("dbNSFP_Polyphen2_HVAR_pred", 'a', 'D', 0.5);
		PriorityValueCountRule prediction_2 = new PriorityValueCountRule("dbNSFP_Polyphen2_HVAR_pred", 'a', 'P', 0.25);
		PriorityValueCountRule prediction_3 = new PriorityValueCountRule("dbNSFP_PROVEAN_pred", 'a', 'D', 0.5);
		PriorityValueCountRule prediction_4 = new PriorityValueCountRule("dbNSFP_SIFT_pred", 'a', 'D', 0.5);

		PriorityValueCountRule annotation_1 = new PriorityValueCountRule("OM", 'c', 1.0);

		PriorityValueCountRule annotation_2 = new PriorityValueCountRule("ANN", 'd', "nonsense", 1.5);
		PriorityValueCountRule annotation_3 = new PriorityValueCountRule("ANN", 'd', "splice", 1.0);
		PriorityValueCountRule annotation_4 = new PriorityValueCountRule("ANN", 'd', "+1", 0.5);
		PriorityValueCountRule annotation_5 = new PriorityValueCountRule("ANN", 'd', "+2", 0.5);
		PriorityValueCountRule annotation_6 = new PriorityValueCountRule("ANN", 'd', "+3", 0.5);
		PriorityValueCountRule annotation_7 = new PriorityValueCountRule("ANN", 'd', "-1", 0.5);
		PriorityValueCountRule annotation_8 = new PriorityValueCountRule("ANN", 'd', "-2", 0.5);
		PriorityValueCountRule annotation_9 = new PriorityValueCountRule("ANN", 'd', "-3", 0.5);

		a.addCalculateConfig(ClinVar_1);
		a.addCalculateConfig(ClinVar_2);
		a.addCalculateConfig(ClinVar_3);
		a.addCalculateConfig(ClinVar_4);
		a.addCalculateConfig(ClinVar_5);

		a.addCalculateConfig(frequency_1);
		a.addCalculateConfig(frequency_2);
		a.addCalculateConfig(frequency_3);
		a.addCalculateConfig(prediction_1);
		a.addCalculateConfig(prediction_2);
		a.addCalculateConfig(prediction_3);
		a.addCalculateConfig(prediction_4);
		a.addCalculateConfig(annotation_1);
		a.addCalculateConfig(annotation_2);
		a.addCalculateConfig(annotation_3);
		a.addCalculateConfig(annotation_4);
		a.addCalculateConfig(annotation_5);
		a.addCalculateConfig(annotation_6);
		a.addCalculateConfig(annotation_7);
		a.addCalculateConfig(annotation_8);
		a.addCalculateConfig(annotation_9);
		
		a.CalculatePriorityMutation();
		a.CalculateImportantMutation(-100.0);
		a.printFile();
	}

}