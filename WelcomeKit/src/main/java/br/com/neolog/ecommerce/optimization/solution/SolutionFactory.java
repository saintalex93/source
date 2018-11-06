package br.com.neolog.ecommerce.optimization.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.product.Product;
import br.com.neolog.ecommerce.product.ProductService;

@Component
public class SolutionFactory
{
    @Autowired
    private ProductService productService;

    public Solution getSolution(
        final long result,
        final List<ProblemItem> problemItems )
    {
        final List<SolutionItem> solutionList = new ArrayList<>();

        for( final ProblemItem problemItem : problemItems ) {

            final int productCode = problemItem.getProductCode();
            final Product product = productService.getProductByCode( productCode );

            final String name = product.getName();

            final SolutionItem solutionItem = new SolutionItem(
                problemItem.getProductCode(), name, problemItem.getQuantity(),
                problemItem.getValue() );

            solutionList.add( solutionItem );

        }

        return new Solution( result, solutionList );
    }

    public Solution emptySolution()
    {
        return new Solution( 0, Collections.emptyList() );
    }

    public PreSolution getPreSolution(
        final Problem problem )
    {
        final PreSolution preSolution = new PreSolution();
        preSolution.setTotalValue( problem.getTarget() );
        for( final ProblemItem problemItem : problem.getProblemItems() ) {
            preSolution.addItem( problemItem );
        }
        return preSolution;
    }

}
