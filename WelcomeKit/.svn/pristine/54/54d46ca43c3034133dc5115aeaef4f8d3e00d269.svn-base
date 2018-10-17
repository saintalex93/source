package br.com.neolog.ecommerce.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.authentication.AuthenticationUtils;
import br.com.neolog.ecommerce.exceptions.CustomerDuplicatedEmailException;

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
        final Customer recievedCustomer = repository.findByEmail( customer.getEmail() );

        if( recievedCustomer != null && recievedCustomer.getInactive() == 0 ) {
            throw new CustomerDuplicatedEmailException();
        }
        customer.setPassword( AuthenticationUtils.encryptPassword( customer.getPassword() ) );
        return repository.save( customer );
    }

}
