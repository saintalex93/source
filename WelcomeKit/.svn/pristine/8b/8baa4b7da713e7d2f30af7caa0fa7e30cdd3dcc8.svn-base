package br.com.neolog.ecommerce.optimization.solution;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.neolog.ecommerce.exceptions.ProblemNullException;
import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.solver.Bell;

@RunWith( SpringJUnit4ClassRunner.class )
public class BellTest
{

    @InjectMocks
    private Bell bell;

    @Test( expected = ProblemNullException.class )
    public void shouldAssertProblemNullException()
    {
        bell.optimize( null );
    }

    @Test
    public void shouldAssertReturnEmptySolution()
    {
        final Problem problem = Problem.create( 0, Collections.emptyList() );
        final Solution optmization = bell.optimize( problem );

        assertThat( optmization.getSolutionItems() ).isEmpty();
        assertThat( optmization.getResult() ).isZero();
    }

    @Test
    public void shouldAssertReturnExactValueWhenTargetIs220()
    {
        final Item batata = Item.create( 44556, 10_00, 2 );
        final Item windao = Item.create( 2, 200_00, 1 );
        final List<Item> problemItem = new LinkedList<>();

        problemItem.add( batata );
        problemItem.add( windao );

        final Problem problem = Problem.create( 220_00, problemItem );
        final Solution solution = bell.optimize( problem );

        for( final Item item : problemItem ) {
            assertThat( solution.getSolutionItems().contains( item ) ).isTrue();
        }
        assertThat( solution.getResult() ).isEqualTo( 220d );
    }

    @Test
    public void shouldAssertReturnExactValueWhentargetIs1420()
    {
        final Item batata = Item.create( 44556, 10_00, 2 );
        final Item windao = Item.create( 2, 200_00, 7 );
        final List<Item> problemItem = new LinkedList<>();

        problemItem.add( batata );
        problemItem.add( windao );

        final Problem problem = Problem.create( 1420_00, problemItem );
        final Solution solution = bell.optimize( problem );

        for( final Item item : problemItem ) {
            assertThat( solution.getSolutionItems().contains( item ) ).isTrue();
        }
        assertThat( solution.getResult() ).isEqualTo( problem.getTarget() / 100 );
    }

    @Test
    public void shouldAssertReturn1200OfSolutionValueWhentargetIs1300()
    {
        final Item batata = Item.create( 44556, 10_00, 2 );
        final Item windao = Item.create( 2, 200_00, 6 );
        final List<Item> problemItem = new LinkedList<>();

        problemItem.add( batata );
        problemItem.add( windao );

        final Problem problem = Problem.create( 1300_00, problemItem );
        final Solution solution = bell.optimize( problem );

        for( final Item item : problemItem ) {
            assertThat( solution.getSolutionItems().contains( item ) ).isTrue();
        }
        assertThat( solution.getResult() ).isEqualTo( 1220d );
    }

    @Test
    public void shouldAssertRecieve12320WithProvideListItems()
    {
        final Item batata = Item.create( 44556, 10_00, 2 );
        final Item windao = Item.create( 2, 200_00, 24 );
        final Item tv = Item.create( 1, 2500_00, 3 );
        final List<Item> problemItem = new LinkedList<>();

        problemItem.add( batata );
        problemItem.add( windao );
        problemItem.add( tv );

        final Problem problem = Problem.create( 1233200, problemItem );
        final Solution solution = bell.optimize( problem );

        for( final Item item : problemItem ) {
            assertThat( solution.getSolutionItems().contains( item ) ).isTrue();
        }
        assertThat( solution.getResult() ).isEqualTo( 12320d );
    }

    @Test
    public void shouldAssertReturnListWithoutItemValue5_00UnSorted()
    {
        final Item item1 = Item.create( 1, 8_00, 1 );
        final Item item2 = Item.create( 1, 5_00, 1 );
        final Item item3 = Item.create( 1, 4_00, 1 );
        final Item item4 = Item.create( 1, 2_00, 1 );
        final List<Item> listItem = new LinkedList<>();

        listItem.add( item2 );
        listItem.add( item3 );
        listItem.add( item1 );
        listItem.add( item4 );

        final Problem alternativeProblem = Problem.create( 1400, listItem );
        Solution solution = new Solution();
        solution = bell.optimize( alternativeProblem );

        assertThat( solution.getResult() ).isEqualTo( 14d );
        assertThat( solution.getSolutionItems() ).doesNotContain( item2 );

    }

}
