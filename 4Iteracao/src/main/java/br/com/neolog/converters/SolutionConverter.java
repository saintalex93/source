package br.com.neolog.converters;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.PresentationClass;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.Solution;
import br.com.neolog.repository.ProductRepository;

/**
 * Classe responsável por criar um resultado que será devolvido com base no
 * Solution recebido como parâmetro.
 * 
 * @author igor.kurman
 * 
 */
@Component
public class SolutionConverter {
	@Autowired
	private ProductRepository productRepository;

	/**
	 * 
	 * @param solution
	 *            contém o Set de HolderCodePrice usado para gerar o Map que
	 *            será retornado para o usuário.
	 * @return o Map com todos os produtos e quantidades fornecidos pela
	 *         Solution.
	 */
	public PresentationClass convert(Solution solution) {

		HashMap<Product, Integer> result = new HashMap<Product, Integer>();
		PresentationClass presentation = new PresentationClass();

		if (solution.getProducts() == null) {
			// return result;
			return presentation;
		}

		HashSet<String> codes = new HashSet<String>();

		for (HolderCodePrice holder : solution.getProducts()) {
			codes.add(holder.getCode());
		}

		Iterable<Product> products = productRepository.findByCodeIn(codes);

		HashMap<String, Product> codeProductMap = new HashMap<String, Product>();

		for (Product p : products) {
			codeProductMap.put(p.getCode(), p);
		}

		double totalValue = 0;

		for (HolderCodePrice holder : solution.getProducts()) {
			if (result.containsKey(codeProductMap.get(holder.getCode()))) {

				result.put(codeProductMap.get(holder.getCode()),
						result.get(codeProductMap.get(holder.getCode())) + 1);
				totalValue = totalValue
						+ codeProductMap.get(holder.getCode()).getPrice();

			} else {
				result.put(codeProductMap.get(holder.getCode()), 1);
				totalValue = totalValue
						+ codeProductMap.get(holder.getCode()).getPrice();
			}
		}

		presentation.setProductQuantity(result);
		presentation.setTotalValue(totalValue);
		// return result;
		return presentation;

	}
}
