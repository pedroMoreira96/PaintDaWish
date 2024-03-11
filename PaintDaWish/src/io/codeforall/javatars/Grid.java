package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    private Rectangle movable;
    private final int COLS;
    private final int ROWS;
    private final int CELL_SIZE;
    private final int PADDING;
    private final int WIDTH;
    private final int HEIGHT;
    private final Square[][] SQUARES;

    private Color color;

    public Grid(int cols, int rows, int cellSize) {
        this.COLS = cols;
        this.ROWS = rows;
        this.CELL_SIZE = cellSize;
        PADDING = cellSize/2;
        WIDTH = cellSize * cols;
        HEIGHT = cellSize * rows;
        SQUARES = new Square[cols][rows];
        color = Color.BLACK;
        drawGrid();
        drawMovable(PADDING,PADDING);
    }
    public void drawGrid() {
        for (int i = 1; i <= ROWS; ) {
            for (int j = 1; j <= COLS; ) {
                SQUARES[j - 1][i - 1] = new Square(j * CELL_SIZE - PADDING, i * CELL_SIZE - PADDING, CELL_SIZE, CELL_SIZE);
                SQUARES[j - 1][i - 1].getRECT().draw();
                j++;
            }
            i++;
        }
    }
    public void drawMovable(int x, int y) {
        movable = new Rectangle(x, y, CELL_SIZE, CELL_SIZE);
        movable.setColor(Color.YELLOW);
        movable.fill();
    }
    public void moveRight() {
        if (movable.getX() <= WIDTH - CELL_SIZE) {
            movable.translate(CELL_SIZE, 0);
        }
    }
    public void moveLeft() {
        if (movable.getX() >= CELL_SIZE) {
            movable.translate(-CELL_SIZE, 0);
        }
    }
    public void moveUp() {
        if (movable.getY() >= CELL_SIZE) {
            movable.translate(0, -CELL_SIZE);
        }
    }
    public void moveDown() {
        if (movable.getY() <= HEIGHT-CELL_SIZE) {
            movable.translate(0, CELL_SIZE);
        }
    }
    public void paintErase() {

        Square localSquare = SQUARES[movable.getX() / CELL_SIZE][movable.getY() / CELL_SIZE];

        if (!localSquare.isFilled()) {
            localSquare.squareFill(color);
            localSquare.setColor(color);

        } else {
            localSquare.squareUnFill();
            movable.delete();
            drawMovable(movable.getX(), movable.getY());
        }
    }
   public void clear() {
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (SQUARES[i][j].isFilled()){
                    SQUARES[i][j].squareUnFill();
                }
            }
        }
    }
   public Square[][] getSquares(){
        return SQUARES;
   }

   public void setColor (Color color){
        this.color = color;
   }
}