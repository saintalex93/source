package br.com.neolog.ecommerce.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

	// Category findByCod(final int cod);
	//
	// Category findById(final int id);
	//
	// Category findByName(final String name);
	//
	// List<Category> findByNameContainingIgnoreCase(final String name);

}
