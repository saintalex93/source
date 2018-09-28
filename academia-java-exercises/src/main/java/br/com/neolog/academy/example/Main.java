package br.com.neolog.academy.example;

import java.util.Objects;
import java.util.Scanner;

class Main {

	// Resolução do exercício do UVA 191
	// https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=127

	public static void main(final String[] args) {

		final Scanner combinations = new Scanner(System.in);
		System.out.println("Press the quantity of rounds and above, your combinations\nExemple:\n2\n8 2 6 3 4 7 7 5\n8 2 8 2 4 7 7 5");

		final int quantity = combinations.nextInt();

		for (int i = 0; i < quantity; i++) {

			final Point2D segmentStartPoint = new Point2D(combinations.nextInt(), combinations.nextInt());
			final Point2D segmentEndPoint = new Point2D(combinations.nextInt(), combinations.nextInt());
			final Point2D rectangleStartPoint = new Point2D(combinations.nextInt(), combinations.nextInt());
			final Point2D rectangleEndPoint = new Point2D(combinations.nextInt(), combinations.nextInt());

			final LineSegment segmentLine = new LineSegment(segmentStartPoint, segmentEndPoint);
			final LineSegment rectangleLine = new LineSegment(rectangleStartPoint, rectangleEndPoint);
			final IntersectionFinderImpl intersectionFinderImpl = new IntersectionFinderImpl();
			System.out.println(intersectionFinderImpl.hasIntersection(segmentLine, rectangleLine) ? "T" : "F");

		}

		combinations.close();

	}

}

interface IntersectionFinder {

	boolean hasIntersection(LineSegment segment1, LineSegment segment2);

}

class Point2D {

	private final int x;
	private final int y;

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
	public String toString() {
		return "(" + x + "," + y + ")";
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

}

class Vector {

	private final long x;
	private final long y;

	Vector(final Point2D startPoint, final Point2D endPoint) {
		if (startPoint == null || endPoint == null) {
			throw new NullPointerException();
		}

		x = startPoint.getX() - endPoint.getX();
		y = startPoint.getY() - endPoint.getY();
	}

	Vector(final LineSegment lineSegment) {
		x = lineSegment.getStart().getX() - lineSegment.getEnd().getX();
		y = lineSegment.getStart().getY() - lineSegment.getEnd().getY();
	}

	public long getX() {
		return this.x;
	}

	public long getY() {
		return this.y;
	}

	public long vectorialProduct(final Vector vector) {
		if (vector == null) {
			throw new NullPointerException();
		}

		return this.getX() * vector.getY() - this.getY() * vector.getX();
	}
}

class Rectangle {

	private final Point2D leftTop; // *
	private final Point2D rightTop;
	private final Point2D rightBottom; // *
	private final Point2D leftBottom;
	private int maxHeight;
	private int maxWidth;
	private int minHeight;
	private int minWidth;

	public int getMaxHeight() {
		return maxHeight;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public Rectangle(final Point2D startPoint, final Point2D endPoint) {

		if (startPoint == null || endPoint == null) {
			throw new NullPointerException();
		}

		this.leftTop = startPoint;
		this.rightBottom = endPoint;
		this.rightTop = new Point2D(rightBottom.getX(), leftTop.getY());
		this.leftBottom = new Point2D(leftTop.getX(), rightBottom.getY());

		setDimensions(startPoint, endPoint);
	}

	private void setDimensions(final Point2D startPoint, final Point2D endPoint) {

		if (startPoint == null || endPoint == null) {
			throw new NullPointerException();
		}

		if (startPoint.getX() < endPoint.getX()) {
			this.maxHeight = endPoint.getX();
			this.minHeight = startPoint.getX();
		}

		else {
			this.minHeight = endPoint.getX();
			this.maxHeight = startPoint.getX();
		}

		if (startPoint.getY() < endPoint.getY()) {
			this.maxWidth = endPoint.getY();
			this.minWidth = startPoint.getY();
		}

		else {
			this.minWidth = endPoint.getY();
			this.maxWidth = startPoint.getY();
		}
	}

	public Point2D getLeftTop() {
		return leftTop;
	}

	public Point2D getRightTop() {
		return rightTop;
	}

	public Point2D getRightBottom() {
		return rightBottom;
	}

	public Point2D getLeftBottom() {
		return leftBottom;
	}

	public Point2D[] getPoints() {
		final Point2D points[] = { this.leftTop, this.rightBottom, this.rightTop, this.leftBottom };
		return points;
	}

	@Override
	public String toString() {
		return "Rectangle [leftTop=" + leftTop + ", rightTop=" + rightTop + ", rightBottom=" + rightBottom + ", leftBottom=" + leftBottom + "]";
	}

}

class IntersectionFinderImpl implements IntersectionFinder {

	@Override
	public boolean hasIntersection(final LineSegment segmentLine, final LineSegment rectangleLine) {

		if (segmentLine == null || rectangleLine == null) {
			throw new NullPointerException();
		}
		if (segmentLine.equals(rectangleLine)) {
			return true;
		}

		final Rectangle rectangle = new Rectangle(rectangleLine.getStart(), rectangleLine.getEnd());

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

class LineSegment {

	private final Point2D linePointStart;
	private final Point2D linePointEnd;

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
	public String toString() {
		return "Line [Point Start=" + linePointStart + ", Point End=" + linePointEnd + "]";
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

}

class GeometryUtils {

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
	 *         positivo e negativo com base no método {@link isZeroBetween}<br>
	 *
	 *         false se houver apenas números positivos ou apenas números
	 *         negativos e nenhum zero com base no método {@link isZeroBetween}.
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
