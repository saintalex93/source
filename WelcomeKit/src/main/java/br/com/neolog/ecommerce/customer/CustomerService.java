package br.com.neolog.ecommerce.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.exceptions.CustomerInactiveException;
import br.com.neolog.ecommerce.exceptions.CustomerNotFoundException;

@Component
public class CustomerService
{
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll()
    {
        return repository.findAll();
    }

    public Customer getById(
        final int id )
    {
        return repository.findById( id );
    }

    public Customer save(
        final Customer customer )
    {
        return repository.save( customer );
    }

    public Customer login(
        final CustomerLogin customerLogin )
    {

        final Customer customer = repository.findByEmailAndPassword( customerLogin.getEmail(), customerLogin.getPassword() );

        if( customer == null ) {
            throw new CustomerNotFoundException();
        }
        if( customer.getInactive() == 1 ) {
            throw new CustomerInactiveException();
        }

        return customer;
    }

}
