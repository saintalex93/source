package br.com.neolog.ecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
    extends
        JpaRepository<Customer,Integer>
{

    public Customer findById(
        final int id );

    public Customer findByEmailAndPassword(
        final String email,
        final String password );

}
