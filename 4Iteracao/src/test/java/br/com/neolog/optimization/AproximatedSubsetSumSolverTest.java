package br.com.neolog.optimization;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
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

    private final AproximatedSubsetSumSolver aproximatedSubsetSumSolver = new AproximatedSubsetSumSolver();

    @Test
    public void shouldEnterInIfConditionInInnerClassOrdenateCompareMethod()
    {

        final Problem problem = new Problem();
        problem.setProducts( new HashSet<HolderCodeValue>() );
        problem.setTarget( 1 );

        final HolderCodeValue holder1 = new HolderCodeValue();
        holder1.setCode( "PS4" );
        holder1.setValue( 1 );

        final HolderCodeValue holder2 = new HolderCodeValue();
        holder2.setCode( "XBOX" );
        holder2.setValue( 5 );

        problem.getProducts().add( holder1 );
        problem.getProducts().add( holder2 );
        problem.setTarget( 6 );

        final Solution solution = aproximatedSubsetSumSolver
            .getClosestSubsetSum( problem );
        final ArrayList<HolderCodeValue> holderCodePrice = new ArrayList<HolderCodeValue>(
            solution.getProducts() );
        final HolderCodeValue result = holderCodePrice.get( 0 );
        System.err.println( result.getValue() );
        Assertions.assertThat( result.getValue() ).isEqualTo( 5 );

    }

    @Test
    public void shouldEnterInElseConditionInInnerClassOrdenateCompareMethod()
    {

        final Problem problem = new Problem();
        problem.setProducts( new HashSet<HolderCodeValue>() );
        problem.setTarget( 1 );

        final HolderCodeValue holder1 = new HolderCodeValue();
        holder1.setCode( "PS4" );
        holder1.setValue( 5 );

        final HolderCodeValue holder2 = new HolderCodeValue();
        holder2.setCode( "XBOX" );
        holder2.setValue( 1 );

        problem.getProducts().add( holder1 );
        problem.getProducts().add( holder2 );
        problem.setTarget( 6 );

        final Solution solution = aproximatedSubsetSumSolver
            .getClosestSubsetSum( problem );
        final ArrayList<HolderCodeValue> holderCodePrice = new ArrayList<HolderCodeValue>(
            solution.getProducts() );
        final HolderCodeValue result = holderCodePrice.get( 0 );
        System.err.println( result.getValue() );
        Assertions.assertThat( result.getValue() ).isEqualTo( 5 );

    }

    @Test
    public void ExpectedSolutionHasANumberOfProductsLimitedByTheProblemsTarget()
    {

        final Problem problem = new Problem();
        problem.setProducts( new HashSet<HolderCodeValue>() );

        final HolderCodeValue holder1 = new HolderCodeValue();
        final HolderCodeValue holder2 = new HolderCodeValue();

        holder1.setCode( "XBOX" );
        holder1.setValue( 10 );

        holder2.setCode( "PS4" );
        holder2.setValue( 20 );

        problem.getProducts().add( holder1 );
        problem.getProducts().add( holder2 );

        problem.setTarget( 30 );

        final Solution solution = aproximatedSubsetSumSolver
            .getClosestSubsetSum( problem );
        Assertions.assertThat( solution.getProducts() ).hasSize( 2 );
    }

    @Test
    public void ShoulLimitTheNumberOfRepeatedProductsToOneThousand()
    {

        final Problem problem = new Problem();
        problem.setProducts( new HashSet<HolderCodeValue>() );

        for( int i = 0; i < 1500; i++ ) {
            final HolderCodeValue holder = new HolderCodeValue();
            holder.setCode( "PS4" );
            holder.setValue( 1 );
            problem.getProducts().add( holder );
        }

        problem.setTarget( 3000 );
        System.err.println( problem.getProducts().size() );

        final Solution solution = aproximatedSubsetSumSolver
            .getClosestSubsetSum( problem );
        Assertions.assertThat( solution.getProducts() ).hasSize( 1500 );
    }

}
