package br.com.neolog.ecommerce.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

import br.com.neolog.ecommerce.CustomerThreadLocal;
import br.com.neolog.ecommerce.customer.Customer;
import br.com.neolog.ecommerce.customer.CustomerRepository;
import br.com.neolog.ecommerce.exceptions.CartCustomerException;
import br.com.neolog.ecommerce.exceptions.CartItemNotFoundException;
import br.com.neolog.ecommerce.exceptions.CartItemRemoveException;
import br.com.neolog.ecommerce.exceptions.CartItemStockException;
import br.com.neolog.ecommerce.exceptions.CartNotFoundException;
import br.com.neolog.ecommerce.exceptions.ProductNotFoundException;
import br.com.neolog.ecommerce.exceptions.StockNotFoundException;
import br.com.neolog.ecommerce.product.Product;
import br.com.neolog.ecommerce.product.ProductRepository;
import br.com.neolog.ecommerce.stock.Stock;
import br.com.neolog.ecommerce.stock.StockItem;
import br.com.neolog.ecommerce.stock.StockRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Cart> listAll()
    {
        return cartRepository.findAll();
    }

    public Cart findById(
        final int id )
    {

        final Optional<Cart> cart = cartRepository.findById( id );
        if( ! cart.isPresent() ) {
            throw new CartNotFoundException();
        }

        if( cart.get().getCustomer().getId() != CustomerThreadLocal.get() ) {
            throw new CartCustomerException();
        }

        return cart.get();
    }

    public Cart addItemToCart(
        final CartItemHolder cartItemHolder )
    {

        final Product product = productRepository.findById( cartItemHolder.getProductId() );
        if( product == null ) {
            throw new ProductNotFoundException();
        }

        final Cart cart = getActiveCustomerCart();
        final CartItem cartItem = cartItemRepository.findByCartAndProduct( cart, product );

        if( cartItem == null ) {
            final Stock productStock = stockService.getStockByProductId( cartItemHolder.getProductId() );
            validateQuantityInStock( productStock, cartItemHolder.getQuantity() );
            final CartItem newCartItem = new CartItem( product, cartItemHolder.getQuantity(), cart );
            cart.setTotalValue( cart.getTotalValue() + cartItemHolder.getQuantity() * product.getPrice() );
            cartItemRepository.save( newCartItem );
            return cartRepository.save( cart );
        }

        final int newQuantity = cartItem.getQuantity() + cartItemHolder.getQuantity();
        final Stock productStock = stockService.getStockByProductId( cartItemHolder.getProductId() );
        validateQuantityInStock( productStock, newQuantity );
        cartItem.setQuantity( newQuantity );
        cart.setTotalValue( cart.getTotalValue() + cartItemHolder.getQuantity() * product.getPrice() );
        cartItemRepository.save( cartItem );
        return cartRepository.save( cart );

    }

    public Cart removeProductQuantityFromCartItem(
        final CartItemHolder cartItemHolder )
    {
        final Product product = productRepository.findById( cartItemHolder.getProductId() );
        if( product == null ) {
            throw new ProductNotFoundException();
        }

        if( ! stockRepository.existsByProductId( cartItemHolder.getProductId() ) ) {
            throw new StockNotFoundException();
        }
        final Cart cart = cartRepository.findByCustomerIdAndStatus( CustomerThreadLocal.get(), CartStatus.ACTIVE );
        if( cart == null ) {
            throw new CartNotFoundException();
        }

        final CartItem cartItem = cartItemRepository.findByCartAndProduct( cart, product );
        if( cartItem == null ) {
            throw new CartItemNotFoundException();
        }
        if( cartItem.getQuantity() < cartItemHolder.getQuantity() ) {
            throw new CartItemRemoveException( "Quantidade informada maior do que est� no carrinho" );
        }

        final int quantity = cartItem.getQuantity() - cartItemHolder.getQuantity();
        if( quantity == 0 ) {
            cartItemRepository.delete( cartItem );
        } else {
            cartItem.setQuantity( quantity );
            cartItemRepository.save( cartItem );
        }

        final Double totalValue = cart.getTotalValue() - cartItemHolder.getQuantity() * product.getPrice();
        cart.setTotalValue( totalValue );
        return cartRepository.save( cart );

    }

    public Cart removeCartItem(
        final int cartItemID )
    {
        final CartItem cartItem = cartItemRepository.findById( cartItemID );
        if( cartItem == null ) {
            throw new CartItemNotFoundException();
        }
        if( ! cartItem.getCart().getCustomer().equals( customerRepository.findById( CustomerThreadLocal.get() ) ) ) {
            throw new CartCustomerException();
        }
        final Cart cart = cartItem.getCart();
        final Double totalValue = cartItem.getQuantity() * cartItem.getProduct().getPrice();
        cart.setTotalValue( totalValue );
        cartItemRepository.delete( cartItem );
        return cartRepository.save( cart );
    }

    public Cart cancelCart()
    {
        final Cart cart = cartRepository.findByCustomerIdAndStatus( CustomerThreadLocal.get(), CartStatus.ACTIVE );
        if( cart == null ) {
            throw new CartNotFoundException();
        }
        cart.setStatus( CartStatus.CANCELLED );
        return cartRepository.save( cart );
    }

    @Transactional
    public Cart checkout()
    {
        final Cart cart = cartRepository.findByCustomerIdAndStatus( CustomerThreadLocal.get(), CartStatus.ACTIVE );
        if( cart == null ) {
            throw new CartNotFoundException();
        }
        checkStockQuantity( cart );
        removeCartItemsFromStock( cart );
        cart.setStatus( CartStatus.FINISHED );
        return cartRepository.save( cart );
    }

    private void checkStockQuantity(
        final Cart cart )
    {
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );
        if( cartItems.isEmpty() ) {
            throw new CartItemNotFoundException();
        }

        final List<String> invalidItems = cartItems.stream()
            .filter( item -> item.getQuantity() > stockService.getStockByProduct( item.getProduct() ).getQuantity() )
            .map( item -> item.getProduct().getName() )
            .collect( Collectors.toList() );

        if( ! invalidItems.isEmpty() ) {
            final String invalidNames = Joiner.on( "-" ).join( invalidItems );
            throw new CartItemStockException( invalidNames );
        }

    }

    private void removeCartItemsFromStock(
        final Cart cart )
    {
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );
        cartItems.stream()
            .forEach( cartItem -> {
                final StockItem stockItem = new StockItem( cartItem.getProduct().getId(), cartItem.getQuantity() );
                stockService.removeStock( stockItem );
            } );
    }

    public Cart getActiveCustomerCart()
    {
        final Cart cart = cartRepository.findByCustomerIdAndStatus( CustomerThreadLocal.get(), CartStatus.ACTIVE );
        if( cart == null ) {
            return createCart();
        }
        return cart;
    }

    public List<CartItem> getCartItensByCartId(
        final int cartId )
    {
        return cartItemRepository.findByCartId( cartId );
    }

    private Cart createCart()
    {
        final Customer customer = customerRepository.findById( CustomerThreadLocal.get() );
        return cartRepository.save( new Cart( 0l, customer, CartStatus.ACTIVE ) );
    }

    private void validateQuantityInStock(
        final Stock productStock,
        final int quantity )
    {
        if( productStock.getQuantity() < quantity ) {
            throw new CartItemStockException( productStock.getProduct().getName() );
        }
    }

}
