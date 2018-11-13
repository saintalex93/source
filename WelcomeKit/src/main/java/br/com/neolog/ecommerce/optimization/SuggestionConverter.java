package br.com.neolog.ecommerce.optimization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.product.Product;
import br.com.neolog.ecommerce.product.ProductService;

@Component
public class SuggestionConverter
{
    @Autowired
    private ProductService productService;

    public Suggestion getSuggestion(

        final Solution solution )
    {
        final List<SuggestionItem> suggestionItems = new ArrayList<>();
        long result = 0;
        for( final Item solutionItem : solution.getSolutionItems() ) {
            final int productCode = solutionItem.getProductCode();
            final Product product = productService.getProductByCode( productCode );
            final String name = product.getName();
            final SuggestionItem suggestionItem = SuggestionItem.create( solutionItem.getProductCode(), name,
                solutionItem.getQuantity(), solutionItem.getValue() );
            result += solutionItem.getValue() * solutionItem.getQuantity();
            if( solutionItem.getQuantity() > 0 ) {
                suggestionItems.add( suggestionItem );
            }
        }
        return Suggestion.create( result, suggestionItems );
    }
}
