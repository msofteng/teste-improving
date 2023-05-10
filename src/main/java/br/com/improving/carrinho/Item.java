package br.com.improving.carrinho;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe que representa um item no carrinho de compras.
 */
@Data
@AllArgsConstructor
public class Item {
	private Produto produto;
	private BigDecimal valorUnitario;
	private int quantidade;

	public Item(Produto produto, int quantidade) {
		this.produto = produto;
		this.valorUnitario = new BigDecimal(produto.getValor());
		this.quantidade = quantidade;
	}

	/**
	 * Retorna o valor total do item.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		return this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
	}
}