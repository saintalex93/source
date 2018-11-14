package br.com.neolog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.exceptions.EmptyCartException;
import br.com.neolog.exceptions.InvalidQuantityException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.exceptions.ProductNotInCartException;
import br.com.neolog.exceptions.ProductQuantityInsufficientException;
import br.com.neolog.pojo.Cart;
import br.com.neolog.pojo.Cart.Status;
import br.com.neolog.pojo.OrderItem;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.ProductQuantity;
import br.com.neolog.pojo.User;
import br.com.neolog.repository.CartRepository;
import br.com.neolog.repository.CategoryRepository;
import br.com.neolog.repository.OrderItemRepository;
import br.com.neolog.repository.ProductRepository;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.StockRepository;

@Component
public class CartService {

	@Autowired
	public StockRepository stockRepository;

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public CartRepository cartRepository;

	@Autowired
	public OrderItemRepository orderItemRepository;

	@Autowired
	public SessionRepository sessionRepository;

	@Autowired
	public TokenService tokenService;

	/**
	 * Método que retorna um {@link Cart} para o usuário corrente.
	 * 
	 * @return Deve retornar um {@link Cart} com o status NOTCOMPLETED, se não
	 *         existir deverá retornar um {@link Cart} novo.
	 */
	public Cart getCart() {
		User user = CurrentUserHolder.getUser();
		Cart cart = cartRepository.findByUserAndStatus(user, "NOTCOMPLETED");
		if (cart != null) {
			return cart;
		}
		cart = new Cart();
		cart.setUser(user);
		cart.setStatus(Status.NOTCOMPLETED);
		return cart;

	}

	/**
	 * Método que adiciona uma quantidade N de {@link Product} ao {@link Cart}.
	 * 
	 * @param code
	 *            código do {@link Product} a ser adicionado ao {@link Cart}.
	 * @param quantity
	 *            a quantidade a ser adicionada.
	 * @throws ProductQuantityInsufficientException
	 *             lança essa exceção se a {@code quantity} a ser adicionada ao
	 *             {@link Cart} for maior que a {@code quantity} em estoque.
	 * @throws InvalidQuantityException
	 *             lança essa exceção se a {@code quantity} for um número
	 *             inválido, menor do que 1.
	 * @throws ProductNotFoundException
	 *             lança essa exceção se não for encontrado nenhum
	 *             {@link Product} com o {@code code} informado.
	 * @return RETORNO AINDA NÃO DEFINIDO!!!
	 */

	@Transactional
	public boolean addToCart(String code, Integer quantity)

	throws ProductQuantityInsufficientException, InvalidQuantityException,
			ProductNotFoundException {

		ProductQuantity productQuantity = stockRepository
				.findByProductCode(code);

		if (productQuantity == null) {
			throw new ProductNotFoundException(
					"Produto n�o encontrado no estoque!!!");
		}

		if (quantity < 1) {
			throw new InvalidQuantityException(
					"Quantidade inv�lida, a quantidade deve ser maior ou igual a 1!!!");
		}
		if (quantity > productQuantity.getQuantity()) {
			throw new ProductQuantityInsufficientException(
					"Quantidade em estoque insuficiente!!! ESTOQUE: "
							+ productQuantity.getQuantity());
		}

		Cart cart = getCart();

		for (OrderItem o : cart.getOrderItens()) {
			if (o.getProduct().getCode().equals(code)) {
				o.setQuantity(o.getQuantity() + quantity);
				cartRepository.save(cart);
				return true;
			}

		}
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(stockRepository.findByProductCode(code)
				.getProduct());
		orderItem.setQuantity(quantity);
		cart.getOrderItens().add(orderItem);
		cartRepository.save(cart);

		return true;
	}

	/**
	 * Método que remove uma quantidade N de produtos do {@link Cart}.
	 * 
	 * @param code
	 *            código do {@link Product} a ser removido do {@link Cart}.
	 * @param quantity
	 *            a quantidade a ser removida.
	 * @throws ProductNotInCartException
	 *             Lança essa exceção se o produto não estiver no {@link Cart}.
	 * @throws InvalidQuantityException
	 *             Lança essa exceção se a quantidade for maior que a quantidade
	 *             do produto no {@link Cart} ou se a quantidade for menor do
	 *             que 1.
	 * @return RETORNO AINDA NÃO DEFINIDO!!!
	 */
	public String removeFromCart(String code, int quantity)
			throws ProductNotInCartException, InvalidQuantityException {

		if (quantity < 1) {
			throw new InvalidQuantityException(
					"A quantidade a ser removida dever ser maior do que 1!!!");
		}

		Cart cart = getCart();

		for (OrderItem o : cart.getOrderItens()) {

			if (o.getProduct().getCode().equals(code)) {
				if (quantity > o.getQuantity()) {
					throw new InvalidQuantityException(
							"Quantidade informada maior do que a do carrinho!!! | Cart: "
									+ o.getProduct().getName() + " "
									+ o.getQuantity());
				}
				o.setQuantity(o.getQuantity() - quantity);
				cartRepository.save(cart);
				return "Produto removido com sucesso!!!";
			}

		}
		return "Produto não encontrado!!!";
	}

