package br.com.neolog.ecommerce.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class ExactOld
    implements
        Optimization
{
    @Autowired
    private SolutionFactory solutionFactory;

    @Autowired
    private OptimizationUtils utils;

    @Override
    public Solution optimize(
        final Problem problem )
    {

        final long start = System.currentTimeMillis();

        long totalValue = utils.getTotalProblemValue( problem );
        if( totalValue <= problem.getTarget() ) {
            return solutionFactory.getSolution( totalValue, problem.getProblemItems() );
        }

        // Inserir dentro do brute
        for( final ProblemItem problemItem : problem.getProblemItems() ) {
            totalValue = problemItem.getQuantity() * problemItem.getValue();
            if( problem.getTarget() == totalValue ) {
                return solutionFactory.getSolution( totalValue, Collections.singletonList( problemItem ) );
            }
        }

        // 30[3] 10[1] 20[2]

        final List<ProblemItem> list = brute( problem.getProblemItems(), problem.getTarget() );

        final Solution solution = solve( problem );

        System.out.println( "Tempo para solu��o." + ( start - System.currentTimeMillis() ) );
        return solution;

        /*
         * 100 20[4] 40[2] 50[3] 70[2] 30[5] for item for quant 100 - itm *
         * quant 40[2] 50[3] 70[2] 30[5]
         */

    }
    
//    50[3] 10[2] 5[2]
        /*
        * 1
        * 2
        * 3
        * 1 1
        * 2 1
        * 3 1
        * 1 2
        * 2 2
        * 3 2
        * ---- Retirar um elemento e adicionar o outro, para efetuar compara��es isoladas com todos os elementos. Caso todas as compara��es j� tenham
        *      sido feitas, clona a lista original novamente e come�a com combina��es da lista toda alterando a abordagem do m�todo com um overload.
        * 1 1 1
        * 2 1 1
        * 3 1 1
        * 1 2 1
        * 2 2 1
        * 3 2 1
        * 1 2 2
        * 2 2 2
        * 3 2 2
        * ---- Swap posi��o 2 para 1 ()
        * 1
        * 2
        * 1 1
        * 2 1
        * 1 2
        * 2 2
        * 1 3
        * 2 3
        * 1 1 1
        * 2 1 1
        * 1 2 1
        * 1 3 1
        * 2 1 1
        * 2 2 1
        * 2 3 1
        * 1 1 2
        * 2 1 1
        * 2 2 1
        * 2 3 1
        * 2 1 2
        * ---- Swap posi��o 3 para 1 ()Repete as combina��es pois s�o os mesmos n�meros)
        * 
        * 
        * 
        * 
        *   
        *  
        */
        
        /* 
        * Tenha um elemento como target, sempre fazendo combina��es com ele e os diversos, fa�a um bubble sort para trocar os outros para o primeiro elemento
        * do array
        * Percorra o elemento pela quantidade dele - a quantidade total, pois j� deu o escape, por�m precisa percorrer a quantidade
        * total com outros elementos atrelados a ele.
        * Adicione um elemento e percorra o primeiro elemento combinando com o pr�ximo e v� removendo o elemento e adicionando o pr�ximo, logo ap�s combinar
        * com todos, remova e combine com o pr�ximo, excluindo o que j� foi combinado.
        * Aqui cabe o mesmo escape feito antes de entrar, analisando se o valor total dos itens bate na quantidade, se n�o bater, n�o fazer o incremento
        * unit�rio, e retornar a soma dele como melhor valor, se for melhor que o encontrado.
        * AWAYS HELP GC!
        * 
        * 50[3] 20[2] 10[1]
        * 
        * 50[3]
        * 50[3] (20[2])
        * 50[3] (10[1])
        * 
        * 20[2]
        * 20[2] (10[1])
        * 20[2] (50[3])
        * 
        * 10[1]
        * 10[1] (50[3])
        * 10[1] (20[2])
        * 
        * Logo ap�s a combina��o incremental, come�e a combina��o de permuta com bubble sort, trocando a refer�ncia do primeiro elemento com o pr�ximo.
        * 
        * � melhor somar sempre o primeiro com refer�ncia a soma dos outros, ou somar os outros com refer�ncia a o primeiro?
        * 
        * 50[1] 20[1] 10[1]
        * 50[1] 20[2] 10[1]
        * 50[1] 20[2] 10[1]
        * 
        * 
        * 50[1] 20[1] 10[1]
        * 50[2] 20[1] 10[1]
        * 50[3] 20[1] 10[1]
        *
        * 50[1] 20[2] 10[1]
        * 50[2] 20[2] 10[1]
        * 50[3] 20[2] 10[1]
        * 
        * 50[1] 20[2] 10[2]
        * 50[2] 20[2] 10[2]
        * 50[3] 20[2] 10[2]
        *           <---
        *           ---->   
        * 50[1] 10[1] 20[1]
        * 50[2] 10[1] 20[1]
        * 50[3] 10[1] 20[1]
        *       <---
        * ----> 
        *    
        * 20[1] 30[1] 10[1]
        * 20[2] 30[1] 10[1]
        * 
        * 20[1] 30[1] 10[1]
        * 20[1] 30[2] 10[1]
        * 20[1] 30[3] 10[1]
        * 
        * 20[1] 30[1] 10[1]
        * 20[1] 30[1] 10[1]
        * 
        *   primeiro escape: valor procurado da lista.
        *   
        *   tentar encontrar uma maneira de que depois que o algoritmo estiver feito, otimiz�lo para percorrer distancias mais significativas,
        *   dividindo o valor do produto para encontrar a quantida necess�ria da combina��o.
        *   
        *   �ltimo escape: se estiver no �ltimo elemento, considerar que ser� a �ltima volta
        * 
        */

    // for( final ProblemItem problemItem : problemItemList ) {
    // if( newTarget == problemItem.getQuantity() * problemItem.getValue() )
    // {
    //
    // betterList = solutionFactory.getSolution( problemItem.getQuantity() *
    // problemItem.getValue(), Collections.singletonList(
    // problemItem ) );
    //
    // return Collections.singletonList(problemItem );
    // }
    // }

    // 50[3] 10[2] 5[2]
    /*
     * 1 2 3 1 1 2 1 3 1 1 2 2 2 3 2 ---- Retirar um elemento e adicionar o
     * outro, para efetuar compara��es isoladas com todos os elementos. Caso
     * todas as compara��es j� tenham sido feitas, clona a lista original
     * novamente e come�a com combina��es da lista toda alterando a abordagem do
     * m�todo com um overload. 1 1 1 2 1 1 3 1 1 1 2 1 2 2 1 3 2 1 1 2 2 2 2 2 3
     * 2 2 ---- Swap posi��o 2 para 1 () 1 2 1 1 2 1 1 2 2 2 1 3 2 3 1 1 1 2 1 1
     * 1 2 1 1 3 1 2 1 1 2 2 1 2 3 1 1 1 2 2 1 1 2 2 1 2 3 1 2 1 2 ---- Swap
     * posi��o 3 para 1 ()Repete as combina��es pois s�o os mesmos n�meros)
     */

    /*
     * Tenha um elemento como target, sempre fazendo combina��es com ele e os
     * diversos, fa�a um bubble sort para trocar os outros para o primeiro
     * elemento do array Percorra o elemento pela quantidade dele - a quantidade
     * total, pois j� deu o escape, por�m precisa percorrer a quantidade total
     * com outros elementos atrelados a ele. Adicione um elemento e percorra o
     * primeiro elemento combinando com o pr�ximo e v� removendo o elemento e
     * adicionando o pr�ximo, logo ap�s combinar com todos, remova e combine com
     * o pr�ximo, excluindo o que j� foi combinado. Aqui cabe o mesmo escape
     * feito antes de entrar, analisando se o valor total dos itens bate na
     * quantidade, se n�o bater, n�o fazer o incremento unit�rio, e retornar a
     * soma dele como melhor valor, se for melhor que o encontrado. AWAYS HELP
     * GC! 50[3] 20[2] 10[1] 50[3] 50[3] (20[2]) 50[3] (10[1]) 20[2] 20[2]
     * (10[1]) 20[2] (50[3]) 10[1] 10[1] (50[3]) 10[1] (20[2]) Logo ap�s a
     * combina��o incremental, come�e a combina��o de permuta com bubble sort,
     * trocando a refer�ncia do primeiro elemento com o pr�ximo. � melhor somar
     * sempre o primeiro com refer�ncia a soma dos outros, ou somar os outros
     * com refer�ncia a o primeiro? 50[1] 20[1] 10[1] 50[1] 20[2] 10[1] 50[1]
     * 20[2] 10[1] 50[1] 20[1] 10[1] 50[2] 20[1] 10[1] 50[3] 20[1] 10[1] 50[1]
     * 20[2] 10[1] 50[2] 20[2] 10[1] 50[3] 20[2] 10[1] 50[1] 20[2] 10[2] 50[2]
     * 20[2] 10[2] 50[3] 20[2] 10[2] <--- ----> 50[1] 10[1] 20[1] 50[2] 10[1]
     * 20[1] 50[3] 10[1] 20[1] <--- ----> 20[1] 30[1] 10[1] 20[2] 30[1] 10[1]
     * 20[1] 30[1] 10[1] 20[1] 30[2] 10[1] 20[1] 30[3] 10[1] 20[1] 30[1] 10[1]
     * 20[1] 30[1] 10[1] primeiro escape: valor procurado da lista. tentar
     * encontrar uma maneira de que depois que o algoritmo estiver feito,
     * otimiz�lo para percorrer distancias mais significativas, dividindo o
     * valor do produto para encontrar a quantida necess�ria da combina��o.
     * �ltimo escape: se estiver no �ltimo elemento, considerar que ser� a
     * �ltima volta
     */

    private final List<ProblemItem> separetedItems = new ArrayList<>();

    private final List<ProblemItem> betterSolution = Collections.emptyList();
    private long totalList = 0;

    public List<ProblemItem> brute(
        final List<ProblemItem> problemItemList,
        long newTarget )
    {

        if( problemItemList.isEmpty() ) {

            return Collections.emptyList();
        }

        if( newTarget == 0 ) {
            return betterSolution;
        }

        for( int i = 0; i < problemItemList.size(); i++ ) {

            for( int j = 0; j < problemItemList.get( i ).getQuantity(); j++ ) {

                final long value = j * problemItemList.get( i ).getValue();

                if( value < newTarget ) {

                    newTarget -= value;
                    ProblemItem problemItemSend = new ProblemItem();
                    problemItemSend = problemItemList.get( i );
                    problemItemSend.setQuantity( j + 1 );
                    separetedItems.add( problemItemSend );
                    totalList += problemItemSend.getValue();
                    problemItemList.get( i ).setQuantity( problemItemList.get( i ).getQuantity() - 1 );
                    brute( problemItemList, newTarget );
                }
            }

            System.out.println( "\n" + separetedItems + "\n" );
            problemItemList.remove( i );
        }

        return problemItemList;

    }

    public void addQuantity()
    {

    }

    public void removeQuantity()
    {

    }

    public void removeItem()
    {

    }

    public List<ProblemItem> getOriginalList(
        final List<ProblemItem> problemItems )
    {

        return Collections.emptyList();
    }

    private Solution solve(
        final Problem problem )
    {

        return solutionFactory.emptySolution();
    }

}
