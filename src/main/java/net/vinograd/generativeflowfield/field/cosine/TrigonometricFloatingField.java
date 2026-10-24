package net.vinograd.generativeflowfield.field.cosine;

import net.vinograd.generativeflowfield.field.FloatingVectorField;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

public class TrigonometricFloatingField implements FloatingVectorField {

    @Override
    public Vector2D sample(Vector2D coordinate, double t) {
        double x = coordinate.getX();
        double y = coordinate.getY();

        double angle = - 2 * Math.PI * t;

        return new Vector2D(
                Math.cos(angle),
                Math.sin(angle)
        );
    }

}
