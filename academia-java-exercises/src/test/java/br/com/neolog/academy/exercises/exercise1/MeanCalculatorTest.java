package br.com.neolog.academy.exercises.exercise1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.util.NoSuchElementException;

import org.junit.Test;

public class MeanCalculatorTest {

	/**
	 * Atribua ao subject a implementa��o a ser testada.
	 */
	private final MeanCalculator subject = new MeanCalculatorImpl();

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionWhenParamIsNull() {
		subject.calculateMean(null);
		shouldHaveThrown(NullPointerException.class);
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowNoSuchElementExceptionWhenParamIsEmpty() {
		subject.calculateMean();
		shouldHaveThrown(NoSuchElementException.class);
	}

	@Test
	public void shouldReturnElementWhenParamLengthIsOne() {
		assertThat(subject.calculateMean(2)).isEqualTo(2);
	}

	@Test
	public void shouldReturnTwoWhenElementsAre2And3() {
		assertThat(subject.calculateMean(2, 3)).isEqualTo(2);
	}

	@Test
	public void shouldReturnFiveWhenElementsAre2And3AndTen() {
		assertThat(subject.calculateMean(2, 3, 10)).isEqualTo(5);
	}

	@Test
	public void shouldReturnThreeWhenElementsAre2AndNegative3AndTen() {
		assertThat(subject.calculateMean(2, -3, 10)).isEqualTo(3);
	}

	@Test
	public void shouldNotOverflow() {
		assertThat(subject.calculateMean(Integer.MAX_VALUE, 1)).isEqualTo(Integer.MIN_VALUE / -2);
	}

	@Test
	public void shouldReturnIntegerMaxValueWhenElementsAreIntegerMaxValue() {
		assertThat(subject.calculateMean(Integer.MAX_VALUE, Integer.MAX_VALUE)).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void shoudNotOverflowWhenSumIsGreaterThanIntegerMaxValue() {
		assertThat(subject.calculateMean(Integer.MAX_VALUE, 1, 2)).isEqualTo((Integer.MAX_VALUE + 1L + 2L) / 3);
	}

	@Test
	public void shouldNotOverflowWhenAllParametersIsIntegerMaxValue() {
		assertThat(subject.calculateMean(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE))
		.isEqualTo(Integer.MAX_VALUE);
	}
	

	@Test
	public void shouldNotUnderflow() {
		assertThat(subject.calculateMean(Integer.MIN_VALUE, -5, Integer.MAX_VALUE)).isEqualTo(-2);
	}
	
	public void shouldNotUnderflowWhenAllParametersIsIntegerMinValue() {
		assertThat(subject.calculateMean(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE))
		.isEqualTo(Integer.MIN_VALUE);
	}


}
