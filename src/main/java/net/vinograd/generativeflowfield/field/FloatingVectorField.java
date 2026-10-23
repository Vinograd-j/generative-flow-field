package net.vinograd.generativeflowfield.field;

import net.vinograd.generativeflowfield.math.rational.Vector2D;

public interface FloatingVectorField {

    Vector2D sample(Vector2D coordinate, double t);

}
