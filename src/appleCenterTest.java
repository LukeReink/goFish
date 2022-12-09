import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PImage;

public class appleCenterTest extends PApplet {
    public static void main(String[] args) {
        PApplet.main("appleCenterTest");
    }


    public void settings() {
        size(600, 600);
    }

    PImage p;

    public void setup() {
        p = loadImage("log.png");
//        p = loadImage("apple1.png");
        p.resize(300, 150);
//        p.resize(60, 60);
    }

    public void draw() {
        background(0);
        stroke(255);
        image(p, mouseX, mouseY);
        for(int i = 0; i < 60; i++){
            line(i*10,0,i*10,height);
        }
        for(int i = 0; i < 60; i++){
            line(0,i*10, height, i*10);
        }
    }
}
