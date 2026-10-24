package net.vinograd.generativeflowfield.field.vortex;

import lombok.AllArgsConstructor;
import net.vinograd.generativeflowfield.field.VectorField;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

@AllArgsConstructor
public class PointVortex implements VectorField {

    private double centerX;

    private double centerY;

    @Override
    public Vector2D sample(Vector2D coordinate) {
        Vector2D r = new Vector2D(coordinate.getX() - centerX, coordinate.getY() - centerY);

        return (Vector2D) r.perpendicular().divide(r.length()).normalize();
    }

}
