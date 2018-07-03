package model;
import controllers.Controller;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controlador;
	
	public Facade() {
		this.controlador = new Controller();
	}
	
	public void inicializa(int dinheiro, double taxa) {
		this.controlador.inicializa(dinheiro, taxa);
	}

	public int getCaixa() {
		return this.controlador.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) {
		return this.controlador.cadastrarCenario(descricao);
	}
	
	public int cadastrarCenario(String descricao, int bonus) {
		return this.controlador.cadastrarCenario(descricao, bonus);
	}
	
	public String exibirCenario(int cenario) {
		return this.controlador.exibirCenario(cenario);
	}
	
	public String exibirCenarios() {
		return this.controlador.exibirCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.controlador.cadastrarAposta(cenario, apostador, valor, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return this.controlador.valorTotalDeApostas(cenario);
	}
	
	public int totalDeApostas(int cenario) {
		return this.controlador.totalDeApostas(cenario);
	}
	
	public String exibeApostas(int cenario) {
		return this.controlador.exibeApostas(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.controlador.fecharAposta(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) {
		return this.controlador.getCaixaCenario(cenario);
	}
		
	public int getTotalRateioCenario(int cenario) {
		return this.controlador.getTotalRateioCenario(cenario);
	} 
	
	public int cadastrarApostaSeguraValor (int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		return this.controlador.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorAssegurado, custo);
	}
	
	public int cadastrarApostaSeguraTaxa (int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return this.controlador.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}
	
	public int alterarSeguroValor(int cenario, int id_apostasSeguras, int valor) {
		return this.controlador.alterarSeguroValor(cenario, id_apostasSeguras, valor);
	}
	
	public int alterarSeguroTaxa(int cenario, int id_apostasSeguras, double taxa) {
		return this.controlador.alterarSeguroTaxa(cenario, id_apostasSeguras, taxa);
	}
	
	public void alterarOrdem(String ordem) {
		this.controlador.alterarOrdem(ordem);
	 }
	
	public String exibirCenarioOrdenado(int cenario) {
		return this.controlador.exibirCenarioOrdenado(cenario);
	}
	
	public static void main(String[] args) {
		args = new String[] {"model.Facade", "acceptance_tests/us1_test.txt", "acceptance_tests/us2_test.txt", "acceptance_tests/us3_test.txt", "acceptance_tests/us4_test.txt", "acceptance_tests/us5_test.txt", "acceptance_tests/us6_test.txt", "acceptance_tests/us7_test.txt"};
		EasyAccept.main(args);
	}
}
