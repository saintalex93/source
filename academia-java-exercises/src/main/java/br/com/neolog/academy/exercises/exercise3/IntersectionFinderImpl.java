package br.com.neolog.academy.exercises.exercise3;

public class IntersectionFinderImpl implements IntersectionFinder {

	/**
	 * <h3>A partir de duas linhas, retorna se tem intersec��o ou n�o.</h3><br>
	 *
	 * <h4>Exemplos:</h4>
	 *
	 * <ul>
	 * <li>hasIntersection(new(Point2D(4,5)), new(Point2D(4,5)),
	 * new(Point2D(4,5)), new(Point2D(4,5))) -> return true;</li>
	 * <li>hasIntersection(new(Point2D(10,10)), new(Point2D(11,11)),
	 * new(Point2D(4,5)), new(Point2D(4,5))) -> return false;</li>
	 *
	 * <li>hasIntersection(lineSegment, null)-> lan�a
	 * {@link NullPointerException}</li>
	 * <li>hasIntersection(null, lineSegment)-> lan�a
	 * {@link NullPointerException}</li>
	 * <li>hasIntersection(null, null)-> lan�a {@link NullPointerException}</li>
	 * </ul>
	 *
	 * @param segmentLine
	 *            A linha a ser analisada.
	 * @param rectangleLine
	 *            A linha do ret�ngulo.
	 *
	 * @throws NullPointerException
	 *             Caso segmentLine ou o rectangleLine seja <code>null</code>
	 */

	@Override
	public boolean hasIntersection(final LineSegment segmentLine, final LineSegment rectangleLine) {

		if (segmentLine == null || rectangleLine == null) {
			throw new NullPointerException();
		}
		if (segmentLine.equals(rectangleLine)) {
			return true;
		}

		final Rectangle rectangle = new Rectangle(rectangleLine.getStart(), rectangleLine.getEnd());

		// Confere se a linha � um ponto, e se algum ponto est� fora do
		// ret�ngulo
		if (segmentLine.getStart().equals(segmentLine.getEnd())) {

			return GeometryUtils.isPointInsideRectangle(segmentLine.getStart(), rectangle);

		}

		if (GeometryUtils.isLineOutOfRectangleProjection(segmentLine, rectangle)) {
			return false;
		}

		if (GeometryUtils.lineHasPointInsideRectangle(segmentLine, rectangle)) {
			return true;
		}

		return GeometryUtils.isVectorialIntersectBetweenLineAndRectangle(segmentLine, rectangle);
	}

}
