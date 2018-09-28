package br.com.neolog.academy.exercises.exercise3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class VectorTest {

	@Test
	public void shouldReturnPositiveWhenVectorsAreAnticlockwise() {
		final Vector vector = new Vector(new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE), new Point2D(0, 0));
		final Vector vector2 = new Vector(new Point2D(Integer.MIN_VALUE, Integer.MAX_VALUE), new Point2D(0, 0));
		assertThat(vector.vectorialProduct(vector2)).isGreaterThan(0);
	}

	@Test
	public void shouldReturnNegativeWhenVectorsAreClockwise() {
		final Vector vector = new Vector(new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE), new Point2D(0, 0));
		final Vector vector2 = new Vector(new Point2D(Integer.MIN_VALUE, Integer.MAX_VALUE), new Point2D(0, 0));
		assertThat(vector2.vectorialProduct(vector)).isLessThan(0);
	}

	@Test
	public void shouldReturnZeroWhenVectorsAreParalell() {
		final Vector vector = new Vector(new Point2D(1, 1), new Point2D(11, 11));
		final Vector vector2 = new Vector(new Point2D(-1, -1), new Point2D(5, 5));
		assertThat(vector2.vectorialProduct(vector)).isEqualTo(0);
	}

	@Test
	public void shouldReturnZeroWhenVectorsAreEqual() {
		final Vector vector = new Vector(new Point2D(1, 1), new Point2D(11, 11));
		assertThat(vector.vectorialProduct(vector)).isEqualTo(0);
	}
}
