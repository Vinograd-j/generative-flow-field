package net.vinograd.generativeflowfield.math;

public interface Vector2<T extends Number> extends Vector<T, Vector2<T>> {

    T getX();

    T getY();

}