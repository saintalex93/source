package br.com.neolog.repository;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Product;

public interface ProductRepository extends
		CrudRepository<Product, Serializable> {

	public Product findByCode(String code);

	public Iterable<Product> findByCodeIn(Collection<String> codes);

}