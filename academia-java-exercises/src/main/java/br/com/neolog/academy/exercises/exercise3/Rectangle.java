package br.com.neolog.academy.exercises.exercise3;

public class Rectangle {

	private final Point2D leftTop;
	private final Point2D rightTop;
	private final Point2D rightBottom;
	private final Point2D leftBottom;
	private int maxHeight;
	private int maxWidth;
	private int minHeight;
	private int minWidth;

	/**
	 * <h3>Cria uma retângulo a partir de dois pontos.</h3> <br>
	 *
	 * <img src="./rectangle.png" style = "border: 1px solid black;
	 * width:400px"/> <br>
	 *
	 * <h4>Exemplos:</h4>
	 *
	 * <ul>
	 * <li>Rectangle(new(Point2D(4,5)), new(Point2D(4,5)));</li>
	 * <li>Rectangle(new(Point2D(5,7)), new(Point2D(5,8)));</li>
	 *
	 * <li>Rectangle(null, null)-> lança {@link NullPointerException}</li>
	 * <li>Rectangle(null, new(Point2D(5,7))-> lança
	 * {@link NullPointerException}</li>
	 * <li>Rectangle(new(Point2D(5,7), null)-> lança
	 * {@link NullPointerException}</li>
	 * </ul>
	 *
	 * @param startPoint
	 *            O ponto inicial.
	 * @param endPoint
	 *            O ponto final.
	 *
	 * @throws NullPointerException
	 *             Caso startPoint ou o endPoint seja <code>null</code>
	 */

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

	public Point2D[] getPoints() {
		final Point2D points[] = { this.leftTop, this.rightBottom, this.rightTop, this.leftBottom };
		return points;
	}

	@Override
	public String toString() {
		return "Rectangle [leftTop=" + leftTop + ", rightTop=" + rightTop + ", rightBottom=" + rightBottom + ", leftBottom=" + leftBottom + "]";
	}

}
