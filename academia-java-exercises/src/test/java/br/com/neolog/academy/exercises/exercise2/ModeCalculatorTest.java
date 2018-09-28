package br.com.neolog.academy.exercises.exercise2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ModeCalculatorTest {
	/**
	 * Atribua ao subject a implementação a ser testada.
	 */
	private final ModeCalculator subject = new ModeCalculatorImp();

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionWhenParamIsNull() {
		subject.getMode(null);
		shouldHaveThrown(NullPointerException.class);
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowNoSuchElementExceptionWhenParamIsEmpty() {
		subject.getMode();
		shouldHaveThrown(NoSuchElementException.class);
	}

	@Test
	public void shouldReturnElementWhenParamHasOneElement() {
		assertThat(subject.getMode(Integer.MAX_VALUE)).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void shouldReturnFourWhenModeIsInTheEndOfArray() {
		assertThat(subject.getMode(1, 2, 3, 4, 4)).isEqualTo(4);
	}

	@Test
	public void shouldReturnOneWhenModeIsInTheStartOfArray() {
		assertThat(subject.getMode(1, 1, 3, 4, 5)).isEqualTo(1);
	}

	@Test
	public void shouldReturnOneWhenModeIsSparseInTheArray() {
		assertThat(subject.getMode(1, 4, 3, 1, 5)).isEqualTo(1);
	}

	@Test
	public void shouldFindModeAmongNegativeNumbers() {
		assertThat(subject.getMode(-1, -4, -3, -1, -5)).isEqualTo(-1);
	}

	@Test
	public void shouldReturnOneWhenOneAndFourAreMode() {
		assertThat(subject.getMode(1, 1, 4, 4, 1, 4)).isEqualTo(1);
	}
	@Test
	public void shouldReturnMinNumberWhenGroupOfElementsHasSameCount() {
		assertThat(subject.getMode(4,4,2,2,1,1,0,0,-1,-1)).isEqualTo(-1);
	}
	
	@Test
	public void shouldReturnMinNumberWhenGroupOfElementsHasSameCount1() {
		assertThat(subject.getMode(4,4,2,2,1,1,0,0,Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE )).isEqualTo(Integer.MAX_VALUE);
	}
	

	@Test
	public void shouldReturnMinimumWhenElementsAreDistinct() {
		assertThat(subject.getMode(3, 4, 1, -1, 5, 2)).isEqualTo(-1);
	}
}
