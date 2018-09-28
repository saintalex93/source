package br.com.neolog.ecommerce.product;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {

	@Autowired
	ProductRepository repository;

	public Product banana() {
		return repository.findByCod(10001);
	}

}
