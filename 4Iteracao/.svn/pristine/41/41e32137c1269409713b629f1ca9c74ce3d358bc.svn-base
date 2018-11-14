package br.com.neolog.solvers;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;

@RunWith(MockitoJUnitRunner.class)
public class RouterSubsetSumSolverTest {
	@Mock
	private AproximatedSubsetSumSolver aproximatedSubsetSumSolver;

	@Mock
	private ExactSubsetSumSolver exactSubsetSumSolver;

	@InjectMocks
	private RouterSubsetSumSolver routerSubsetSumSolver;

	@Test
	public void mustReturnASolutionFromAproximatedSubSetSumSolver() {
		Solution solution = new Solution();

		Problem problem = new Problem();
		HashSet<HolderCodePrice> set = new HashSet<HolderCodePrice>();

		for (int i = 0; i < 18; i++) {
			set.add(new HolderCodePrice());
		}

		set.size();
		problem.setProducts(set);

		Mockito.when(aproximatedSubsetSumSolver.getClosestSubsetSum(problem))
				.thenReturn(solution);

		Mockito.when(exactSubsetSumSolver.getClosestSubsetSum(problem))
				.thenReturn(solution);

		Solution solutionResult = routerSubsetSumSolver
				.getClosestSubsetSum(problem);
		Assert.assertNotNull(solutionResult);
	}

	@Test
	public void mustReturnASolutionFromExactSubSetSumSolver() {
		Solution solution = new Solution();

		Problem problem = new Problem();
		HashSet<HolderCodePrice> set = new HashSet<HolderCodePrice>();

		for (int i = 0; i < 5; i++) {
			set.add(new HolderCodePrice());
		}

		set.size();
		problem.setProducts(set);

		Mockito.when(aproximatedSubsetSumSolver.getClosestSubsetSum(problem))
				.thenReturn(solution);

		Mockito.when(exactSubsetSumSolver.getClosestSubsetSum(problem))
				.thenReturn(solution);

		Solution solutionResult = routerSubsetSumSolver
				.getClosestSubsetSum(problem);
		Assert.assertNotNull(solutionResult);
	}

}
