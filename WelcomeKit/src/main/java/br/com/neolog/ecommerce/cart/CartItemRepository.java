package br.com.neolog.ecommerce.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neolog.ecommerce.product.Product;

@Repository
public interface CartItemRepository
    extends
        JpaRepository<CartItem,Integer>
{

    public List<CartItem> findByCart(
        Cart cart );

    public CartItem findByCartAndProduct(
        Cart cart,
        Product product );

}
