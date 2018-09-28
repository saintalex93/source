package br.com.neolog.academy.exercises.exercise3;

public class GeometryUtils {

	/**
	 * <h3>Procura um ponto da linha dentro de um retângulo.</h3>
	 * <h4>Exemplos:</h4>
	 * <ul>
	 * <li>lineHasPointInsideRectangle(line, rectangle)</li>
	 *
	 * <li>lineHasPointInsideRectangle(null, null)-> lança
	 * {@link NullPointerException}</li>
	 * <li>lineHasPointInsideRectangle(null, rectangle)-> lança
	 * {@link NullPointerException}</li>
	 * <li>lineHasPointInsideRectangle(line, null)-> lança
	 * {@link NullPointerException}</li>
	 * </ul>
	 *
	 * @param line
	 *            A linha inicial.
	 * @param rectangle
	 *            O retângulo a ser comparado.
	 *
	 * @return true se houver um ou dois pontos da linha inicial dentro ou na
	 *         linha do retângulo <br>
	 *         false se não houver nenhum ponto da linha no retângulo
	 *
	 * @throws NullPointerException
	 *             caso line ou rectangle seja <code>null</code>
	 */

	public static boolean lineHasPointInsideRectangle(final LineSegment line, final Rectangle rectangle) {

		if (line == null || rectangle == null) {
			throw new NullPointerException();
		}

		return isPointInsideRectangle(line.getStart(), rectangle) || isPointInsideRectangle(line.getEnd(), rectangle);
	}

	/**
	 * <h3>Procura um ponto dentro de um retângulo.</h3>
	 * <h4>Exemplos:</h4>
	 * <ul>
	 * <li>isPointInsideRectangle(line, rectangle)</li>
	 *
	 * <li>isPointInsideRectangle(null, null)-> lança
	 * {@link NullPointerException}</li>
	 * <li>isPointInsideRectangle(null, rectangle)-> lança
	 * {@link NullPointerException}</li>
	 * <li>isPointInsideRectangle(line, null)-> lança
	 * {@link NullPointerException}</li>
	 *
	 * </ul>
	 *
	 * @param line
	 *            A linha inicial.
	 * @param rectangle
	 *            O retângulo a ser comparado.
	 *
	 * @return true se houver o ponto dentro ou na linha do retângulo<br>
	 *         false se não houver o ponto dentro ou na linha do retângulo
	 *
	 * @throws NullPointerException
	 *             caso point ou rectangle seja <code>null</code>
	 *
	 */

	public static boolean isPointInsideRectangle(final Point2D point, final Rectangle rectangle) {

		if (point == null || rectangle == null) {
			throw new NullPointerException();
		}

		return point.getX() >= rectangle.getMinHeight() && point.getX() <= rectangle.getMaxHeight() && point.getY() >= rectangle.getMinWidth()
				&& point.getY() <= rectangle.getMaxWidth();
	}

	/**
	 * <h3>Procura se a linha está fora da projeção do retângulo.</h3>
	 * <h4>Exemplos:</h4>
	 * <ul>
	 * <li>isLineOutOfRectangleProjection(line, rectangle)</li>
	 *
	 *
	 * <li>isLineOutOfRectangleProjection(null, null)-> lança
	 * {@link NullPointerException}</li>
	 * <li>isLineOutOfRectangleProjection(line, null)-> lança
	 * {@link NullPointerException}</li>
	 * <li>isLineOutOfRectangleProjection(null, rectangle)-> lança
	 * {@link NullPointerException}</li>
	 *
	 * </ul>
	 *
	 * @param line
	 *            A linha inicial.
	 * @param rectangle
	 *            O retângulo a ser comparado.
	 *
	 * @return true se houver a linha estiver fora da projeção do retângulo <br>
	 *         false se a linha estiver dentro do retângulo
	 *
	 * @throws NullPointerException
	 *             caso line ou rectangle seja <code>null</code>
	 */

