package br.com.neolog.academy.exercises.exercise3;

public class Vector {

	private final long x;
	private final long y;

	/**
	 * <h3>Cria uma vetor a partir de dois pontos.</h3> <br>
	 *
	 * <img src="./vector.png" style = "border: 1px solid black; width:400px"/>
	 * <br>
	 *
	 * <h4>Exemplos:</h4>
	 *
	 * <ul>
	 * <li>Vector(new(Point2D(4,5)), new(Point2D(4,5)));</li>
	 * <li>Vector(new(Point2D(5,7)), new(Point2D(5,8)));</li>
	 *
	 * <li>Vector(null, null)-> lança {@link NullPointerException}</li>
	 * <li>Vector(null, new(Point2D(5,7))-> lança
	 * {@link NullPointerException}</li>
	 * <li>Vector(new(Point2D(5,7), null)-> lança
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
