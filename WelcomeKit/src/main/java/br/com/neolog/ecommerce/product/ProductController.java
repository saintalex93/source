package br.com.neolog.ecommerce.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.ecommerce.exceptions.ProductNotFoundException;
import br.com.neolog.ecommerce.stock.Stock;
import br.com.neolog.ecommerce.stock.StockService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService service;

	@Autowired
	StockService stockService;

	@GetMapping(value = "list")
	public List<Product> getProducts() {
		return service.getProducts();
	}

	@GetMapping(value = "list/inStock")
	public List<Stock> getProductsInStock() {
		return stockService.getActiveStock();
	}

	@GetMapping(value = "list/outStock")
	public List<Stock> getProductsOutOfStock() {
		return stockService.getInactiveStock();
	}

	@GetMapping(value = "list/price/greater/{price}")
	public List<Product> getProductsGreatherThan(@PathVariable("price") final double price) {
		return service.findByPriceGreaterThan(price);
	}

	@GetMapping(value = "list/price/lesser/{price}")
	public List<Product> getProductsLesserThan(@PathVariable("price") final double price) {
		return service.findByPrieceLessThan(price);
	}

	@GetMapping(value = "search/category/{cod}")
	public List<Product> getProductsByCategory(@PathVariable("cod") final int cod) {
		return service.getProductsByCategoryId(cod);
	}

	@GetMapping(value = "search/cod/{cod}")
	public Product getProductByCode(@PathVariable("cod") final int cod) {
		return service.getProductByCode(cod);
	}

	@GetMapping(value = "search/id/{id}")
	public Product getProductById(@PathVariable("id") final int id) {
		return service.getProductById(id);
	}

	@GetMapping(value = "search/name/{id}")
	public String getProductName(@PathVariable("id") final int id) {
		return service.getProductById(id).getName();
	}

	@PostMapping(value = "save")
	public Product getProductSave(@RequestBody @Valid final Product prod) {

		System.out.println(prod.getCategory());
		return service.save(prod);
	}

	@PostMapping(value = "update")
	public Product getProductUpdate(@RequestBody @Valid final Product prod) {
		return service.save(prod);
	}

	@PostMapping(value = "delete/{id}")
	public boolean getProductUpdate(@RequestBody @Valid @PathVariable("id") final int id) {

		if (getProductById(id) == null) {
			throw new ProductNotFoundException("Produto de ID" + id + " n�o encontrado");
		}

		return service.delete(id);
	}

}
