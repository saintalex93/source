package br.com.neolog.solvers;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;

public class ExactSubsetSumSolverTest {

	private ExactSubsetSumSolver exactSubsetSumSolver = new ExactSubsetSumSolver();

	@Test
	public void mustReturnAEmptySolutionIfProblemsInternalProductsIsEmpty() {
		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodeValue>());

		Solution solution = exactSubsetSumSolver.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).isNull();

	}

	@Test
	public void MustReturnANewSolution() {
		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodeValue>());

		HolderCodeValue holder1 = new HolderCodeValue();
		holder1.setCode("PS4");
		holder1.setValue(1);

		HolderCodeValue holder2 = new HolderCodeValue();
		holder2.setCode("XBOX");
		holder2.setValue(5);

		problem.setTarget(10);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);

		Solution solution = exactSubsetSumSolver.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).isNotNull();

	}
}
