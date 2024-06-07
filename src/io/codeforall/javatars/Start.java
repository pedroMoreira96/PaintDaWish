package io.codeforall.javatars;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Start {

    static Picture BACKGROUND;
    static Text TEXT;
    static Boolean hasStarted = false;

    public Start() {
        BACKGROUND = new Picture(10, 10, "resources/background.jpg");
        TEXT = new org.academiadecodigo.simplegraphics.graphics.Text(265, 560, "Press ENTER to start");
        drawStartMenu();
    }

    private void drawStartMenu() {
        BACKGROUND.draw();
        TEXT.grow(80.0, 20.0);
        TEXT.draw();
    }

    static void deleteStartMenu() {
        BACKGROUND.delete();
        TEXT.delete();
    }
}
