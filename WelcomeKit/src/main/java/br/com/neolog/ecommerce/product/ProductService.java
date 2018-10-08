package br.com.neolog.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.category.CategoryService;
import br.com.neolog.ecommerce.exceptions.CategoryFillException;
import br.com.neolog.ecommerce.exceptions.CategoryNotFoundException;
import br.com.neolog.ecommerce.exceptions.ProductDuplicatedCodeException;
import br.com.neolog.ecommerce.exceptions.ProductNotFoundException;
import br.com.neolog.ecommerce.exceptions.StockProductRemoveWithQuantityException;
import br.com.neolog.ecommerce.stock.Stock;
import br.com.neolog.ecommerce.stock.StockRepository;

@Component
public class ProductService
{

    @Autowired
    ProductRepository repository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CategoryService categoryService;

    public Product getProductByCode(
        final int cod )
    {
        final Product product = repository.findByCode( cod );
        if( product == null ) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    public Product getProductById(
        final int id )
    {
        final Product product = repository.findById( id );
        if( product == null ) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    public List<Product> getProducts()
    {
        return repository.findAll();
    }

    public List<Product> getProductsByCategoryId(
        final int cat )
    {
        if( categoryService.getCategoryById( cat ) == null ) {
            throw new CategoryNotFoundException();
        }
        return repository.findByCategoryCode( cat );
    }

    public List<Product> findByPriceGreaterThan(
        final double price )
    {
        return repository.findByPriceGreaterThan( price );
    }

    public List<Product> findByPrieceLessThan(
        final double price )
    {
        return repository.findByPriceLessThan( price );
    }

    public List<Product> getProductsByWord(
        final String word )
    {
        return repository.findByNameContainingIgnoreCase( word );
    }

    public Product save(
        final Product p )
    {
        if( p.getCategory().getCode() == null ) {
            throw new CategoryFillException( "A Categoria precisa ter um código" );
        }
        if( p.getCategory().getId() == null ) {
            throw new CategoryFillException( "A categoria precisa ter um ID" );
        }
        if( categoryService.getCategoryById( p.getCategory().getId() ) == null ) {
            throw new CategoryNotFoundException();
        }
        if( repository.findByCode( p.getCode() ) != null ) {
            throw new ProductDuplicatedCodeException();
        }
        return repository.save( p );
    }

    public Product update(
        final Product p )
    {
        if( ! repository.existsById( p.getId() ) ) {
            throw new ProductNotFoundException();
        }
        if( p.getCategory().getId() == null ) {
            throw new CategoryFillException( "O ID Categoria não foi informada." );
        }
        if( categoryService.getCategoryById( p.getCategory().getId() ) == null ) {
            throw new CategoryNotFoundException();
        }

        final Product productDB = getProductById( p.getId() );
        if( productDB == null ) {
            return repository.save( p );
        }
        if( productDB.getId() != p.getId() ) {
            throw new ProductDuplicatedCodeException();
        }
        return repository.save( p );
    }

    public boolean delete(
        final int id )
    {
        if( getProductById( id ) == null ) {
            throw new ProductNotFoundException();
        }

        final Stock stock = stockRepository.findByProductId( id );

        if( stock.getQuantity() > 0 ) {
            throw new StockProductRemoveWithQuantityException();
        }

        stockRepository.delete( stock );
        repository.deleteById( id );
        return ! repository.existsById( id );
    }

}
