package br.com.neolog.services;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.neolog.converters.ProblemConverter;
import br.com.neolog.converters.SolutionConverter;
import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.models.Problem;
import br.com.neolog.models.ProblemType;
import br.com.neolog.models.Solution;
import br.com.neolog.solvers.RouterSubsetSumSolver;

@RunWith( MockitoJUnitRunner.class )
public class OptimizationServiceTest
{
    @Mock
    private ProblemConverter problemConverter;

    @Mock
    private RouterSubsetSumSolver routerSubsetSumSolver;

    @Mock
    private SolutionConverter solutionConverter;

    @InjectMocks
    private OptimizationService optimizationService;

    @Test
    public void problemConverterShouldReturnAProblem()
    {
        final Problem problem = new Problem();

        final OptimizationHolder optmizationHolder = OptimizationHolder.newInstance( 15, ProblemType.VALUE );

        Mockito.when( problemConverter.convert( optmizationHolder ) ).thenReturn( problem );

        final Problem problemResult = problemConverter.convert( optmizationHolder );

        final Solution solution = routerSubsetSumSolver
            .getClosestSubsetSum( problemResult );

        final PresentationSolution map = PresentationSolution.of( Collections.emptyList(), 0 );

        Mockito.when( solutionConverter.convert( solution ) ).thenReturn( map );

        final PresentationSolution mapRes = optimizationService.optimizeShopList( optmizationHolder );
        Assert.assertNotNull( mapRes );

    }
}