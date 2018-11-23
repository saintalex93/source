package br.com.neolog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.models.Stock;

public interface StockRepository
    extends
        JpaRepository<Stock,Integer>
{

    Stock findByProductCode(
        String code );

    List<Stock> findByProductPriceLessThanEqual(
        long price );

    List<Stock> findByProductVolumeLessThanEqual(
        long volume );

    List<Stock> findByProductWeightLessThanEqual(
        long weight );

    void deleteByProductCode(
        String code );

}