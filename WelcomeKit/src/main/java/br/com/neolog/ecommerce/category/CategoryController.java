package br.com.neolog.ecommerce.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "category" )
public class CategoryController
{
    @Autowired
    private CategoryService service;

    @GetMapping( value = "search/all" )
    public ResponseEntity<List<Category>> getCategories()
    {
        return new ResponseEntity<List<Category>>( service.getCategories(), HttpStatus.OK );
    }

    @GetMapping( value = "search/{code}" )
    public ResponseEntity<Category> getCategory(
        @PathVariable( "code" ) final int code )
    {
        return new ResponseEntity<Category>( service.getCategoryByCode( code ), HttpStatus.OK );
    }

    @GetMapping( value = "search" )
    public ResponseEntity<Category> getCategoryName(
        @RequestParam( value = "name" ) final String name )
    {
        final Category category = service.getCategoryByName( name );
        if( category == null ) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Category>( service.getCategoryByName( name ), HttpStatus.OK );
    }

    @GetMapping( value = "search/contains" )
    public ResponseEntity<List<Category>> getCategoriesByWord(
        @RequestParam( value = "word" ) final String word )
    {
        final List<Category> categoryList = service.getCategoriesByWord( word );
        if( categoryList.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<List<Category>>( categoryList, HttpStatus.OK );
    }

    @PostMapping( value = "save" )
    public ResponseEntity<Category> save(
        @RequestBody final Category category )
    {
        return new ResponseEntity<Category>( service.save( category ), HttpStatus.OK );
    }

    @PostMapping( value = "update" )
    public ResponseEntity<Category> update(
        @RequestBody final Category category )
    {
        return new ResponseEntity<Category>( service.update( category ), HttpStatus.OK );
    }

    @PostMapping( value = "delete/{id}" )
    public ResponseEntity<Boolean> delete(
        @PathVariable( "id" ) final int id )
    {
        return new ResponseEntity<Boolean>( service.delete( id ), HttpStatus.OK );
    }

}
