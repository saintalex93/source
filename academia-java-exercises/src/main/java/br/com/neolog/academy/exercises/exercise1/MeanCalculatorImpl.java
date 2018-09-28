package br.com.neolog.academy.exercises.exercise1;

import java.util.NoSuchElementException;

public class MeanCalculatorImpl implements MeanCalculator {

	@Override
	public int calculateMean(int... values) {
		if (values == null) {
			throw new NullPointerException();
		}
		if (values.length == 0) {
			throw new NoSuchElementException();
		}
		if (values.length == 1) {
			return values[0];
		}
		long soma = 0;
		for (int i : values) {
			soma += i;
		}
		return checkedCast(soma / values.length);
	}

	private int checkedCast(long average) {
		if(average > Integer.MAX_VALUE || average < Integer.MIN_VALUE){
			throw new IllegalArgumentException();
		}
		return (int) average;
	}

}
