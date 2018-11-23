package br.com.neolog.solvers;

import java.util.Comparator;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;

@Component
public class AproximatedSubsetSumSolver
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
        final TreeSet<HolderCodeValue> ordenedHolderCodePriceSet = new TreeSet<HolderCodeValue>(
            new ValueComparator() );

        ordenedHolderCodePriceSet.addAll( problem.getProducts() );

        final TreeSet<HolderCodeValue> separatedItems = new TreeSet<HolderCodeValue>(
            new ValueComparator() );

        double currentValue = 0;
        for( final HolderCodeValue holderItem : ordenedHolderCodePriceSet ) {

            if( currentValue == problem.getTarget() ) {
                break;
            }

            if( currentValue + holderItem.getValue() <= problem.getTarget() ) {
                currentValue = currentValue + holderItem.getValue();
                separatedItems.add( holderItem );
            }

        }
        return Solution.create( separatedItems );
    }

    private class ValueComparator
        implements
            Comparator<HolderCodeValue>
    {
        @Override
        public int compare(
            final HolderCodeValue holder1,
            final HolderCodeValue holder2 )
        {
            if( holder1.getValue() > holder2.getValue() ) {
                return - 1;
            }
            return 1;
        }

    }

}
