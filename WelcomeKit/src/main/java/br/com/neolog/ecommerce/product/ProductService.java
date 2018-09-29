package br.com.neolog.ecommerce.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

	@Autowired
	ProductRepository repository;

	public Product getProductByCode(final int cod) {
		return repository.findByCod(cod);
	}

}
