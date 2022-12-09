import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PImage;

public class game extends PApplet {
    public static void main(String[] args) {
        PApplet.main("game");
    }

    ArrayList<spikey> Spikeys = new ArrayList<spikey>();
    ArrayList<Boolean> addSpike = new ArrayList<Boolean>();
    ArrayList<log> Logs = new ArrayList<log>();
    PImage light;
    int score;

    public void settings() {
        size(600, 600);
        for (int i = 0; i < 2; i++) {
            s1 = new spikey(this);
            Spikeys.add(s1);
        }

        light = loadImage("glow.png");
        light.resize(60,70);
        im = loadImage("trees.jpg");
//        im.resize(600,600);
        a = new apple(this);
        float xL;
        float yL;
        for(int i = 0; i < 3; i++){
            if(i % 2 == 0)
                xL = 0;
            else
                xL = 400;
            yL = i * 200;
            log l = new log(this, xL, yL );
            Logs.add(l);
        }
        for(int i = 0; i < 20; i++){
            addSpike.add(false);
        }
    }

    apple a;
    spikey s1;
    boolean gameOver = false;
    boolean play = false;
    PImage im;
    float imX = 100;
    int count = 0;

    public void draw() {
        background(0);
        image(im,0,0);
        image(light,500,0);
        fill(255);
        textSize(60);
        text(":" + score,550,60);
        textSize(imX);
        fill(205,0,0);
        stroke(0);
        if(imX < 200)
            text("Apple Escape", 25, height/2);
        fill(0,255,0);
        if(!play)
            triangle(200,350,200,550,400,450);
        if(mouseX >= 200 && mouseX <= 400 && mouseY <= 550 && mouseY >= 350 && mousePressed)
            play = true;
        if (play) {
            imX += 10;
            for (int i = 0; i < Spikeys.size(); i++) {
                Spikeys.get(i).move();
            }
            for(int i = 0; i < Spikeys.size(); i++) {
                if(Spikeys.get(i).xspeed > 0)
                    Spikeys.get(i).xspeed = (float) (2 + 0.35*score);
                if(Spikeys.get(i).xspeed < 0)
                    Spikeys.get(i).xspeed = (float) (-2 - 0.35*score);
                if(Spikeys.get(i).yspeed > 0)
                    Spikeys.get(i).yspeed = (float) (2 + 0.35*score);
                if(Spikeys.get(i).yspeed < 0)
                    Spikeys.get(i).yspeed = (float) (-2 - 0.35*score);
            }
            for(int i = 0; i < Logs.size(); i++){
                Logs.get(i).ySpeed = (float)(0.65 + 0.075*score);
            }
            for (int i = 0; i < Spikeys.size(); i++) {
                if (a.intersect(Spikeys.get(i))) {
                    Spikeys.removeAll(Spikeys);
                    gameOver = true;
                }
            }
//            for(int i = 0; i < Spikeys.size(); i++){
//                for(int j = 0; j < Logs.size(); j++){
//                    Spikeys.get(i).intersect(Logs.get(j).o);
//                    Spikeys.get(i).intersect(Logs.get(j));
//                }
//            }
            for(int i = 0; i < Logs.size(); i++){
                Logs.get(i).display();
                Logs.get(i).move();
            }
            for(int i = 0; i < Logs.size(); i++){
                if(a.intersect(Logs.get(i))){
                    gameOver = true;
                    Spikeys.removeAll(Spikeys);
                }
                if(a.intersect(Logs.get(i).o) && !Logs.get(i).o.discovered){
                    if(score % 3 == 0){
                        spikey spi = new spikey(this);
                        Spikeys.add(spi);
                    }
                    Logs.get(i).o.setIdea();
                    if(!gameOver)
                        score ++;
                }
            }
            //must go last
            if(gameOver){
                textSize(100);
                fill(255,0,0);
                stroke(0);
                text("Game Over", 65, height/2);
            }
            a.display();



        }

    }
}
