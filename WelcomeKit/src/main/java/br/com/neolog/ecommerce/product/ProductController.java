package br.com.neolog.ecommerce.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "product" )
public class ProductController
{
    @Autowired
    private ProductService service;

    @GetMapping( value = "list" )
    public ResponseEntity<List<Product>> getProducts()
    {
        return ResponseEntity.ok( service.getProducts() );
    }

    @GetMapping( value = "list/price/greater/{price}" )
    public ResponseEntity<List<Product>> getProductsGreatherThan(
        @PathVariable( "price" ) final double price )
    {
        return ResponseEntity.ok( service.findByPriceGreaterThan( price ) );

    }

    @GetMapping( value = "list/price/lesser/{price}" )
    public ResponseEntity<List<Product>> getProductsLesserThan(
        @PathVariable( "price" ) final double price )
    {
        return ResponseEntity.ok( service.findByPrieceLessThan( price ) );
    }

    @GetMapping( value = "search/category/{code}" )
    public ResponseEntity<List<Product>> getProductsByCategory(
        @PathVariable( "code" ) final int code )
    {
        return ResponseEntity.ok( service.getProductsByCategoryCode( code ) );
    }

    @GetMapping( value = "search/code/{code}" )
    public ResponseEntity<Product> getProductByCode(
        @PathVariable( "code" ) final int code )
    {
        return ResponseEntity.ok( service.getProductByCode( code ) );
    }

    @GetMapping( value = "search/id/{id}" )
    public ResponseEntity<Product> getProductById(
        @PathVariable( "id" ) final int id )
    {
        return ResponseEntity.ok( service.getProductById( id ) );
    }

    @GetMapping( value = "search/contains" )
    public ResponseEntity<List<Product>> getProductByWord(
        @RequestParam( value = "word" ) final String word )
    {
        return ResponseEntity.ok( service.getProductsByContainingWord( word ) );
    }

    @PostMapping( value = "save" )
    public ResponseEntity<Product> getProductSave(
        @Valid @RequestBody final Product prod )
    {
        return ResponseEntity.ok( service.save( prod ) );
    }

    @PostMapping( value = "update" )
    public ResponseEntity<Product> getProductUpdate(
        @RequestBody @Valid final Product prod )
    {
        return ResponseEntity.ok( service.update( prod ) );
    }

    @PostMapping( value = "delete/{id}" )
    public ResponseEntity<Boolean> delete(
        @Valid @PathVariable( "id" ) final int id )
    {
        return ResponseEntity.ok( service.delete( id ) );
    }

}
