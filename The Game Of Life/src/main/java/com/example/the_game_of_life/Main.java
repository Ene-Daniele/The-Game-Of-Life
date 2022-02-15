package com.example.the_game_of_life;

import com.example.the_game_of_life.classes.Matrix;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static Group root = new Group();
    static Matrix matrix = new Matrix();

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(root, 900, 760, Color.BLACK);

        Slider speed = new Slider();
        speed.setTranslateX(770);
        speed.setTranslateY(100);
        speed.setMax(60);
        speed.setMin(1);
        speed.setPrefWidth(120);
        speed.setValue(10);
        root.getChildren().add(speed);

        Button start = new Button("start");
        start.setTranslateX(770);
        start.setTranslateY(10);
        start.setPrefWidth(120);
        root.getChildren().add(start);

        Button stop = new Button("stop");
        stop.setTranslateX(770);
        stop.setTranslateY(40);
        stop.setPrefWidth(120);
        stop.setDisable(true);
        root.getChildren().add(stop);

        Button reset = new Button("reset");
        reset.setTranslateX(770);
        reset.setTranslateY(70);
        reset.setPrefWidth(120);
        root.getChildren().add(reset);

        AnimationTimer cycleTimer = new AnimationTimer() {

            private static int cycleSpeed = 0;

            @Override
            public void handle(long l) {
                cycleSpeed++;
                if (cycleSpeed >= speed.getValue()) {
                    matrix.cycle();
                    cycleSpeed = 0;
                }
            }
        };

        start.setOnMouseClicked(mouseEvent -> {cycleTimer.start();
            start.setDisable(true);
            stop.setDisable(false);
        });
        stop.setOnMouseClicked(mouseEvent -> {cycleTimer.stop();
            start.setDisable(false);
            stop.setDisable(true);
        });
        reset.setOnMouseClicked(mouseEvent -> {
            matrix = new Matrix();
            cycleTimer.stop();
            start.setDisable(false);
            stop.setDisable(true);
        });

        stage.setTitle("The Game Of Life");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}