package validador;
import java.util.HashMap;
import java.util.NoSuchElementException;

import model.*;


public class Validador {
	
	public static void validadorCadastraAposta(int id_cenario, String nome, int valor, String previsao, String msg) {
		Validador.intMenorOuIgualZero(id_cenario, msg + "Cenario invalido");
		Validador.stringVaziaNula(nome, msg + "Apostador nao pode ser vazio ou nulo");
		Validador.intMenorOuIgualZero(valor, msg + "Valor nao pode ser menor ou igual a zero");
		Validador.stringVaziaNula(previsao, msg + "Previsao nao pode ser vazia ou nula");
		Validador.previsaoInvalida(previsao, msg + "Previsao invalida");
	}
	
	public static void inteiroMenorque0(int i, String msg) {
		if (i < 0) {
			throw new ArithmeticException(msg); 
		}
	}
	
	public static void doubleMenorque0(double d, String msg) {
		if (d < 0) {
			throw new ArithmeticException(msg);
		}
	}
	
	public static void stringVaziaNula(String str, String msg) {
		if (str.isEmpty() || str == null || str.trim().isEmpty()) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void intMenorOuIgualZero(int i, String msg) {
		if (i <= 0) {
			throw new ArithmeticException(msg);
		}
	}
	
	public static void cenarioNaoCadastrado(int i, HashMap<Integer,Cenario> cenarios, String msg) {
		if (!cenarios.containsKey(i)) {
			throw new NoSuchElementException(msg);
		}
	}
	
	public static void previsaoInvalida(String str, String msg) {
		if (!str.equals("VAI ACONTECER") && !str.equals("N VAI ACONTECER")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public static void cenarioFechado(String str, String msg) {
		if (str.equals("Finalizado (ocorreu)") || str.equals("Finalizado (n ocorreu)")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public static void cenarioAberto(String str, String msg) {
		if (str.equals("Nao finalizado")) {
			throw new IllegalArgumentException(msg);
		}
	}
}
