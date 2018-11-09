package br.com.neolog.ecommerce.optimization.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.Optimization;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.optimization.solution.Solution;

@Component
@Primary
public final class Route
    implements
        Optimization
{

    @Autowired
    private Heuristic heuristic;

    @Autowired
    private Exact exact;
    // private Exemplo exemplo;

    @Override
    public Solution optimize(
        final Problem problem )
    {
        long quantity = 0;
        for( final Item problemItem : problem.getProblemItems() ) {
            quantity += problemItem.getQuantity();
        }

        if( quantity > 100 ) {
            return heuristic.optimize( problem );
        }

        return exact.optimize( problem );
    }

}
