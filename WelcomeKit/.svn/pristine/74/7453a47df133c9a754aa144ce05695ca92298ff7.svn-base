package br.com.neolog.ecommerce.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.authentication.AuthenticationUtils;
import br.com.neolog.ecommerce.exceptions.CustomerDuplicatedEmailException;
import br.com.neolog.ecommerce.exceptions.CustomerFillException;
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
        if( customer.getId() != null ) {
            customer.setId( null );
        }

        final Customer recievedCustomer = repository.findByEmail( customer.getEmail() );

        if( recievedCustomer != null && recievedCustomer.getInactive() == false ) {
            throw new CustomerDuplicatedEmailException();
        }
        customer.setPassword( AuthenticationUtils.encryptPassword( customer.getPassword() ) );
        return repository.save( customer );
    }

    public Customer update(
        final Customer customer )
    {
        if( customer.getId() == null ) {
            throw new CustomerFillException( "Informe o ID do usuário a ser alterado" );
        }
        final int id = customer.getId();
        final Customer customerToUpdate = repository.findById( id );
        if( customerToUpdate == null || customerToUpdate.getInactive() == true ) {
            throw new CustomerNotFoundException();
        }
        if( repository.existsByEmailAndIdNotAndInactiveFalse( customer.getEmail(), id ) ) {
            throw new CustomerDuplicatedEmailException();
        }
        customer.setPassword( AuthenticationUtils.encryptPassword( customer.getPassword() ) );
        return repository.save( customer );
    }

}
