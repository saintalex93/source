package br.com.neolog.academy.exercises.exercise3;

import java.util.Objects;

public class Point2D {

	private final int x;
	private final int y;

	/**
	 * <h3>Cria um ponto a partir de dois inteiros.</h3> <br>
	 *
	 * <h4>Exemplos:</h4>
	 *
	 * <ul>
	 * <li>Point2D(4,5);</li>
	 * <li>Point2D(5,7);</li>
	 * </ul>
	 *
	 * @param x
	 *            O eixo X.
	 * @param y
	 *            O eixo Y.
	 *
	 */

	public Point2D(final int x, final int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Point2D)) {
			return false;
		}
		final Point2D other = (Point2D) obj;
		return Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
