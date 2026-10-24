package net.vinograd.generativeflowfield.math;

import net.vinograd.generativeflowfield.math.rational.Vector2D;

public interface Vector2<T extends Number> extends Vector<T, Vector2<T>> {

    T getX();

    T getY();

    Vector2<T> perpendicular();

}