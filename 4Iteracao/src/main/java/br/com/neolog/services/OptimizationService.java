package br.com.neolog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.converters.ProblemConverter;
import br.com.neolog.converters.SolutionConverter;
import br.com.neolog.models.Customer;
import br.com.neolog.models.OptimizationHolder;
import br.com.neolog.models.PresentationSolution;
import br.com.neolog.models.Problem;
import br.com.neolog.models.Product;
import br.com.neolog.models.Solution;
import br.com.neolog.solvers.RouterSubsetSumSolver;

/**
 * @author igor.kurman
 */
@Component
public class OptimizationService
{

    @Autowired
    private ProblemConverter problemConverter;
    @Autowired
    private SolutionConverter solutionConverter;
    @Autowired
    private RouterSubsetSumSolver routerSubsetSumSolver;

    /**
     * Método que otimiza as opções de compra com base no valor que o
     * {@link Customer} tem disponível.
     *
     * @param value valor que o {@link Customer} dispõe.
     * @return um {@link Map} com {@link Product} e {@code quantity} com as
     *         melhores opções de compra.
     */
    public PresentationSolution optimizeShopList(
        final OptimizationHolder optmizationHolder )
    {

        final Problem problem = problemConverter.convert( optmizationHolder );
        final Solution solution = routerSubsetSumSolver.getClosestSubsetSum( problem );

        return solutionConverter.convert( solution );

    }

}
