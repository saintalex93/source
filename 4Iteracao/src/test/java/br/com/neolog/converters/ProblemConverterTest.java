package br.com.neolog.converters;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.Problem;
import br.com.neolog.models.ProblemType;
import br.com.neolog.models.Product;
import br.com.neolog.models.Stock;
import br.com.neolog.repository.StockRepository;

@RunWith( MockitoJUnitRunner.class )
public class ProblemConverterTest
{
    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private ProblemConverter problemConverter;

    @Test
    public void shouldReturnAProblem()
    {
        final List<Stock> set = new ArrayList<>();
        final Product product1 = new Product();
        product1.setCode( "PS4" );
        product1.setPrice( 4 );
        final Stock productQuantity = new Stock();
        productQuantity.setProduct( product1 );
        productQuantity.setQuantity( 2 );
        set.add( productQuantity );

        Mockito.when( stockRepository.findByProductPriceLessThanEqual( 4 ) )
            .thenReturn( set );

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 4, ProblemType.VALUE );
        final Problem problem = problemConverter.convert( optmizationHolder );
        Assert.assertNotNull( problem );

    }

}
