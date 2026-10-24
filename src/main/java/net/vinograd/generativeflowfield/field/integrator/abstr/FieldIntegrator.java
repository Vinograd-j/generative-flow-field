package net.vinograd.generativeflowfield.field.integrator.abstr;

import net.vinograd.generativeflowfield.math.rational.Vector2D;

import java.util.function.Function;

public interface FieldIntegrator {

    Vector2D integrate(Vector2D position, Function<Vector2D, Vector2D> sampler, float dt, float speed);

}