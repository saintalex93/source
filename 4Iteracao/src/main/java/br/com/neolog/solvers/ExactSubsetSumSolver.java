package br.com.neolog.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
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

    Logger logger = Logger.getLogger( this.getClass().getName() );

    @Override
    public Solution getClosestSubsetSum(
        final Problem problem )
    {
        logger.info( "EXACT" );
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

        private long sum(
            final Set<HolderCodeValue> newSet )
        {
            long total = 0;
            for( final HolderCodeValue h : newSet ) {
                total = total + h.getValue();
            }

            return total;
        }

        public Set<Set<HolderCodeValue>> resolve(
            final Problem problem )
        {

            final Set<Set<HolderCodeValue>> returnSet = new HashSet<Set<HolderCodeValue>>();

            if( problem.getProducts().isEmpty() ) {
                returnSet.add( new HashSet<HolderCodeValue>() );
                return returnSet;
            }

            final List<HolderCodeValue> list = new ArrayList<HolderCodeValue>(
                problem.getProducts() );

            final HolderCodeValue head = list.get( 0 );

            final Set<HolderCodeValue> rest = new HashSet<HolderCodeValue>(
                list.subList( 1, list.size() ) );

            problem.setProducts( rest );

            for( final Set<HolderCodeValue> aCombination : resolve( problem ) ) {

                final Set<HolderCodeValue> newSet = new HashSet<HolderCodeValue>();
                newSet.add( head );
                newSet.addAll( aCombination );
                returnSet.add( newSet );
                returnSet.add( aCombination );

                final long combinationSum = sum( newSet );

                if( combinationSum > bestSum
                    && combinationSum <= problem.getTarget() ) {
                    bestCombination = newSet;
                    bestSum = combinationSum;
                }

            }

            return returnSet;

        }

    }

}