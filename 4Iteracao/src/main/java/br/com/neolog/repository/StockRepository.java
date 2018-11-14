package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.ProductQuantity;

public interface StockRepository extends
		CrudRepository<ProductQuantity, Serializable> {

	public ProductQuantity findByProductCode(String code);

	public Iterable<ProductQuantity> findByProductPriceLessThanEqual(
			double price);

	public void deleteByProductCode(String code);

}