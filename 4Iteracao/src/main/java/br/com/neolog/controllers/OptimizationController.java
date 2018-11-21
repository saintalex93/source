package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.pojo.PresentationClass;
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

    @GetMapping( value = "optime{value}" )
    public PresentationClass optimizeShopList(
        @RequestParam( name = "value" ) final double value )
    {
        return optimizationService.optimizeShopList( value );
    }
}
