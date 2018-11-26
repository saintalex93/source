package br.com.neolog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.models.Cart;
import br.com.neolog.models.Customer;

public interface CartRepository
    extends
        JpaRepository<Cart,Integer>
{

    Cart findByStatus(
        String status );

    Cart findByCustomerAndStatus(
        Customer customer,
        String satus );

}