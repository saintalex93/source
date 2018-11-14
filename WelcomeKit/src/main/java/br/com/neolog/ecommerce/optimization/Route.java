package br.com.neolog.ecommerce.optimization;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Item;
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
        final long start = System.currentTimeMillis();
        long quantity = 0;
        for( final Item problemItem : problem.getProblemItems() ) {
            quantity += problemItem.getQuantity();
        }
        if( quantity > 200 ) {
            Solution solution = new Solution();
            solution = heuristic.optimize( problem );
            logger.info( "Tempo para solução (Heuristc): " + ( System.currentTimeMillis() - start ) );
            return solution;
        }
        Solution solution = new Solution();
        solution = exact.optimize( problem );
        logger.info( "Tempo para solução (Exact): " + ( System.currentTimeMillis() - start ) );
        return solution;

        // BELL ALGORITHM
        // final Item item1 = Item.create( 1, 800, 1 );
        // final Item item2 = Item.create( 1, 500, 1 );
        // final Item item3 = Item.create( 1, 400, 1 );
        // final Item item4 = Item.create( 1, 200, 1 );
        //
        // final List<Item> listItem = new LinkedList<>();
        //
        // listItem.add( item1 );
        // listItem.add( item2 );
        // listItem.add( item3 );
        // listItem.add( item4 );
        //
        // final Problem alternativeProblem = Problem.create( 1400, listItem );

        // Solution solution = new Solution();
        // solution = bell.optimize( Problem );
        // return solution;

    }
}
