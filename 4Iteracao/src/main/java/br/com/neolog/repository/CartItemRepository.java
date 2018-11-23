package br.com.neolog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.models.Cart;
import br.com.neolog.models.CartItem;

public interface CartItemRepository
    extends
        JpaRepository<CartItem,Integer>
{

    List<CartItem> findByCart(
        Cart cart );

}