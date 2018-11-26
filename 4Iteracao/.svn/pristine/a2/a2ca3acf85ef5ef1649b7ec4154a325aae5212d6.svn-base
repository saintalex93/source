package br.com.neolog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.models.Category;

public interface CategoryRepository
    extends
        JpaRepository<Category,Integer>
{

    Category findByCode(
        String code );

    @Override
    List<Category> findAll();

}