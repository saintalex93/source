package br.com.neolog.academy.exercises.exercise2;

public class TestModa {

	public static void main(String[] args) {

		ModeCalculatorImp modeCalculatorImp = new ModeCalculatorImp();
		
		System.out.println(modeCalculatorImp.getMode(5,3,2,0,-5,1));
		
	}

}
