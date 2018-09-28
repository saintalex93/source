package br.com.neolog.academy.exercises.exercise1;

import java.util.NoSuchElementException;

public interface MeanCalculator {

	/**
	 * Calcula a m�dia utilizando divis�o inteira entre os valores especificados.
	 * <p>
	 * Exemplos:
	 * <ul>
	 * <li>calculateMean(2) -> 2</li>
	 * <li>calculateMean(2,3) -> 2</li>
	 * <li>calculateMean(1,2,3) -> 2</li>
	 * <li>calculateMean(2147483647, 2147483647) -> 2147483647</li>
	 * <li>calculateMean() -> lan�a {@link NoSuchElementException}</li>
	 * <li>calculateMean(null) -> lan�a {@link NullPointerException}</li>
	 * </ul>
	 * 
	 * @param values
	 *            valores cuja m�dia ser� calculada
	 * @return m�dia inteira
	 * @throws NullPointerException
	 *             caso values seja <code>null</code>
	 * @throws NoSuchElementException
	 *             caso nenhum valor seja especificado
	 */
	int calculateMean(int... values);
}
