package br.com.neolog.ecommerce.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.exceptions.StockDuplicatedProductException;
import br.com.neolog.ecommerce.exceptions.StockFillException;
import br.com.neolog.ecommerce.exceptions.StockNotFoundException;
import br.com.neolog.ecommerce.product.Product;
import br.com.neolog.ecommerce.product.ProductService;

@Component
public class StockService {

	@Autowired
	StockRepository repository;

	@Autowired
	ProductService productService;

	public List<Stock> getStock() {
		return repository.findAll();
	}

	public Stock getProductStock(final Product p) {
		final Stock stock = repository.findByProduct(p);
		if (stock == null) {
			throw new StockNotFoundException();
		}
		return stock;
	}

	public Stock getProductStock(final int idProduct) {
		final Product objProduct = productService.getProductById(idProduct);

		final Stock stock = repository.findByProduct(objProduct);
		if (stock == null) {
			throw new StockNotFoundException();
		}
		return stock;
	}

	public List<Stock> getActiveStock() {
		return repository.findByQuantityGreaterThan(0);
	}

	public List<Stock> getInactiveStock() {
		return repository.findByQuantityLessThan(1);
	}

	public Stock getMaxProduct() {
		return repository.maxProduct();
	}

	public Stock getMinProduct() {
		return repository.minProduct();
	}

	public Stock createStock(final StockItem stockItem) {
		if (productService.getProductById(stockItem.getIdProduct()) != null) {
			throw new StockDuplicatedProductException();
		}
		if (stockItem.getQuantity() < 1) {
			throw new StockFillException("Quantidade deve ser maior que 1");
		}

		final Product objProduct = productService.getProductById(stockItem.getIdProduct());

		return repository.save(new Stock(stockItem.getQuantity(), objProduct));

	}

	public Stock addStock(final StockItem stockItem) {
		if (stockItem.getQuantity() < 1) {
			throw new StockFillException("Quantidade deve ser maior que 1");
		}

		final Product objProduct = productService.getProductById(stockItem.getIdProduct());

		final Stock objStock = getProductStock(objProduct);

		if (stockItem.getQuantity() + objStock.getQuantity() > Integer.MAX_VALUE) {
			throw new StockFillException("Quantidade informada � maior do que a permitida");
		}

		return repository
				.save(new Stock(objStock.getId(), stockItem.getQuantity() + objStock.getQuantity(), objProduct));

	}

	public Stock removeStock(final StockItem stockItem) {
		final Product objProduct = productService.getProductById(stockItem.getIdProduct());
		final Stock objStock = getProductStock(objProduct);

		if (stockItem.getQuantity() > objStock.getQuantity()) {
			throw new StockFillException("Quantidade informada � maior que estoque");
		}

		return repository
				.save(new Stock(objStock.getId(), objStock.getQuantity() - stockItem.getQuantity(), objProduct));

	}

	public boolean deleteStock(final int idStock) {

		if (!repository.existsById(idStock)) {
			throw new StockNotFoundException();
		}

		repository.deleteById(idStock);
		return !repository.existsById(idStock);
	}

}
