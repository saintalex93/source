package br.com.neolog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.pojo.PresentationClass;
import br.com.neolog.services.OptimizationService;

@RestController
public class OptimizationController {
	private OptimizationService optimizationService;

	@Autowired
	public OptimizationController(OptimizationService optimizationService) {
		this.optimizationService = optimizationService;
	}

	@RequestMapping(value = "/Teste", method = RequestMethod.POST)
	public PresentationClass optimizeShopList(@RequestBody double value) {
		return optimizationService.optimizeShopList(value);
	}
}
