package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.EmptyCartException;
import br.com.neolog.exceptions.InvalidQuantityException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.exceptions.ProductNotInCartException;
import br.com.neolog.exceptions.ProductQuantityInsufficientException;
import br.com.neolog.pojo.HolderCodeQuantity;
import br.com.neolog.services.CartService;

@RestController
public class CartController {
	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping(value = "/cart/addtocart", method = RequestMethod.POST)
	public boolean addToCart(@RequestBody HolderCodeQuantity holderCodeQuantity)
			throws ProductNotFoundException, InvalidQuantityException,
			ProductQuantityInsufficientException {
		return cartService.addToCart(holderCodeQuantity.getCode(),
				holderCodeQuantity.getQuantity());
	}

	@RequestMapping(value = "/cart/removequantity", method = RequestMethod.POST)
	public String removeFromCart(
			@RequestBody HolderCodeQuantity holderCodeQuantity)
			throws ProductNotInCartException, InvalidQuantityException {
		return cartService.removeFromCart(holderCodeQuantity.getCode(),
				holderCodeQuantity.getQuantity());
	}

	@RequestMapping(value = "/cart/remove", method = RequestMethod.POST)
	public String removeFromCart(@RequestBody String code)
			throws ProductNotInCartException {
		return cartService.removeFromCart(code);
	}

	@RequestMapping(value = "/cart/clear", method = RequestMethod.GET)
	public boolean clearCart() {
		return cartService.clearCart();
	}

	@RequestMapping(value = "/cart/show", method = RequestMethod.GET)
	public String showCart() {
		return cartService.showCart();
	}

	@RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
	public String chekout() throws EmptyCartException {
		return cartService.checkOut();
	}

	@RequestMapping(value = "/cart/cancel", method = RequestMethod.GET)
	public boolean cancelcart() {
		return cartService.cancelCart();
	}

}
