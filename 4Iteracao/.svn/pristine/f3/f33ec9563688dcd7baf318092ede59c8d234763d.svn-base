package br.com.neolog.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.models.Cart;
import br.com.neolog.models.Customer;
import br.com.neolog.models.Cart.Status;
import br.com.neolog.repository.CartItemRepository;
import br.com.neolog.repository.CartRepository;
import br.com.neolog.repository.CategoryRepository;
import br.com.neolog.repository.ProductRepository;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.StockRepository;

@RunWith( MockitoJUnitRunner.class )
public class CartServiceTest
{
    @Mock
    private CartRepository cartRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CartItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private CartService cartService;

    @Test
    public void MustReturnACartWithStatusNotcompletedIfCartRepositoryReturnIsNull()
    {
        final Cart cart = new Cart();
        cart.setStatus( Status.ACTIVE );
        final Customer user = CurrentUserHolder.getUser();
        cart.setCustomer( user );

        Mockito.when( cartRepository.findByCustomerAndStatus( user, "ACTIVE" ) )
            .thenReturn( null ).thenReturn( cart ).thenReturn( cart );

        final Cart cart2 = cartService.getCart();
        Assert.assertNotNull( cart2 );

    }

    @Test
    public void MustReturnACartWithStatusNotcompletedIfCartRepositoryReturnIsNotNull()
    {
        final Cart cart = new Cart();
        cart.setStatus( Status.ACTIVE );
        final Customer user = CurrentUserHolder.getUser();
        cart.setCustomer( user );

        Mockito.when( cartRepository.findByCustomerAndStatus( user, "ACTIVE" ) )
            .thenReturn( cart );

        final Cart cart2 = cartService.getCart();
        Assert.assertNotNull( cart2 );

    }

}
