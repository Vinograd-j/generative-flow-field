package net.vinograd.generativeflowfield.field.integrator;

import net.vinograd.generativeflowfield.field.integrator.abstr.FieldIntegrator;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

import java.util.function.Function;

public class RK2Integrator implements FieldIntegrator {

    @Override
    public Vector2D integrate(Vector2D position, Function<Vector2D, Vector2D> sampler, float dt, float speed) {
        Vector2D apply = sampler.apply(position);

        Vector2D k1 = apply.multiply(speed);
        Vector2D k2 = sampler.apply(position.add(k1.multiply(dt / 2)));

        return position.add(
                k1.add(k2.multiply(2)).multiply(dt / 6));
    }

}