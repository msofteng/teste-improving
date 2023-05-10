package br.com.improving.carrinho;

import java.util.Locale;

import lombok.AllArgsConstructor;

/**
 * enum responsável pelo armazenamento de informações relacionadas a moedas internacionais e sua cotação para real brasileiro (BRL).
 */
@AllArgsConstructor
public enum Moeda {
	EURO("EUR", "€", 5.47, Locale.GERMANY),
	DOLAR("USD", "US$", 4.99, Locale.US),
	REAL("BRL", "R$", 0, Locale.forLanguageTag("pt-BR"));

	private final String codigo;
	private final String simbolo;
	private final double cotacao;
	private final Locale pais;
	
	/**
     * Retorna o código da moeda internacional.
	 * 
	 * @return String
	*/
	public String getCodigo() {
		return codigo;
	}
	
	/**
     * Retorna o símbolo da moeda internacional.
	 * 
	 * @return String
	*/
	public String getSimbolo() {
		return simbolo;
	}
	
	/**
     * Retorna o valor da moeda internacional para BRL (real brasileiro).
	 * 
	 * @return String
	*/
	public double getCotacao() {
		return cotacao;
	}
	
	/**
     * Retorna a formatação/representação local da moeda internacional.
	 * 
	 * @return String
	*/
	public Locale getPais() {
		return pais;
	}
}