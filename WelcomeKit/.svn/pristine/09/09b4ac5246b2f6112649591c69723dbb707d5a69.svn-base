package br.com.neolog.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping(value = "search")
	public List<Product> getProducts() {
		return service.getProducts();
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
	public Product getProductSave(@RequestBody final Product prod) {
		return service.save(prod);
	}

	@PostMapping(value = "update")
	public Product getProductUpdate(@RequestBody final Product prod) {
		return service.save(prod);
	}

	@PostMapping(value = "delete/{id}")
	public boolean getProductUpdate(@RequestBody @PathVariable("id") final int id) {
		return service.delete(id);
	}

}
