package br.com.neolog.ecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
    extends
        JpaRepository<Customer,Integer>
{

    Customer findById(
        final int id );

    Customer findByEmail(
        final String email );

    Customer findByEmailAndInactiveFalse(
        final String email );

    boolean existsByEmailAndIdNotAndInactiveFalse(
        final String email,
        final int id );

}
