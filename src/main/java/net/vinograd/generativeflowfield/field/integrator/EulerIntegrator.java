package net.vinograd.generativeflowfield.field.integrator;

import net.vinograd.generativeflowfield.field.integrator.abstr.FieldIntegrator;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

import java.util.function.Function;

public class EulerIntegrator implements FieldIntegrator {

    @Override
    public Vector2D integrate(Vector2D position, Function<Vector2D, Vector2D> sampler, float dt, float speed) {
        Vector2D newPos = sampler.apply(position);
        return position.add(newPos.multiply(speed * dt));
    }

}