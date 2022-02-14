package com.example.the_game_of_life;

import com.example.the_game_of_life.classes.Matrix;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static Group root = new Group();
    static Matrix matrix = new Matrix();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, 850, 760, Color.BLACK);


        AnimationTimer cycleTimer = new AnimationTimer() {

            private static int cycleSpeed = 0;
            
            @Override
            public void handle(long l) {
                cycleSpeed++;
                if (cycleSpeed >= 5) {
                    matrix.cycle();
                    cycleSpeed = 0;
                }
            }
        };

        Button start = new Button("start");
        start.setTranslateX(800);
        start.setTranslateY(10);
        root.getChildren().add(start);

        Button stop = new Button("stop");
        stop.setTranslateX(800);
        stop.setTranslateY(40);
        root.getChildren().add(stop);

        Button reset = new Button("reset");
        reset.setTranslateX(800);
        reset.setTranslateY(70);
        root.getChildren().add(reset);

        start.setOnMouseClicked(mouseEvent -> {cycleTimer.start();
            start.setDisable(true);
            stop.setDisable(false);
        });
        stop.setOnMouseClicked(mouseEvent -> {cycleTimer.stop();
            start.setDisable(false);
            stop.setDisable(true);
        });
        reset.setOnMouseClicked(mouseEvent -> matrix = new Matrix());

        stage.setTitle("The Game Of Life");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}