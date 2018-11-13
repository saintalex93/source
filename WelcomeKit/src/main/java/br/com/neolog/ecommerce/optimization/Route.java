package br.com.neolog.ecommerce.optimization;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solver.Bell;
import br.com.neolog.ecommerce.optimization.solver.Exact;
import br.com.neolog.ecommerce.optimization.solver.Heuristic;

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

    @Autowired
    private Bell bell;

    private static final Logger logger = Logger.getLogger( Exact.class.getName() );

    @Override
    public Solution optimize(
        final Problem problem )
    {
        // final long start = System.currentTimeMillis();
        // long quantity = 0;
        // for( final Item problemItem : problem.getProblemItems() ) {
        // quantity += problemItem.getQuantity();
        // }
        // if( quantity > 200 ) {
        // Solution solution = new Solution();
        // solution = heuristic.optimize( problem );
        // logger.info( "Tempo para solu��o (Heuristc): " + (
        // System.currentTimeMillis() - start ) );
        // return solution;
        // }
        // Solution solution = new Solution();
        // solution = exact.optimize( problem );
        // logger.info( "Tempo para solu��o (Exact): " + (
        // System.currentTimeMillis() - start ) );
        // return solution;

        Solution solution = new Solution();
        solution = bell.optimize( problem );
        return solution;

    }
}
