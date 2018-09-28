package br.com.neolog.academy.exercises.exercise3;

import java.util.Scanner;

public class Main {

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
