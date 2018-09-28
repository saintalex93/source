package br.com.neolog.academy.exercises.exercise4;

import java.util.NoSuchElementException;

public class MedianFinderImpl implements MedianFinder {

	@Override
	public double getMedium(int... values) {

		if (values == null) {
			throw new NullPointerException();
		}

		if (values.length == 0) {
			throw new NoSuchElementException();
		}

		if (values.length == 1) {
			return values[0];
		}

		values = sortNumbers(values);
		int length = values.length;

		if (length % 2 != 0) {
			return values[length / 2];
		}

		double rightPiece = values[length / 2];
		double leftPiece = values[(length / 2) - 1];

		// if(leftPiece == rightPiece){
		// return leftPiece;
		// }

		return (rightPiece + leftPiece) / 2d;

	}

	private int[] sortNumbers(int... values) {
		int aux;
		for (int i = 0; i < values.length; i++) {
			boolean hasChange = false;
			for (int j = 0; j < values.length - i - 1; j++) {
				if (values[j] > values[j + 1]) {
					hasChange = true;
					aux = values[j];
					values[j] = values[j + 1];
					values[j + 1] = aux;
				}
			}
			if (hasChange == false) {
				break;
			}
		}
		return values;
	}

}
