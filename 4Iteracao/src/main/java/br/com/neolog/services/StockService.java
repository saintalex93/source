package br.com.neolog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.exceptions.InvalidQuantityException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.Stock;
import br.com.neolog.repository.CategoryRepository;
import br.com.neolog.repository.ProductRepository;
import br.com.neolog.repository.StockRepository;

@Component
public class StockService {

	@Autowired
	public StockRepository stockRepository;

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	public ProductRepository productRepository;

	/**
	 * 
	 * @param productCode
	 *            (código do produto) a ser adicionado ao estoque.
	 * @param quantity
	 *            (quantidade) do {@link Product} a ser adicionado.
	 * @throws InvalidQuantityException
	 *             essa exceçãoo é lançada caso o valor para a {@code quantity}
	 *             seja menor do que 1.
	 * 
	 * @throws ProductNotFoundException
	 *             essa exceção é lançada quando o código informado não retona
	 *             nenhum {@link Product} da base de dados.
	 */

	@Transactional
	public void addToStock(String productCode, Integer quantity)
			throws InvalidQuantityException, ProductNotFoundException {

		if (quantity < 1) {

			throw new InvalidQuantityException(
					"quantidade deve ser um n�mero positivo!!!");
		}
		Product product = productRepository.findByCode(productCode);

		if (product == null) {
			throw new ProductNotFoundException("Produto não encontrado!!!");

		}
		Stock productQuantity = stockRepository
				.findByProductCode(productCode);

		if (productQuantity == null) {
			productQuantity = new Stock();
			productQuantity.setProduct(product);
			productQuantity.setQuantity(quantity);
			stockRepository.save(productQuantity);
		} else {
			int databaseQuantity = productQuantity.getQuantity();
			productQuantity.setQuantity(databaseQuantity + quantity);
			stockRepository.save(productQuantity);
		}

	}

	/**
	 * 
	 * @param code
	 *            do {@link Product} a ser removido.
	 * @param quantity
	 *            a ser removida do estoque.
	 * @throws ProductNotFoundException
	 *             lançada quando nenhum produto é encontrado com o código
	 *             recebido.
	 * @throws InvalidQuantityException
	 *             lançada quando a quantidade passada como argumento for menor
	 *             do que 1.
	 * @return
	 */

	@Transactional
	public String removeFromStock(String code, Integer quantity)
			throws ProductNotFoundException, InvalidQuantityException {

		if (quantity < 1) {

			throw new InvalidQuantityException(
					"Quantidade deve ser um número positivo!!!");
		}

		Stock h = stockRepository.findByProductCode(code);
		if (h == null) {
			return "Produto não existe na base de dados!!!";
		}
		if (h.getQuantity() < quantity) {
			throw new InvalidQuantityException(
					"Não foi possível remover, ESTOQUE:" + h.getQuantity());
		}
		h.setQuantity(h.getQuantity() - quantity);
		stockRepository.save(h);
		return "Foram removidas " + quantity + " unidades de "
				+ h.getProduct().getName() + " | ESTOQUE: " + h.getQuantity();

	}

	/**
	 * 
	 * @param code
	 *            do {@link Product} a ser removido do estoque (
	 *            {@link Stock}).
	 * @throws ProductNotFoundException
	 *             lançada quando nenhum produto é encontrado com o código
	 *             recebido.
	 * @return
	 */
	public String removeFromStock(String code) throws ProductNotFoundException {

		Stock result = stockRepository.findByProductCode(code);

		if (result == null) {
			throw new ProductNotFoundException("Produto n�o encontrado!!!");
		}
		stockRepository.delete(result);
		return "Produto " + result.getProduct() + " removido do estoque!";

	}

	/**
	 * 
	 * @param code
	 *            do {@link Product} para que seja mostrada a sua quantidade.
	 * @throws ProductNotFoundException
	 *             lançada se o {@code code} passado como argumento não for
	 *             encontrado no estoque.
	 * 
	 * @return
	 */
	public String showProductQuantity(String code)
			throws ProductNotFoundException {

		if (stockRepository.findByProductCode(code) == null) {
			throw new ProductNotFoundException("Produto n�o encontrado!!!");
		}
		return "Produto: "
				+ stockRepository.findByProductCode(code).getProduct()
						.getName() + " | Quantidade em estoque: "
				+ stockRepository.findByProductCode(code).getQuantity();
	}

	/**
	 * 
	 * @return
	 */
	public String showStock() {
		String result = "";

		for (Stock p : stockRepository.findAll())
			result = result.concat("COD: " + p.getProduct().getCode()
					+ " | Produto: " + p.getProduct().getName()
					+ " | Quantidade: " + p.getQuantity() + "\n\n");

		return "STOCK \n\n" + result;

	}

}
