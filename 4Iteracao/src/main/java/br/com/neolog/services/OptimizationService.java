package br.com.neolog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.converters.ProblemConverter;
import br.com.neolog.converters.SolutionConverter;
import br.com.neolog.pojo.PresentationClass;
import br.com.neolog.pojo.Problem;
import br.com.neolog.pojo.Product;
import br.com.neolog.pojo.Solution;
import br.com.neolog.repository.StockRepository;
import br.com.neolog.solvers.RouterSubsetSumSolver;

/**
 * 
 * 
 * @author igor.kurman
 * 
 */
@Component
public class OptimizationService {

	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private ProblemConverter problemConverter;
	@Autowired
	private SolutionConverter solutionConverter;
	@Autowired
	private RouterSubsetSumSolver routerSubsetSumSolver;

	/**
	 * Método que otimiza as opções de compra com base no valor que o
	 * {@link User} tem disponível.
	 * 
	 * @param valueToSpend
	 *            valor que o {@link User} dispõe.
	 * @return um {@link Map} com {@link Product} e {@code quantity} com as
	 *         melhores opções de compra.
	 */
	public PresentationClass optimizeShopList(double valueToSpend) {

		Problem problem = problemConverter.convert(valueToSpend);
		Solution solution = routerSubsetSumSolver.getClosestSubsetSum(problem);

		return solutionConverter.convert(solution);

	}
}
