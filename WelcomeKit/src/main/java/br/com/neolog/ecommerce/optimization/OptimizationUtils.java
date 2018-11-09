package br.com.neolog.ecommerce.optimization;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.Item;

@Component
public final class OptimizationUtils
{
    public long getTotalProblemValue(
        final Problem problem )
    {
        final List<Item> problemItems = problem.getProblemItems();
        final long totalValueList = problemItems.stream()
            .mapToLong( problemItem -> ( problemItem.getValue() * problemItem.getQuantity() ) )
            .sum();
        return totalValueList;
    }

}
