package br.com.neolog.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.neolog.converters.ProblemConverter;
import br.com.neolog.converters.SolutionConverter;
import br.com.neolog.pojo.PresentationClass;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;
import br.com.neolog.solvers.RouterSubsetSumSolver;

@RunWith(MockitoJUnitRunner.class)
public class OptimizationServiceTest {
	@Mock
	private ProblemConverter problemConverter;

	@Mock
	private RouterSubsetSumSolver routerSubsetSumSolver;

	@Mock
	private SolutionConverter solutionConverter;

	@InjectMocks
	private OptimizationService optimizationService;

	@Test
	public void problemConverterShouldReturnAProblem() {
		Problem problem = new Problem();

		Mockito.when(problemConverter.convert(15)).thenReturn(problem);

		Problem problemResult = problemConverter.convert(15);

		Solution solution = routerSubsetSumSolver
				.getClosestSubsetSum(problemResult);

		PresentationClass map = new PresentationClass();

		Mockito.when(solutionConverter.convert(solution)).thenReturn(map);

		PresentationClass mapRes = optimizationService.optimizeShopList(15);
		Assert.assertNotNull(mapRes);

	}
}