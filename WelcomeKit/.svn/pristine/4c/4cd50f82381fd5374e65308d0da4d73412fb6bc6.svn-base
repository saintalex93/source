package br.com.neolog.ecommerce.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryService {

	@Autowired
	CategoryRepository repository;

	public List<Category> getCategories() {
		return repository.findAll();
	}

	public Category getCategory(final int cod) {
		return repository.findByCod(cod);
	}

	public Category getCategoryByCode(final int cod) {
		return repository.findByCod(cod);
	}

	public Category getCategoryByName(final String name) {
		return repository.findByName(name);
	}

	public List<Category> getCategoriesByWord(final String word) {
		return repository.findByNameContainingIgnoreCase(word);
	}

	public Category save(final Category cat) {
		try {
			return repository.save(cat);
		} catch (final Exception e) {
			throw new IllegalArgumentException("Código Duplicado");
		}

	}

	public boolean delete(final int id) {
		repository.deleteById(id);
		return !repository.existsById(id);

	}

}
