package br.com.neolog.ecommerce.optimization.solution;

import java.util.List;

public class Solution
{

    private long result;
    List<SolutionItem> solutionItems;

    public Solution()
    {
    }

    public Solution(
        final long result,
        final List<SolutionItem> solutionItems )
    {
        this.result = result;
        this.solutionItems = solutionItems;
    }

    public double getResult()
    {
    	return result / 100.0;
    }

    public List<SolutionItem> getSolutionItems()
    {
        return solutionItems;
    }

}
