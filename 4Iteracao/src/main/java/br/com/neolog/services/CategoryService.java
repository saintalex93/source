package br.com.neolog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.exceptions.CategoryAlreadyExistsException;
import br.com.neolog.exceptions.CategoryNotFoundException;
import br.com.neolog.models.Category;
import br.com.neolog.repository.CategoryRepository;

@Component
public class CategoryService
{

    @Autowired
    CategoryRepository categoryRepository;

    public Category add(
        final Category category )
        throws CategoryAlreadyExistsException
    {
        final Category categoryFromDatabase = categoryRepository.findByCode( category
            .getCode() );
        if( categoryFromDatabase == null ) {
            return categoryRepository.save( category );
        } else {
            throw new CategoryAlreadyExistsException(
                "A categoria informada já existe!!!" );
        }

    }

    public void remove(
        final String code )
        throws CategoryNotFoundException
    {
        final Category category = categoryRepository.findByCode( code );
        if( category == null ) {
            throw new CategoryNotFoundException( "Categoria não encontrada!!!" );
        }
        categoryRepository.delete( category );
    }

    public Category update(
        final Category category )
        throws CategoryNotFoundException
    {
        final Category categoryFromDatabase = categoryRepository.findByCode( category
            .getCode() );
        if( categoryFromDatabase != null ) {
            category.setId( categoryFromDatabase.getId() );
            return categoryRepository.save( category );
        } else {
            throw new CategoryNotFoundException(
                "Categoria não existe na base de dados!!!" );
        }
    }

    public Category find(
        final String code )
        throws CategoryNotFoundException
    {
        final Category category = categoryRepository.findByCode( code );
        if( category == null ) {
            throw new CategoryNotFoundException( "" );
        }
        return category;
    }

    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }

}
