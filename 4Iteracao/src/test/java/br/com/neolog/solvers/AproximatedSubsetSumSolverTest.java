package br.com.neolog.solvers;

import java.util.ArrayList;
import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;

public class AproximatedSubsetSumSolverTest {

	private AproximatedSubsetSumSolver aproximatedSubsetSumSolver = new AproximatedSubsetSumSolver();

	@Test
	public void shouldEnterInIfConditionInInnerClassOrdenateCompareMethod() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodeValue>());
		problem.setTarget(1);

		HolderCodeValue holder1 = new HolderCodeValue();
		holder1.setCode("PS4");
		holder1.setValue(1);

		HolderCodeValue holder2 = new HolderCodeValue();
		holder2.setCode("XBOX");
		holder2.setValue(5);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);
		problem.setTarget(6);

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		ArrayList<HolderCodeValue> holderCodePrice = new ArrayList<HolderCodeValue>(
				solution.getProducts());
		HolderCodeValue result = holderCodePrice.get(0);
		System.err.println(result.getValue());
		Assertions.assertThat(result.getValue()).isEqualTo(5);

	}

	@Test
	public void shouldEnterInElseConditionInInnerClassOrdenateCompareMethod() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodeValue>());
		problem.setTarget(1);

		HolderCodeValue holder1 = new HolderCodeValue();
		holder1.setCode("PS4");
		holder1.setValue(5);

		HolderCodeValue holder2 = new HolderCodeValue();
		holder2.setCode("XBOX");
		holder2.setValue(1);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);
		problem.setTarget(6);

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		ArrayList<HolderCodeValue> holderCodePrice = new ArrayList<HolderCodeValue>(
				solution.getProducts());
		HolderCodeValue result = holderCodePrice.get(0);
		System.err.println(result.getValue());
		Assertions.assertThat(result.getValue()).isEqualTo(5);

	}

	@Test
	public void ExpectedSolutionHasANumberOfProductsLimitedByTheProblemsTarget() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodeValue>());

		HolderCodeValue holder1 = new HolderCodeValue();
		HolderCodeValue holder2 = new HolderCodeValue();

		holder1.setCode("XBOX");
		holder1.setValue(10);

		holder2.setCode("PS4");
		holder2.setValue(20);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);

		problem.setTarget(30);

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).hasSize(2);
	}

	@Test
	public void ShoulLimitTheNumberOfRepeatedProductsToOneThousand() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodeValue>());

		for (int i = 0; i < 1500; i++) {
			HolderCodeValue holder = new HolderCodeValue();
			holder.setCode("PS4");
			holder.setValue(1);
			problem.getProducts().add(holder);
		}

		problem.setTarget(3000);
		System.err.println(problem.getProducts().size());

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).hasSize(1000);
	}

}
