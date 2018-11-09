package br.com.neolog.ecommerce.optimization;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecommerce.optimization.problem.Problem;
import br.com.neolog.ecommerce.optimization.problem.ProblemItem;
import br.com.neolog.ecommerce.optimization.solution.Solution;
import br.com.neolog.ecommerce.optimization.solution.SolutionFactory;

@Component
public class Exact implements Optimization {
	@Autowired
	private SolutionFactory solutionFactory;


	@Override
	public Solution optimize(final Problem problem) {

		final long start = System.currentTimeMillis();

//		long totalValue = utils.getTotalProblemValue(problem);
//		if (totalValue <= problem.getTarget()) {
//			return solutionFactory.getSolution(problem.getProblemItems());
//		}

		// Inserir dentro do brute
//		for (final ProblemItem problemItem : problem.getProblemItems()) {
//
//			totalValue = problemItem.getQuantity() * problemItem.getValue();
//			if (problem.getTarget() == totalValue) {
//				return solutionFactory.getSolution(Collections.singletonList(problemItem));
//			}
//		}


		final List<ProblemItem> preSolution = brute(problem.getTarget(), problem.getProblemItems());

		System.out.println(preSolution);

		System.out.println("Tempo para solução." + (start - System.currentTimeMillis()));
		return solutionFactory.getSolution(preSolution);

	}

	public List<ProblemItem> brute(long target, List<ProblemItem> resolveItem) {

		final List<ProblemItem> separetedItems = new LinkedList<>(resolveItem);

		if (target <= 0) {
			return new LinkedList<>();
		}

		if (separetedItems.isEmpty()) {

			return new LinkedList<>();
		}

		List<ProblemItem> bestResult = new LinkedList<>();

		for (final ProblemItem separetedItem : separetedItems) {

			for (int quantity = 0; quantity <= separetedItem.getQuantity(); quantity++) {

				final long newTarget = target - (quantity * separetedItem.getValue());
				List<ProblemItem> partialSolution = new LinkedList<>();
				if (target >= 0 && separetedItems.size() > 1) {
					partialSolution = brute(newTarget, separetedItems.subList(1, separetedItems.size()));
				}
				partialSolution.add(generateSolutionProblemItem(separetedItem, quantity));
				final long partialSolutionValue = getTotalValue(partialSolution);
				if (partialSolutionValue > getTotalValue(bestResult) && partialSolutionValue <= target) {
					bestResult = partialSolution;
				}
				System.out.println("\n" + bestResult + "\n");
			}

		}
		return bestResult;
	}

	private ProblemItem generateSolutionProblemItem(final ProblemItem item, final int quantity) {
		return new ProblemItem(item.getProductCode(), item.getValue(), quantity);
	}

	private long getTotalValue(final List<ProblemItem> list) {
		long totalValue = 0;
		for (final ProblemItem problemItem : list) {
			totalValue += problemItem.getValue() * problemItem.getQuantity();
		}
		return totalValue;

	}

}

/*
 * 1 2 3 1 1 2 1 3 1 1 2 2 2 3 2 ---- Retirar um elemento e adicionar o outro,
 * para efetuar compara��es isoladas com todos os elementos. Caso todas as
 * compara��es j� tenham sido feitas, clona a lista original novamente e come�a
 * com combina��es da lista toda alterando a abordagem do m�todo com um
 * overload. 1 1 1 2 1 1 3 1 1 1 2 1 2 2 1 3 2 1 1 2 2 2 2 2 3 2 2 ---- Swap
 * posi��o 2 para 1 () 1 2 1 1 2 1 1 2 2 2 1 3 2 3 1 1 1 2 1 1 1 2 1 1 3 1 2 1 1
 * 2 2 1 2 3 1 1 1 2 2 1 1 2 2 1 2 3 1 2 1 2 ---- Swap posi��o 3 para 1 ()Repete
 * as combina��es pois s�o os mesmos n�meros)
 * 
 * 
 * 
 * 
 * 
 * 
 */

