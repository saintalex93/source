package br.com.neolog.converters;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.Stock;
import br.com.neolog.repository.StockRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProblemConverterTest {
	@Mock
	private StockRepository stockRepository;
	@InjectMocks
	private ProblemConverter problemConverter;

	@Test
	public void shouldReturnAProblem() {
		Set<Stock> set = new HashSet<Stock>();
		Product product1 = new Product();
		product1.setCode("PS4");
		product1.setPrice(4);
		Stock productQuantity = new Stock();
		productQuantity.setProduct(product1);
		productQuantity.setQuantity(2);
		set.add(productQuantity);

		Mockito.when(stockRepository.findByProductPriceLessThanEqual(4))
				.thenReturn(set);

		Problem problem = problemConverter.convert(4);
		Assert.assertNotNull(problem);

	}

}
