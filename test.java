import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test {

	@Test
	void MutationParameterTest() {
		String mutParam = "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;	GT:GQ:DP:HQ";
		
		MutationParameter mut = new MutationParameter("");
		mut = new MutationParameter(mutParam);
		if (!mut.VcfFormat().equals(mutParam))
			fail("Not correct");
		
	}

}
