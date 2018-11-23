package br.com.neolog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.exceptions.EmptyCartException;
import br.com.neolog.exceptions.InvalidQuantityException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.exceptions.ProductNotInCartException;
import br.com.neolog.exceptions.ProductQuantityInsufficientException;
import br.com.neolog.models.Cart;
import br.com.neolog.models.CartItem;
import br.com.neolog.models.CartItemHolder;
import br.com.neolog.models.Customer;
import br.com.neolog.models.Product;
import br.com.neolog.models.Stock;
import br.com.neolog.models.Cart.Status;
import br.com.neolog.repository.CartItemRepository;
import br.com.neolog.repository.CartRepository;
import br.com.neolog.repository.CategoryRepository;
import br.com.neolog.repository.ProductRepository;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.StockRepository;

@Component
public class CartService
{

    @Autowired
    public StockRepository stockRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    public SessionRepository sessionRepository;

    @Autowired
    public TokenService tokenService;

    /**
     * Método que retorna um {@link Cart} para o usuário corrente.
     *
     * @return Deve retornar um {@link Cart} com o status ACTIVE, se não
     *         existir deverá retornar um {@link Cart} novo.
     */
    public Cart getCart()
    {
        final Customer user = CurrentUserHolder.getUser();
        Cart cart = cartRepository.findByCustomerAndStatus( user, "ACTIVE" );
        if( cart != null ) {
            return cart;
        }
        cart = new Cart();
        cart.setCustomer( user );
        cart.setStatus( Status.ACTIVE );
        return cart;

    }

    /**
     * Método que adiciona uma quantidade N de {@link Product} ao {@link Cart}.
     *
     * @param code código do {@link Product} a ser adicionado ao {@link Cart}.
     * @param quantity a quantidade a ser adicionada.
     * @throws ProductQuantityInsufficientException lança essa exceção se a
     *         {@code quantity} a ser adicionada ao {@link Cart} for maior que a
     *         {@code quantity} em estoque.
     * @throws InvalidQuantityException lança essa exceção se a
     *         {@code quantity} for um número inválido, menor do que 1.
     * @throws ProductNotFoundException lança essa exceção se não for
     *         encontrado nenhum {@link Product} com o {@code code} informado.
     * @return RETORNO AINDA NÃO DEFINIDO!!!
     */

    @Transactional
    public List<CartItem> addToCart(
        final CartItemHolder cartItemHolder )
        throws ProductQuantityInsufficientException,
            InvalidQuantityException,
            ProductNotFoundException
    {
        final String code = cartItemHolder.getCode();
        final Integer quantity = cartItemHolder.getQuantity();

        final Stock productQuantity = stockRepository
            .findByProductCode( code );

        if( productQuantity == null ) {
            throw new ProductNotFoundException(
                "Produto n�o encontrado no estoque!!!" );
        }

        if( quantity < 1 ) {
            throw new InvalidQuantityException(
                "Quantidade inv�lida, a quantidade deve ser maior ou igual a 1!!!" );
        }
        if( quantity > productQuantity.getQuantity() ) {
            throw new ProductQuantityInsufficientException(
                "Quantidade em estoque insuficiente!!! ESTOQUE: "
                    + productQuantity.getQuantity() );
        }

        final Cart cart = getCart();

        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );

        for( final CartItem cartItem : cartItems ) {
            if( cartItem.getProduct().getCode().equals( code ) ) {
                cartItem.setQuantity( cartItem.getQuantity() + quantity );
                final Product product = cartItem.getProduct();
                cartItems.remove( cartItem );
                cartItems.add( cartItemRepository.save( cartItem ) );
                System.out.println( cartItems );
                return cartItems;
            }

        }
        final CartItem orderItem = new CartItem();
        orderItem.setProduct( stockRepository.findByProductCode( code )
            .getProduct() );
        orderItem.setQuantity( quantity );
        cartItems.remove( orderItem );
        cartItems.add( orderItem );
        cartItemRepository.save( orderItem );

        cart.setTotalValue( getTotal() );
        cartRepository.save( cart );

