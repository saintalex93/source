package br.com.neolog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.exceptions.CategoryNotFoundException;
import br.com.neolog.exceptions.ProductAlreadyExistsException;
import br.com.neolog.exceptions.ProductInStockException;
import br.com.neolog.exceptions.ProductNotFoundException;
import br.com.neolog.pojo.Category;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.ProductQuantity;
import br.com.neolog.repository.CategoryRepository;
import br.com.neolog.repository.ProductRepository;
import br.com.neolog.repository.StockRepository;

@Component
public class ProductService {

	@Autowired
	public ProductRepository productRepository;
	@Autowired
	public StockRepository stockRepository;
	@Autowired
	public CategoryRepository categoryRepository;

	@Transactional
	public void add(Product product) throws ProductAlreadyExistsException,
			CategoryNotFoundException {
		Category category = categoryRepository.findByCode(product.getCategory()
				.getCode());

		Product productFromDatabase = productRepository.findByCode(product
				.getCode());
		if (productFromDatabase != null) {
			throw new ProductAlreadyExistsException(
					"Este produto já encontra-se na base de dados, impossível adicionar!!!");
		}
		if (category == null) {
			throw new CategoryNotFoundException(
					"Informe uma categoria válida!!!");
		}
		product.setCategory(category);
		productRepository.save(product);
	}

	public void remove(String code) throws ProductNotFoundException,
			ProductInStockException {
		Product product = productRepository.findByCode(code);
		ProductQuantity stock = stockRepository.findByProductCode(code);
		if (product == null) {
			throw new ProductNotFoundException("Produto não existe!!!");
		}
		if (stock == null) {
			productRepository.delete(product);
		} else {
			throw new ProductInStockException(
					"Produto encontra-se no estoque, remova-o do estoque antes de excluí-lo!!!");
		}
	}

	@Transactional
	public void update(Product product) throws ProductNotFoundException,
			CategoryNotFoundException {
		Product productFromDatabase = productRepository.findByCode(product
				.getCode());

		if (productFromDatabase != null) {
			if (product.getCategory().getCode()
					.equals(productFromDatabase.getCategory().getCode())) {
				product.setCategory(productFromDatabase.getCategory());
			} else {
				Category category = categoryRepository.findByCode(product
						.getCategory().getCode());
				if (category == null) {
					throw new CategoryNotFoundException(
							"Categoria não encontrada no sistema, informe uma categoria válida!!!");
				}
				product.setCategory(category);
			}

			product.setId(productFromDatabase.getId());
			productRepository.save(product);
		} else {
			throw new ProductNotFoundException("Produto não existe!!!");
		}
	}

	public Product find(String code) throws ProductNotFoundException {
		Product product = productRepository.findByCode(code);
		if (product == null) {
			throw new ProductNotFoundException("");
		}
		return product;
	}

	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}
}
