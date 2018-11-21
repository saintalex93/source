package br.com.neolog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.pojo.Cart;
import br.com.neolog.pojo.CartItem;

public interface CartItemRepository
    extends
        JpaRepository<CartItem,Integer>
{

    List<CartItem> findByCart(
        Cart cart );

}