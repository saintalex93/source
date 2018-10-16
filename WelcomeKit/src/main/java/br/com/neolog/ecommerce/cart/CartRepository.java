package br.com.neolog.ecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository
    extends
        JpaRepository<Cart,Integer>
{

}
