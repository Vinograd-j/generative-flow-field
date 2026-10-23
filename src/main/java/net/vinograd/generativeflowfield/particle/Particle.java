package net.vinograd.generativeflowfield.particle;

import javafx.scene.paint.Color;
import lombok.Getter;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

@Getter
public class Particle {

    private Vector2D position;

    private Color color;

    public Particle(Vector2D initialPosition, Color color) {
        position = initialPosition;
        this.color = color;
    }

    public void updateColor(Color color) {
        this.color = color;
    }

    public void updatePosition(Vector2D position) {
        this.position = position;
    }

}