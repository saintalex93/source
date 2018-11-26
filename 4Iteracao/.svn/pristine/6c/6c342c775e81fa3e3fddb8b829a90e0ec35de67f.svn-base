package br.com.neolog.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;

/**
 * @author igor.kurman
 */
@Component
public class ExactSubsetSumSolver
    implements
        SubsetSumSolver
{

    @Override
    public Solution getClosestSubsetSum(
        final Problem problem )
    {
        if( problem.getProducts().isEmpty() ) {
            return Solution.emptySolution();
        }

        final Resolver resolver = new Resolver();
        resolver.resolve( problem );

        return Solution.create( resolver.getBestCombination() );
    }

    private class Resolver
    {
        Set<HolderCodeValue> bestCombination;
        long bestSum;

        public Set<HolderCodeValue> getBestCombination()
        {
            return bestCombination;
        }

        public Set<Set<HolderCodeValue>> resolve(
            final Problem problem )
        {

            final Set<Set<HolderCodeValue>> returnSet = new HashSet<>();

            if( problem.getProducts().isEmpty() ) {
                returnSet.add( new HashSet<HolderCodeValue>() );
                return returnSet;
            }

            final List<HolderCodeValue> listItems = new ArrayList<>( problem.getProducts() );

            final HolderCodeValue head = listItems.get( 0 );

            final Set<HolderCodeValue> restItems = new HashSet<>(
                listItems.subList( 1, listItems.size() ) );

            problem.setProducts( restItems );

            for( final Set<HolderCodeValue> combination : resolve( problem ) ) {

                final Set<HolderCodeValue> newSet = new HashSet<>();
                newSet.add( head );
                newSet.addAll( combination );
                returnSet.add( newSet );
                returnSet.add( combination );

                final long combinationSum = sum( newSet );

                if( combinationSum > bestSum
                    && combinationSum <= problem.getTarget() ) {
                    bestCombination = newSet;
                    bestSum = combinationSum;
                }

            }

            return returnSet;

        }

        private long sum(
            final Set<HolderCodeValue> newSet )
        {
            long total = 0;
            for( final HolderCodeValue h : newSet ) {
                total = total + h.getValue();
            }

            return total;
        }

    }

}