package br.com.neolog.academy.exercises.exercise3;

public interface IntersectionFinder {

	/**
	 * <h3>Verifica se há uma intersecção de uma linha em um retângulo.</h3>
	 * <br>
	 * <img src="./cartesianPlane.png" style = "border: 1px solid black;"/> <br>
	 * <a href=
	 * "https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=127">Uva
	 * Example</a>
	 * <h4>Exemplos:</h4>
	 * <ul>
	 *
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(3,3), new Point2D(3,3)),
	 * new LineSegment(new Point2D(3,3), new Point2D(4,4))) -> return
	 * false;</li>
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(8,8), new Point2D(8,8)),
	 * new LineSegment(new Point2D(4,7), new Point2D(7,5))) -> return
	 * false;</li>
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(5,3), new Point2D(5,3)),
	 * new LineSegment(new Point2D(5,5), new Point2D(5,5))) -> return
	 * false;</li>
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(2,3), new Point2D(4,4)),
	 * new LineSegment(new Point2D(2,3), new Point2D(6,5))) -> return true;</li>
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(1,5), new Point2D(7,5)),
	 * new LineSegment(new Point2D(5,2), new Point2D(5,6))) -> return true;</li>
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(2147483647,2147483647),
	 * new Point2D(2147483647,2147483647)), new LineSegment(new
	 * Point2D(2147483647,2147483647), new Point2D(2147483647,2147483647))) ->
	 * return true;</li>
	 *
	 * <li>hasIntersection(new LineSegment(new Point2D(-2147483648,-2147483648),
	 * new Point2D(-2147483648,-2147483648)), new LineSegment(new
	 * Point2D(-2147483648,-2147483648), new Point2D(-2147483648,-2147483648)))
	 * -> return true;</li>
	 *
	 * <li>hasIntersection(null, null)-> lança {@link NullPointerException}</li>
	 * <li>hasIntersection(null, line)-> lança {@link NullPointerException}</li>
	 * <li>hasIntersection(line, null)-> lança {@link NullPointerException}</li>
	 *
	 * </ul>
	 *
	 *
	 *
	 * @param segment1
	 *            Linha construída através de 2 pontos.
	 * @param segment2
	 *            Linha utilizada para construir o retângulo.
	 *
	 * @return True se houver intersecção ou False caso não haja.
	 *
	 * @throws NullPointerException
	 *             Caso segment1 ou segment2 seja <code>null</code>
	 */

	boolean hasIntersection(LineSegment segment1, LineSegment segment2);

}
