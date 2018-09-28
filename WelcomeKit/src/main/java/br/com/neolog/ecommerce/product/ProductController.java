package br.com.neolog.ecommerce.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.ecommerce.productcategory.Category;

@RestController
@RequestMapping("product")
public class ProductController {

	@PostMapping(value = "setName")
	public String getProduct(@RequestBody final Product prod) {

		return prod.getName();
	}

	@GetMapping(value = "search/{identifier}")
	public Product getProduct(@PathVariable("identifier") final int id) {

		final Category eletronico = new Category(1, 1, "Eletronicos");
		final Category tecnologico = new Category(2, 2, "Tecnologia");

		final Product product1 = new Product(1, 1, "TV", 2500.00, "Plasma", 2.500, eletronico);
		final Product product2 = new Product(2, 2, "Windão Original", 10000.00, "S.O 'BOM' ", 10.00, tecnologico);
		final Product product3 = new Product(3, 3, "Linux", 0.00, "S.O", 0.00, tecnologico);

		final ArrayList<Product> products = new ArrayList<Product>();

		products.add(product1);
		products.add(product2);
		products.add(product3);

		return products.get(id);

	}

	@GetMapping(value = "search/name/{id}")
	public String getProductName(@PathVariable("id") final int id) {

		final Category eletronico = new Category(1, 1, "Eletronicos");
		final Category tecnologico = new Category(2, 2, "Tecnologia");

		final Product product1 = new Product(1, 1, "TV", 2500.00, "Plasma", 2.500, eletronico);
		final Product product2 = new Product(2, 2, "Windão Original", 10000.00, "S.O 'BOM' ", 10.00, tecnologico);
		final Product product3 = new Product(3, 3, "Linux", 0.00, "S.O", 0.00, tecnologico);

		final ArrayList<Product> products = new ArrayList<Product>();

		products.add(product1);
		products.add(product2);
		products.add(product3);

		return products.get(id).getName();

	}

	@GetMapping(value = "search/")
	public List<Product> getProducts() {

		final Category eletronico = new Category(1, 1, "Eletronicos");
		final Category tecnologico = new Category(2, 2, "Tecnologia");

		final Product product1 = new Product(1, 1, "TV", 2500.00, "Plasma", 2.500, eletronico);
		final Product product2 = new Product(2, 2, "Windão Original", 10000.00, "S.O 'BOM' ", 10.00, tecnologico);
		final Product product3 = new Product(3, 3, "Linux", 0.00, "S.O", 0.00, tecnologico);

		final List<Product> products = new ArrayList<Product>();

		products.add(product1);
		products.add(product2);
		products.add(product3);

		return products;

	}

}
