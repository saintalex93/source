package br.com.neolog.ecommerce.optimization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.exceptions.OptimizationInvalidValueException;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemFactory;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.suggestion.Suggestion;
import br.com.neolog.ecommerce.optimization.suggestion.SuggestionConverter;

@Component
public class OptimizationService
{
    @Autowired
    private ProblemFactory problemFactory;
    @Autowired
    private SuggestionConverter suggestionConverter;
    @Autowired
    private Route route;

    public Suggestion optimize(
        final Double value )
    {
        final long longValue = Math.round( value * 10 * 10 );
        if( value < 1 ) {
            throw new OptimizationInvalidValueException();
        }
        final Problem problem = problemFactory.generateProblem( longValue );
        if( problem.getProblemItems().isEmpty() ) {
            return Suggestion.empty();
        }
        final Solution solution = route.optimize( problem );
        return suggestionConverter.getSuggestion( solution );
    }
}
