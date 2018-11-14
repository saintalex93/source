package br.com.neolog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.exceptions.CategoryAlreadyExistsException;
import br.com.neolog.exceptions.CategoryNotFoundException;
import br.com.neolog.pojo.Category;
import br.com.neolog.repository.CategoryRepository;

@Component
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void add(Category category) throws CategoryAlreadyExistsException {
		Category categoryFromDatabase = categoryRepository.findByCode(category
				.getCode());
		if (categoryFromDatabase == null) {
			categoryRepository.save(category);
		} else {
			throw new CategoryAlreadyExistsException(
					"A categoria informada já existe!!!");
		}

	}

	public void remove(String code) throws CategoryNotFoundException {
		Category category = categoryRepository.findByCode(code);
		if (category == null) {
			throw new CategoryNotFoundException("Categoria não encontrada!!!");
		}
		categoryRepository.delete(category);
	}

	public void update(Category category) throws CategoryNotFoundException {
		Category categoryFromDatabase = categoryRepository.findByCode(category
				.getCode());
		if (categoryFromDatabase != null) {
			category.setId(categoryFromDatabase.getId());
			categoryRepository.save(category);
		} else {
			throw new CategoryNotFoundException(
					"Categoria não existe na base de dados!!!");
		}

	}

	public Category find(String code) throws CategoryNotFoundException {
		Category category = categoryRepository.findByCode(code);
		if (category == null) {
			throw new CategoryNotFoundException("");
		}
		return category;
	}

	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

}
