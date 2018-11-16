package br.com.neolog.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.neolog.currentuser.CurrentUserHolder;
import br.com.neolog.pojo.Cart;
import br.com.neolog.pojo.Cart.Status;
import br.com.neolog.pojo.Customer;
import br.com.neolog.repository.CartRepository;
import br.com.neolog.repository.CategoryRepository;
import br.com.neolog.repository.OrderItemRepository;
import br.com.neolog.repository.ProductRepository;
import br.com.neolog.repository.SessionRepository;
import br.com.neolog.repository.StockRepository;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
	@Mock
	private CartRepository cartRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private OrderItemRepository orderItemRepository;

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
	public void MustReturnACartWithStatusNotcompletedIfCartRepositoryReturnIsNull() {
		Cart cart = new Cart();
		cart.setStatus(Status.NOTCOMPLETED);
		Customer user = CurrentUserHolder.getUser();
		cart.setUser(user);

		Mockito.when(cartRepository.findByUserAndStatus(user, "NOTCOMPLETED"))
				.thenReturn(null).thenReturn(cart).thenReturn(cart);

		Cart cart2 = cartService.getCart();
		Assert.assertNotNull(cart2);

	}

	@Test
	public void MustReturnACartWithStatusNotcompletedIfCartRepositoryReturnIsNotNull() {
		Cart cart = new Cart();
		cart.setStatus(Status.NOTCOMPLETED);
		Customer user = CurrentUserHolder.getUser();
		cart.setUser(user);

		Mockito.when(cartRepository.findByUserAndStatus(user, "NOTCOMPLETED"))
				.thenReturn(cart);

		Cart cart2 = cartService.getCart();
		Assert.assertNotNull(cart2);

	}

}
