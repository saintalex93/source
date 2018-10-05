package br.com.neolog.ecommerce.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByCode(final int code);

	Product findByName(final String name);

	boolean existsByCode(final int code);

	Product findById(final int id);

	List<Product> findByCategoryCode(final int cat);

	List<Product> findByPriceGreaterThan(double price);

	List<Product> findByPriceLessThan(double price);

	List<Product> findByNameContainingIgnoreCase(final String name);
}

// @Query("SELECT p FROM Product p WHERE COD = :cod")
// Product findByCod(/* @Param("cod") */ final int cod);