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
}
