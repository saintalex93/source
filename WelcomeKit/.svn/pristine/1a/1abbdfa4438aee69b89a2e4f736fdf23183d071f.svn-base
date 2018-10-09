package br.com.neolog.ecommerce.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCode(final int code);

	Category findById(final int id);

	Category findByName(final String name);

	List<Category> findByNameContainingIgnoreCase(final String name);
}
