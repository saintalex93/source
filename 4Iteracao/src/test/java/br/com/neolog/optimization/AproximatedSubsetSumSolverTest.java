package br.com.neolog.optimization;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;
import br.com.neolog.solvers.AproximatedSubsetSumSolver;

@RunWith( SpringJUnit4ClassRunner.class )
public class AproximatedSubsetSumSolverTest
{

    @InjectMocks
    private AproximatedSubsetSumSolver aproximated;

    @Test
    public void shouldAssertReturnTrueWhenSumOfProductsIsEqualsToTarget()
    {
        final Set<HolderCodeValue> products = new HashSet<>();

        for( int i = 0; i < 251; i++ ) {
            products.add( HolderCodeValue.create( "gosto", 1 ) );
            products.add( HolderCodeValue.create( "muito", 1 ) );
            products.add( HolderCodeValue.create( "de", 1 ) );
            products.add( HolderCodeValue.create( "batata", 1 ) );
        }

        final Problem problem = Problem.create( products, 1004 );
        final Solution solution = aproximated.getClosestSubsetSum( problem );
        assertThat( solution.getProducts() ).isNotEmpty();
        long value = 0;
        for( final HolderCodeValue item : solution.getProducts() ) {
            value += item.getValue();
        }
        assertThat( Long.valueOf( value ).doubleValue() ).isEqualTo( problem.getTarget() );
    }

}
