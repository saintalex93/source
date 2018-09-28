package br.com.neolog.academy.exercises.exercise3;

public class LineSegment {

	private final Point2D linePointStart;
	private final Point2D linePointEnd;

	/**
	 * <h3>Cria uma linha a partir de dois pontos.</h3> <br>
	 *
	 * <img src="./line.png" style = "border: 1px solid black;"/> <br>
	 *
	 * <h4>Exemplos:</h4> <br>
	 *
	 * <ul>
	 * <li>LineSegment(new(Point2D(4,5)), new(Point2D(4,5)));</li>
	 * <li>LineSegment(new(Point2D(5,7)), new(Point2D(5,8)));</li>
	 *
	 * <li>LineSegment(null, null)-> lança {@link NullPointerException}</li>
	 * <li>LineSegment(null, new(Point2D(5,7))-> lança
	 * {@link NullPointerException}</li>
	 * <li>LineSegment(new(Point2D(5,7), null)-> lança
	 * {@link NullPointerException}</li>
	 * </ul>
	 *
	 * @param start
	 *            O ponto inicial.
	 * @param end
	 *            O ponto final.
	 *
	 * @throws NullPointerException
	 *             Caso start ou end sejam <code>null</code>
	 */

	public LineSegment(final Point2D start, final Point2D end) {

		if (start == null || end == null) {
			throw new NullPointerException();
		}

		this.linePointStart = start;
		this.linePointEnd = end;

	}

	public Point2D getStart() {
		return linePointStart;
	}

	public Point2D getEnd() {
		return linePointEnd;
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LineSegment)) {
			return false;
		}

		final LineSegment other = (LineSegment) obj;

		return linePointStart.equals(other.linePointStart) && linePointEnd.equals(other.linePointEnd);
	}

	@Override
	public String toString() {
		return "Line [Point Start=" + linePointStart + ", Point End=" + linePointEnd + "]";
	}
}
