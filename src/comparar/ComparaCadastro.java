package comparar;
import java.util.Comparator;
import model.*;

public class ComparaCadastro implements Comparator<Cenario>{
	
	@Override
	public int compare(Cenario arg0, Cenario arg1) {
		if (arg0.getId() > arg1.getId()) {
			return 1;
		}
		else if (arg0.getId() < arg1.getId()) {
			return -1;
		}
		else {
			return 0;
		}
	}
}