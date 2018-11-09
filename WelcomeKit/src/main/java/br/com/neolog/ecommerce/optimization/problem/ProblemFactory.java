package br.com.neolog.ecommerce.optimization.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.stock.Stock;
import br.com.neolog.ecommerce.stock.StockRepository;

@Component
public class ProblemFactory
{

    @Autowired
    private StockRepository stockRepository;

    public Problem generateProblem(
        final long target )
    {
        final List<Stock> stockList = stockRepository.findByProductPriceLessThanEqualOrderByProductPriceAsc( target );

        if( stockList.isEmpty() ) {
            return emptyProblem( target );
        }
        final List<ProblemItem> problemItems = new ArrayList<>();
        for( final Stock stock : stockList ) {
            if( stock.getQuantity() < 1 ) {
                continue;
            }
            final long price = doubleToLong( stock.getProduct().getPrice() );
            if( price <= target ) {

                final long quantity = target / price;

                if( quantity <= stock.getQuantity() ) {

                    problemItems.add( new ProblemItem( stock.getProduct().getCode(), price, quantity ) );
                    continue;
                }

                problemItems.add( new ProblemItem( stock.getProduct().getCode(), price, stock
                    .getQuantity() ) );

            }
        }
        return new Problem( target, problemItems );
    }

    public static Problem emptyProblem(
        final long target )
    {
        return new Problem( target, Collections.emptyList() );
    }

    private long doubleToLong(
        final double price )
    {
        return Math.round( price * 10 * 10 );
    }

}
