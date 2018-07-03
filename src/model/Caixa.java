package model;
import validador.Validador;

public class Caixa {
	
	private int caixa;
	private double taxa;
	
	public Caixa(int caixa, double taxa){
		String msgpadrao = "Erro na inicializacao: ";
		Validador.inteiroMenorque0(caixa, msgpadrao + "Caixa nao pode ser inferior a 0");
		Validador.doubleMenorque0(taxa, msgpadrao + "Taxa nao pode ser inferior a 0");
		this.caixa = caixa;
		this.taxa = taxa;
	}

	public int getCaixa() {
		return caixa;
	}

	public void setCaixa(int caixa) {
		this.caixa = caixa;
	}

	public double getTaxa() {
		return taxa;
	}
	
	
}
