package br.com.improving.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe que representa as informações do cliente.
 */
@Data
@AllArgsConstructor
public class Cliente {
	private String id;
	private String nome;
}