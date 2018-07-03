package model;

import java.text.NumberFormat;
import java.util.Currency;

public class ApostaSeguraValor extends Aposta {
	
	private int id_apostaAssegurada;
	private int valorAssegurado;
	private int custo;
	
	public ApostaSeguraValor(int id_cenario, String nome, int valor, String previsao, int id_apostaAssegurada, int valorAssegurado, int custo) {
		super(id_cenario, nome, valor, previsao);
		this.id_apostaAssegurada = id_apostaAssegurada;
		this.valorAssegurado = valorAssegurado;
		this.custo = custo;
	}

	public int getId_apostaAssegurada() {
		return id_apostaAssegurada;
	}

	public int getValorAssegurado() {
		return valorAssegurado;
	}

	public void setValorAssegurado(int valorAssegurado) {
		this.valorAssegurado = valorAssegurado;
	}

	public int getCusto() {
		return custo;
	}
	
	@Override
	public String toString() {
		NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();
		formatoMoeda.setCurrency(Currency.getInstance("BRL"));
		double valor = this.valorAssegurado;
		return super.toString() + " - ASSEGURADA (VALOR) - " + formatoMoeda.format(valor/100);
	}
}
