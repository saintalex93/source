package br.com.neolog.academy.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.util.NoSuchElementException;

import org.junit.Test;

public class MinFinderTest {

	/**
	 * Atribua ao subject a implementação a ser testada.
	 */
	private final MinFinder subject = new MinFinderImpl();

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExcetptionWhenParameterIsNull() {
		subject.findMinimum(null);
		shouldHaveThrown(NullPointerException.class);
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowNoSuchElementExceptionWhenParameterIsEmpty() {
		subject.findMinimum(new int[0]);
		shouldHaveThrown(NoSuchElementException.class);
	}

	@Test
	public void shouldReturnZeroWhenArrayHasOnlyZeroAsElement() {
		assertThat(subject.findMinimum(asArray(0))).isEqualTo(0);
	}

	@Test
	public void shouldReturnMaxValueWhenArrayHasOnlyMaxValue() {
		assertThat(subject.findMinimum(asArray(Integer.MAX_VALUE))).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void shouldReturnMinValueWhenArrayHasOnlyMinValue() {
		assertThat(subject.findMinimum(asArray(Integer.MIN_VALUE))).isEqualTo(Integer.MIN_VALUE);
	}

	@Test
	public void shouldReturnMinValueAmongThreePositiveElements() {
		assertThat(subject.findMinimum(asArray(1, 2, 3))).isEqualTo(1);
	}

	@Test
	public void shouldReturnMinValueAmongThreeNegativeElements() {
		assertThat(subject.findMinimum(asArray(-1, -2, -3))).isEqualTo(-3);
	}

	@Test
	public void shouldReturnMinValueAmongThreeElements() {
		assertThat(subject.findMinimum(asArray(1, -2, 3))).isEqualTo(-2);
	}

	@Test
	public void shouldDetectMinPossibleValueShortCircuiting() {
		final int[] param = new int[Integer.MAX_VALUE / 10];
		param[0] = Integer.MIN_VALUE;
		param[param.length - 1] = Integer.MIN_VALUE;
		final long instant = System.nanoTime();
		final int minimum = subject.findMinimum(param);
		final long elapsed = System.nanoTime() - instant;
		assertThat(minimum).isEqualTo(Integer.MIN_VALUE);
		assertThat(elapsed).overridingErrorMessage("Execution time %s ms was expected to be under 0.5 ms", elapsed / 1e6).isLessThan(500_000);
	}

	private static int[] asArray(final int... values) {
		return values;
	}
}
