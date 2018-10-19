package br.com.neolog.ecommerce.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.CustomerThreadLocal;
import br.com.neolog.ecommerce.customer.Customer;
import br.com.neolog.ecommerce.exceptions.CartItemStockException;
import br.com.neolog.ecommerce.exceptions.CartNotFoundException;
import br.com.neolog.ecommerce.exceptions.CustomerFillException;
import br.com.neolog.ecommerce.exceptions.ProductNotFoundException;
import br.com.neolog.ecommerce.exceptions.StockNotFoundException;
import br.com.neolog.ecommerce.product.Product;
import br.com.neolog.ecommerce.product.ProductRepository;
import br.com.neolog.ecommerce.stock.Stock;
import br.com.neolog.ecommerce.stock.StockItem;
import br.com.neolog.ecommerce.stock.StockService;

@Component
public class CartService
{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductRepository productRepository;

    public List<Cart> listAll()
    {
        return cartRepository.findAll();
    }

    public Cart findById(
        final int id )
    {
        return cartRepository.findById( id );
    }

    public CartItem addItemToCart(
        final HolderCartItem holderCartItem )
    {
        final Customer customer = CustomerThreadLocal.get();

        final Product product = productRepository.findById( holderCartItem.getProductId() );
        if( product == null ) {
            throw new ProductNotFoundException();
        }
        final Stock productStock = stockService.getStockByProductId( holderCartItem.getProductId() );

        if( productStock == null ) {
            throw new StockNotFoundException();
        }

        final Cart cart = getActiveCustomerCart( customer );

        CartItem cartItem = cartItemRepository.findByCartAndProduct( cart, product );

        if( cartItem == null ) {
            cartItem = new CartItem();
            cartItem.setCart( cart );
            cartItem.setProduct( product );
        }
        final int quantity = cartItem.getQuantity() + holderCartItem.getQuantity();
        if( productStock.getQuantity() < quantity ) {
            throw new CartItemStockException();
        }
        cartItem.setQuantity( quantity );
        return cartItemRepository.save( cartItem );
    }

    public Cart getActiveCustomerCart(
        final Customer customer )
    {
        if( customer.getId() == null ) {
            throw new CustomerFillException( "ID do usu�rio n�o encontrado" );
        }

        Cart cart = cartRepository.findByCustomerAndStatus( customer, CartStatus.ACTIVE );

        if( cart == null ) {
            cart = creatCart( customer );
        }
        return cart;
    }

    public Cart cancelActiveCart(
        final Customer customer )
    {
        final Cart cart = cartRepository.findByCustomerAndStatus( customer, CartStatus.ACTIVE );
        if( cart == null ) {
            throw new CartNotFoundException();
        }
        cart.setStatus( CartStatus.CANCELLED );
        return cartRepository.save( cart );
    }

    public Cart checkout(
        final Customer customer )
    {
        final Cart cart = cartRepository.findByCustomerAndStatus( customer, CartStatus.ACTIVE );
        if( cart == null ) {
            throw new CartNotFoundException();
        }
        removeFromStock( cart );
        cart.setStatus( CartStatus.FINISHED );
        return cartRepository.save( cart );
    }

    private void removeFromStock(
        final Cart cart )
    {
        final List<CartItem> cartItens = cartItemRepository.findByCart( cart );
        if( ! cartItens.isEmpty() ) {

            for( final CartItem cartItem : cartItens ) {
                stockService.removeStock( new StockItem( cartItem.getProduct().getId(), cartItem.getQuantity() ) );
            }
        }
    }

    private Cart creatCart(
        final Customer customer )
    {
        final Cart cart = cartRepository.save( new Cart( 0l, customer, CartStatus.ACTIVE ) );
        return cart;
    }

}
