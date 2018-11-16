package br.com.neolog.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Stock;
import br.com.neolog.repository.StockRepository;

/**
 * Essa classe é responsável por receber um valor (target) e criar um
 * {@link Problem} a partir desse valor.
 * 
 * @author igor.kurman
 * 
 */
@Component
public class ProblemConverter {

	@Autowired
	private StockRepository stockRepository;

	/**
	 * 
	 * @param target
	 *            valor base para a criação do {@link Problem}.
	 * @return o Problem criado com base no target recebido como parâmetro, o
	 *         {@link Problem} conta com um Set interno de
	 *         {@link HolderCodePrice} contendo somente produtos com valor
	 *         abaixo do target informado.
	 */
	public Problem convert(double target) {

		Iterable<Stock> products = stockRepository
				.findByProductPriceLessThanEqual(target);

		Problem problem = new Problem();

		Set<HolderCodePrice> set = new HashSet<>();

		double currentValue = 0;

		for (Stock p : products) {
			int quantity = p.getQuantity();
			while (p.getProduct().getPrice() + currentValue <= target
					&& quantity > 0) {
				currentValue = currentValue + p.getProduct().getPrice();
				HolderCodePrice holderCodePrice = new HolderCodePrice();
				holderCodePrice.setCode(p.getProduct().getCode());
				holderCodePrice.setPrice(p.getProduct().getPrice());
				set.add(holderCodePrice);
				quantity--;
			}

			currentValue = 0;
		}

		problem.setProducts(set);
		problem.setTarget(target);

		return problem;
	}
}
