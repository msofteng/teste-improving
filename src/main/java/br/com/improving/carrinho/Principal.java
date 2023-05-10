package br.com.improving.carrinho;

import static br.com.improving.carrinho.Util.extractNumbers;

import java.util.List;

public class Principal {
	public static void main(String[] args) {
		
		try {
			System.out.println("");
			System.out.println("============== CARRINHO DE COMPRAS IMPROVING ==============");

			// pegando o catálogo de produtos da API

			List<Produto> produtos = Produtos.getProdutos();

			// criando a minha conta

			Cliente cliente = new Cliente("#0001", "Mateus Silva");

			// abrindo minha lista de pedidos

			CarrinhoComprasFactory loja = new CarrinhoComprasFactory();

			// meu carrinho de compras

			CarrinhoCompras meuCarrinho = loja.criar(cliente);

			// adicionando os produtos no meu carrinho de compras

			System.out.println("\n============== ADICIONANDO OS PRODUTOS ==============\n");
				

			meuCarrinho.adicionarItem(produtos.get(5), 1);
			meuCarrinho.adicionarItem(produtos.get(5), 4);
			meuCarrinho.adicionarItem(produtos.get(10), 1);
			meuCarrinho.adicionarItem(produtos.get(15), 1);
			meuCarrinho.adicionarItem(produtos.get(20), 1);

			System.out.println("\n============== DETALHES DO CARRINHO ==============\n");

			meuCarrinho.getDetalhes();

			System.out.println("============== INFORMAÇÕES DO CARRINHO ==============\n");

			meuCarrinho.getInfo();

			System.out.println("\n============== INFORMAÇÕES DOS PEDIDOS DA LOJA ==============\n");

			loja.getInfo();

			System.out.println("\n============== CADASTRANDO MAIS CLIENTES ==============\n");

			Cliente cliente2 = new Cliente("#0002", "Ana Paula");
			
			// ana paula entrou na loja e pegou o seu carrinho

			CarrinhoCompras carrinhoAna = loja.criar(cliente2);
			
			// ana paula adicionou os itens ao seu carrinho

			carrinhoAna.adicionarItem(produtos.get(4), 6);
			carrinhoAna.adicionarItem(produtos.get(53), 2);
			carrinhoAna.adicionarItem(produtos.get(23), 15);
			carrinhoAna.adicionarItem(produtos.get(78), 3);
			carrinhoAna.adicionarItem(produtos.get(54), 1);
			
			// ana paula removeu 2 itens que ela não gostou

			System.out.println((carrinhoAna.removerItem(produtos.get(4))) ? "O item foi removido com sucesso!" : "O item não foi encontrado!");
			System.out.println((carrinhoAna.removerItem(2)) ? "O item foi removido com sucesso!" : "O item não foi encontrado!");
			
			// ana paula vende na internet e comprou de atacado para revender na shopee

			System.out.println((carrinhoAna.aumentar(produtos.get(53), 650)) ? "O item foi atualizado no carrinho!" : "O item não foi encontrado!");
			System.out.println((carrinhoAna.aumentar(produtos.get(53), 100)) ? "O item foi atualizado no carrinho!" : "O item não foi encontrado!");
			
			// o marido de ana paula falou para ela tirar 2 produtos da cesta pra conta fechar

			System.out.println((carrinhoAna.removerItem(6)) ? "O item foi removido com sucesso!" : "O item não foi encontrado!");
			System.out.println((carrinhoAna.removerItem(2)) ? "O item foi removido com sucesso!" : "O item não foi encontrado!");

			System.out.println();
			
			// ana paula passou no caixa

			carrinhoAna.getDetalhes();
			carrinhoAna.getInfo();
			
			System.out.println();
			
			// ana paula fez o seu pedido e já foi entregue
			
			System.out.println((loja.invalidar(cliente2)) ? String.format("A sessão do(a) %s expirou", cliente2.getNome()) : "Este cliente não foi encontrado!");
			
			System.out.println("\n============== INFORMAÇÕES DOS PEDIDOS DA LOJA ==============\n");
			
			// no final do dia a loja foi ver o seu faturamento

			loja.getInfo();
		} catch (CarrinhoException erro) {
			System.err.println(">>> " + erro.getMessage());
		} catch (IndexOutOfBoundsException iofEx) {
			List<String> numbers = extractNumbers(iofEx.getMessage());
			StackTraceElement localErro = iofEx.getStackTrace()[iofEx.getStackTrace().length - 1];
			
			System.out.println((iofEx.getMessage().contains("out of")) ? String.format(">>> Erro: O índice %s está fora dos limites para uma lista com %s posições na linha %d do arquivo %s", numbers.get(0), numbers.get(1), localErro.getLineNumber(), localErro.getFileName()) : ">>> Erro: " + iofEx.getMessage());
		}
		
	}
}