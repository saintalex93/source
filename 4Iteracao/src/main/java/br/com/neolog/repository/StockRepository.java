package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Stock;

public interface StockRepository extends
		CrudRepository<Stock, Serializable> {

	public Stock findByProductCode(String code);

	public Iterable<Stock> findByProductPriceLessThanEqual(
			double price);

	public void deleteByProductCode(String code);

}