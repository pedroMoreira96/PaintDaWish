package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import javax.swing.*;
import java.io.IOException;

public class MyKeyboard implements KeyboardHandler {

    private final Keyboard KEYBOARD;
    private final Grid GRID;
    private final Engine ENGINE;
    private boolean pressed;

    public MyKeyboard(Grid grid, Engine paint) {
        this.GRID = grid;
        this.ENGINE = paint;
        KEYBOARD = new Keyboard(this);
        addKeyboard();
    }

    private void addKeyboardEvent(int key, KeyboardEventType eventType) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setKeyboardEventType(eventType);
        KEYBOARD.addEventListener(event);
    }

    public void addKeyboard() {

        addKeyboardEvent(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_RELEASED);
        addKeyboardEvent(KeyboardEvent.KEY_ENTER, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_C, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_S, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_L, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_1, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_2, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_3, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_4, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_5, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_6, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_7, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_8, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_9, KeyboardEventType.KEY_PRESSED);
        addKeyboardEvent(KeyboardEvent.KEY_ESC, KeyboardEventType.KEY_PRESSED);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();

        switch (key) {
            case 10 -> {
                if (!Start.hasStarted) {
                    GRID.drawCanvas();
                }
            }
            case 27 -> {
                if (Start.hasStarted) {
                    try {
                        ENGINE.quit();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
            case 32 -> {
                GRID.paintErase();
                pressed = true;
            }
            case 39 -> {
                if (pressed) {
                    GRID.moveRight();
                    GRID.paintErase();
                } else {
                    GRID.moveRight();
                }
            }
            case 37 -> {
                if (pressed) {
                    GRID.moveLeft();
                    GRID.paintErase();
                } else {
                    GRID.moveLeft();
                }
            }
            case 38 -> {
                if (pressed) {
                    GRID.moveUp();
                    GRID.paintErase();
                } else {
                    GRID.moveUp();
                }
            }
            case 40 -> {
                if (pressed) {
                    GRID.moveDown();
                    GRID.paintErase();
                } else {
                    GRID.moveDown();
                }
            }
            case 67 -> GRID.clear();
            case 76 -> {
                try {
                    ENGINE.load();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 83 -> {
                try {
                    ENGINE.save();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
            case 49 -> GRID.setColor(Color.BLACK);
            case 50 -> GRID.setColor(Color.GREEN);
            case 51 -> GRID.setColor(Color.BLUE);
            case 52 -> GRID.setColor(Color.RED);
            case 53 -> GRID.setColor(Color.ORANGE);
            case 54 -> GRID.setColor(Color.PINK);
            case 55 -> GRID.setColor(Color.YELLOW);
            case 56 -> GRID.setColor(Color.GRAY);
            case 57 -> GRID.setColor(Color.CYAN);
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