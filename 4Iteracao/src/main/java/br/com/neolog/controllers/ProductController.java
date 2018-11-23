package br.com.neolog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.CategoryNotFoundException;
import br.com.neolog.exceptions.ProductAlreadyExistsException;
import br.com.neolog.exceptions.ProductInStockException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.models.Product;
import br.com.neolog.services.ProductService;

@RestController
@RequestMapping( value = "product" )
public class ProductController
{
    private final ProductService productService;

    @Autowired
    public ProductController(
        final ProductService productService )
    {
        this.productService = productService;
    }

    @PostMapping( value = "add" )
    public ResponseEntity<Product> add(
        @RequestBody final Product product )
        throws ProductAlreadyExistsException,
            CategoryNotFoundException
    {
        return ResponseEntity.ok( productService.add( product ) );
    }

    @PostMapping( value = "remove/{code}" )
    public ResponseEntity<String> remove(
        @PathVariable( "code" ) final String code )
        throws ProductNotFoundException,
            ProductInStockException
    {

        return ResponseEntity.ok( productService.remove( code ) );
    }

    @PostMapping( value = "update" )
    public ResponseEntity<Product> update(
        @RequestBody final Product product )
        throws ProductNotFoundException,
            CategoryNotFoundException
    {
        return ResponseEntity.ok( productService.update( product ) );

    }

    @GetMapping( value = "find/{code}" )
    public ResponseEntity<Product> find(
        @PathVariable( "code" ) final String code )
        throws ProductNotFoundException
    {
        return ResponseEntity.ok( productService.find( code ) );
    }

    @GetMapping( value = "all" )
    public ResponseEntity<List<Product>> findAll()
    {
        return ResponseEntity.ok( productService.findAll() );
    }

}
