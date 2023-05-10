package br.com.improving.carrinho;

import static br.com.improving.carrinho.Util.formatarValorReal;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements LojaI {
	@JsonAlias("id")
    private Long codigo;
	@JsonAlias("title")
    private String descricao;
	@JsonAlias("price")
    private double valor;
	@JsonAlias("thumbnail")
	private String imagem;
	
	/**
     * Retorna as informações do produto.
     */
	@Override
	public void getInfo() {
		System.out.println("ID: #" + codigo);
		System.out.println("Nome: " + descricao);
		System.out.println("Imagem: " + imagem);
		System.out.println("Valor (BRL): " + formatarValorReal(valor));
	}
}