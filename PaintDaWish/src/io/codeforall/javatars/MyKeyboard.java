package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import java.io.IOException;

public class MyKeyboard implements KeyboardHandler {

    private final Keyboard KEYBOARD;
    private final Grid GRID;
    private final PaintDaWish PAINT;
    private boolean pressed;

    public MyKeyboard(Grid grid, PaintDaWish paint) {
        this.GRID = grid;
        this.PAINT = paint;
        KEYBOARD = new Keyboard(this);
        addKeyboard();
    }

    public void addKeyboard() {
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(moveRight);

        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(moveLeft);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(moveUp);

        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(moveDown);

        KeyboardEvent paintErase = new KeyboardEvent();
        paintErase.setKey(KeyboardEvent.KEY_SPACE);
        paintErase.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(paintErase);

        KeyboardEvent release = new KeyboardEvent();
        release.setKey(KeyboardEvent.KEY_SPACE);
        release.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        KEYBOARD.addEventListener(release);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKey(KeyboardEvent.KEY_C);
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(clear);

        KeyboardEvent save = new KeyboardEvent();
        save.setKey(KeyboardEvent.KEY_S);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(save);

        KeyboardEvent load = new KeyboardEvent();
        load.setKey(KeyboardEvent.KEY_L);
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(load);

        KeyboardEvent green = new KeyboardEvent();
        green.setKey(KeyboardEvent.KEY_1);
        green.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(green);

        KeyboardEvent blue = new KeyboardEvent();
        blue.setKey(KeyboardEvent.KEY_2);
        blue.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(blue);

        KeyboardEvent red = new KeyboardEvent();
        red.setKey(KeyboardEvent.KEY_3);
        red.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(red);

        KeyboardEvent black = new KeyboardEvent();
        black.setKey(KeyboardEvent.KEY_0);
        black.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KEYBOARD.addEventListener(black);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();
        switch (key) {
            case 39 -> {
                if (pressed){
                    GRID.moveRight();
                    GRID.paintErase();
                } else {
                    GRID.moveRight();
                }
            }
            case 37 ->{
                if (pressed){
                    GRID.moveLeft();
                    GRID.paintErase();
                } else {
                    GRID.moveLeft();
                }
            }
            case 38 -> {
                if (pressed){
                    GRID.moveUp();
                    GRID.paintErase();
                } else {
                    GRID.moveUp();
                }
            }
            case 40 -> {
                if (pressed){
                    GRID.moveDown();
                    GRID.paintErase();
                } else {
                    GRID.moveDown();
                }
            }
            case 32 -> {
                GRID.paintErase();
                pressed = true;
            }
            case 67 -> GRID.clear();
            case 83 -> {
                try {
                    PAINT.save();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            case 76 -> {
                try {
                    PAINT.load();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            case 48 -> GRID.setColor(Color.BLACK);
            case 49 -> GRID.setColor(Color.GREEN);
            case 50 -> GRID.setColor(Color.BLUE);
            case 51 -> GRID.setColor(Color.RED);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();

        if (key == 32) {
            pressed = false;
        }
    }

}