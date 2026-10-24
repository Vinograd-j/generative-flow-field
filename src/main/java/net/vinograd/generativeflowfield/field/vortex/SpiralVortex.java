package net.vinograd.generativeflowfield.field.vortex;

import lombok.AllArgsConstructor;
import net.vinograd.generativeflowfield.field.VectorField;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

@AllArgsConstructor
public class SpiralVortex implements VectorField {

    private double centerX;

    private double centerY;

    private double radialCoefficient;
    private double tangentCoefficient;

    @Override
    public Vector2D sample(Vector2D coordinate) {
        Vector2D r = new Vector2D(coordinate.getX() - centerX, coordinate.getY() - centerY).normalize();

        Vector2D t = (Vector2D) r.perpendicular().normalize();

        return t.multiply(tangentCoefficient).add(r.multiply(radialCoefficient));
    }

}