	public static boolean isLineOutOfRectangleProjection(final LineSegment line, final Rectangle rectangle) {

		if (line == null || rectangle == null) {
			throw new NullPointerException();
		}

		if (line.getStart().getX() < rectangle.getMinHeight() && line.getEnd().getX() < rectangle.getMinHeight()
				|| line.getStart().getX() > rectangle.getMaxHeight() && line.getEnd().getX() > rectangle.getMaxHeight()
				|| line.getStart().getY() < rectangle.getMinWidth() && line.getEnd().getY() < rectangle.getMinWidth()
				|| line.getStart().getY() > rectangle.getMaxWidth() && line.getEnd().getY() > rectangle.getMaxWidth()) {

			return true;
		}

		return false;
	}

	/**
	 * <h3>Procura se há uma intersecção com a criação de um vetor.</h3>
	 * <h4>Exemplos:</h4>
	 * <ul>
	 * <li>isVectorialIntersectBetweenLineAndRectangle(line, rectangle)</li>
	 *
	 * <li>isVectorialIntersectBetweenLineAndRectangle(null, null)-> lança
	 * {@link NullPointerException}</li>
	 * <li>isVectorialIntersectBetweenLineAndRectangle(line, null)-> lança
	 * {@link NullPointerException}</li>
	 * <li>isVectorialIntersectBetweenLineAndRectangle(null, rectangle)-> lança
	 * {@link NullPointerException}</li>
	 *
	 * </ul>
	 *
	 * @param line
	 *            A linha inicial.
	 * @param rectangle
	 *            O retângulo a ser comparado.
	 *
	 * @return true se houver algum resultado 0, ou de numeros distindos entre
	 *         positivo e negativo com base no método
	 *         {@link GeometryUtils#isZeroBetween}<br>
	 *
	 *         false se houver apenas números positivos ou apenas números
	 *         negativos e nenhum zero com base no método
	 *         {@link GeometryUtils#isZeroBetween}.
	 *
	 * @throws NullPointerException
	 *             caso lineSegment ou rectangle seja <code>null</code>
	 */

	public static boolean isVectorialIntersectBetweenLineAndRectangle(final LineSegment lineSegment, final Rectangle rectangle) {

		if (lineSegment == null || rectangle == null) {
			throw new NullPointerException();
		}

		final Vector vectorLine = new Vector(lineSegment);

		final int[] vectorialProducts = new int[4];

		vectorialProducts[0] = (int) vectorLine.vectorialProduct(new Vector(lineSegment.getStart(), rectangle.getLeftTop()));
		vectorialProducts[1] = (int) vectorLine.vectorialProduct(new Vector(lineSegment.getStart(), rectangle.getRightTop()));
		vectorialProducts[2] = (int) vectorLine.vectorialProduct(new Vector(lineSegment.getStart(), rectangle.getRightBottom()));
		vectorialProducts[3] = (int) vectorLine.vectorialProduct(new Vector(lineSegment.getStart(), rectangle.getLeftBottom()));

		return isZeroBetween(vectorialProducts);

	}

	/**
	 * <h3>Procura se o número 0 está entre o mínimo e o máximo, provando que há
	 * intersecção no vetor.</h3>
	 *
	 * <h4>Exemplos:</h4>
	 *
	 * <ul>
	 * <li>isZeroBetween(1,2,3,4) - return false;</li>
	 * <li>isZeroBetween(-1,-2,1,2) - return true;</li>
	 *
	 * <li>isZeroBetween(null)-> lança {@link NullPointerException}</li>
	 *
	 * </ul>
	 *
	 * @param values
	 *            Os produtos vetoriais.
	 *
	 * @return true se houver algum a possibilidade de ter um 0 entre o mínimo e
	 *         o máximo
	 *
	 * @returns false se não houver algum a possibilidade de ter um 0 entre o
	 *          mínimo e o máximo
	 *
	 * @throws NullPointerException
	 *             caso values <code>null</code>
	 */

	private static boolean isZeroBetween(final int... values) {

		if (values == null) {
			throw new NullPointerException();
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (final int i : values) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		return min <= 0 && max >= 0;
	}

}
