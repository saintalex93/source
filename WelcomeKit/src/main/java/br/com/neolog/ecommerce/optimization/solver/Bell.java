package br.com.neolog.ecommerce.optimization.solver;

import java.util.ArrayList;
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

        final HolderBell holderBell = getBellArrayTarget( problem.getTarget(), listItems );

        final List<Integer> agglomerateListItem = agglomerateListItemValue( holderBell );

        final List<Item> solvedProblemItems = discoveryItemsByValue( agglomerateListItem, problem.getProblemItems() );

        // TODO Auto-generated method stub
        return Solution.create( holderBell.getSolvedValue(), solvedProblemItems );
    }

    private HolderBell getBellArrayTarget(
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

            if( i > 9 ) {
                System.out.print( " " + i + " " );
            } else if( i > 99 ) {
                System.out.print( " " + i + " " );
            } else {
                System.out.print( i + " " );
            }

        }

        System.out.println( "" );

        for( int line = 1; line <= listItem.size(); line++ ) {
            for( int currentTargetIndexCollumn = 0; currentTargetIndexCollumn <= target; currentTargetIndexCollumn++ ) {
                // Só serve para a impressão.
                final Long value = listItem.get( line - 1 ).getValue();
                if( currentTargetIndexCollumn == 0 ) {
                    bellArray[ line ][ currentTargetIndexCollumn ] = value.intValue();
                    System.out.print( bellArray[ line ][ currentTargetIndexCollumn ] + " " );
                    continue;
                }

                if( value.intValue() > currentTargetIndexCollumn ) {
                    final int superiorValueItem = bellArray[ line - 1 ][ currentTargetIndexCollumn ];
                    bellArray[ line ][ currentTargetIndexCollumn ] = maxBetween( 0, superiorValueItem );

                } else {
                    // Factível
                    final int modValue = Math.abs( currentTargetIndexCollumn - value.intValue() );

                    if( modValue > 0 ) {

                        final int superiorValueItem = bellArray[ line - 1 ][ currentTargetIndexCollumn ];
                        final int sumOfIndexUpper = value.intValue() + bellArray[ line - 1 ][ currentTargetIndexCollumn - value
                            .intValue() ];

                        bellArray[ line ][ currentTargetIndexCollumn ] = maxBetween( sumOfIndexUpper, superiorValueItem );
                    }
                    // Refatorar essa merda
                    else {
                        final int superiorValueItem = bellArray[ line - 1 ][ currentTargetIndexCollumn ];

                        bellArray[ line ][ currentTargetIndexCollumn ] = maxBetween( value.intValue(), superiorValueItem );
                    }
                }

                if( currentTargetIndexCollumn > 9 ) {
                    System.out.print( " " + bellArray[ line ][ currentTargetIndexCollumn ] + " " );
                    continue;
                }

                System.out.print( bellArray[ line ][ currentTargetIndexCollumn ] + " " );
            }

            System.out.println( "" );

        }

        return HolderBell.create( bellArray[ listItem.size() ][ target.intValue() ], listItem.size(), target.intValue(), bellArray );

    }

    private List<Item> sliceListItem(
        final Problem problem )
    {

        final List<Item> separetedItem = new LinkedList<>();

        for( final Item item : problem.getProblemItems() ) {

            for( int i = 1; i <= item.getQuantity(); i++ ) {

                separetedItem.add( Item.create( item.getProductCode(), item.getValue() / 100, 1 ) );
            }

        }

        return separetedItem;

    }

    private List<Integer> agglomerateListItemValue(
        final HolderBell holderBell )
    {
        final List<Integer> combinatedValues = new ArrayList<>();
        final int[][] holderBellArray = holderBell.getBellArray();
        int column = holderBell.getMaxCollums();
        for( int line = holderBell.getMaxLines(); line > 0; line-- ) {
            // declarei para fora, serve para o fluxo, mas melhorar.
            for( ; column > 0; ) {
                System.out.println( "Valor do produto: " + holderBellArray[ line ][ 0 ] );
                final int targetValue = holderBellArray[ line ][ 0 ];
                if( holderBellArray[ line ][ column ] != holderBellArray[ line - 1 ][ column ] ) {

                    combinatedValues.add( targetValue );

                }
                // Usei a mesma condição duas vezes, refatorar isso, pois dá
                // AIOOBE
                if( line != 1 && holderBellArray[ line ][ column ] != holderBellArray[ line - 1 ][ column ] ) {

                    column = holderBellArray[ line ][ column ] - targetValue;
                    System.out.println( "Pula para o índice: " + holderBellArray[ line ][ column ] );
                    break;
                }

                System.out.println( "Pula para o índice: " + holderBellArray[ line ][ column ] );

                break;

            }
            System.out.println( "Valores adicionados na lista como definitivos: " + combinatedValues );
            System.out.println( "" );

        }

        return combinatedValues;
    }

    private List<Item> discoveryItemsByValue(
        final List<Integer> agglomerateListValueItem,
        final List<Item> problemItems )
    {

        final List<Item> separatedProblemItems = new LinkedList<>( problemItems );

        for( int i = 0; i < agglomerateListValueItem.size(); i++ ) {

            for( final Item item : problemItems ) {
                if( agglomerateListValueItem.get( i ).longValue() == item.getValue() ) {
                    separatedProblemItems.add( item );
                }
            }

        }

        return separatedProblemItems;

    }

    private int maxBetween(
        final int value,
        final int superiorValue )
    {

        return Integer.max( value, superiorValue );

    }

    private static class HolderBell
    {

        private int solvedValue;
        private int maxLines;
        private int maxCollums;
        private int[][] bellArray;

        public static HolderBell create(
            final int solvedValue,
            final int maxLines,
            final int maxCollums,
            final int[][] bellArray )
        {
            final HolderBell holderBell = new HolderBell();
            holderBell.solvedValue = solvedValue;
            holderBell.maxLines = maxLines;
            holderBell.maxCollums = maxCollums;
            holderBell.bellArray = bellArray;

            return holderBell;
        }

        public int getSolvedValue()
        {
            return solvedValue;
        }

        public int getMaxCollums()
        {
            return maxCollums;
        }

        public int getMaxLines()
        {
            return maxLines;
        }

        public int[][] getBellArray()
        {
            return bellArray;
        }

    }

}
