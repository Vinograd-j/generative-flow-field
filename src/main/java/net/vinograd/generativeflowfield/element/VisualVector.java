package net.vinograd.generativeflowfield.element;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.vinograd.generativeflowfield.math.Vector2;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

@Getter
public class VisualVector {

    private final int length;

    private final float arrowAngle;

    public VisualVector(int length, float arrowAngle) {
        this.length = length;
        this.arrowAngle = arrowAngle;
    }

    public VectorComponents forVector(Vector2D direction, double xStart, double yStart) {
        double xEnd = xStart + direction.getX() * length;
        double yEnd = yStart + direction.getY() * length;

        double dx = xEnd - xStart;
        double dy = yEnd - yStart;

        Vector2D vector = new Vector2D(dx, dy);
        Vector2D perp = (Vector2D) vector.perpendicular().normalize();

        double tan = Math.tan(Math.toRadians(arrowAngle));
        perp = perp.multiply(tan * vector.length());

        double perpXPoint = xStart + perp.getX();
        double perpYPoint = yStart + perp.getY();

        double perpXPointNeg = xStart - perp.getX();
        double perpYPointNeg = yStart - perp.getY();

        Vector2D firstArrow = new Vector2D(xEnd - perpXPoint, yEnd - perpYPoint).normalize().multiply(5);
        Vector2D secondArrow = new Vector2D(xEnd - perpXPointNeg, yEnd - perpYPointNeg).normalize().multiply(5);

        return new VectorComponents(firstArrow, secondArrow, new Vector2D(xEnd, yEnd));
    }

    @AllArgsConstructor
    @Getter
    public static class VectorComponents {

        private Vector2D firstArrowEnd;

        private Vector2D secondArrowEnd;

        private Vector2D vectorLineEnd;

    }

}