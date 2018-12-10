import java.io.IOException;

public class Examples {

	public static void main(String[] args) throws IOException {
		VCF a = new VCF("src/resources/test.vcf"); // распарсили VCF в класс
		
		// Способы создания параметров для рассчета приоритета мутации
		//1.1  - параметры для ключей 'e'/'b'/'s'
		
		// если eQUALS, то +1.5, если нет, то +0
		PriorityValueCountRule example1 = new PriorityValueCountRule("NS", 'e', 3, 1.5); 
		
		// если больше чем 1.5, то +3, если меньше, то -5, если равны, то + 3
		PriorityValueCountRule example2 = new PriorityValueCountRule("NS", 'b', 3, -5, 1.5); 
		
		// если больше, то +3, меньше -5, одинаково +0
		PriorityValueCountRule example3 = new PriorityValueCountRule("NS", 'e', 3, -5, 0, 1.5);   

	}

}
