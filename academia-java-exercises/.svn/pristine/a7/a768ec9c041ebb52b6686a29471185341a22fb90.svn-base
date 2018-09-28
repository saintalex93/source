package br.com.neolog.academy.exercises.exercise2;

import java.util.NoSuchElementException;

public interface ModeCalculator {

	/**
	 * Calcula a moda entre os valores especificados. Isto é, retorna o elemento que
	 * aparece o maior número de vezes no array especificado.
	 * <p>
	 * Caso exista mais de um elemento que seja a moda, arbitrariamente deve ser
	 * retornado o menor valor entre eles.
	 * <p>
	 * Exemplos:
	 * <ul>
	 * <li>getMode(2) -> 2</li>
	 * <li>getMode(2, 2, 3) -> 2</li>
	 * <li>getMode(-1, 2, 3, -1, 2, -1) -> -1</li>
	 * <li>getMode(5, 5, 4, 4, 3, 0) -> 4</li>
	 * <li>getMode(0, 1, 2, 3, 4) -> 0</li>
	 * <li>getMode() -> lança {@link NoSuchElementException}</li>
	 * <li>getMode(null) -> lança {@link NullPointerException}</li>
	 * </ul>
	 * 
	 * @param values
	 *            valores cuja moda será calculada
	 * @return moda entre os elementos
	 * @throws NullPointerException
	 *             caso values seja <code>null</code>
	 * @throws NoSuchElementException
	 *             caso nenhum valor seja especificado
	 */
	int getMode(int... values);
}
