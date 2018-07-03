package model;

import java.text.NumberFormat;
import java.util.Currency;

public class ApostaSeguraTaxa extends Aposta {
	private int id_apostaAssegurada;
	private double taxa;
	private int custo;
	
	public ApostaSeguraTaxa(int id_cenario, String nome, int valor, String previsao, int id_apostaAssegurada, double taxa, int custo) {
		super(id_cenario, nome, valor, previsao);
		this.id_apostaAssegurada = id_apostaAssegurada;
		this.taxa = taxa;
		this.custo = custo;
	}

	public int getId_apostaAssegurada() {
		return id_apostaAssegurada;
	}

	public void setId_apostaAssegurada(int id_apostaAssegurada) {
		this.id_apostaAssegurada = id_apostaAssegurada;
	}

	public double getTaxa() {
		return taxa;
	}

	public int getCusto() {
		return custo;
	}
	
	@Override
	public String toString() {
		NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();
		formatoMoeda.setCurrency(Currency.getInstance("BRL"));
		double valor = this.taxa;
		return super.toString() + " - ASSEGURADA (TAXA) - " + formatoMoeda.format(valor/100);
	}
}
