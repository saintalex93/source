package br.com.neolog.converters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.models.Product;
import br.com.neolog.models.Solution;
import br.com.neolog.repository.ProductRepository;

@RunWith( MockitoJUnitRunner.class )
public class SolutionConverterTest
{
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SolutionConverter solutionConverter;

    @Test
    public void mustResturnAMap()
    {

        final HashSet<String> codes = new HashSet<String>();
        codes.add( "" );
        Mockito.when( productRepository.findByCodeIn( codes ) ).thenReturn(
            new ArrayList<Product>() );

        final PresentationSolution result = solutionConverter.convert( Solution.emptySolution() );
        Assert.assertNotNull( result );

    }

    @Test
    public void mustResturnAMap2()
    {
        final HashSet<HolderCodeValue> products = new HashSet<HolderCodeValue>();

        final HolderCodeValue holder = new HolderCodeValue();
        holder.setCode( "" );
        holder.setValue( 100 );

        final HolderCodeValue holder2 = new HolderCodeValue();
        holder2.setCode( "" );
        holder2.setValue( 100 );

        products.add( holder );
        products.add( holder2 );
        final Solution solution = Solution.create( products );

        final HashSet<String> codes = new HashSet<String>();
        codes.add( "" );

        final List<Product> hashSetProduct = new ArrayList<Product>();
        final Product product = new Product();
        product.setCode( "" );
        hashSetProduct.add( product );

        Mockito.when( productRepository.findByCodeIn( codes ) ).thenReturn(
            hashSetProduct );

        final PresentationSolution result = solutionConverter.convert( solution );

        Assert.assertNotNull( result );

    }
}
