package br.com.neolog.ecommerce.optimization.solver;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.Optimization;
import br.com.neolog.ecommerce.optimization.problem.Item;
import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class Exact
    implements
        Optimization
{
    @Autowired
    private SolutionFactory solutionFactory;

    @Override
    public Solution optimize(
        final Problem problem )
    {

        final long start = System.currentTimeMillis();

        final List<Item> preSolution = brute( problem.getTarget(), problem.getProblemItems() );

        System.out.println( preSolution );

        System.out.println( "Tempo para solução Exact: ." + ( start - System.currentTimeMillis() ) );
        return solutionFactory.getSolution( preSolution );

    }

    public List<Item> brute(
        final long target,
        final List<Item> resolveItem )
    {
        final List<Item> separetedItems = new LinkedList<>( resolveItem );
        if( target <= 0 ) {
            return new LinkedList<>();
        }
        if( separetedItems.isEmpty() ) {
            return new LinkedList<>();
        }
        List<Item> bestResult = new LinkedList<>();
        for( final Item separetedItem : separetedItems ) {
            for( int quantity = 0; quantity <= separetedItem.getQuantity(); quantity++ ) {
                final long newTarget = target - quantity * separetedItem.getValue();
                List<Item> partialSolution = new LinkedList<>();
                if( target >= 0 && separetedItems.size() > 1 ) {
                    partialSolution = brute( newTarget, separetedItems.subList( 1, separetedItems.size() ) );
                }
                partialSolution.add( generateItem( separetedItem, quantity ) );
                final long partialSolutionValue = getTotalValue( partialSolution );
                if( partialSolutionValue > getTotalValue( bestResult ) && partialSolutionValue <= target ) {
                    bestResult = partialSolution;
                }
                System.out.println( "\n" + bestResult + "\n" );
            }
        }
        return bestResult;
    }

    private Item generateItem(
        final Item item,
        final int quantity )
    {
        return new Item( item.getProductCode(), item.getValue(), quantity );
    }

    private long getTotalValue(
        final List<Item> list )
    {
        long totalValue = 0;
        for( final Item problemItem : list ) {
            totalValue += problemItem.getValue() * problemItem.getQuantity();
        }
        return totalValue;

    }

}

/*
 * 1 2 3 1 1 2 1 3 1 1 2 2 2 3 2 ---- Retirar um elemento e adicionar o outro,
 * para efetuar comparaï¿½ï¿½es isoladas com todos os elementos. Caso todas as
 * comparaï¿½ï¿½es jï¿½ tenham sido feitas, clona a lista original novamente e
 * comeï¿½a com combinaï¿½ï¿½es da lista toda alterando a abordagem do mï¿½todo
 * com um overload. 1 1 1 2 1 1 3 1 1 1 2 1 2 2 1 3 2 1 1 2 2 2 2 2 3 2 2 ----
 * Swap posiï¿½ï¿½o 2 para 1 () 1 2 1 1 2 1 1 2 2 2 1 3 2 3 1 1 1 2 1 1 1 2 1 1
 * 3 1 2 1 1 2 2 1 2 3 1 1 1 2 2 1 1 2 2 1 2 3 1 2 1 2 ---- Swap posiï¿½ï¿½o 3
 * para 1 ()Repete as combinaï¿½ï¿½es pois sï¿½o os mesmos nï¿½meros)
 */

