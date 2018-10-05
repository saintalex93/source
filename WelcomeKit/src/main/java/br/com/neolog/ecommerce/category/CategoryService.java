package br.com.neolog.ecommerce.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.exceptions.CategoryDuplicatedCodeException;
import br.com.neolog.ecommerce.exceptions.CategoryDuplicatedNameException;
import br.com.neolog.ecommerce.exceptions.CategoryFillException;
import br.com.neolog.ecommerce.exceptions.CategoryNotFoundException;
import br.com.neolog.ecommerce.exceptions.CategoryRemoveWithProductException;
import br.com.neolog.ecommerce.product.ProductRepository;

@Component
public class CategoryService {

	@Autowired
	CategoryRepository repository;

	@Autowired
	ProductRepository productRepository;

	public List<Category> getCategories() {
		return repository.findAll();
	}

	public Category getCategoryById(final int id) {
		final Category category = repository.findById(id);
		if (category == null) {
			throw new CategoryNotFoundException();
		}
		return category;
	}

	public Category getCategoryByCode(final int code) {
		final Category category = repository.findByCode(code);
		if (category == null) {
			throw new CategoryNotFoundException();
		}
		return category;
	}

	public Category getCategoryByName(final String name) {
		return repository.findByName(name);
	}

	public List<Category> getCategoriesByWord(final String word) {
		return repository.findByNameContainingIgnoreCase(word);
	}

	public Category save(final Category cat) {
		if (cat.getCode() == null) {
			throw new CategoryFillException("O c�digo da categoria n�o foi preenchido");
		}
		if (repository.findByCode(cat.getCode()) != null) {
			throw new CategoryDuplicatedCodeException();
		}
		if (repository.findByName(cat.getName()) != null) {
			throw new CategoryDuplicatedNameException();
		}
		return repository.save(cat);
	}

	public Category update(final Category cat) {
		if (cat.getId() == null) {
			throw new CategoryFillException("O ID da categoria a ser exclu�da n�o foi informado");
		}
		if (cat.getCode() == null) {
			throw new CategoryFillException("O c�digo da categoria n�o foi preenchido");
		}
		if (!repository.existsById(cat.getId())) {
			throw new CategoryNotFoundException();
		}
		final Category categoryDB = getCategoryByCode(cat.getCode());
		if (categoryDB == null) {
			return repository.save(categoryDB);
		}
		if (categoryDB.getId() != cat.getId()) {
			throw new CategoryDuplicatedCodeException();
		}

		return repository.save(cat);
	}

	public boolean delete(final int id) {
		if (!repository.existsById(id)) {
			throw new CategoryNotFoundException();
		}
		if (productRepository.findByCategoryCode(id) != null) {
			throw new CategoryRemoveWithProductException();
		}
		repository.deleteById(id);
		return !repository.existsById(id);

	}

}
