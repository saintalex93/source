package br.com.neolog.ecommerce.optimization;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class Exemplo implements Optimization {

	@Autowired
	private SolutionFactory solutionFactory;

	@Override
	public Solution optimize(final Problem problem) {


		return solutionFactory.getSolution( recursive(problem.getTarget(), problem.getProblemItems()));

	}

	private List<ProblemItem> recursive(final long target, final List<ProblemItem> list) {
		if (target <= 0) {
			return Collections.emptyList();
		}
		if (list.isEmpty()) {
			return Collections.emptyList();
		}
		List<ProblemItem> bestResult = new LinkedList<>();
		for (final ProblemItem problemItem : list) {
			for (int quantity = 0; quantity <= problemItem.getQuantity(); quantity++) {
				final long newTarget = target - problemItem.getValue() * quantity;
				List<ProblemItem> partialSolution = new LinkedList<>();
				if (newTarget >= 0 && list.size() > 1) {
					partialSolution = recursive(newTarget, list.subList(1, list.size()));
				}
				partialSolution.add(generateSolutionProblemItem(problemItem, quantity));
				final long partialSolutionValue = getTotalValue(partialSolution);
				if (partialSolutionValue > getTotalValue(bestResult) && partialSolutionValue <= target) {
					bestResult = partialSolution;
				}
			}
		}
		return bestResult;
	}

	private ProblemItem generateSolutionProblemItem(final ProblemItem item, final int quantity) {
		return new ProblemItem(item.getProductCode(), item.getValue(), quantity);
	}

	private long getTotalValue(final List<ProblemItem> list) {
		long totalValue = 0;
		for (final ProblemItem problemItem : list) {
			totalValue += problemItem.getValue() * problemItem.getQuantity();
		}
		return totalValue;

	}

}