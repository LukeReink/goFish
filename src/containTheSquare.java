import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PImage;

public class containTheSquare extends PApplet {

    public static void main(String[] args) {
        PApplet.main("containTheSquare");
    }


    public void settings() {
        size(700, 600);
        timeStart = millis();
        landMinesSaved = 0;
        landMinesExploded  = 0;
        frequency = 2000;
        im = loadImage("heart.png");
        im.resize(30, 30);

    }
    public void setup(){
         dvd = new dvdLogo(this);
    }
    float timeStart;

    PImage im;

    int highScore = 41;


    int landMinesSaved;

    float landMinesExploded;

    float frequency;

    float recordedSaved = 0;

    boolean play = false;

    boolean gOver = false;

    float timeAtInstant = 0;
    containMouse m = new containMouse(this);

    dvdLogo dvd;


    ArrayList<landMine> mines = new ArrayList<>();

    public void draw() {
        if(!play) {
            background(255);
            textSize(55);
            text("Protect the Brand!", 125, height/2-70);
            text("Click To Start", 160, height/2);
            m.draw();
            m.removeLines();
            dvd.display();
            if (mousePressed)
                play = true;
        }
        else {
            if (gOver) {
                background(255, 0, 0);
                textSize(70);
                text("Game Over", width / 2-170, height / 2);
                textSize(30);
                text("Mines Disarmed: " + landMinesSaved, width-250, 50);
                textSize(30);
                text("High Score: " + highScore, width-250, 100);
                m.alive = false;
                m.draw();
                dvd.alive = false;
                dvd.display();
            } else {
                background(255);
                //replace with: image(im,xpos,ypos);
                if(landMinesExploded == 0) {
                    image(im,  30, 30);
                    image(im, 70,30);
                    image(im, 110, 30);
                }
                if(landMinesExploded == 1) {
                    image(im, 70,30);
                    image(im, 110, 30);
                }
                if(landMinesExploded == 2)
                    image(im, 110, 30);
                if(landMinesExploded == 3)
                    gOver = true;
                textSize(30);
                text("Mines Disarmed: " + landMinesSaved, width-250, 50);
                textSize(30);
                text("High Score: " + highScore, width-250, 100);

                if(landMinesSaved > highScore)
                    highScore = landMinesSaved;

                if (landMinesSaved > 5)
                    frequency = 1500;
                if(landMinesSaved > 10)
                    frequency = 1350;

                if ((millis() - timeStart >= frequency)) {
                    timeStart = millis();
                    landMine l;
                    if(landMinesSaved < 6)
                        l = new landMine(this, 1);
                    else if(landMinesSaved < 10)
                        l = new landMine(this,1.6F);
                    else
                        l = new landMine(this,1.8F);
                    mines.add(l);
                }

                dvd.display();
                if (dvd.intersect(m.xpos, m.ypos, m.missing))
                    gOver = true;

                for (int i = 0; i < mines.size(); i++) {
                    if (mines.get(i).intersect(m.xpos, m.ypos)) {
                        if (mousePressed && mines.get(i).alive) {
                            fill(0,255,0);
                            textSize(30);
                            text("Mines Disarmed: " + landMinesSaved, width-250, 50);
                            mines.get(i).alive = false;
                            landMinesSaved++;
                        }
                    }
                    if (mines.get(i).alive) {
                        mines.get(i).detail();
                        mines.get(i).display();
                        if (mines.get(i).isExploded() || !(mines.get(i).alive)) {
                            landMinesExploded++;
                            System.out.println("sdf");
                            mines.remove(i);
                            i--;
                        }
                    }
                }
                m.draw();
                m.removeLines();
            }
        }
    }
}