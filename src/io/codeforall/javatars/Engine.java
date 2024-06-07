package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Engine {

    private final Grid GRID;
    private final int COLS;
    private final int ROWS;
    private final Map<Color, Character> colorCharMap;
    private final Map<Character, Color> charColorMap;

    public Engine(int cols, int rows, int cellSize) {
        new Start();
        this.COLS = cols;
        this.ROWS = rows;
        this.GRID = new Grid(cols, rows, cellSize);
        this.colorCharMap = new HashMap<>();
        fillMap();
        this.charColorMap = new HashMap<>();
        reverseMap();
        new MyKeyboard(GRID, this);
    }

    private void fillMap() {

        colorCharMap.put(Color.BLACK, '1');
        colorCharMap.put(Color.GREEN, '2');
        colorCharMap.put(Color.BLUE, '3');
        colorCharMap.put(Color.RED, '4');
        colorCharMap.put(Color.ORANGE, '5');
        colorCharMap.put(Color.PINK, '6');
        colorCharMap.put(Color.YELLOW, '7');
        colorCharMap.put(Color.GRAY, '8');
        colorCharMap.put(Color.CYAN, '9');
    }

    private void reverseMap() {

        for (Map.Entry<Color, Character> entry : colorCharMap.entrySet()) {
            charColorMap.put(entry.getValue(), entry.getKey());
        }
    }

    public void save() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();

            if (!fileName.toLowerCase().endsWith(".txt")) {
                fileName += ".txt";
            }

            try (FileWriter outputStream = new FileWriter(fileName);) {
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLS; j++) {
                        Color color = GRID.getSquares()[j][i].getColor();
                        Character character = colorCharMap.getOrDefault(color, 'n');
                        outputStream.write(character);
                    }
                    outputStream.write('\n');
                }
            }
        }
    }

    public void load() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();

            StringBuilder result = new StringBuilder();

            try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = bReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {

                    char character = result.charAt(j + (i * COLS + i));

                    Color color = charColorMap.get(character);

                    if (color != null) {
                        GRID.getSquares()[j][i].squareFill(color);
                    } else {
                        GRID.getSquares()[j][i].squareUnFill();
                    }
                }
            }
        }
    }

    public void quit() throws IOException {

        int option = JOptionPane.showConfirmDialog(
                null,
                "Do you want to save before quitting?",
                "Quit",
                JOptionPane.YES_NO_CANCEL_OPTION);

        switch (option) {
            case JOptionPane.YES_OPTION:
                save();
                System.exit(0);
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            default:
                break;
        }
    }

}
