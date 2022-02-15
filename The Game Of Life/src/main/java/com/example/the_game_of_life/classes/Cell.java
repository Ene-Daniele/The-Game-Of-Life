package com.example.the_game_of_life.classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Cell extends Rectangle {

    public static final int TILE = 20;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.setFill(this.isActive() ? Color.LIMEGREEN : Color.BLACK);
    }

    public Cell(int x, int y){
        super(x, y, TILE, TILE);
        this.setFill(Color.BLACK);
        this.setStroke(Color.LIMEGREEN);
        this.setOnMouseClicked(mouseEvent -> {
            this.setActive(!this.isActive()); // Invert the state
        });
    }
}
