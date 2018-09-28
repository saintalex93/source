package br.com.neolog.academy.exercises.exercise2;

import java.util.NoSuchElementException;

import br.com.neolog.academy.example.MinFinderImpl;

public class ModeCalculatorImp implements ModeCalculator {

	public MinFinderImpl minFinder = new MinFinderImpl();

	@Override
	public int getMode(int... values) {

		if (values == null) {
			throw new NullPointerException();
		}

		if (values.length == 0) {
			throw new NoSuchElementException();
		}

		if (values.length == 1) {
			return values[0];
		}

		int maxTimes = 0;
		int moda = 0;
		for (int i = 0; i < values.length; i++) {
			int count = 0;
			for (int j = 0; j < values.length; j++) {

				if (values[i] == values[j]) {
					count++;
				}
			}

			if (count > maxTimes) {
				moda = values[i];
				maxTimes = count;
			}

			if (count == maxTimes && values[i] < moda) {
				moda = values[i];
			}

		}

		return moda;
	}

}
