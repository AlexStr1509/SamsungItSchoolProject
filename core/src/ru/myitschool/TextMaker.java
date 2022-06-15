package ru.myitschool;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class TextMaker {
    float x, y;
    String text;

    public TextMaker(String text, BitmapFont font, float x, float y) {
        this.x = x;
        this.y = y;
        this.text=text;
    }

}