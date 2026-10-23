package net.vinograd.generativeflowfield.presentation;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import net.vinograd.generativeflowfield.field.cosine.TrigonometricField;
import net.vinograd.generativeflowfield.field.cosine.TrigonometricFloatingField;
import net.vinograd.generativeflowfield.math.rational.Vector2D;
import net.vinograd.generativeflowfield.particle.Particle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Controller {

    @FXML
    private StackPane canvasContainer;

    @FXML
    private Canvas canvas;

    @FXML
    private Slider particleSlider;

    @FXML
    private Slider speedSlider;

    @FXML
    private Slider scaleSlider;

    @FXML
    private Slider timeSlider;

    @FXML
    private Slider fadeSlider;

    @FXML
    private Label particleValueLabel;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button pauseButton;

    private GraphicsContext graphics;

    private AnimationTimer animationTimer;

    private boolean paused;

    private double time;

    @FXML
    private void initialize() {

        graphics = canvas.getGraphicsContext2D();

        colorPicker.setValue(Color.CORNFLOWERBLUE);

        canvas.widthProperty()
                .bind(canvasContainer.widthProperty());

        canvas.heightProperty()
                .bind(canvasContainer.heightProperty());

        particleSlider.valueProperty().addListener(
                (observable, oldValue, newValue) ->
                        particleValueLabel.setText(
                                Integer.toString(newValue.intValue())
                        )
        );
        prepare();

        animationTimer = new AnimationTimer() {

            private long previousTime;

            @Override
            public void handle(long now) {

                if (previousTime == 0) {
                    previousTime = now;
                    return;
                }

                double dt = (now - previousTime) / 1_000_000_000.0;

                previousTime = now;

                if (!paused) {
                    update(dt);
                    render(dt);
                }
            }
        };

        animationTimer.start();
    }

    private void update(double dt) {
        time += dt * timeSlider.getValue();
    }



    TrigonometricFloatingField field = new TrigonometricFloatingField();
    List<Particle> particles = new ArrayList<>();

    private void prepare() {
        Random random = new Random();
        for (int i = 0; i < 4000; i++) {
            particles.add(new Particle(new Vector2D(random.nextInt(0, 1200), random.nextInt(0, 1200)), colorPicker.getValue()));
        }
    }

    private void render(double dt) {

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        graphics.setFill(Color.rgb(10, 10, 12));

        graphics.fillRect(
                0,
                0,
                width,
                height
        );

        graphics.setFill(colorPicker.getValue());

        for (Particle particle : particles) {
            Vector2D velocity = field.sample(particle.getPosition(), time);
            particle.updatePosition(particle.getPosition().add(velocity.multiply(20 * dt)));

            double x = particle.getPosition().getX();
            double y = particle.getPosition().getY();

            if (x > canvas.getWidth()) {
                x = 0;
            } else if (x < 0) {
                x = canvas.getWidth();
            }

            if (y > canvas.getHeight()) {
                y = 0;
            } else if (y < 0) {
                y = canvas.getHeight();
            }

            particle.updatePosition(new Vector2D(x, y));

            graphics.fillOval(
                    particle.getPosition().getX() - 5,
                    particle.getPosition().getY() - 5,
                    10,
                    10
            );
        }

    }

    @FXML
    private void togglePause() {

        paused = !paused;

        pauseButton.setText(
                paused ? "Resume" : "Pause"
        );
    }

    @FXML
    private void reset() {
        time = 0;
    }

}