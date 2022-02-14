package com.example.the_game_of_life.classes;

import com.example.the_game_of_life.Main;

import java.util.ArrayList;
import java.util.HashMap;

public class Matrix {

    private static final int MATRIX_SIZE = 38;

    private final ArrayList <ArrayList <Cell>> matrixGrid;

    /**
     * Cycles the matrix, checks the state of every cell in the matrix and updates it according to its state
     */
    public void cycle() {

        HashMap<Cell, Boolean> updateMatrix = new HashMap<>(); //* We store the changes that must be done here

        for (int i = 0; i < MATRIX_SIZE; i++){
            for (int j = 0; j < MATRIX_SIZE; j++){
                int neighbours = 0;

                // Checking around the current cell
                try {neighbours += matrixGrid.get(i).get(j - 1).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i - 1).get(j - 1).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i - 1).get(j).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i - 1).get(j + 1).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i).get(j + 1).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i + 1).get(j + 1).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i + 1).get(j).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}
                try {neighbours += matrixGrid.get(i + 1).get(j - 1).isActive() ? 1 : 0;} catch (IndexOutOfBoundsException ignored){}

                // Saving the changes needed in the hashmap
                if (!matrixGrid.get(i).get(j).isActive()) {
                    updateMatrix.put(matrixGrid.get(i).get(j), neighbours == 3);
                } else {
                    updateMatrix.put(matrixGrid.get(i).get(j), neighbours == 2 || neighbours == 3);
                }
            }
        }

        // Applying the changes saved in the hashmap
        for (int i = 0; i < MATRIX_SIZE; i++){
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrixGrid.get(i).get(j).setActive(updateMatrix.get(matrixGrid.get(i).get(j)));
            }
        }
    }

    public Matrix(){

        matrixGrid = new ArrayList<>();

        for (int i = 0; i < MATRIX_SIZE; i++){ // Loading the matrix

            matrixGrid.add(new ArrayList<>());
            for (int j = 0; j < MATRIX_SIZE; j++){

                // Proportionally placing cells on the matrix
                matrixGrid.get(i).add(new Cell(i * Cell.TILE, j * Cell.TILE));
                Main.root.getChildren().add(matrixGrid.get(i).get(j));
            }
        }
    }
}
