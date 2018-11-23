package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.services.OptimizationService;

@RequestMapping( "optimization" )
@RestController
public class OptimizationController
{
    private final OptimizationService optimizationService;

    @Autowired
    public OptimizationController(
        final OptimizationService optimizationService )
    {
        this.optimizationService = optimizationService;
    }

    @PostMapping( value = "optimize" )
    public ResponseEntity<PresentationSolution> optimizeShopList(
        @RequestBody final OptimizationHolder optimizationHolder )
    {
        return ResponseEntity.ok( optimizationService.optimizeShopList( optimizationHolder ) );
    }

}
