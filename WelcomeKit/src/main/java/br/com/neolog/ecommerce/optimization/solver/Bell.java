package br.com.neolog.ecommerce.optimization.solver;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.Optimization;
import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.solution.Solution;

@Component
public final class Bell
    implements
        Optimization
{

    @Override
    public Solution optimize(
        final Problem problem )
    {
        final List<Item> listItems = sliceListItem( problem );

        getBellArrayTarget( problem.getTarget(), listItems );

        // TODO Auto-generated method stub
        return Solution.empty();
    }

    private int[][] getBellArrayTarget(
        Long target,
        final List<Item> listItem )
    {
        target = target / 100;
        final int x = target.intValue();
        // for para fazer a primeira volta e colocar os índices 0, depois
        // arrumar.
        final int[][] bellArray = new int[ listItem.size() + 1 ][ x + 1 ];
        for( int i = 0; i <= target; i++ ) {

            bellArray[ 0 ][ i ] = 0;
            System.out.print( i + " " );

        }

        System.out.println( "" );

        for( int i = 1; i <= listItem.size(); i++ ) {
            for( int j = 0; j <= target; j++ ) {

                if( j == 0 ) {
                    bellArray[ i ][ j ] = 0;
                    System.out.print( bellArray[ i ][ j ] + " " );
                    continue;
                }

                final Long value = listItem.get( i - 1 ).getValue();

                if( value.intValue() > j ) {
                    bellArray[ i ][ j ] = 0;
                } else {
                    bellArray[ i ][ j ] = value.intValue();
                }

                if( j > 9 ) {
                    System.out.print( " " + bellArray[ i ][ j ] + " " );
                    continue;
                }

                System.out.print( bellArray[ i ][ j ] + " " );
            }

            System.out.println( "" );

        }

        return bellArray;

    }

    private List<Item> sliceListItem(
        final Problem problem )
    {

        final List<Item> separetedItem = new LinkedList<>();

        for( final Item item : problem.getProblemItems() ) {

            for( int i = 1; i <= item.getQuantity(); i++ ) {

                separetedItem.add( Item.create( item.getProductCode(), item.getQuantity(), 1 ) );
            }

        }

        return separetedItem;

    }

}
