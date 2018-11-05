package br.com.neolog.ecommerce.optimization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;

@Component
public class Route
    implements
        Optimization
{

    @Autowired
    private Heuristic heuristic;

    @Autowired
    private Exact exact;

    @Override
    public Solution optimize(
        final Problem problem )
    {
        long quantity = 0;
        for( final ProblemItem problemItem : problem.getProblemItems() ) {
            quantity += problemItem.getQuantity();
        }

        if( quantity > 10 ) {
            return heuristic.optimize( problem );
        }

        return exact.optimize( problem );
    }

}
