package controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import comparar.*;
import model.*;
import validador.Validador;

public class Controller {
	
	private HashMap<Integer, Cenario> cenarios;
	private ArrayList<Aposta> apostas;
	private Caixa caixa;
	private int id_apostas;
	private String msgpadrao;
	private ArrayList<Aposta> apostasSeguras;
	private int id_apostasSeguras;
	private Comparator<Cenario> estrategiaDeComparacao;
	
	
	public Controller() {
		this.cenarios = new HashMap<>();
		this.apostas = new ArrayList<>();
		this.id_apostas = 1;
		this.apostasSeguras = new ArrayList<>();
		this.id_apostasSeguras = 1;
		this.estrategiaDeComparacao = new ComparaCadastro();
	}
	
	public void inicializa(int dinheiro, double taxa){
		this.caixa = new Caixa(dinheiro, taxa);
	}

	
	public int getCaixa() {
		return this.caixa.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) {
		this.cenarios.put(id_apostas, new Cenario(id_apostas, descricao));
		return this.id_apostas++;
	}
	public int cadastrarCenario(String descricao, int bonus) {
		Cenario cenario = new CenarioBonus(id_apostas, descricao, bonus);
		this.caixa.setCaixa(this.getCaixa() - bonus);
		this.cenarios.put(id_apostas, cenario);
		return this.id_apostas++;
	}
	
	public String exibirCenario(int cenario) {
		msgpadrao = "Erro na consulta de cenario: ";
		Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
		Validador.cenarioNaoCadastrado(cenario, cenarios, msgpadrao + "Cenario nao cadastrado");
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
		msgpadrao = "Erro no cadastro de aposta: ";
		Validador.validadorCadastraAposta(cenario, apostador, valor, previsao, msgpadrao);
		Aposta aposta = new Aposta(cenario, apostador, valor, previsao);
		Validador.cenarioNaoCadastrado(cenario, this.cenarios, msgpadrao + "Cenario nao cadastrado");
		cenarios.get(cenario).setNum_apostas(cenarios.get(cenario).getNum_apostas()+1);
		this.apostas.add(aposta);
	}
	
	public int valorTotalDeApostas(int cenario) {
		msgpadrao = "Erro na consulta do valor total de apostas: ";
		Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
		Validador.cenarioNaoCadastrado(cenario, this.cenarios, msgpadrao + "Cenario nao cadastrado");
		return buscaApostas(cenario, "valorTotalDeApostas");
	}
	
	public int totalDeApostas(int cenario) {
		msgpadrao = "Erro na consulta do total de apostas: ";
		Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
		Validador.cenarioNaoCadastrado(cenario, this.cenarios, msgpadrao + "Cenario nao cadastrado");
		return cenarios.get(cenario).getNum_apostas();
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
		msgpadrao = "Erro ao fechar aposta: ";
		Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
		Validador.cenarioNaoCadastrado(cenario, this.cenarios, msgpadrao + "Cenario nao cadastrado");
		Validador.cenarioFechado(this.cenarios.get(cenario).getEstado(), msgpadrao + "Cenario ja esta fechado");
		if (ocorreu == true) {
			this.cenarios.get(cenario).setEstado("Finalizado (ocorreu)");
		}
		else {
			this.cenarios.get(cenario).setEstado("Finalizado (n ocorreu)");
		}
		caixa.setCaixa(this.buscaApostas(cenario, "fecharAposta"));
	}
	
	public int getCaixaCenario(int cenario) {
		int cont = 0;
		msgpadrao = "Erro na consulta do caixa do cenario: ";
		Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
		Validador.cenarioNaoCadastrado(cenario, this.cenarios, msgpadrao + "Cenario nao cadastrado");
		Validador.cenarioAberto(this.cenarios.get(cenario).getEstado(), msgpadrao + "Cenario ainda esta aberto");
		
		for (Aposta aposta: this.apostas) {
			if (aposta.getId_cenario() == cenario) {
				if (this.cenarios.get(cenario).getEstado().equals("Finalizado (ocorreu)")) {
					if (aposta.getPrevisao() == false) {
						cont += aposta.getValor() * caixa.getTaxa();
					}
				}
				else {
					if(aposta.getPrevisao() == true) {
						cont += aposta.getValor() * caixa.getTaxa();
					}
				}
			}
		}
		return cont;
	}
		
