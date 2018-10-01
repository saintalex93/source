package br.com.neolog.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByCod(final int cod);

	Product findById(final int id);

}

// @Query("SELECT p FROM Product p WHERE COD = :cod")
// Product findByCod(/* @Param("cod") */ final int cod);