package br.com.neolog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.pojo.Stock;

public interface StockRepository
    extends
        JpaRepository<Stock,Integer>
{

    public Stock findByProductCode(
        String code );

    public Iterable<Stock> findByProductPriceLessThanEqual(
        double price );

    public void deleteByProductCode(
        String code );

}