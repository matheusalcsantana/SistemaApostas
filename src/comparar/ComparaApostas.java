package comparar;

import java.util.Comparator;
import model.*;

public class ComparaApostas implements Comparator<Cenario>{
	
	@Override
	public int compare(Cenario arg0, Cenario arg1) {
		
		if (arg0.getNum_apostas() < arg1.getNum_apostas()) {
			return 1;
		}
		else if (arg0.getNum_apostas() > arg1.getNum_apostas()) {
			return -1;
		}
		else {
			Comparator<Cenario> c = new ComparaCadastro();
			return c.compare(arg0, arg1);
		}
	}
}
