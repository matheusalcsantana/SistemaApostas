package model;
import validador.Validador;

public class Cenario {
	
	private int id;
	private String descricao;
	private String estado;
	private int num_apostas;
	
	public Cenario(int id, String descricao) {
		String msgpadrao = "Erro no cadastro de cenario: ";
		Validador.stringVaziaNula(descricao, msgpadrao + "Descricao nao pode ser vazia");
		this.id = id;
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.num_apostas = 0;
	}

	public int getNum_apostas() {
		return num_apostas;
	}

	public void setNum_apostas(int num_apostas) {
		this.num_apostas = num_apostas;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getEstado() {
		return estado;
	}
	
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id + " - " + descricao + " - " + estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cenario other = (Cenario) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
