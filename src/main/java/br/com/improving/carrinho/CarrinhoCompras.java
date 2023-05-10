package br.com.improving.carrinho;

import static br.com.improving.carrinho.Util.formatarValorDolar;
import static br.com.improving.carrinho.Util.formatarValorEuro;
import static br.com.improving.carrinho.Util.formatarValorReal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
@Data
@AllArgsConstructor
public class CarrinhoCompras implements LojaI {
	
	private String id;
	private Cliente cliente;
	private List<Item> itens = new ArrayList<>();

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) throws CarrinhoException {
		Optional<Item> itemEncontrado = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
		
		if (itemEncontrado.isPresent()) {
			if (quantidade < 0) {
				throw new CarrinhoException(quantidade);
			}
			
			int index = itens.indexOf(itemEncontrado.get());
			itens.get(index).setQuantidade(itens.get(index).getQuantidade() + quantidade);
			
			if (!itens.get(index).getValorUnitario().equals(valorUnitario)) {
				itens.get(index).setValorUnitario(valorUnitario);
			}
			
			System.out.println("O produto foi atualizado no carrinho!");
		} else {
			itens.add(new Item(produto, valorUnitario, quantidade));
			
			System.out.println("O produto foi adicionado ao seu carrinho!");
		}
    }
	
	/**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param quantidade
     */
	public void adicionarItem(Produto produto, int quantidade) throws CarrinhoException {
		Optional<Item> itemEncontrado = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
		
		if (itemEncontrado.isPresent()) {
			if (quantidade < 0) {
				throw new CarrinhoException(quantidade);
			}
			
			int index = itens.indexOf(itemEncontrado.get());
			
			itens.get(index).setQuantidade(itens.get(index).getQuantidade() + quantidade);
			
			System.out.println("O produto foi atualizado no carrinho!");
		} else {
			itens.add(new Item(produto, quantidade));
			
			System.out.println("O produto foi adicionado no carrinho!");
		}
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
		Optional<Item> itemEncontrado = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
		
		return (itemEncontrado.isPresent()) ? itens.remove(itemEncontrado.get()) : false;
    }
	
	/**
     * Permite aumentar a quantidade de um item no carrinho
     *
     * @param produto
	 * @param quantidade
     * @return Retorna um boolean, true caso o produto seja encontrado no carrinho e false se nada for encontrado
     */
	public boolean aumentar(Produto produto, int quantidade) {
		Optional<Item> itemEncontrado = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
		
		if (itemEncontrado.isPresent()) {
			int index = itens.indexOf(itemEncontrado.get());
			itens.get(index).setQuantidade(itens.get(index).getQuantidade() + quantidade);
			return true;
		}
		
		return false;
    }
	
	/**
     * Permite diminuir a quantidade de um item no carrinho
     *
     * @param produto
	 * @param quantidade
     * @return Retorna um boolean, true caso o produto seja encontrado no carrinho e false se nada for encontrado
     */
	public boolean diminuir(Produto produto, int quantidade) throws CarrinhoException {
		Optional<Item> itemEncontrado = itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
		
		if (itemEncontrado.isPresent()) {
			if (quantidade < 0) {
				throw new CarrinhoException(quantidade);
			}
			
			int index = itens.indexOf(itemEncontrado.get());
			
			if (quantidade <= itens.get(index).getQuantidade()) {
				itens.get(index).setQuantidade(itens.get(index).getQuantidade() - quantidade);
			} else {
				this.removerItem(itemEncontrado.get().getProduto()); // remove o produto se ele tirar tudo
				// itens.get(index).setQuantidade(0); // para não entrar valores negativos
			}
			
			return true;
		}
		
		return false;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
		if (posicaoItem < itens.size() && itens.get(posicaoItem) != null) {
			itens.remove(posicaoItem);
			return true;
		}
		
		return false;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal(0);
		
		for (Item item: itens) {
			valorTotal = valorTotal.add(item.getValorTotal());
		}	
		
		return valorTotal;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
		return itens.stream().collect(Collectors.toList());
    }
	
	/**
     * Retorna a lista de itens do carrinho de compras.
     */
    public void getDetalhes() {
		itens.stream().forEach(item -> {
			System.out.println(String.format("> %s\n  Quantidade: x%s\n  Valor Unitário: %s un.\n  Total: %s\n  =========\n  ![image](%s)\n", item.getProduto().getDescricao(), item.getQuantidade(), formatarValorReal(item.getValorUnitario()), formatarValorReal(item.getValorTotal()), item.getProduto().getImagem()));
		});
    }
	
	/**
     * Retorna as informações do carrinho.
     */
	@Override
	public void getInfo() {
		System.out.println("Valor Total (BRL): " + formatarValorReal(this.getValorTotal()));
		System.out.println("Valor Total (EUR): " + formatarValorEuro(this.getValorTotal()));
		System.out.println("Valor Total (USD): " + formatarValorDolar(this.getValorTotal()));
		System.out.println("Quantidade: " + itens.size() + " produto" + ((itens.size() > 1) ? "s" : ""));
	}
}