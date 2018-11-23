package br.com.neolog.solvers;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;

@RunWith( MockitoJUnitRunner.class )
public class RouterSubsetSumSolverTest
{
    @Mock
    private AproximatedSubsetSumSolver aproximatedSubsetSumSolver;

    @Mock
    private ExactSubsetSumSolver exactSubsetSumSolver;

    @InjectMocks
    private RouterSubsetSumSolver routerSubsetSumSolver;

    @Test
    public void mustReturnASolutionFromAproximatedSubSetSumSolver()
    {
        final Solution solution = Solution.emptySolution();

        final Problem problem = new Problem();
        final HashSet<HolderCodeValue> set = new HashSet<HolderCodeValue>();

        for( int i = 0; i < 18; i++ ) {
            set.add( new HolderCodeValue() );
        }

        set.size();
        problem.setProducts( set );

        Mockito.when( aproximatedSubsetSumSolver.getClosestSubsetSum( problem ) )
            .thenReturn( solution );

        Mockito.when( exactSubsetSumSolver.getClosestSubsetSum( problem ) )
            .thenReturn( solution );

        final Solution solutionResult = routerSubsetSumSolver
            .getClosestSubsetSum( problem );
        Assert.assertNotNull( solutionResult );
    }

    @Test
    public void mustReturnASolutionFromExactSubSetSumSolver()
    {
        final Solution solution = Solution.emptySolution();

        final Problem problem = new Problem();
        final HashSet<HolderCodeValue> set = new HashSet<HolderCodeValue>();

        for( int i = 0; i < 5; i++ ) {
            set.add( new HolderCodeValue() );
        }

        set.size();
        problem.setProducts( set );

        Mockito.when( aproximatedSubsetSumSolver.getClosestSubsetSum( problem ) )
            .thenReturn( solution );

        Mockito.when( exactSubsetSumSolver.getClosestSubsetSum( problem ) )
            .thenReturn( solution );

        final Solution solutionResult = routerSubsetSumSolver
            .getClosestSubsetSum( problem );
        Assert.assertNotNull( solutionResult );
    }

}
