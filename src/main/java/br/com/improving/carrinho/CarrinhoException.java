package br.com.improving.carrinho;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CarrinhoException extends RuntimeException {
	private String message;
	private LocalDateTime time;
	private int quantidade;

	public CarrinhoException(String message) {
		super(message);
		this.message = message;
		this.time = LocalDateTime.now();
	}

	public CarrinhoException(int quantidade) {
		this.message = String.format("Erro: Não é permitido adicionar ou remover quantidade negativas ao item do carrinho [%d]", quantidade);
		this.quantidade = quantidade;
		this.time = LocalDateTime.now();
	}
	
	
	
}