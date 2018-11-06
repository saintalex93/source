package br.com.neolog.project.optimization.solver;

import static br.com.neolog.project.optimization.ProblemFactory.refactorProblem;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionItem;
import br.com.neolog.project.optimization.OptimizationItem;

@Component
public class BruteForce
    implements
        Solver
{

    @Override
    public Solution solve(
        final Problem problem )
    {
        final Recommendation recommendations = resolve( problem );
        return new Solution( recommendations.getSolutionItems() );
    }

    private Recommendation resolve(
        final Problem problem )
    {
        final List<OptimizationItem> selectedProducts = new LinkedList<>();
        selectedProducts.addAll( problem.getSelectedProducts() );

        Recommendation bestRecommendation = new Recommendation();
        if( selectedProducts.isEmpty() ) {
            return bestRecommendation;
        }

        for( final OptimizationItem item : selectedProducts ) {
            for( int currentQuantity = 0; currentQuantity <= item.getQuantity(); currentQuantity++ ) {
                final long remainingTarget = problem.getTarget().longValue() - currentQuantity * item.getValue();
                if( remainingTarget < 0 ) {
                    break;
                }
                final SolutionItem itemToRecommend = new SolutionItem( item, currentQuantity );
                if( remainingTarget == 0 ) {
                    bestRecommendation.addRecommendation( itemToRecommend );
                    return bestRecommendation;
                }
                final Recommendation recommendation = resolve( refactorProblem( problem, remainingTarget ) );
                recommendation.addRecommendation( itemToRecommend );
                if( recommendation.totalValue() == problem.getTarget().longValue() ) {
                    return recommendation;
                }
                if( recommendation.totalValue() < problem.getTarget().longValue() ) {
                    if( recommendation.totalValue() > bestRecommendation.totalValue() ) {
                        bestRecommendation = recommendation;
                    }
                }
            }
        }
        return bestRecommendation;
    }

    private class Recommendation
    {
        private final List<SolutionItem> solutionItems = new LinkedList<>();

        public List<SolutionItem> getSolutionItems()
        {
            return solutionItems;
        }

        public void addRecommendation(
            final SolutionItem item )
        {
            if( item.getQuantity() > 0 ) {
                solutionItems.remove( item );
                solutionItems.add( item );
            }
        }

        public long totalValue()
        {
            long value = 0L;
            for( final SolutionItem solutionItem : solutionItems ) {
                value += solutionItem.longValue() * solutionItem.getQuantity();
            }
            return value;
        }

        @Override
        public String toString()
        {
            return MoreObjects.toStringHelper( this ).add( "Recommendations", getSolutionItems() ).toString();
        }
    }
}
