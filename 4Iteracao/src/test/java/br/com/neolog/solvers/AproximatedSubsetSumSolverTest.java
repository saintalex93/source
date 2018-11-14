package br.com.neolog.solvers;

import java.util.ArrayList;
import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;

public class AproximatedSubsetSumSolverTest {

	private AproximatedSubsetSumSolver aproximatedSubsetSumSolver = new AproximatedSubsetSumSolver();

	@Test
	public void shouldEnterInIfConditionInInnerClassOrdenateCompareMethod() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodePrice>());
		problem.setTarget(1);

		HolderCodePrice holder1 = new HolderCodePrice();
		holder1.setCode("PS4");
		holder1.setPrice(1);

		HolderCodePrice holder2 = new HolderCodePrice();
		holder2.setCode("XBOX");
		holder2.setPrice(5);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);
		problem.setTarget(6);

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		ArrayList<HolderCodePrice> holderCodePrice = new ArrayList<HolderCodePrice>(
				solution.getProducts());
		HolderCodePrice result = holderCodePrice.get(0);
		System.err.println(result.getPrice());
		Assertions.assertThat(result.getPrice()).isEqualTo(5);

	}

	@Test
	public void shouldEnterInElseConditionInInnerClassOrdenateCompareMethod() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodePrice>());
		problem.setTarget(1);

		HolderCodePrice holder1 = new HolderCodePrice();
		holder1.setCode("PS4");
		holder1.setPrice(5);

		HolderCodePrice holder2 = new HolderCodePrice();
		holder2.setCode("XBOX");
		holder2.setPrice(1);

		problem.getProducts().add(holder1);
		problem.getProducts().add(holder2);
		problem.setTarget(6);

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		ArrayList<HolderCodePrice> holderCodePrice = new ArrayList<HolderCodePrice>(
				solution.getProducts());
		HolderCodePrice result = holderCodePrice.get(0);
		System.err.println(result.getPrice());
		Assertions.assertThat(result.getPrice()).isEqualTo(5);

	}

	@Test
	public void ExpectedSolutionHasANumberOfProductsLimitedByTheProblemsTarget() {

		Problem problem = new Problem();
		problem.setProducts(new HashSet<HolderCodePrice>());

		HolderCodePrice holder1 = new HolderCodePrice();
		HolderCodePrice holder2 = new HolderCodePrice();

		holder1.setCode("XBOX");
		holder1.setPrice(10);

		holder2.setCode("PS4");
		holder2.setPrice(20);

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
		problem.setProducts(new HashSet<HolderCodePrice>());

		for (int i = 0; i < 1500; i++) {
			HolderCodePrice holder = new HolderCodePrice();
			holder.setCode("PS4");
			holder.setPrice(1);
			problem.getProducts().add(holder);
		}

		problem.setTarget(3000);
		System.err.println(problem.getProducts().size());

		Solution solution = aproximatedSubsetSumSolver
				.getClosestSubsetSum(problem);
		Assertions.assertThat(solution.getProducts()).hasSize(1000);
	}

}
