package br.com.neolog.optimization;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;
import br.com.neolog.solvers.ExactSubsetSumSolver;

public class ExactSubsetSumSolverTest
{

    private final ExactSubsetSumSolver exactSubsetSumSolver = new ExactSubsetSumSolver();

    @Test
    public void mustReturnAEmptySolutionIfProblemsInternalProductsIsEmpty()
    {
        final Problem problem = new Problem();
        problem.setProducts( new HashSet<>() );

        final Solution solution = exactSubsetSumSolver.getClosestSubsetSum( problem );
        Assertions.assertThat( solution.getProducts() ).isEmpty();

    }

    @Test
    public void MustReturnANewSolution()
    {
        final Problem problem = new Problem();
        problem.setProducts( new HashSet<>() );

        final HolderCodeValue holder1 = new HolderCodeValue();
        holder1.setCode( "PS4" );
        holder1.setValue( 1 );

        final HolderCodeValue holder2 = new HolderCodeValue();
        holder2.setCode( "XBOX" );
        holder2.setValue( 5 );

        problem.setTarget( 10 );

        problem.getProducts().add( holder1 );
        problem.getProducts().add( holder2 );

        problem.getProducts().add( holder1 );
        problem.getProducts().add( holder2 );

        final Solution solution = exactSubsetSumSolver.getClosestSubsetSum( problem );
        Assertions.assertThat( solution.getProducts() ).isNotNull();

    }
}
