package br.com.neolog.ecommerce.optimization.solution;

import java.util.LinkedList;
import java.util.List;

import br.com.neolog.ecommerce.optimization.problem.ProblemItem;

public class PreSolution {

	private long totalValue;
	private List<ProblemItem> problemItems = new LinkedList<>();

	public PreSolution(final long totalValue, final List<ProblemItem> problemItems) {
		super();
		this.totalValue = totalValue;
		this.problemItems = problemItems;
	}

	public PreSolution() {
	}

	public long getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(final long totalValue) {
		this.totalValue = totalValue;
	}

	public List<ProblemItem> getProblemItems() {
		return problemItems;
	}

	public void setProblemItems(final List<ProblemItem> problemItems) {
		this.problemItems = problemItems;
	}

	public void addItem(final ProblemItem problemItem) {
		setTotalValue(totalValue + problemItem.getValue() * problemItem.getQuantity());

		if (problemItems.contains(problemItem)) {
			ProblemItem existProblem = problemItems.get(problemItems.indexOf(problemItem));
			existProblem.setQuantity(existProblem.getQuantity() + problemItem.getQuantity());
		} else {
			problemItems.add(problemItem);

		}

	}


	@Override
	public String toString() {
		return "[totalValue=" + totalValue + ", problemItems=" + problemItems + "]";
	}

}
