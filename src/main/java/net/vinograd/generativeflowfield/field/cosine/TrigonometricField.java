package net.vinograd.generativeflowfield.field.cosine;

import net.vinograd.generativeflowfield.field.VectorField;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

public class TrigonometricField implements VectorField {

    @Override
    public Vector2D sample(Vector2D coordinate) {
        double angle = Math.sin(0.01 * coordinate.getX()) + Math.cos(0.01 * coordinate.getY());

        return new Vector2D(Math.cos(angle), Math.sin(angle));
    }

}
