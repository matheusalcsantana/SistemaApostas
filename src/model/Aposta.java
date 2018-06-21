package model;

public class Aposta {
	
	private int id_cenario;
	private String nome;
	private int valor;
	private String previsao;
	
	public Aposta(int id_cenario, String nome, int valor, String previsao) {
		
		if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException("Previsão inserida não é válida!");
		}
		
		this.id_cenario = id_cenario;
		this.nome = nome;
		this.valor = valor;
		this.previsao = previsao;
	
	}
	
	public int getId_cenario() {
		return id_cenario;
	}


	public String getNome() {
		return nome;
	}

	public int getValor() {
		return valor;
	}

	public boolean getPrevisao() {
		if (this.previsao.equals("VAI ACONTECER")) {
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public String toString() {
		return nome + " - " + valor + " - " + previsao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_cenario;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((previsao == null) ? 0 : previsao.hashCode());
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		if (id_cenario != other.id_cenario)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (previsao == null) {
			if (other.previsao != null)
				return false;
		} else if (!previsao.equals(other.previsao))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
	
}
