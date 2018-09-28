package br.com.neolog.academy.exercises.exercise4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.util.NoSuchElementException;

import org.junit.Test;

import br.com.neolog.academy.exercises.exercise4.MedianFinder;
import br.com.neolog.academy.exercises.exercise4.MedianFinderImpl;

public class MedianaFinderTest {

	private final MedianFinder subject = new MedianFinderImpl();

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionWhenParamIsNull() {
		subject.getMedium(null);
		shouldHaveThrown(NullPointerException.class);
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowNoSuchElementExceptionWhenParamIsEmpty() {
		subject.getMedium();
		shouldHaveThrown(NoSuchElementException.class);
	}

	@Test
	public void shouldReturnTheMiddleNumberInOddAray() {
		assertThat(subject.getMedium(1, 2, 3)).isEqualTo(2);
	}

	@Test
	public void shouldReturnTheSumAndDivisionOfMiddlePartsOfEvenArray() {
		assertThat(subject.getMedium(8, 5, 2, 4, 6, 5, 3, 9, 7, 10, -1, -8, -5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE)).isEqualTo(4.5);
	}

	@Test
	public void shouldReturnIntegerMaxValueOfTwoMaxValues() {
		assertThat(subject.getMedium(Integer.MAX_VALUE, Integer.MAX_VALUE)).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void shouldReturnIntegerMinValueOfTwoMinValues() {
		assertThat(subject.getMedium(Integer.MIN_VALUE, Integer.MIN_VALUE)).isEqualTo(Integer.MIN_VALUE);
	}

	@Test
	public void shouldNotOverFlow() {
		assertThat(subject.getMedium(Integer.MIN_VALUE, Integer.MAX_VALUE)).isEqualTo(-0.5);
	}

}
