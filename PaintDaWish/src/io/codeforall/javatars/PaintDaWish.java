package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;

import java.io.*;

public class PaintDaWish {

    private final String FILENAME;
    private final Grid GRID;
    private final int COLS;
    private final int ROWS;

    public PaintDaWish(int cols, int rows, int cellSize, String filename) {
        this.COLS = cols;
        this.ROWS = rows;
        this.FILENAME = filename;
        this.GRID = new Grid(cols, rows, cellSize);
        MyKeyboard myKeyboard = new MyKeyboard(GRID, this);
    }

    public void save() throws IOException {

        FileOutputStream outputStream = new FileOutputStream(FILENAME);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                if ((GRID.getSquares()[j][i].getColor() == Color.BLACK)) {
                    outputStream.write(("0").getBytes());
                } else if ((GRID.getSquares()[j][i].getColor() == Color.GREEN)) {
                    outputStream.write(("1").getBytes());
                } else if ((GRID.getSquares()[j][i].getColor() == Color.BLUE)) {
                    outputStream.write(("2").getBytes());
                } else if ((GRID.getSquares()[j][i].getColor() == Color.RED)) {
                    outputStream.write(("3").getBytes());
                } else {
                    outputStream.write(("n").getBytes());
                }
            }
            outputStream.write("\n".getBytes());
        }
        outputStream.close();
    }

    public void load() throws IOException {

        FileReader reader = new FileReader(FILENAME);
        BufferedReader bReader = new BufferedReader(reader);

        String line;
        String result = "";

        while ((line = bReader.readLine()) != null) {
            result += line + "\n";
        }

        reader.close();
        bReader.close();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                char character = result.charAt(j + (i * COLS + i));

                if (character == '0') {
                    GRID.getSquares()[j][i].squareFill(Color.BLACK);
                } else if (character == '1') {
                    GRID.getSquares()[j][i].squareFill(Color.GREEN);
                } else if (character == '2') {
                    GRID.getSquares()[j][i].squareFill(Color.BLUE);
                } else if (character == '3') {
                    GRID.getSquares()[j][i].squareFill(Color.RED);
                } else {
                    GRID.getSquares()[j][i].squareUnFill();
                }
            }
        }
    }
}
