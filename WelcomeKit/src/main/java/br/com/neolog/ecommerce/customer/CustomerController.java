package br.com.neolog.ecommerce.customer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "customer" )
public class CustomerController
{
    @Autowired
    private CustomerService service;

    @GetMapping( value = "search" )
    public ResponseEntity<List<Customer>> getAll()
    {
        return ResponseEntity.ok( service.getAll() );
    }

    @GetMapping( value = "search/{id}" )
    public ResponseEntity<Customer> getById(
        @PathVariable( "id" ) final int id )
    {
        return ResponseEntity.ok( service.getById( id ) );
    }

    @PostMapping( value = "save" )
    public ResponseEntity<Customer> save(
        @RequestBody @Valid final Customer customer )
    {
        return ResponseEntity.ok( service.save( customer ) );
    }

    @PostMapping( value = "update" )
    public ResponseEntity<Customer> update(
        @RequestBody @Valid final Customer customer )
    {
        return ResponseEntity.ok( service.update( customer ) );
    }

    @PostMapping( value = "inactive" )
    public ResponseEntity<Customer> inactive()
    {
        return ResponseEntity.ok( service.inactiveUser() );

    }

}
