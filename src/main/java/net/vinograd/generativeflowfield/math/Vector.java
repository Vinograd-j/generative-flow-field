package net.vinograd.generativeflowfield.math;

public interface Vector<T extends Number, V extends Vector<T, V>> {

    V add(V vector);

    V subtract(V vector);

    V multiply(Number number);

    V divide(Number number);

    Number lengthSquared();

    T length();

    T dotProduct(V vector);

    V normalize();

}