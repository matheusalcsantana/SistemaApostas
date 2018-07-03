package model;

import java.text.NumberFormat;
import java.util.Currency;

import validador.Validador;

public class CenarioBonus extends Cenario{
	
	private int bonus;
	
	public CenarioBonus(int id, String descricao, int bonus) {
		super(id, descricao);
		String msgpadrao = "Erro no cadastro de cenario: ";
		Validador.intMenorOuIgualZero(bonus, msgpadrao + "Bonus invalido");
		this.bonus = bonus;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	@Override
	public String toString() {
		NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();
		formatoMoeda.setCurrency(Currency.getInstance("BRL"));
		double valor = this.bonus;
		return super.toString() + " - " + formatoMoeda.format(valor/100);
	}

	
}
