package br.com.improving.carrinho;

import static br.com.improving.carrinho.Util.formatarValorReal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Data;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
@Data
public class CarrinhoComprasFactory implements LojaI {
	private List<CarrinhoCompras> carrinhos = new ArrayList<>();

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
		Optional<CarrinhoCompras> carrinhoEncontrado = carrinhos.stream().filter(carrinho -> carrinho.getCliente().getId().equals(identificacaoCliente)).findFirst();
		
		if (carrinhoEncontrado.isPresent()) {
			return carrinhoEncontrado.get();
		} else {
			CarrinhoCompras newCart = new CarrinhoCompras(Integer.toString(carrinhos.size() + 1), new Cliente(identificacaoCliente, null), new ArrayList<>());
			carrinhos.add(newCart);
			return newCart;
		}
    }
	
	/**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param cliente
     * @return CarrinhoCompras
     */
	public CarrinhoCompras criar(Cliente cliente) {
		Optional<CarrinhoCompras> carrinhoEncontrado = carrinhos.stream().filter(carrinho -> carrinho.getCliente().equals(cliente)).findFirst();
		
		if (carrinhoEncontrado.isPresent()) {
			return carrinhoEncontrado.get();
		} else {
			CarrinhoCompras newCart = new CarrinhoCompras(Integer.toString(carrinhos.size() + 1), cliente, new ArrayList<>());
			carrinhos.add(newCart);
			return newCart;
		}
    }
	
	/**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
	 * @param id
     * @param cliente
     * @return CarrinhoCompras
     */
	public CarrinhoCompras criar(String id, Cliente cliente) {
		Optional<CarrinhoCompras> carrinhoEncontrado = carrinhos.stream().filter(carrinho -> carrinho.getCliente().equals(cliente)).findFirst();
		
		if (carrinhoEncontrado.isPresent()) {
			return carrinhoEncontrado.get();
		} else {
			CarrinhoCompras newCart = new CarrinhoCompras(id, cliente, new ArrayList<>());
			carrinhos.add(newCart);
			return newCart;
		}
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		for (CarrinhoCompras carrinho : carrinhos) {
			valorTotal = valorTotal.add(carrinho.getValorTotal());
		}

		if (carrinhos.isEmpty()) {
			return BigDecimal.ZERO;
		}

		BigDecimal ticketMedio = valorTotal.divide(BigDecimal.valueOf(carrinhos.size()), 2, RoundingMode.HALF_UP);
		return ticketMedio;
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
		boolean carrinhoInvalidado = false;
		
		for (CarrinhoCompras carrinho : carrinhos) {
			if (carrinho.getCliente().getId().equals(identificacaoCliente)) {
				carrinhos.remove(carrinho);
				carrinhoInvalidado = true;
				break;
			}
		}
		
		return carrinhoInvalidado;
    }
	
	/**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param cliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
	public boolean invalidar(Cliente cliente) {
		boolean carrinhoInvalidado = false;
		
		for (CarrinhoCompras carrinho : carrinhos) {
			if (carrinho.getCliente().equals(cliente)) {
				carrinhos.remove(carrinho);
				carrinhoInvalidado = true;
				break;
			}
		}
		
		return carrinhoInvalidado;
    }
	
	/**
     * Retorna as informações dos meus carrinhos.
     */
	@Override
	public void getInfo() {
		System.out.println("Compras: " + carrinhos.size() + " carrinho" + ((carrinhos.size() > 1) ? "s" : ""));
		System.out.println("Valor do Ticket (Médio): " + formatarValorReal(this.getValorTicketMedio()));
	}
}
