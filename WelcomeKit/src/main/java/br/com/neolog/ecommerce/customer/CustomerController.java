package br.com.neolog.ecommerce.customer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<List<Customer>>( service.getAll(), HttpStatus.OK );
    }

    @GetMapping( value = "search/{id}" )
    public ResponseEntity<Customer> getById(
        @PathVariable( "id" ) final int id )
    {
        return new ResponseEntity<Customer>( service.getById( id ), HttpStatus.OK );
    }

    @PostMapping( value = "save" )
    public ResponseEntity<Customer> save(
        @RequestBody @Valid final Customer customer )
    {
        return new ResponseEntity<Customer>( service.save( customer ), HttpStatus.OK );
    }

}
