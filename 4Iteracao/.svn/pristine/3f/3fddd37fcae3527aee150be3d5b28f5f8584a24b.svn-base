package br.com.neolog.solvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;

@Component
public class RouterSubsetSumSolver implements SubsetSumSolver {

	@Autowired
	private ExactSubsetSumSolver exactSubsetSumSolver;
	@Autowired
	private AproximatedSubsetSumSolver aproximatedSubsetSumSolver;

	@Override
	public Solution getClosestSubsetSum(Problem problem) {
		System.err.println("itens: " + problem.getProducts().size());
		if (problem.getProducts().size() > 17) {
			return aproximatedSubsetSumSolver.getClosestSubsetSum(problem);
		}
		return exactSubsetSumSolver.getClosestSubsetSum(problem);

	}
}