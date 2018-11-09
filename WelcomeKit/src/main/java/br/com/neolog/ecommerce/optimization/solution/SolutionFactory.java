package br.com.neolog.ecommerce.optimization.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.product.Product;
import br.com.neolog.ecommerce.product.ProductService;

@Component
public class SolutionFactory
{
    @Autowired
    private ProductService productService;

    public Solution getSolution(

        final List<Item> problemItems )
    {
        final List<SolutionItem> solutionList = new ArrayList<>();
        long result = 0;
        for( final Item problemItem : problemItems ) {

            final int productCode = problemItem.getProductCode();
            final Product product = productService.getProductByCode( productCode );

            final String name = product.getName();

            final SolutionItem solutionItem = new SolutionItem( problemItem.getProductCode(), name,
                problemItem.getQuantity(), problemItem.getValue() );
            result += problemItem.getValue() * problemItem.getQuantity();

            if( problemItem.getQuantity() > 0 ) {
                solutionList.add( solutionItem );
            }

        }

        return new Solution( result, solutionList );
    }

    public Solution emptySolution()
    {
        return new Solution( 0, Collections.emptyList() );
    }

}
