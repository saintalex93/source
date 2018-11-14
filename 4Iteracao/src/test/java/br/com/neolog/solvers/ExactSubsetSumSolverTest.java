package br.com.neolog.solvers;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;

public class ExactSubsetSumSolverTest {

	private ExactSubsetSumSolver exactSubsetSumSolver = new ExactSubsetSumSolver();

	@Test
	public void mustReturnAEmptySolutionIfProblemsInternalProductsIsEmpty() {
		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodePrice>());

		Solution solution = exactSubsetSumSolver.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).isNull();

	}

	@Test
	public void MustReturnANewSolution() {
		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodePrice>());

		HolderCodePrice holder1 = new HolderCodePrice();
		holder1.setCode("PS4");
		holder1.setPrice(1);

		HolderCodePrice holder2 = new HolderCodePrice();
		holder2.setCode("XBOX");
		holder2.setPrice(5);

		problem.setTarget(10);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);

		Solution solution = exactSubsetSumSolver.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).isNotNull();

	}
}
