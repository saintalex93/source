package br.com.neolog.optimization;

import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;
import br.com.neolog.solvers.AproximatedSubsetSumSolver;
import br.com.neolog.solvers.ExactSubsetSumSolver;
import br.com.neolog.solvers.RouterSubsetSumSolver;

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

        for( int i = 0; i < 21; i++ ) {
            set.add( new HolderCodeValue() );
        }

        problem.setProducts( set );

        Mockito.when( aproximatedSubsetSumSolver.getClosestSubsetSum( problem ) )
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

        problem.setProducts( set );

        when( exactSubsetSumSolver.getClosestSubsetSum( problem ) ).thenReturn( solution );

        final Solution solutionResult = routerSubsetSumSolver.getClosestSubsetSum( problem );
        Assert.assertNotNull( solutionResult );
    }

}
