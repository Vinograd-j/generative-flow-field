package net.vinograd.generativeflowfield.element;

import lombok.Getter;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

@Getter
public class Particle {

    private Vector2D position;

    private Color color;

    private final Vector2D size;

    public Particle(Vector2D initialPosition, Color color, Vector2D size) {
        this.position = initialPosition;
        this.color = color;
        this.size = size;
    }

    public void updateColor(Color color) {
        this.color = color;
    }

    public void updatePosition(Vector2D position) {
        this.position = position;
    }

}