/*
 * Tenha um elemento como target, sempre fazendo combinaï¿½ï¿½es com ele e os
 * diversos, faï¿½a um bubble sort para trocar os outros para o primeiro
 * elemento do array Percorra o elemento pela quantidade dele - a quantidade
 * total, pois jï¿½ deu o escape, porï¿½m precisa percorrer a quantidade total
 * com outros elementos atrelados a ele. Adicione um elemento e percorra o
 * primeiro elemento combinando com o prï¿½ximo e vï¿½ removendo o elemento e
 * adicionando o prï¿½ximo, logo apï¿½s combinar com todos, remova e combine com
 * o prï¿½ximo, excluindo o que jï¿½ foi combinado. Aqui cabe o mesmo escape
 * feito antes de entrar, analisando se o valor total dos itens bate na
 * quantidade, se nï¿½o bater, nï¿½o fazer o incremento unitï¿½rio, e retornar a
 * soma dele como melhor valor, se for melhor que o encontrado. AWAYS HELP GC!
 * 50[3] 20[2] 10[1] 50[3] 50[3] (20[2]) 50[3] (10[1]) 20[2] 20[2] (10[1]) 20[2]
 * (50[3]) 10[1] 10[1] (50[3]) 10[1] (20[2]) Logo apï¿½s a combinaï¿½ï¿½o
 * incremental, comeï¿½e a combinaï¿½ï¿½o de permuta com bubble sort, trocando a
 * referï¿½ncia do primeiro elemento com o prï¿½ximo. ï¿½ melhor somar sempre o
 * primeiro com referï¿½ncia a soma dos outros, ou somar os outros com
 * referï¿½ncia a o primeiro? 50[1] 20[1] 10[1] 50[1] 20[2] 10[1] 50[1] 20[2]
 * 10[1] 50[1] 20[1] 10[1] 50[2] 20[1] 10[1] 50[3] 20[1] 10[1] 50[1] 20[2] 10[1]
 * 50[2] 20[2] 10[1] 50[3] 20[2] 10[1] 50[1] 20[2] 10[2] 50[2] 20[2] 10[2] 50[3]
 * 20[2] 10[2] <--- ----> 50[1] 10[1] 20[1] 50[2] 10[1] 20[1] 50[3] 10[1] 20[1]
 * <--- ----> 20[1] 30[1] 10[1] 20[2] 30[1] 10[1] 20[1] 30[1] 10[1] 20[1] 30[2]
 * 10[1] 20[1] 30[3] 10[1] 20[1] 30[1] 10[1] 20[1] 30[1] 10[1] primeiro escape:
 * valor procurado da lista. tentar encontrar uma maneira de que depois que o
 * algoritmo estiver feito, otimizï¿½lo para percorrer distancias mais
 * significativas, dividindo o valor do produto para encontrar a quantida
 * necessï¿½ria da combinaï¿½ï¿½o. ï¿½ltimo escape: se estiver no ï¿½ltimo
 * elemento, considerar que serï¿½ a ï¿½ltima volta
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
 * 1 2 3 1 1 2 1 3 1 1 2 2 2 3 2 ---- Retirar um elemento e adicionar o outro,
 * para efetuar comparaï¿½ï¿½es isoladas com todos os elementos. Caso todas as
 * comparaï¿½ï¿½es jï¿½ tenham sido feitas, clona a lista original novamente e
 * comeï¿½a com combinaï¿½ï¿½es da lista toda alterando a abordagem do mï¿½todo
 * com um overload. 1 1 1 2 1 1 3 1 1 1 2 1 2 2 1 3 2 1 1 2 2 2 2 2 3 2 2 ----
 * Swap posiï¿½ï¿½o 2 para 1 () 1 2 1 1 2 1 1 2 2 2 1 3 2 3 1 1 1 2 1 1 1 2 1 1
 * 3 1 2 1 1 2 2 1 2 3 1 1 1 2 2 1 1 2 2 1 2 3 1 2 1 2 ---- Swap posiï¿½ï¿½o 3
 * para 1 ()Repete as combinaï¿½ï¿½es pois sï¿½o os mesmos nï¿½meros)
 */

/*
 * Tenha um elemento como target, sempre fazendo combinaï¿½ï¿½es com ele e os
 * diversos, faï¿½a um bubble sort para trocar os outros para o primeiro
 * elemento do array Percorra o elemento pela quantidade dele - a quantidade
 * total, pois jï¿½ deu o escape, porï¿½m precisa percorrer a quantidade total
 * com outros elementos atrelados a ele. Adicione um elemento e percorra o
 * primeiro elemento combinando com o prï¿½ximo e vï¿½ removendo o elemento e
 * adicionando o prï¿½ximo, logo apï¿½s combinar com todos, remova e combine com
 * o prï¿½ximo, excluindo o que jï¿½ foi combinado. Aqui cabe o mesmo escape
 * feito antes de entrar, analisando se o valor total dos itens bate na
 * quantidade, se nï¿½o bater, nï¿½o fazer o incremento unitï¿½rio, e retornar a
 * soma dele como melhor valor, se for melhor que o encontrado. AWAYS HELP GC!
 * 50[3] 20[2] 10[1] 50[3] 50[3] (20[2]) 50[3] (10[1]) 20[2] 20[2] (10[1]) 20[2]
 * (50[3]) 10[1] 10[1] (50[3]) 10[1] (20[2]) Logo apï¿½s a combinaï¿½ï¿½o
 * incremental, comeï¿½e a combinaï¿½ï¿½o de permuta com bubble sort, trocando a
 * referï¿½ncia do primeiro elemento com o prï¿½ximo. ï¿½ melhor somar sempre o
 * primeiro com referï¿½ncia a soma dos outros, ou somar os outros com
 * referï¿½ncia a o primeiro? 50[1] 20[1] 10[1] 50[1] 20[2] 10[1] 50[1] 20[2]
 * 10[1] 50[1] 20[1] 10[1] 50[2] 20[1] 10[1] 50[3] 20[1] 10[1] 50[1] 20[2] 10[1]
 * 50[2] 20[2] 10[1] 50[3] 20[2] 10[1] 50[1] 20[2] 10[2] 50[2] 20[2] 10[2] 50[3]
 * 20[2] 10[2] <--- ----> 50[1] 10[1] 20[1] 50[2] 10[1] 20[1] 50[3] 10[1] 20[1]
 * <--- ----> 20[1] 30[1] 10[1] 20[2] 30[1] 10[1] 20[1] 30[1] 10[1] 20[1] 30[2]
 * 10[1] 20[1] 30[3] 10[1] 20[1] 30[1] 10[1] 20[1] 30[1] 10[1] primeiro escape:
 * valor procurado da lista. tentar encontrar uma maneira de que depois que o
 * algoritmo estiver feito, otimizï¿½lo para percorrer distancias mais
 * significativas, dividindo o valor do produto para encontrar a quantida
 * necessï¿½ria da combinaï¿½ï¿½o. ï¿½ltimo escape: se estiver no ï¿½ltimo
 * elemento, considerar que serï¿½ a ï¿½ltima volta
 */
