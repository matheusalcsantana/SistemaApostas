package comparar;

import java.util.Comparator;
import model.*;

public class ComparaNome implements Comparator<Cenario>{
	
	@Override
	public int compare(Cenario arg0, Cenario arg1) {
		if (arg0.getDescricao().equals(arg1.getDescricao())) {
			Comparator<Cenario> c = new ComparaCadastro();
			return c.compare(arg0, arg1);
		}
		else {
		return arg0.getDescricao().compareTo(arg1.getDescricao());
		}
	}
}