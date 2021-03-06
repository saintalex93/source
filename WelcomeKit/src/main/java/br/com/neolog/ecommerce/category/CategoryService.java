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
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> getCategories()
    {
        return categoryRepository.findAll();
    }

    public Category getCategoryByCode(
        final int code )
    {
        final Category category = categoryRepository.findByCode( code );
        if( category == null ) {
            throw new CategoryNotFoundException();
        }
        return category;
    }

    public Category getCategoryByName(
        final String name )
    {
        return categoryRepository.findByName( name );
    }

    public List<Category> getCategoriesByWord(
        final String word )
    {
        return categoryRepository.findByNameContainingIgnoreCase( word );
    }

    public Category save(
        final Category category )
    {
        if( category.getCode() == null ) {
            throw new CategoryFillException( "O c�digo da categoria n�o foi preenchido" );
        }
        if( categoryRepository.findByCode( category.getCode() ) != null ) {
            throw new CategoryDuplicatedCodeException();
        }
        if( categoryRepository.findByName( category.getName() ) != null ) {
            throw new CategoryDuplicatedNameException();
        }
        return categoryRepository.save( category );
    }

    public Category update(
        final Category category )
    {
        if( category.getId() == null ) {
            throw new CategoryFillException( "O ID da categoria a ser atualizada n�o foi informado" );
        }
        if( category.getCode() == null ) {
            throw new CategoryFillException( "O c�digo da categoria n�o foi preenchido" );
        }
        if( ! categoryRepository.existsById( category.getId() ) ) {
            throw new CategoryNotFoundException();
        }
        final Category currentCategory = categoryRepository.findByCode( category.getCode() );
        if( currentCategory != null && currentCategory.getId() != category.getId() ) {
            throw new CategoryDuplicatedCodeException();
        }
        return categoryRepository.save( category );
    }

    public boolean delete(
        final int id )
    {
        if( ! categoryRepository.existsById( id ) ) {
            throw new CategoryNotFoundException();
        }
        if( ! productRepository.findByCategoryId( id ).isEmpty() ) {
            throw new CategoryRemoveWithProductException();
        }
        categoryRepository.deleteById( id );
        return ! categoryRepository.existsById( id );
    }

    public void existsCategoryByCode(
        final int categoryCode )
    {
        final Category category = categoryRepository.findByCode( categoryCode );
        if( category == null ) {
            throw new CategoryNotFoundException();
        }

    }

}