/*
 * Tenha um elemento como target, sempre fazendo combina��es com ele e os
 * diversos, fa�a um bubble sort para trocar os outros para o primeiro elemento
 * do array Percorra o elemento pela quantidade dele - a quantidade total, pois
 * j� deu o escape, por�m precisa percorrer a quantidade total com outros
 * elementos atrelados a ele. Adicione um elemento e percorra o primeiro
 * elemento combinando com o pr�ximo e v� removendo o elemento e adicionando o
 * pr�ximo, logo ap�s combinar com todos, remova e combine com o pr�ximo,
 * excluindo o que j� foi combinado. Aqui cabe o mesmo escape feito antes de
 * entrar, analisando se o valor total dos itens bate na quantidade, se n�o
 * bater, n�o fazer o incremento unit�rio, e retornar a soma dele como melhor
 * valor, se for melhor que o encontrado. AWAYS HELP GC!
 * 
 * 50[3] 20[2] 10[1]
 * 
 * 50[3] 50[3] (20[2]) 50[3] (10[1])
 * 
 * 20[2] 20[2] (10[1]) 20[2] (50[3])
 * 
 * 10[1] 10[1] (50[3]) 10[1] (20[2])
 * 
 * Logo ap�s a combina��o incremental, come�e a combina��o de permuta com bubble
 * sort, trocando a refer�ncia do primeiro elemento com o pr�ximo.
 * 
 * � melhor somar sempre o primeiro com refer�ncia a soma dos outros, ou somar
 * os outros com refer�ncia a o primeiro?
 * 
 * 50[1] 20[1] 10[1] 50[1] 20[2] 10[1] 50[1] 20[2] 10[1]
 * 
 * 
 * 50[1] 20[1] 10[1] 50[2] 20[1] 10[1] 50[3] 20[1] 10[1]
 *
 * 50[1] 20[2] 10[1] 50[2] 20[2] 10[1] 50[3] 20[2] 10[1]
 * 
 * 50[1] 20[2] 10[2] 50[2] 20[2] 10[2] 50[3] 20[2] 10[2] <--- ----> 50[1] 10[1]
 * 20[1] 50[2] 10[1] 20[1] 50[3] 10[1] 20[1] <--- ---->
 * 
 * 20[1] 30[1] 10[1] 20[2] 30[1] 10[1]
 * 
 * 20[1] 30[1] 10[1] 20[1] 30[2] 10[1] 20[1] 30[3] 10[1]
 * 
 * 20[1] 30[1] 10[1] 20[1] 30[1] 10[1]
 * 
 * primeiro escape: valor procurado da lista.
 * 
 * tentar encontrar uma maneira de que depois que o algoritmo estiver feito,
 * otimiz�lo para percorrer distancias mais significativas, dividindo o valor do
 * produto para encontrar a quantida necess�ria da combina��o.
 * 
 * �ltimo escape: se estiver no �ltimo elemento, considerar que ser� a �ltima
 * volta
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
 * 1 2 3 1 1 2 1 3 1 1 2 2 2 3 2 ---- Retirar um elemento e adicionar o outro,
 * para efetuar compara��es isoladas com todos os elementos. Caso todas as
 * compara��es j� tenham sido feitas, clona a lista original novamente e come�a
 * com combina��es da lista toda alterando a abordagem do m�todo com um
 * overload. 1 1 1 2 1 1 3 1 1 1 2 1 2 2 1 3 2 1 1 2 2 2 2 2 3 2 2 ---- Swap
 * posi��o 2 para 1 () 1 2 1 1 2 1 1 2 2 2 1 3 2 3 1 1 1 2 1 1 1 2 1 1 3 1 2 1 1
 * 2 2 1 2 3 1 1 1 2 2 1 1 2 2 1 2 3 1 2 1 2 ---- Swap posi��o 3 para 1 ()Repete
 * as combina��es pois s�o os mesmos n�meros)
 */

/*
 * Tenha um elemento como target, sempre fazendo combina��es com ele e os
 * diversos, fa�a um bubble sort para trocar os outros para o primeiro elemento
 * do array Percorra o elemento pela quantidade dele - a quantidade total, pois
 * j� deu o escape, por�m precisa percorrer a quantidade total com outros
 * elementos atrelados a ele. Adicione um elemento e percorra o primeiro
 * elemento combinando com o pr�ximo e v� removendo o elemento e adicionando o
 * pr�ximo, logo ap�s combinar com todos, remova e combine com o pr�ximo,
 * excluindo o que j� foi combinado. Aqui cabe o mesmo escape feito antes de
 * entrar, analisando se o valor total dos itens bate na quantidade, se n�o
 * bater, n�o fazer o incremento unit�rio, e retornar a soma dele como melhor
 * valor, se for melhor que o encontrado. AWAYS HELP GC! 50[3] 20[2] 10[1] 50[3]
 * 50[3] (20[2]) 50[3] (10[1]) 20[2] 20[2] (10[1]) 20[2] (50[3]) 10[1] 10[1]
 * (50[3]) 10[1] (20[2]) Logo ap�s a combina��o incremental, come�e a combina��o
 * de permuta com bubble sort, trocando a refer�ncia do primeiro elemento com o
 * pr�ximo. � melhor somar sempre o primeiro com refer�ncia a soma dos outros,
 * ou somar os outros com refer�ncia a o primeiro? 50[1] 20[1] 10[1] 50[1] 20[2]
 * 10[1] 50[1] 20[2] 10[1] 50[1] 20[1] 10[1] 50[2] 20[1] 10[1] 50[3] 20[1] 10[1]
 * 50[1] 20[2] 10[1] 50[2] 20[2] 10[1] 50[3] 20[2] 10[1] 50[1] 20[2] 10[2] 50[2]
 * 20[2] 10[2] 50[3] 20[2] 10[2] <--- ----> 50[1] 10[1] 20[1] 50[2] 10[1] 20[1]
 * 50[3] 10[1] 20[1] <--- ----> 20[1] 30[1] 10[1] 20[2] 30[1] 10[1] 20[1] 30[1]
 * 10[1] 20[1] 30[2] 10[1] 20[1] 30[3] 10[1] 20[1] 30[1] 10[1] 20[1] 30[1] 10[1]
 * primeiro escape: valor procurado da lista. tentar encontrar uma maneira de
 * que depois que o algoritmo estiver feito, otimiz�lo para percorrer distancias
 * mais significativas, dividindo o valor do produto para encontrar a quantida
 * necess�ria da combina��o. �ltimo escape: se estiver no �ltimo elemento,
 * considerar que ser� a �ltima volta
 */
