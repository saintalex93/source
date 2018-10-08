package br.com.neolog.ecommerce.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.neolog.ecommerce.product.Product;

@Repository
public interface StockRepository
    extends
        JpaRepository<Stock,Integer>
{

    List<Stock> findByQuantityGreaterThan(
        int quantity );

    List<Stock> findByQuantityLessThan(
        int quantity );

    @Query( "SELECT s FROM Stock s WHERE s.quantity = (SELECT MAX(z.quantity) FROM Stock z)" )
    Stock maxProduct();

    @Query( "SELECT s FROM Stock s WHERE s.quantity = (SELECT MIN(z.quantity) FROM Stock z)" )
    Stock minProduct();

    Stock findByProduct(
        Product p );

    Stock findByProductId(
        int id );

    Stock findById(
        int id );

}
