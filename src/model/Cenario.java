package model;

public class Cenario {
	
	private int id;
	private String descricao;
	private String estado;
	
	public Cenario(int id, String descricao) {
		
		this.id = id;
		this.descricao = descricao;
		this.estado = "Não finalizado";
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
		return descricao + " - " + estado;
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
