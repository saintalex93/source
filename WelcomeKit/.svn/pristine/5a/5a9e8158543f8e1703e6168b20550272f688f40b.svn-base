package br.com.neolog.ecommerce.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {

	@Autowired
	StockService service;

	@GetMapping(value = "search/all")
	public List<Stock> getStock() {
		return service.getStock();
	}
	//
	// @GetMapping(value = "search/{cod}")
	// public Stock getStock(@PathVariable("cod") final int cod) {
	// return service.getStock(cod);
	// }
	//
	// // @GetMapping(value = "search/name/{cod}")
	// // public String getProductName(@PathVariable("cod") final int cod) {
	// // return services.getStockByCode(cod).getName();
	// // }
	//
	// // Stock/search/name?nameStock=Eletrônicos
	// @GetMapping(value = "search")
	// public Stock getProductName(@RequestParam(value = "name") final String
	// name) {
	// return service.getStockByName(name);
	// }
	//
	// @GetMapping(value = "search/contains")
	// public List<Stock> getCategoriesByWord(@RequestParam(value = "word")
	// final String word) {
	// return service.getCategoriesByWord(word);
	// }
	//
	// @PostMapping(value = "save")
	// public Stock getProductSave(@RequestBody final Stock categ) {
	// return service.save(categ);
	// }
	//
	// @PostMapping(value = "update")
	// public Stock getProductUpdate(@RequestBody final Stock categ) {
	// return service.save(categ);
	// }
	//
	// @PostMapping(value = "delete/{id}")
	// public boolean getProductUpdate(@RequestBody @PathVariable("id") final
	// int id) {
	// return service.delete(id);
	// }
	//

}
