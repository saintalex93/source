package br.com.neolog.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.stock.StockRepository;

@Component
public class ProductService {

	@Autowired
	ProductRepository repository;

	@Autowired
	StockRepository stockRepository;

	public Product getProductByCode(final int cod) {
		return repository.findByCod(cod);
	}

	public Product getProductById(final int id) {
		return repository.findById(id);
	}

	public List<Product> getProducts() {
		return repository.findAll();
	}

	public Product save(final Product p) {

		if (p.getCategory() == null) {
			throw new IllegalArgumentException();
		}

		return repository.save(p);

		// try {
		//
		// return repository.save(p);
		// } catch (final Exception e) {
		// throw new IllegalArgumentException("C�digo Duplicado");
		// }

	}

	public boolean delete(final int id) {
		repository.deleteById(id);
		return !repository.existsById(id);

	}

	public List<Product> getProductsByCategoryId(final int cat) {
		return repository.findByCategoryCod(cat);
	}

	public List<Product> findByPriceGreaterThan(final double price) {
		return repository.findByPriceGreaterThan(price);
	}

	public List<Product> findByPrieceLessThan(final double price) {
		return repository.findByPriceLessThan(price);
	}

}
