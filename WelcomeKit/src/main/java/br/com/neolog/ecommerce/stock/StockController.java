package br.com.neolog.ecommerce.stock;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {

	@Autowired
	StockService service;

	@GetMapping(value = "search/all")
	public ResponseEntity<List<Stock>> getStock() {
		final List<Stock> listStock = service.getStock();
		if (listStock.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Stock>>(listStock, HttpStatus.OK);
	}

	@GetMapping(value = "search/active")
	public ResponseEntity<List<Stock>> getActiveStock() {
		final List<Stock> listStock = service.getActiveStock();
		if (listStock.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Stock>>(listStock, HttpStatus.OK);
	}

	@GetMapping(value = "search/inactive")
	public ResponseEntity<List<Stock>> getInactiveStock() {
		final List<Stock> listStock = service.getInactiveStock();
		if (listStock.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Stock>>(listStock, HttpStatus.OK);

	}

	@GetMapping(value = "search/product/{code}")
	public ResponseEntity<Stock> getProductStock(@PathVariable("cod") final int idProduct) {
		return new ResponseEntity<Stock>(service.getProductStock(idProduct), HttpStatus.OK);
	}

	@GetMapping(value = "search/max")
	public ResponseEntity<Stock> getMaxProduct() {
		final Stock stock = service.getMaxProduct();
		if (stock == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.OK);

	}

	@GetMapping(value = "search/min")
	public ResponseEntity<Stock> getMinProduct() {
		final Stock stock = service.getMinProduct();
		if (stock == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.OK);
	}

	@PostMapping(value = "create")
	public ResponseEntity<Stock> addStock(@Valid @RequestBody final StockItem stockItem) {
		return new ResponseEntity<Stock>(service.createStock(stockItem), HttpStatus.OK);
	}

	@PostMapping(value = "addQuantity")
	public ResponseEntity<Stock> setQuantityAdd(@Valid @RequestBody final StockItem stockItem) {
		return new ResponseEntity<Stock>(service.addStock(stockItem), HttpStatus.OK);
	}

	@PostMapping(value = "removeQuantity")
	public ResponseEntity<Stock> setQuantityRemove(@Valid @RequestBody final StockItem stockItem) {
		return new ResponseEntity<Stock>(service.removeStock(stockItem), HttpStatus.OK);
	}

	@PostMapping(value = "remove/{id}")
	public ResponseEntity<Boolean> removeStock(@Valid @PathVariable("id") final int id) {

		return new ResponseEntity<Boolean>(service.deleteStock(id), HttpStatus.OK);
	}

}
