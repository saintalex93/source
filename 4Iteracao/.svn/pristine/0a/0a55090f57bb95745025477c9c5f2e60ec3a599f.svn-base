package br.com.neolog.solvers;

import java.util.Comparator;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.neolog.pojo.HolderCodePrice;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Solution;

@Component
public class AproximatedSubsetSumSolver implements SubsetSumSolver {

	@Override
	public Solution getClosestSubsetSum(Problem problem) {
		System.err.println("CAIU NO APROXIMATED");
		TreeSet<HolderCodePrice> ordenedHolderCodePriceSet = new TreeSet<HolderCodePrice>(
				new Ordenate());

		for (HolderCodePrice h : problem.getProducts()) {
			ordenedHolderCodePriceSet.add(h);
		}

		double currentSum = 0;

		TreeSet<HolderCodePrice> set = new TreeSet<HolderCodePrice>(
				new Ordenate());

		String code = ordenedHolderCodePriceSet.first().getCode();
		int quantity = 0;

		for (HolderCodePrice h : ordenedHolderCodePriceSet) {

			if (currentSum + h.getPrice() <= problem.getTarget()) {

				if (code.equals(h.getCode())) {
					if (quantity < 1000) {
						quantity++;
						currentSum = currentSum + h.getPrice();
						set.add(h);
					}

				} else {
					code = h.getCode();
					quantity = 1;
					currentSum = currentSum + h.getPrice();
					set.add(h);
				}
			}
		}
		Solution solution = new Solution();
		solution.setProducts(set);

		return solution;
	}

	private class Ordenate implements Comparator<HolderCodePrice> {
		@Override
		public int compare(HolderCodePrice o1, HolderCodePrice o2) {
			if (o1.getPrice() > o2.getPrice()) {
				return -1;
			}
			return 1;
		}

	}

}
