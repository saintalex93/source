package br.com.neolog.ecommerce.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
    extends
        JpaRepository<Product,Integer>
{

    Product findByCode(
        final int code );

    Product findById(
        final int id );

    List<Product> findByCategoryCode(
        final int categoryCode );

    List<Product> findByCategoryId(
        final int categoryId );

    List<Product> findByPriceGreaterThan(
        long price );

    List<Product> findByPriceLessThan(
        long price );

    List<Product> findByNameContainingIgnoreCase(
        final String name );
}
