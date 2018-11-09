package br.com.neolog.ecommerce.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.PreProblem;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class ExactSingle
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
            return solutionFactory.getSolution( problem.getProblemItems() );
        }

        // Inserir dentro do brute
        for( final ProblemItem problemItem : problem.getProblemItems() ) {
            totalValue = problemItem.getQuantity() * problemItem.getValue();
            if( problem.getTarget() == totalValue ) {
                return solutionFactory.getSolution(  Collections.singletonList( problemItem ) );
            }
        }

        final List<PreProblem> preProblemList = extract( problem );

        brute( preProblemList );

        System.out.println( preProblemList.toString() );

        return null;

    }

    private List<PreProblem> brute(
        final List<PreProblem> preProblemList )
    {

        for( int i = 0; i < preProblemList.size(); i++ ) {

            for( final int j = 0; j < preProblemList.size(); i++ ) {

                if( preProblemList.get( i ).getPresent() == 1 ) {
                    preProblemList.get( i ).setPresent( 0 );

                    return brute( preProblemList );

                }

                preProblemList.get( i ).setPresent( 1 );
                System.out.println( preProblemList.toString() );
                return brute( preProblemList );

            }
            return brute( preProblemList );
        }

        return preProblemList;

    }

    private List<PreProblem> extract(
        final Problem problem )
    {
        final List<PreProblem> preProblemList = new ArrayList<>();
        final List<ProblemItem> problemItemList = new ArrayList<>( problem.getProblemItems() );

        for( int i = 0; i < problemItemList.size(); i++ ) {

            while( problemItemList.get( i ).getQuantity() > 0 ) {
                preProblemList.add( new PreProblem( problemItemList.get( i ).getProductCode(), problemItemList.get( i ).getValue(),
                    0 ) );

                problemItemList.get( i ).setQuantity( problemItemList.get( i ).getQuantity() - 1 );
            }

        }

        return preProblemList;
    }

}