	public int getTotalRateioCenario(int cenario) {
		msgpadrao = "Erro na consulta do total de rateio do cenario: ";
		Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
		Validador.cenarioNaoCadastrado(cenario, this.cenarios, msgpadrao + "Cenario nao cadastrado");
		Validador.cenarioAberto(this.cenarios.get(cenario).getEstado(), msgpadrao + "Cenario ainda esta aberto");
		return this.buscaApostas(cenario, "getTotalRateioCenario");
	}
	
	public int cadastrarApostaSeguraValor (int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		msgpadrao = "Erro no cadastro de aposta assegurada por valor: ";
		Validador.validadorCadastraAposta(cenario, apostador, valor, previsao, msgpadrao);
		Aposta aposta = new ApostaSeguraValor(cenario, apostador, valor, previsao, this.id_apostasSeguras, valorAssegurado, custo);
		apostas.add(aposta);
		this.id_apostas++;
		apostasSeguras.add(aposta);
		this.caixa.setCaixa(this.caixa.getCaixa() + custo);
		cenarios.get(cenario).setNum_apostas(cenarios.get(cenario).getNum_apostas()+1);
		return id_apostasSeguras++;
	}
	
	public int cadastrarApostaSeguraTaxa (int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		msgpadrao = "Erro no cadastro de aposta assegurada por taxa: ";
		Validador.validadorCadastraAposta(cenario, apostador, valor, previsao, msgpadrao);
		Aposta aposta = new ApostaSeguraTaxa(cenario, apostador, valor, previsao, this.id_apostasSeguras, taxa, custo);
		apostas.add(aposta);
		this.id_apostas++;
		apostasSeguras.add(aposta);
		this.caixa.setCaixa(this.caixa.getCaixa() + custo);
		cenarios.get(cenario).setNum_apostas(cenarios.get(cenario).getNum_apostas()+1);
		return id_apostasSeguras++;
	}
	
	public int alterarSeguroValor(int cenario, int id_apostasSeguras, int valor) {

		if (this.apostasSeguras.get(id_apostasSeguras-1) instanceof ApostaSeguraTaxa) {
			Aposta apostaAnt = this.apostasSeguras.get(id_apostasSeguras-1);
			int id_apostaAnt = this.apostas.indexOf(apostaAnt);
			for (Aposta aposta: this.apostas) {
				if (apostaAnt.equals(aposta)) {
					Aposta apostaNew = new ApostaSeguraValor(apostaAnt.getId_cenario(), apostaAnt.getNome(), apostaAnt.getValor(), apostaAnt.getPrevisaoString(), id_apostasSeguras, valor, ((ApostaSeguraTaxa) apostaAnt).getCusto()); 
					apostas.set(id_apostaAnt, apostaNew);
					apostasSeguras.set(id_apostasSeguras-1, apostaNew);
					return id_apostasSeguras;
				}
			}
		}
		return 0;
	}
	
	public int alterarSeguroTaxa(int cenario, int id_apostasSeguras, double taxa) {

		if (this.apostasSeguras.get(id_apostasSeguras-1) instanceof ApostaSeguraValor) {
			Aposta apostaAnt = this.apostasSeguras.get(id_apostasSeguras-1);
			int id_apostaAnt = this.apostas.indexOf(apostaAnt);
			for (Aposta aposta: this.apostas) {
				if (apostaAnt.equals(aposta)) {
					Aposta apostaNew = new ApostaSeguraTaxa(apostaAnt.getId_cenario(), apostaAnt.getNome(), apostaAnt.getValor(), apostaAnt.getPrevisaoString(), id_apostasSeguras, taxa, ((ApostaSeguraValor) apostaAnt).getCusto()); 
					apostas.set(id_apostaAnt, apostaNew);
					apostasSeguras.set(id_apostasSeguras-1, apostaNew);
					return id_apostasSeguras;
				}
			}
		}
		return 0;
	}
	
