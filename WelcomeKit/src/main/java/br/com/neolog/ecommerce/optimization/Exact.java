package br.com.neolog.ecommerce.optimization;

import java.util.ArrayList;
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

        long totalValue = utils.getTotalProblemValue( problem );
        if( totalValue <= problem.getTarget() ) {
            return solutionFactory.getSolution( totalValue, problem.getProblemItems() );
        }

        // Inserir dentro do brute
        for( final ProblemItem problemItem : problem.getProblemItems() ) {
            totalValue = problemItem.getQuantity() * problemItem.getValue();
            if( problem.getTarget() == totalValue ) {
                return solutionFactory.getSolution( totalValue, Collections.singletonList( problemItem ) );
            }
        }

        // 30[3] 10[1] 20[2]

        brute( problem.getProblemItems(), problem.getTarget() );

        final Solution solution = solve( problem );

        System.out.println( "Tempo para solução." + ( start - System.currentTimeMillis() ) );
        return solution;

        /*
         * 100 20[4] 40[2] 50[3] 70[2] 30[5] for item for quant 100 - itm *
         * quant 40[2] 50[3] 70[2] 30[5]
         */

    }

    // for( final ProblemItem problemItem : problemItemList ) {
    // if( newTarget == problemItem.getQuantity() * problemItem.getValue() )
    // {
    //
    // betterList = solutionFactory.getSolution( problemItem.getQuantity() *
    // problemItem.getValue(), Collections.singletonList(
    // problemItem ) );
    //
    // return Collections.singletonList(problemItem );
    // }
    // }

    private final List<ProblemItem> separetedItems = new ArrayList<>();

    private final List<ProblemItem> betterSolution = Collections.emptyList();
    private long totalList = 0;

    public List<ProblemItem> brute(
        final List<ProblemItem> problemItemList,
        final long newTarget )
    {

        if( problemItemList.isEmpty() ) {

            return Collections.emptyList();
        }

        if( newTarget == 0 ) {
            return betterSolution;
        }

        for( int i = 0; i < problemItemList.size(); i++ ) {

            for( int j = 0; j < problemItemList.get( i ).getQuantity(); j++ ) {

                final long value = problemItemList.get( i ).getQuantity() * problemItemList.get( i ).getValue();

                problemItemList.get( i ).setQuantity( j + 1 );
                totalList += problemItemList.get( i ).getValue();

                separetedItems.add( problemItemList.get( i ) );
                System.out.println( separetedItems );
            }

        }

        brute( separetedItems, newTarget - totalList );

        return problemItemList;

    }

    public void addQuantity()
    {

    }

    public void removeQuantity()
    {

    }

    public void removeItem()
    {

    }

    public List<ProblemItem> getOriginalList(
        final List<ProblemItem> problemItems )
    {

        return Collections.emptyList();
    }

    private Solution solve(
        final Problem problem )
    {

        return solutionFactory.emptySolution();
    }

}
