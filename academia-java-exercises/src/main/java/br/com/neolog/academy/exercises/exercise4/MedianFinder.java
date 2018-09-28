package br.com.neolog.academy.exercises.exercise4;

import java.util.NoSuchElementException;

public interface MedianFinder {

	/**
	 * Retorna o n�mero do meio de um array ordenado. Caso ele seja par retorna
	 * a soma dos n�meros do meio, dividido por 2.
	 * 
	 * Exemplos:
	 * 
	 * getMediana(2) -> 2;
	 * getMediana(15, 15, 27, 32, 44) -> 27;
	 * getMediana(15, 15, 27, 32, 32, 44) -> 29,5;
	 * 
	 *<li>getMediana() -> lan�a {@link NoSuchElementException}</li>
	 *<li>getMediana(null) -> lan�a {@link NullPointerException}</li>
	 *  
	 *@param values
	 *	valores cuja a mediana ser� calculada
	 *@return 
	 *	mediana extra�da do elemento
	 * 
	 *@throws NullPointerException
	 *	caso values seja null
	 *@throws NoSuchElementException
	 *	caso nenhum valor seja especificado
	 * 
	 */

	public double getMedium(int...values);

}
