package br.com.neolog.ecommerce.optimization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.ecommerce.optimization.suggestion.Suggestion;

@RestController
@RequestMapping( "suggest-cart" )
public class OptimizationController
{
    @Autowired
    private OptimizationService optimizationService;

    @PostMapping( "/{value}" )
    public ResponseEntity<Suggestion> optimization(
        @PathVariable( name = "value" ) final Double value )
    {
        return ResponseEntity.ok( optimizationService.optimize( value ) );
    }

}
