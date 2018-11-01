package br.com.neolog.ecommerce.optimization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class Heuristic
    implements
        Optimization
{

    @Autowired
    private SolutionFactory solutionFactory;

    @Autowired
    private OptimizationUtils utils;

    @Override
    public Solution optimize(
        final Problem problem )
    {

        final long totalValue = utils.getTotalProblemValue( problem );
        if( totalValue <= problem.getTarget() ) {
            return solutionFactory.getSolution( totalValue, problem.getProblemItems() );
        }

        return solve( problem );
    }

    private Solution solve(
        final Problem problem )
    {

        long solvedValue = 0l;
        final List<ProblemItem> solvedProblemItems = new ArrayList<ProblemItem>();

        for( final ProblemItem problemItem : problem.getProblemItems() ) {

            final long productValue = problemItem.getValue();
            final long quantity = ( problem.getTarget() - solvedValue ) / productValue;

            if( quantity < 1 ) {
                continue;
            }

            if( quantity <= problemItem.getQuantity() ) {
                problemItem.setQuantity( quantity );
            }

            solvedProblemItems.add( problemItem );
            solvedValue += problemItem.getQuantity() * productValue;

            if( solvedValue == problem.getTarget() ) {
                break;
            }

        }

        return solutionFactory.getSolution( solvedValue, solvedProblemItems );

    }

}
