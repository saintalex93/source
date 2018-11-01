package br.com.neolog.ecommerce.optimization;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class Exact
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

        final long start = System.currentTimeMillis();

        final long totalValue = utils.getTotalProblemValue( problem );
        if( totalValue <= problem.getTarget() ) {
            return solutionFactory.getSolution( totalValue, problem.getProblemItems() );
        }

        // for( final ProblemItem problemItem : problem.getProblemItems() ) {
        // totalValue = utils.getTotalProblemItemValue( problemItem );
        // if( problem.getTarget() == totalValue ) {
        // return solutionFactory.getSolution( totalValue,
        // Collections.singletonList( problemItem ) );
        // }
        // }

        final long actualValue = 0l;
        Collections.emptyList(); // Lista separada.
        // BestResult
        // brute( list, target );

        //
        //
        // final List<ProblemItem> list = ImmutableList.copyOf(
        // problem.getProblemItems().subList( 1,
        // problem.getProblemItems().size() ) );

        // }

        final Solution solution = solve( problem );

        System.out.println( "Tempo para solução." + ( start - System.currentTimeMillis() ) );
        return solution;
    }

    public List<ProblemItem> brute(
        final long target,
        final List<ProblemItem> problemItemList )
    {

        if( problemItemList.isEmpty() ) {

            return Collections.emptyList();
        }

        for( final ProblemItem problemItem : problemItemList ) {

            // brute( toAlaniseProblemItem, problemItemList );

        }

        return problemItemList;

    }

    private Solution solve(
        final Problem problem )
    {

        return solutionFactory.emptySolution();
    }

}
