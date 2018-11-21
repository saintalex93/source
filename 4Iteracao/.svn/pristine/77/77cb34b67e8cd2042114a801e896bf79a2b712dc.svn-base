package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.InvalidQuantityException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.exceptions.ProductQuantityInsufficientException;
import br.com.neolog.pojo.CartItemHolder;
import br.com.neolog.services.StockService;

@RestController
public class StockController {
	private StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@RequestMapping(value = "stock/add", method = RequestMethod.POST)
	public String addToStock(@RequestBody CartItemHolder holderCodeQuantity)
			throws ProductNotFoundException, InvalidQuantityException,
			ProductQuantityInsufficientException {
		stockService.addToStock(holderCodeQuantity.getCode(),
				holderCodeQuantity.getQuantity());
		return holderCodeQuantity.getQuantity()
				+ " unidade(s) adicionado ao estoque!";

	}

	@RequestMapping(value = "stock/quantity", method = RequestMethod.DELETE)
	public String removeFromStock(
			@RequestBody CartItemHolder holderCodeQuantity)
			throws ProductNotFoundException, InvalidQuantityException,
			ProductQuantityInsufficientException {
		return stockService.removeFromStock(holderCodeQuantity.getCode(),
				holderCodeQuantity.getQuantity());

	}

	@RequestMapping(value = "stock/code", method = RequestMethod.DELETE)
	public String removeFromStock(@RequestBody String code)
			throws ProductNotFoundException {
		return stockService.removeFromStock(code);

	}

	@RequestMapping(value = "stock/showquantity", method = RequestMethod.POST)
	public String showProductQuantity(@RequestBody String code)
			throws ProductNotFoundException {
		return stockService.showProductQuantity(code);

	}

	@RequestMapping(value = "/stock/show", method = RequestMethod.GET)
	public String showStock() {
		return stockService.showStock();

	}

}