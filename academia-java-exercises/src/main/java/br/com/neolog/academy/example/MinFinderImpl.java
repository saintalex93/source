package br.com.neolog.academy.example;

import java.util.NoSuchElementException;

public class MinFinderImpl implements MinFinder {

	@Override
	public int findMinimum(final int[] values) {
		if (values == null) {
			throw new NullPointerException();
		}
		if (values.length == 0) {
			throw new NoSuchElementException();
		}
		int min = values[0];
 		for (int i = 1; i < values.length && min > Integer.MIN_VALUE; i++) {
			final int value = values[i];
			if (value < min) {
				min = value;
			}
		}
		return min;
	}

}
