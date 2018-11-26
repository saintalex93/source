package br.com.neolog.converters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.models.HolderCodeValue;
import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.Problem;
import br.com.neolog.models.ProblemType;
import br.com.neolog.models.Stock;
import br.com.neolog.repository.StockRepository;

/**
 * Esta classe é responsável por receber um objeto que contém o tipo de extração
 * do problema (Por peso, valor ou volume) e devolver um {@link Problem} a
 * partir desse valor.
 *
 * @author igor.kurman
 * @author alex.santos
 */
@Component
public class ProblemConverter
{

    @Autowired
    private StockRepository stockRepository;

    /**
     * @param HolderCodeValue objeto contendo o valor e o tipo do problema
     * @return o Problem criado com base no objeto recebido {@link Problem}
     *         conta com um Set interno de {@link HolderCodeValue} contendo
     *         somente produtos com valor abaixo do target informado.
     */
    public Problem convert(
        final OptimizationHolder optmizationHolder )
    {

        final long target = optmizationHolder.getTarget();
        final ProblemType type = optmizationHolder.getType();

        final List<Stock> stock = extractProducts( target, type );

        final Set<HolderCodeValue> separatedHolderItems = new HashSet<>();
        for( final Stock stockItem : stock ) {
            final int quantity = getMaxPossibleQuantities( target, type, stockItem );

            for( int i = 0; i < quantity; i++ ) {
                final HolderCodeValue holderCodeValue = HolderCodeValue.create( stockItem.getProduct().getCode(), type.getValue(
                    stockItem.getProduct() ) );
                separatedHolderItems.add( holderCodeValue );
            }
        }

        return Problem.create( separatedHolderItems, target );
    }

    private int getMaxPossibleQuantities(
        final double target,
        final ProblemType type,
        final Stock stock )
    {
        final int maxQuantity = Double.valueOf( target / type.getValue( stock.getProduct() ) ).intValue();
        final int quantityInStock = stock.getQuantity();
        return Integer.min( maxQuantity, quantityInStock );
    }

    private List<Stock> extractProducts(
        final long target,
        final ProblemType type )
    {

        if( type == ProblemType.VALUE ) {
            return stockRepository.findByProductPriceLessThanEqual( target );
        }
        if( type == ProblemType.WEIGHT ) {
            return stockRepository.findByProductWeightLessThanEqual( target );
        }
        if( type == ProblemType.VOLUME ) {
            return stockRepository.findByProductVolumeLessThanEqual( target );
        }

        throw new IllegalArgumentException( "Invalid Type" );

    }

}
