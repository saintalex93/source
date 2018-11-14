package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.exceptions.CategoryNotFoundException;
import br.com.neolog.exceptions.ProductAlreadyExistsException;
import br.com.neolog.exceptions.ProductInStockException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.pojo.Product;
import br.com.neolog.services.ProductService;

@RestController
public class ProductController {
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String add(@RequestBody Product product)
			throws ProductAlreadyExistsException, CategoryNotFoundException {
		productService.add(product);
		return "Produto adicionado \n\n" + product.getName();
	}

	@RequestMapping(value = "/product/remove", method = RequestMethod.POST)
	public String remove(@RequestBody String code)
			throws ProductNotFoundException, ProductInStockException {
		productService.remove(code);
		return "Produto removido!";
	}

	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
	public String update(@RequestBody Product product)
			throws ProductNotFoundException, CategoryNotFoundException {
		productService.update(product);
		return "Produto atualizado!";
	}

	@RequestMapping(value = "/product/find", method = RequestMethod.POST)
	public String find(@RequestBody String code)
			throws ProductNotFoundException {
		Product product = productService.find(code);
		return "PRODUTO ENCONTRADO" + "\n" + "\n" + product.getName();
	}

	@RequestMapping(value = "product/all", method = RequestMethod.GET)
	public String findAll() {
		String result = "PRODUTOS CADASTRADOS" + "\n";
		for (Product p : productService.findAll()) {
			result = result.concat("CODE: " + p.getCode() + " " + "NAME: "
					+ p.getName() + "\n");
		}
		return result;
	}

}
