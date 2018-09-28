package br.com.neolog.academy.exercises.exercise4;

public class TestMedian {

	public static void main(String[] args) {

		MedianFinderImpl medianaFinderImpl = new MedianFinderImpl();

//		int[] testArray = {32, 27, 15, 44, 15, 32};
		int[] testArray = {Integer.MIN_VALUE, Integer.MAX_VALUE};
//		int[] testArray = {3,2,2,1};
		System.out.println(medianaFinderImpl.getMedium(testArray));
	}

}
