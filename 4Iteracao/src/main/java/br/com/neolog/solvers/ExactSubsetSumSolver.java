package br.com.neolog.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;

/**
 * 
 * @author igor.kurman
 * 
 */
@Component
public class ExactSubsetSumSolver implements SubsetSumSolver {

	@Override
	public Solution getClosestSubsetSum(Problem problem) {
		System.err.println("CAIU NO EXACT");
		if (problem.getProducts().isEmpty()) {

			return new Solution();
		}

		Resolver resolver = new Resolver();
		resolver.resolve(problem);

		Solution solution = new Solution();
		solution.setProducts(resolver.getBestCombination());
		return solution;
	}

	private class Resolver {

		Set<HolderCodePrice> bestCombination;
		double bestSum;

		public Set<HolderCodePrice> getBestCombination() {
			return bestCombination;
		}

		private double sum(Set<HolderCodePrice> newSet) {
			double total = 0;
			for (HolderCodePrice h : newSet) {
				total = total + h.getPrice();
			}

			return total;
		}

		public Set<Set<HolderCodePrice>> resolve(Problem problem) {

			Set<Set<HolderCodePrice>> returnSet = new HashSet<Set<HolderCodePrice>>();

			if (problem.getProducts().isEmpty()) {
				returnSet.add(new HashSet<HolderCodePrice>());
				return returnSet;
			}

			List<HolderCodePrice> list = new ArrayList<HolderCodePrice>(
					problem.getProducts());

			HolderCodePrice head = list.get(0);

			Set<HolderCodePrice> rest = new HashSet<HolderCodePrice>(
					list.subList(1, list.size()));

			problem.setProducts(rest);

			for (Set<HolderCodePrice> aCombination : resolve(problem)) {

				Set<HolderCodePrice> newSet = new HashSet<HolderCodePrice>();
				newSet.add(head);
				newSet.addAll(aCombination);
				returnSet.add(newSet);
				returnSet.add(aCombination);

				double combinationSum = sum(newSet);

				if (combinationSum > bestSum
						&& combinationSum <= problem.getTarget()) {
					bestCombination = newSet;
					bestSum = combinationSum;
				}

			}

			return returnSet;

		}

	}

}