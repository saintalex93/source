package br.com.neolog.ecommerce.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockService {

	@Autowired
	StockRepository repository;

	public List<Stock> getStock() {
		return repository.findAll();
	}

	// public Product getProductByCode(final int cod) {
	// return repository.findByCod(cod);
	// }
	//
	// public Product getProductById(final int id) {
	// return repository.findById(id);
	// }
	//
	//
	// public Product save(final Product p) {
	// try {
	// return repository.save(p);
	// } catch (final Exception e) {
	// throw new IllegalArgumentException("Código Duplicado");
	// }
	//
	// }
	//
	// public boolean delete(final int id) {
	// repository.deleteById(id);
	// return !repository.existsById(id);
	//
	// }

}
