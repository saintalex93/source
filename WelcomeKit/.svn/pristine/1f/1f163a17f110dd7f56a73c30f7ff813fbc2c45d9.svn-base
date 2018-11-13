package br.com.neolog.ecommerce.optimization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solver.Exact;
import br.com.neolog.ecommerce.optimization.solver.Heuristic;

@RunWith( PowerMockRunner.class )
@PrepareForTest( {
    Route.class,
    Exact.class,
    Heuristic.class
} )
public class RouteTest
{
    @Mock
    private Exact exact;

    @Mock
    private Heuristic heuristic;

    @InjectMocks
    private Route route;

    @Test
    public void shouldAssertPassInHeuristicClass()
    {

        final Item batata = Item.create( 44556, 10_00, 2 );
        final Item windao = Item.create( 2, 200_00, 500 );
        final Item tv = Item.create( 1, 2500_00, 20 );

        final List<Item> problemItem = new LinkedList<>();
        problemItem.add( batata );
        problemItem.add( windao );
        problemItem.add( tv );
        final Problem problem = Problem.create( 150021_00, problemItem );

        final Solution solution = Solution.create( 150021_00, problemItem );
        when( heuristic.optimize( problem ) ).thenReturn( solution );
        final Solution optimize = route.optimize( problem );

        verify( heuristic, Mockito.times( 1 ) ).optimize( problem );
        assertThat( optimize ).isEqualTo( solution );

    }

    @Test
    public void shouldAssertPassInExactClass()
    {

        final Item batata = Item.create( 44556, 10_00, 2 );
        final Item windao = Item.create( 2, 200_00, 20 );
        final Item tv = Item.create( 1, 2500_00, 20 );

        final List<Item> problemItem = new LinkedList<>();
        problemItem.add( batata );
        problemItem.add( windao );
        problemItem.add( tv );
        final Problem problem = Problem.create( 150021_00, problemItem );

        final Solution solution = Solution.create( 150021_00, problemItem );
        when( exact.optimize( problem ) ).thenReturn( solution );
        final Solution optimize = route.optimize( problem );

        verify( exact, Mockito.times( 1 ) ).optimize( problem );
        assertThat( optimize ).isEqualTo( solution );

    }

}
