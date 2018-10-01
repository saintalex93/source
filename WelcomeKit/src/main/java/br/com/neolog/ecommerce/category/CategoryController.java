package br.com.neolog.ecommerce.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryService service;

	@GetMapping(value = "search/all")
	public List<Category> getCategories() {
		return service.getCategories();
	}

	@GetMapping(value = "search/{cod}")
	public Category getCategory(@PathVariable("cod") final int cod) {
		return service.getCategory(cod);
	}

	// @GetMapping(value = "search/name/{cod}")
	// public String getProductName(@PathVariable("cod") final int cod) {
	// return services.getCategoryByCode(cod).getName();
	// }

	// category/search/name?nameCategory=Eletr�nicos
	@GetMapping(value = "search")
	public Category getProductName(@RequestParam(value = "name") final String name) {
		return service.getCategoryByName(name);
	}

	@GetMapping(value = "search/contains")
	public List<Category> getCategoriesByWord(@RequestParam(value = "word") final String word) {
		return service.getCategoriesByWord(word);
	}

	@PostMapping(value = "save")
	public Category getProductSave(@RequestBody final Category categ) {
		return service.save(categ);
	}

	@PostMapping(value = "update")
	public Category getProductUpdate(@RequestBody final Category categ) {
		return service.save(categ);
	}

	@PostMapping(value = "delete/{id}")
	public boolean getProductUpdate(@RequestBody @PathVariable("id") final int id) {
		return service.delete(id);
	}

}
