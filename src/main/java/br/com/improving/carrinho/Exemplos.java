package br.com.improving.carrinho;

import java.math.BigDecimal;

public class Exemplos {
	/**
     * Exemplo básico de uso.
     */
	public void exemplo1() {
		
		// produtos
		
		Produto produto1 = new Produto(1L, "LG Gram 14' W11, 256 SSD, I5", 5000, null);
		Produto produto2 = new Produto(2L, "LG All in One 27' 27V750 I5", 6000, null);
		
		// clientes
		
		Cliente cliente = new Cliente("#156", "Mateus Silva");
		
		// inicializando a loja
		
		CarrinhoComprasFactory loja = new CarrinhoComprasFactory();
		
		// criando um carrinho de compras para mim
		
		CarrinhoCompras meuCarrinho = loja.criar("#0001", cliente);
		
		// adicionando o produto ao meu carrinho
		
		meuCarrinho.adicionarItem(produto1, new BigDecimal(4750.40), 2);
		
		// retornando as informações do carrinho
		
		meuCarrinho.getInfo();
		
		// removendo um item do carrinho
		
		System.out.println("=============================");
		
		if (meuCarrinho.removerItem(produto1)) {
			System.out.println("O item foi removido!");
		} else {
			System.out.println("O item não foi encontrado!");
		}
		
		// tentando remover o item mais uma vez (sem querer apertei o botão de "Excluir" mais uma vez na correria)
		
		System.out.println("=============================");
		
		if (meuCarrinho.removerItem(produto1)) {
			System.out.println("O item foi removido!");
		} else {
			System.out.println("O item não foi encontrado!");
		}
		
		// escolhendo outros itens da loja
		
		System.out.println("=============================");
		
		meuCarrinho.getInfo();
		
		System.out.println("=============================");
		
		meuCarrinho.adicionarItem(produto2, new BigDecimal(7650.22), 3);
		
		System.out.println("=============================");
		
		meuCarrinho.getInfo();
		
		System.out.println("=============================");
		
		// criando outro carrinho para mim e adicionando mais um produto
		
		CarrinhoCompras meuCarrinho2 = loja.criar("#0002", cliente);
		
		meuCarrinho.adicionarItem(produto1, new BigDecimal(5000), 1);
		
		meuCarrinho.getInfo();
		
		// vendo as informações da loja
		
		System.out.println("=============================");
		
		loja.getInfo();
		
	}
}