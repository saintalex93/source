package br.com.neolog.ecommerce.optimization.solution;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.ImmutableList;

import br.com.neolog.ecommerce.optimization.problem.Item;

public class Solution
{
    private long result;
    List<Item> solutionItems;

    public Solution()
    {
    }

    public static Solution create(
        final long result,
        final List<Item> preSolution )
    {
        final Solution solution = new Solution();
        solution.result = result;
        solution.solutionItems = Objects.requireNonNull( preSolution );
        return solution;
    }

    public double getResult()
    {
        return result / 100.0;
    }

    public List<Item> getSolutionItems()
    {
        return solutionItems;
    }

    public static Solution empty()
    {
        return create( 0, ImmutableList.of() );

    }

}