	/**
	 * Método que remove todos os {@link Product} do {@link Cart} que tiverem o
	 * mesmo código.
	 * 
	 * @param code
	 *            código do {@link Product} a ser removido do {@link Cart}.
	 * @throws ProductNotInCartException
	 *             Lança essa exceção se o {@link Product} não for encontrado no
	 *             {@link Cart}.
	 * @return RETORNO AINDA NÃO DEFINIDO!!!
	 */

	public String removeFromCart(String code) throws ProductNotInCartException {

		Cart cart = getCart();
		for (OrderItem o : cart.getOrderItens()) {
			if (o.getProduct().getCode().equals(code)) {
				cart.getOrderItens().remove(o);
				cartRepository.save(cart);
				return "Produto removido com sucesso!!!";
			}
		}
		throw new ProductNotInCartException("Produto n�o existe no carrinho!!!");
	}

	/**
	 * Método que limpa todos os {@link Product} do {@link Cart}.
	 * 
	 * @return RETORNO AINDA NÃO DEFINIDO!!!
	 */
	public boolean clearCart() {
		Cart cart = getCart();
		cart.getOrderItens().clear();
		cartRepository.save(cart);
		return true;
	}

	/**
	 * Método que mostra todos os {@link Product} presentes no {@link Cart}.
	 * 
	 * @return RETORNO AINDA NÃO DEFINIDO!!!
	 */
	public String showCart() {

		Cart cart = getCart();

		String result = "";
		for (OrderItem o : cart.getOrderItens()) {
			result = result.concat("PRODUTO: " + o.getProduct().getName()
					+ " | QUANTIDADE: " + o.getQuantity() + "\n");
		}
		return result;

	}

	/**
	 * Cancela o {@link Cart} do {@link User} atual.
	 * 
	 * @return ainda não definido
	 */
	public boolean cancelCart() {
		Cart cart = getCart();
		cart.setStatus(Status.CANCELED);
		cartRepository.save(cart);
		return true;
	}

	/**
	 * Método que faz a soma de todos os {@link Product} no {@link Cart}.
	 * 
	 * @return retorna a soma dos {@link Product} no {@link Cart}.
	 */
	public Double getTotal() {
		Cart cart = getCart();
		double result = 0;
		for (OrderItem o : cart.getOrderItens()) {
			result = result + o.getProduct().getPrice() * o.getQuantity();
		}
		return result;

	}

	/**
	 * Método chamado para finalizar a compra.
	 * 
	 * @return
	 * @throws EmptyCartException
	 *             lançada quando o checkout é chamado para um {@link Cart}
	 *             vazio.
	 */

	@Transactional
	public String checkOut() throws EmptyCartException {
		Cart cart = getCart();
		if (cart.getOrderItens().size() == 0)
			throw new EmptyCartException("Carrinho vazio!!!");

		String result = "";
		int count = 0;
		double total = 0;

		for (OrderItem o : cart.getOrderItens()) {
			if (o.getQuantity() <= stockRepository.findByProductCode(
					o.getProduct().getCode()).getQuantity()) {
				count = count + 1;
			}
		}

		if (cart.getOrderItens().size() == count) {
			result = result
					.concat("PRODUTO | PREÇO | QUANTIDADE | TOTAL(R$) \n\n");

			for (OrderItem o : cart.getOrderItens()) {
				ProductQuantity p = stockRepository.findByProductCode(o
						.getProduct().getCode());
				p.setQuantity(p.getQuantity() - o.getQuantity());
				stockRepository.save(p);
				result = result.concat(o.getProduct().getName() + " | "
						+ o.getProduct().getPrice() + " | " + o.getQuantity()
						+ " | " + o.getProduct().getPrice() * o.getQuantity()
						+ "\n");
				total = total + o.getProduct().getPrice() * o.getQuantity();
			}
			result = result.concat("\nTOTAL DA COMPRA: R$ " + total);
			cart.setStatus(Status.COMPLETED);
			cartRepository.save(cart);
		} else {
			result = result.concat("Não foi possível concluir a compra!!!");
		}

		return result;
	}
}
