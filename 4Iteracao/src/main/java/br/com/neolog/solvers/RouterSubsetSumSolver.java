package br.com.neolog.solvers;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.models.Problem;
import br.com.neolog.models.Solution;

@Component
public class RouterSubsetSumSolver
    implements
        SubsetSumSolver
{
    Logger logger = Logger.getLogger( this.getClass().getName() );
    @Autowired
    private ExactSubsetSumSolver exactSubsetSumSolver;
    @Autowired
    private AproximatedSubsetSumSolver aproximatedSubsetSumSolver;

    @Override
    public Solution getClosestSubsetSum(
        final Problem problem )
    {
        logger.info( "itens: " + problem.getProducts().size() );
        if( problem.getProducts().size() > 20 ) {
            logger.info( "APROXIMATED" );
            return aproximatedSubsetSumSolver.getClosestSubsetSum( problem );
        }
        logger.info( "EXACT" );
        return exactSubsetSumSolver.getClosestSubsetSum( problem );

    }
}