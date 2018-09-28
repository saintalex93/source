package br.com.neolog.academy.exercises.exercise3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import org.junit.Test;

public class IntersectionFinderTest {

	private final IntersectionFinder subject = new IntersectionFinderImpl();

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionWhenAnyParamIsNull() {
		subject.hasIntersection(null, null);
		shouldHaveThrown(NullPointerException.class);
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionWhenOneParamIsNull() {
		subject.hasIntersection(new LineSegment(new Point2D(2, 3), new Point2D(2, 3)), null);
		shouldHaveThrown(NullPointerException.class);
	}

	@Test
	public void shouldReturnFalseWhenLineAndRectangleAreADot() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(3, 3), new Point2D(3, 3)), new LineSegment(new Point2D(4, 4), new Point2D(4, 4)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineAndRectangleAreADot() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(4, 4), new Point2D(4, 4)), new LineSegment(new Point2D(4, 4), new Point2D(4, 4)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnFalseWhenLineIsADotAndRectangleIsAVerticalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(3, 3), new Point2D(3, 3)), new LineSegment(new Point2D(5, 5), new Point2D(5, 2)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineIsADotAndRectangleIsAVerticalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(5, 3), new Point2D(5, 3)), new LineSegment(new Point2D(5, 5), new Point2D(5, 2)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnFalseWhenLineIsADotAndRectangleIsAHorizontalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(3, 3), new Point2D(3, 3)), new LineSegment(new Point2D(2, 5), new Point2D(5, 5)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineIsADotAndRectangleIsAHorizontalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(3, 5), new Point2D(3, 5)), new LineSegment(new Point2D(2, 5), new Point2D(5, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenLineIsADotInRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(5, 5), new Point2D(5, 5)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnFalseWhenLineIsADotOutRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(8, 8), new Point2D(8, 8)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnFalseWhenLineIsOutOfTheRectangleWhenRectangleIsADot() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(8, 5), new Point2D(8, 5)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineIsInTheRectangleWhenRectangleIsADot() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(5, 5), new Point2D(5, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenLineIsOutOfTheRectangleWhenRectangleIsAVerticalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(5, 2), new Point2D(5, 6)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnFalseWhenLineIsInTheRectangleWhenRectangleIsAVerticalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(5, 2), new Point2D(5, 3)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineIsInTheRectangleWhenRectangleIsAHorizontalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(4, 5), new Point2D(7, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnFalseWhenLineIsOutOfTheRectangleWhenRectangleIsAHorizontalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(1, 4), new Point2D(7, 4)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineIsOutOfTheRectangleWhenRectangleIsAHorizontalLine() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 5), new Point2D(7, 5)), new LineSegment(new Point2D(4, 5), new Point2D(7, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenHaveBothPointsInsideTheRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(4, 5), new Point2D(5, 4)), new LineSegment(new Point2D(3, 3), new Point2D(6, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenAtLeastOnePointOfTheLineIsInsideARectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(2, 3), new Point2D(4, 4)), new LineSegment(new Point2D(3, 3), new Point2D(6, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenAtLeastOnePointIsInsideARectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(2, 3), new Point2D(4, 4)), new LineSegment(new Point2D(3, 3), new Point2D(6, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnFalseWhenADiagonalLineIsOutOfRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 1), new Point2D(4, 5)), new LineSegment(new Point2D(3, 3), new Point2D(5, 1)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnFalseWhenADiagonalLineIsBelowTheRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(1, 0), new Point2D(2, 1)), new LineSegment(new Point2D(3, 3), new Point2D(5, 1)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnFalseWhenADiagonalLineIsAboveTheRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(4, 8), new Point2D(5, 8)), new LineSegment(new Point2D(3, 3), new Point2D(5, 1)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnFalseWhenALineIsToRightOfTheRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(8, 7), new Point2D(8, 5)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnFalseWhenALineIsToLeftOfTheRectangle() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(3, 7), new Point2D(3, 5)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(false);
	}

	@Test
	public void shouldReturnTrueWhenLineIsADiagonalIntersecction() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(6, 8), new Point2D(8, 6)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenLineIsAVerticalIntersecction() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(3, 6), new Point2D(8, 6)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueWhenLineIsAHorizontalIntersecction() {
		assertThat(subject.hasIntersection(new LineSegment(new Point2D(6, 2), new Point2D(6, 8)), new LineSegment(new Point2D(4, 7), new Point2D(7, 5)))).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueIfAllElementsAreSame() {
		final Point2D maxValuePoint = new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE);
		final LineSegment segment = new LineSegment(maxValuePoint, maxValuePoint);
		final LineSegment rectangle = new LineSegment(maxValuePoint, maxValuePoint);
		assertThat(subject.hasIntersection(segment, rectangle)).isEqualTo(true);
	}

	@Test
	public void shouldReturnTrueIfHasIntersectionCloseToMaxValuePoint() {
		final Point2D southWest = new Point2D(Integer.MIN_VALUE + 10, Integer.MIN_VALUE);
		final Point2D northEast = new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
		final LineSegment segment = new LineSegment(new Point2D(southWest.getX() - 1, southWest.getY()), new Point2D(southWest.getX() + 1, northEast.getY() + 1));
		final LineSegment rectangle = new LineSegment(southWest, northEast);
		assertThat(subject.hasIntersection(segment, rectangle)).isEqualTo(true);
	}

}
