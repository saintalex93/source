package br.com.neolog.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.neolog.pojo.Category;

public interface CategoryRepository extends
		CrudRepository<Category, Serializable> {

	public Category findByCode(String code);

}