   public void alterarOrdem(String ordem) {
	   msgpadrao = "Erro ao alterar ordem: ";
	   Validador.stringVaziaNula(ordem, msgpadrao + "Ordem nao pode ser vazia ou nula");
	   switch (ordem) {
	   case "nome":
		   estrategiaDeComparacao = new ComparaNome();
		   break;
	   case "apostas":
		   estrategiaDeComparacao = new ComparaApostas();
		   break;
	   case "cadastro":
		   estrategiaDeComparacao = new ComparaCadastro();
		   break;
	   default:
		   throw new IllegalArgumentException(msgpadrao + "Ordem invalida");
	   }
   }
   
   public String exibirCenarioOrdenado(int cenario) {
	   msgpadrao = "Erro na consulta de cenario ordenado: ";
	   Validador.intMenorOuIgualZero(cenario, msgpadrao + "Cenario invalido");
	   Validador.cenarioNaoCadastrado(cenario, cenarios, msgpadrao + "Cenario nao cadastrado");
	   ArrayList<Cenario> copia = new ArrayList<>();
	   copia.addAll(cenarios.values());
	   Collections.sort(copia, estrategiaDeComparacao);
	   return copia.get(cenario-1).toString();
   }
	
	private int buscaApostas(int cenario, String metodo) {
		int cont = 0;
		if (metodo.equals("fecharAposta")) { cont = this.getCaixa(); }
		int aux = 0;
		for(Aposta aposta: this.apostas) {
			if (aposta.getId_cenario() == cenario) {
				switch (metodo) {
				case "valorTotalDeApostas":
					cont += aposta.getValor();
					break;
				case "fecharAposta":
					if (this.cenarios.get(cenario).getEstado().equals("Finalizado (ocorreu)")) {
						if (aposta.getPrevisao() == false) {
							if (aposta instanceof ApostaSeguraValor) {
								cont -= ((ApostaSeguraValor) aposta).getValorAssegurado();
							}
							else if (aposta instanceof ApostaSeguraTaxa) {
								aux = (int) (aposta.getValor()*((ApostaSeguraTaxa) aposta).getTaxa());
								cont -= aux;
							}
						}
					}
					else {
						if(aposta.getPrevisao() == true) {
							if (aposta instanceof ApostaSeguraValor) {
								cont -= ((ApostaSeguraValor) aposta).getValorAssegurado();
							}
							else if (aposta instanceof ApostaSeguraTaxa) {
								aux = (int) (aposta.getValor()*((ApostaSeguraTaxa) aposta).getTaxa());
								cont -= aux;
							}
						}
					}
					break;
				case "getTotalRateioCenario":
					if (this.cenarios.get(cenario).getEstado().equals("Finalizado (ocorreu)")) {
						if (aposta.getPrevisao() == false) {
							cont += aposta.getValor();
							aux += aposta.getValor() * caixa.getTaxa();
						}
					}
					else {
						if(aposta.getPrevisao() == true) {
							cont += aposta.getValor();
							aux += aposta.getValor() * caixa.getTaxa();
						}
					}
					break;
				default:
					break;
				}
			}
		}
		if (metodo.equals("getTotalRateioCenario")) {
			cont -= this.getCaixaCenario(cenario);
		}
		if (this.cenarios.get(cenario) instanceof CenarioBonus && metodo.equals("getTotalRateioCenario")) {
			cont += ((CenarioBonus) this.cenarios.get(cenario)).getBonus();
		}
		if (metodo.equals("fecharAposta")) {cont += this.getCaixaCenario(cenario);}
		return cont;
	}
}
