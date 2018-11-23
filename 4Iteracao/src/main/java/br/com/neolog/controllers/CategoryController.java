package br.com.neolog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.CategoryAlreadyExistsException;
import br.com.neolog.exceptions.CategoryNotFoundException;
import br.com.neolog.models.Category;
import br.com.neolog.services.CategoryService;

@RestController
@RequestMapping( value = "category" )
public class CategoryController
{
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(
        final CategoryService categoryService )
    {
        this.categoryService = categoryService;
    }

    @PostMapping( value = "add" )
    public ResponseEntity<Category> addCategory(
        @RequestBody final Category category )
        throws CategoryAlreadyExistsException
    {
        return ResponseEntity.ok( categoryService.add( category ) );
    }

    @PostMapping( value = "remove" )
    public String removeCategory(
        @RequestParam( value = "code" ) final String code )
        throws CategoryNotFoundException
    {
        // Tratar se algum produto usa essa categoria
        categoryService.remove( code );
        return "Categoria removida com sucesso!";
    }

    @PostMapping( value = "update" )
    public ResponseEntity<Category> updateCategory(
        @RequestBody final Category category )
        throws CategoryNotFoundException
    {
        return ResponseEntity.ok( categoryService.update( category ) );
    }

    @GetMapping( value = "find" )
    public ResponseEntity<Category> updateCategory(
        @RequestParam( value = "code" ) final String code )
        throws CategoryNotFoundException
    {
        final Category category = categoryService.find( code );
        return ResponseEntity.ok( category );
    }

    @GetMapping( value = "all" )
    public ResponseEntity<List<Category>> findAll()
    {
        return ResponseEntity.ok( categoryService.findAll() );

    }

}
