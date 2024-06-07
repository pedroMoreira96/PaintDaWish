package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Square {

    private final Rectangle RECT;
    private boolean filled;
    private Color color;

    public Square(int x, int y, int width, int height){
        RECT = new Rectangle(x, y, width, height);
        color = null;
    }

    //GETTERS

    public Rectangle getRECT(){
        return RECT;
    }

    public boolean isFilled(){
        return filled;
    }

    public Color getColor() {
        return color;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void squareFill(Color color){
        RECT.setColor(color);
        setColor(color);
        RECT.fill();
        setFilled(true);
    }
    public void squareUnFill(){
        //RECT.setColor(Color.BLACK);
        //RECT.draw();
        RECT.delete();
        setFilled(false);
        setColor(null);
    }

}
