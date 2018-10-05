package br.com.neolog.ecommerce.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.ecommerce.stock.StockService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService service;

	@Autowired
	StockService stockService;

	@GetMapping(value = "list")
	public ResponseEntity<List<Product>> getProducts() {
		final List<Product> productList = service.getProducts();
		if (productList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@GetMapping(value = "list/price/greater/{price}")
	public ResponseEntity<List<Product>> getProductsGreatherThan(@PathVariable("price") final double price) {

		final List<Product> productList = service.findByPriceGreaterThan(price);
		if (productList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);

	}

	@GetMapping(value = "list/price/lesser/{price}")
	public ResponseEntity<List<Product>> getProductsLesserThan(@PathVariable("price") final double price) {

		final List<Product> productList = service.findByPrieceLessThan(price);
		if (productList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);

	}

	@GetMapping(value = "search/category/{code}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("cod") final int cod) {
		return new ResponseEntity<List<Product>>(service.getProductsByCategoryId(cod), HttpStatus.OK);

	}

	@GetMapping(value = "search/cod/{code}")
	public ResponseEntity<Product> getProductByCode(@PathVariable("cod") final int cod) {
		return new ResponseEntity<Product>(service.getProductByCode(cod), HttpStatus.OK);

	}

	@GetMapping(value = "search/id/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") final int id) {
		return new ResponseEntity<Product>(service.getProductById(id), HttpStatus.OK);

	}

	@GetMapping(value = "search/name/{id}")
	public ResponseEntity<String> getProductName(@PathVariable("id") final int id) {
		return new ResponseEntity<String>(service.getProductById(id).getName(), HttpStatus.OK);
	}

	@GetMapping(value = "search/contains")
	public ResponseEntity<List<Product>> getCategoriesByWord(@RequestParam(value = "word") final String word) {

		final List<Product> productList = service.getProductsByWord(word);
		if (productList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);

	}

	@PostMapping(value = "save")
	public ResponseEntity<Product> getProductSave(@Valid @RequestBody final Product prod) {
		return new ResponseEntity<Product>(service.save(prod), HttpStatus.OK);
	}

	@PostMapping(value = "update")
	public ResponseEntity<Product> getProductUpdate(@RequestBody @Valid final Product prod) {
		return new ResponseEntity<Product>(service.update(prod), HttpStatus.OK);
	}

	@PostMapping(value = "delete/{id}")
	public ResponseEntity<Boolean> getProductUpdate(@Valid @PathVariable("id") final int id) {
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.OK);
	}

}
