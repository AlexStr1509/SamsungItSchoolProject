package ru.myitschool;

public class RealButton {
    float x, y, size;

    public RealButton(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    boolean hit(float tX, float tY){
        return tX>x && tX<x+size && tY>y && tY<y+size;
    }
}
