package br.com.neolog.ecommerce.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "cart" )
public class CartController
{

    @Autowired
    private CartService cartService;

    @GetMapping( "search" )
    public ResponseEntity<List<Cart>> listAll()
    {
        return ResponseEntity.ok( cartService.listAll() );

    }

    @GetMapping( "search/{id}" )
    public ResponseEntity<Cart> listById(
        @PathVariable( "id" ) final int id )
    {
        return ResponseEntity.ok( cartService.findById( id ) );
    }

    @GetMapping( "get-cart-item/{id}" )
    public ResponseEntity<List<CartItem>> getCartItensByCart(
        @PathVariable( "id" ) final int id )
    {
        return ResponseEntity.ok( cartService.getCartItensByCartId( id ) );
    }

    @GetMapping( "customer-cart" )
    public ResponseEntity<Cart> activeCustomerCart()
    {
        return ResponseEntity.ok( cartService.getActiveCustomerCart() );

    }

    @PostMapping( "cancel-cart" )
    public ResponseEntity<Cart> cancelCustomerCart()
    {
        return ResponseEntity.ok( cartService.cancelCart() );

    }

    @PostMapping( "checkout" )
    public ResponseEntity<Cart> checkoutCart()
    {
        return ResponseEntity.ok( cartService.checkout() );

    }

    @PostMapping( "add-item-to-cart" )
    public ResponseEntity<Cart> addItemToCart(
        @RequestBody final CartItemHolder holderCartItem )
    {
        return ResponseEntity.ok( cartService.addItemToCart( holderCartItem ) );
    }

    @PostMapping( "remove-quantity" )
    public ResponseEntity<Cart> removeQuantity(
        @RequestBody final CartItemHolder holderCartItem )
    {
        return ResponseEntity.ok( cartService.removeProductQuantityFromCartItem( holderCartItem ) );
    }

    @PostMapping( "remove-cart-item/{cartItemId}" )
    public ResponseEntity<Cart> removeCartItem(
        @PathVariable( "cartItemId" ) final int cartItemId )
    {
        return ResponseEntity.ok( cartService.removeCartItem( cartItemId ) );
    }

}
