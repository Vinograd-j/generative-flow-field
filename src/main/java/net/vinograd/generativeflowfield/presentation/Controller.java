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
import net.vinograd.generativeflowfield.element.Particle;
import net.vinograd.generativeflowfield.element.VisualVector;
import net.vinograd.generativeflowfield.field.vortex.PointVortex;
import net.vinograd.generativeflowfield.field.vortex.SpiralVortex;
import net.vinograd.generativeflowfield.math.rational.Vector2D;

import java.util.ArrayList;
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
        time += dt;
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

        float gridStep = 40;
        int vectorSize = 20;

        VisualVector visualVector = new VisualVector(vectorSize, 45);
        SpiralVortex floatingField = new SpiralVortex(canvas.getWidth() / 2, canvas.getHeight() / 2, 0.7, -1);
        for (float y = gridStep / 2; y < canvas.getHeight(); y += gridStep) {
            for (float x = gridStep / 2; x < canvas.getWidth(); x += gridStep) {

                Vector2D position = new Vector2D(x, y);

                Vector2D field = floatingField.sample(position);
                Vector2D normalizedPosition = field.normalize();

                VisualVector.VectorComponents vectorComponents = visualVector
                        .forVector(normalizedPosition, x, y);

                double xEnd = x + normalizedPosition.getX();
                double yEnd = y + normalizedPosition.getY();

                graphics.setStroke(Color.WHITE);
                graphics.setLineWidth(1.5);
                graphics.strokeLine(
                        x, y,
                        vectorComponents.getVectorLineEnd().getX(), vectorComponents.getVectorLineEnd().getY()
                );

                graphics.strokeLine(
                        xEnd, yEnd,
                        xEnd + vectorComponents.getFirstArrowEnd().getX(), yEnd + vectorComponents.getFirstArrowEnd().getY()
                );
                graphics.strokeLine(
                        xEnd, yEnd,
                        xEnd + vectorComponents.getSecondArrowEnd().getX(), yEnd + vectorComponents.getSecondArrowEnd().getY()
                );

            }
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