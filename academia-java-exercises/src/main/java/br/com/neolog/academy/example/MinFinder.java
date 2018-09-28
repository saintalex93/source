package br.com.neolog.academy.example;

import java.util.NoSuchElementException;

public interface MinFinder {

	/**
	 * Retorna o menor valor presente no array especificado.
	 * <p>
	 * Caso o array esteja vazio, � esperado que seja lan�ada a exce��o
	 * {@link NoSuchElementException}. Ainda, no caso de array <code>null</code>, o
	 * comportamento esperado � o lan�amento de {@link NullPointerException}.
	 * <p>
	 * Exemplos:
	 * <ul>
	 * <li>findMinimum([1]) -> 1</li>
	 * <li>findMinimum([1, 2, 3]) -> 1</li>
	 * <li>findMinimum([1, -2, 3]) -> -2</li>
	 * <li>findMinimum([]) -> lan�a {@link NoSuchElementException}</li>
	 * <li>findMinimum(null) -> lan�a {@link NullPointerException}</li>
	 * </ul>
	 * 
	 * @param values
	 *            array de valores inteiros
	 * @return menor valor presente no array
	 * @throws NullPointerException
	 *             caso values seja <code>null</code>
	 * @throws NoSuchElementException
	 *             caso o array esteja vazio
	 */
	int findMinimum(int[] values);
}