        return cartItems;
    }

    /**
     * Método que remove uma quantidade N de produtos do {@link Cart}.
     *
     * @param code código do {@link Product} a ser removido do {@link Cart}.
     * @param quantity a quantidade a ser removida.
     * @throws ProductNotInCartException Lança essa exceção se o produto não
     *         estiver no {@link Cart}.
     * @throws InvalidQuantityException Lança essa exceção se a quantidade
     *         for maior que a quantidade do produto no {@link Cart} ou se a
     *         quantidade for menor do que 1.
     * @return RETORNO AINDA NÃO DEFINIDO!!!
     */
    public String removeFromCart(
        final CartItemHolder holderCodeQuantity )
        throws ProductNotInCartException,
            InvalidQuantityException
    {
        final String code = holderCodeQuantity.getCode();
        final int quantity = holderCodeQuantity.getQuantity();

        if( quantity < 1 ) {
            throw new InvalidQuantityException(
                "A quantidade a ser removida dever ser maior do que 1!!!" );
        }

        final Cart cart = getCart();
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );

        for( final CartItem cartItem : cartItems ) {

            if( cartItem.getProduct().getCode().equals( code ) ) {
                if( quantity > cartItem.getQuantity() ) {
                    throw new InvalidQuantityException(
                        "Quantidade informada maior do que a do carrinho!!! | Cart: "
                            + cartItem.getProduct().getName() + " "
                            + cartItem.getQuantity() );
                }
                cartItem.setQuantity( cartItem.getQuantity() - quantity );
                cartRepository.save( cart );
                return "Quantidade removida com sucesso!!!";
            }

        }
        return "Produto n�o encontrado!!!";
    }

    /**
     * Método que remove todos os {@link Product} do {@link Cart} que tiverem o
     * mesmo código.
     *
     * @param code código do {@link Product} a ser removido do {@link Cart}.
     * @throws ProductNotInCartException Lança essa exceção se o
     *         {@link Product} não for encontrado no {@link Cart}.
     * @return RETORNO AINDA NÃO DEFINIDO!!!
     */

    public String removeFromCart(
        final String code )
        throws ProductNotInCartException
    {

        final Cart cart = getCart();
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );

        for( final CartItem cartItem : cartItems ) {
            if( cartItem.getProduct().getCode().equals( code ) ) {
                cartItems.remove( cartItem );
                cartRepository.save( cart );
                return "Produto removido com sucesso!!!";
            }
        }
        throw new ProductNotInCartException( "Produto n�o existe no carrinho!!!" );
    }

    /**
     * Método que limpa todos os {@link Product} do {@link Cart}.
     *
     * @return RETORNO AINDA NÃO DEFINIDO!!!
     */
    public boolean clearCart()
    {
        final Cart cart = getCart();
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );
        cartItemRepository.deleteAll( cartItems );
        cart.setTotalValue( getTotal() );
        cartRepository.save( cart );
        return true;
    }

    /**
     * Método que mostra todos os {@link Product} presentes no {@link Cart}.
     *
     * @return RETORNO AINDA NÃO DEFINIDO!!!
     */
    public String showCart()
    {

        final Cart cart = getCart();
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );

        String result = "";
        for( final CartItem cartItem : cartItems ) {
            result = result.concat( "PRODUTO: " + cartItem.getProduct().getName()
                + " | QUANTIDADE: " + cartItem.getQuantity() + "\n" );
        }
        return result;

    }

    /**
     * Cancela o {@link Cart} do {@link Customer} atual.
     *
     * @return ainda não definido
     */
    public boolean cancelCart()
    {
        final Cart cart = getCart();
        cart.setStatus( Status.CANCELED );
        cartRepository.save( cart );
        return true;
    }

    /**
     * Método que faz a soma de todos os {@link Product} no {@link Cart}.
     *
     * @return retorna a soma dos {@link Product} no {@link Cart}.
     */
    public Double getTotal()
    {
        final Cart cart = getCart();
        double result = 0;
        for( final CartItem o : cartItemRepository.findByCart( cart ) ) {
            result = result + o.getProduct().getPrice() * o.getQuantity();
        }
        return result;

    }

    /**
     * Método chamado para finalizar a compra.
     *
     * @return
     * @throws EmptyCartException lançada quando o checkout é chamado para um
     *         {@link Cart} vazio.
     */

    @Transactional
    public String checkOut()
        throws EmptyCartException
    {
        final Cart cart = getCart();
        final List<CartItem> cartItems = cartItemRepository.findByCart( cart );
        if( cartItems.size() == 0 ) {
            throw new EmptyCartException( "Carrinho vazio!!!" );
        }

        String result = "";
        int count = 0;
        double total = 0;

        for( final CartItem o : cartItems ) {
            if( o.getQuantity() <= stockRepository.findByProductCode(
                o.getProduct().getCode() ).getQuantity() ) {
                count = count + 1;
            }
        }

        if( cartItems.size() == count ) {
            result = result
                .concat( "PRODUTO | PRE�O | QUANTIDADE | TOTAL(R$) \n\n" );

            for( final CartItem o : cartItems ) {
                final Stock p = stockRepository.findByProductCode( o
                    .getProduct().getCode() );
                p.setQuantity( p.getQuantity() - o.getQuantity() );
                stockRepository.save( p );
                result = result.concat( o.getProduct().getName() + " | "
                    + o.getProduct().getPrice() + " | " + o.getQuantity()
                    + " | " + o.getProduct().getPrice() * o.getQuantity()
                    + "\n" );
                total = total + o.getProduct().getPrice() * o.getQuantity();
            }
            result = result.concat( "\nTOTAL DA COMPRA: R$ " + total );
            cart.setStatus( Status.FINISHED );
            cartRepository.save( cart );
        } else {
            result = result.concat( "N�o foi poss�vel concluir a compra!!!" );
        }

        return result;
    }
}
