package net.vinograd.generativeflowfield.math.rational;

import net.vinograd.generativeflowfield.math.Vector2;

public class Vector2D implements Vector2<Double> {

    private final double x;

    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Vector2<Double> perpendicular() {
        return new Vector2D(-y, x);
    }

    @Override
    public Vector2D add(Vector2<Double> vector) {
        return new Vector2D(x + vector.getX(), y + vector.getY());
    }

    @Override
    public Vector2D subtract(Vector2<Double> vector) {
        return new Vector2D(x - vector.getX(), y - vector.getY());
    }

    @Override
    public Vector2D multiply(Number number) {
        return new Vector2D(x * number.doubleValue(), y * number.doubleValue());
    }

    @Override
    public Vector2D divide(Number number) {
        return this.multiply(1 / number.doubleValue());
    }

    @Override
    public Double lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    @Override
    public Double length() {
        return Math.sqrt(lengthSquared());
    }

    @Override
    public Double dotProduct(Vector2<Double> vector) {
        return this.x * vector.getX() + this.y * vector.getY();
    }

    @Override
    public Vector2D normalize() {
        return new Vector2D(this.x / this.length(), this.y / this.length());
    }

}