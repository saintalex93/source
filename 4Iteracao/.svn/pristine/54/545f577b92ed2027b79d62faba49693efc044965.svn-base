package br.com.neolog.converters;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.PresentationClass;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.Solution;
import br.com.neolog.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class SolutionConverterTest {
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private SolutionConverter solutionConverter;

	@Test
	public void mustResturnAMap() {
		Solution solution = new Solution();

		HashSet<String> codes = new HashSet<String>();
		codes.add("");
		Mockito.when(productRepository.findByCodeIn(codes)).thenReturn(
				new HashSet<Product>());

		PresentationClass result = solutionConverter.convert(solution);
		Assert.assertNotNull(result);

	}

	@Test
	public void mustResturnAMap2() {
		Solution solution = new Solution();
		HashSet<HolderCodePrice> products = new HashSet<HolderCodePrice>();

		HolderCodePrice holder = new HolderCodePrice();
		holder.setCode("");
		holder.setPrice(100);

		HolderCodePrice holder2 = new HolderCodePrice();
		holder2.setCode("");
		holder2.setPrice(100);

		products.add(holder);
		products.add(holder2);
		solution.setProducts(products);

		HashSet<String> codes = new HashSet<String>();
		codes.add("");

		HashSet<Product> hashSetProduct = new HashSet<Product>();
		Product product = new Product();
		product.setCode("");
		hashSetProduct.add(product);

		Mockito.when(productRepository.findByCodeIn(codes)).thenReturn(
				hashSetProduct);

		PresentationClass result = solutionConverter.convert(solution);

		Assert.assertNotNull(result);

	}
}
