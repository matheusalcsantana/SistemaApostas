package controllers;
import java.util.ArrayList;
import java.util.HashMap;
import model.*;

public class Controller {
	
	private HashMap<Integer, Cenario> cenarios;
	private ArrayList<Aposta> apostas;
	private Caixa caixa;
	private int num_cenario;
	private String erropadrao;
	
	public Controller(int dinheiro, double taxa) {
		this.cenarios = new HashMap<>();
		this.apostas = new ArrayList<>();
		this.caixa = new Caixa(dinheiro, taxa);
		this.num_cenario = 1;
	}
	
	public int getCaixa() {
		return this.caixa.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) {
		this.cenarios.put(num_cenario, new Cenario(num_cenario, descricao));
		return this.num_cenario++;
	}
	
	public String exibirCenario(int cenario) {
		erropadrao = "Erro na consulta de cenario: ";
		return this.cenarios.get(cenario).toString();
	}
	
	public String exibirCenarios() {
		String str = "";
		for (Cenario cenario: this.cenarios.values()) {
			str += cenario.toString();
		}
		return str;
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		Aposta aposta = new Aposta(cenario, apostador, valor, previsao);
		this.apostas.add(aposta);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return buscaApostas(cenario, "valorTotalDeApostas");
	}
	
	public int totalDeApostas(int cenario) {
		return buscaApostas(cenario, "totalDeApostas");
	}
	
	public String exibeApostas(int cenario) {
		String str = "";
		for (Aposta aposta: this.apostas) {
			if (aposta.getId_cenario() == cenario) {
				str += aposta.toString() + '\n';
			}
		}
		return str;
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		if (ocorreu == true) {
			this.cenarios.get(cenario).setEstado("Finalizado (ocorreu)");
		}
		else {
			this.cenarios.get(cenario).setEstado("Finalizado (n ocorreu)");
		}
	}
	
	public int getCaixaCenario(int cenario) {
		this.buscaApostas(cenario, "getCaixaCenario")
	}
		
	int getTotalRateioCenario(int cenario)

	private int buscaApostas(int cenario, String metodo) {
		int cont = 0;
		for(Aposta aposta: this.apostas) {
			if (aposta.getId_cenario() == cenario) {
				switch (metodo) {
				case "valorTotalDeApostas":
					cont += aposta.getValor();
					break;
				case "totalDeApostas":
					cont++;
					break;
				case "getCaixaCenario":
					//Teste se o cenario ainda tá aberto
					
					break;
				case "getTotalRateioCenario":
					break;
					
				default:
					break;
				}
			}
		}
		return cont;
	}
}
