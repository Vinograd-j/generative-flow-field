package net.vinograd.generativeflowfield.element;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.vinograd.generativeflowfield.math.Vector2;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

@Getter
@AllArgsConstructor
public class VisualVector {

    private final int length;

    private final float arrowAngle;

    public VectorComponents forVector(Vector2D direction, double xStart, double yStart) {
        double xEnd = xStart + direction.getX() * length;
        double yEnd = yStart + direction.getY() * length;

        double dx = xEnd - xStart;
        double dy = yEnd - yStart;

        Vector2D vector = new Vector2D(dx, dy);
        Vector2D back = vector.multiply(-1);
        Vector2D perpendicular = (Vector2D) vector.perpendicular();

        perpendicular = perpendicular.normalize().multiply(vector.length() * Math.tan(Math.toRadians(arrowAngle)));

        Vector2D firstArrow = perpendicular.add(back).normalize().multiply(4);
        Vector2D secondArrow = perpendicular.multiply(-1).add(back).normalize().multiply(4);

        return new VectorComponents(firstArrow, secondArrow, vector);
    }

    @AllArgsConstructor
    @Getter
    public static class VectorComponents {

        private Vector2D firstArrowEnd;

        private Vector2D secondArrowEnd;

        private Vector2D vectorLineEnd;

    }

}