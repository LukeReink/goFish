import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class landMine {
    float xpos;
    float ypos;
    timer t;
    PApplet p;

    float speed;

    int landMinesUnArmed;

    float newTime = 0;

    float blinkSpeed;

    boolean onOrange = false;

    boolean onRed = false;

    float blinkAdjust;

    float blinkOffset;

    float blinkColorR;

    float blinkColorG;

    boolean newTimeSet = false;

    float blinkColorB;

    boolean explode;

    boolean alive = true;

    PImage explosion;

    int explosionSize = 10;

    float size;
    float timeFounded;
    public landMine(PApplet p, float speed){
        this.p = p;
        //1 = normal 1.3 = fast 1.6 = very fast
        this.speed = speed;
        t = new timer(2, p);
        timeFounded = p.millis();
        landMinesUnArmed = 0;
        size = 25;
        ypos = p.random(2*size, p.height-2*size);
        xpos = p.random(2*size, p.width-2*size);
        explosion = p.loadImage("explosion.png");
    }
    public void display(){
        p.noStroke();
        if(p.millis() > ((int)(p.millis()/1000))*1000 + blinkAdjust + blinkSpeed && p.millis() <= ((int)(p.millis()/1000))*1000 + blinkAdjust + blinkSpeed + 50 && (blinkAdjust == 0 || blinkAdjust == 300 || blinkAdjust == 600 || blinkAdjust == 900 || blinkAdjust == 150 || blinkAdjust == 450 || blinkAdjust == 750)) {
            blinkAdjust += blinkOffset;
//            debugging: System.out.println("EOIFEF");
        }
        if(blinkAdjust == 1200 || blinkAdjust == 1050 || (p.millis() - ((int)(p.millis()/1000))*1000 >= 950 && (p.millis() - ((int)(p.millis()/1000))*1000 <= 1000)))
            blinkAdjust = 0;
      // debugging:  System.out.println("Range: " + (((int)(p.millis()/1000))*1000 + blinkAdjust) + " < " +p.millis() + " < " + (((int)(p.millis()/1000))*1000 + blinkAdjust + blinkSpeed));
        if (p.millis() >= ((int)(p.millis()/1000))*1000 + blinkAdjust && p.millis() <= ((int)(p.millis()/1000))*1000 + blinkAdjust + blinkSpeed) {
                p.fill(blinkColorR, blinkColorG, blinkColorB);
        } else
                p.fill(0);
        if(!newTimeSet) {
            p.triangle(xpos - size, ypos + size, xpos + size, ypos + size, xpos, ypos - size);
            p.fill(255);
            p.rectMode(p.CENTER);
            p.rect(xpos, ypos + 5, containMouse.cWidth + 15, containMouse.cHeight + 5);
            p.fill(0);
            p.textSize(9);
            p.text("Disarm", xpos - 13, ypos + 6);
        }
    }
    public boolean isExploded(){
        return explode;
    }

    public void detail(){
        if(p.millis()-timeFounded <= 5000/speed){
            //faster blink speed = slower actual blink speed on game screen
            blinkSpeed = 400;
            blinkAdjust = 0.01F;
            blinkOffset = 0;
            blinkColorR = 0;
            blinkColorB = 0;
            blinkColorG = 255;
        }
        if(p.millis()-timeFounded > 5000/speed && p.millis()-timeFounded < 9000/speed && !onOrange){
            onOrange = true;
            blinkSpeed = 200;
            blinkAdjust = 0;
            blinkOffset = 300;
            blinkColorR = 155;
            blinkColorB = 10;
            blinkColorG = 100;
        }
        if(p.millis()-timeFounded >= 9000/speed && !onRed){
            onRed = true;
            blinkSpeed = 100;
            blinkAdjust = 0;
            blinkOffset = 150;
            blinkColorR = 255;
            blinkColorB = 0;
            blinkColorG = 0;
        }
        //boom
        if(p.millis()-timeFounded >= 13000/speed) {
            if(!newTimeSet) {
                newTime = p.millis();
                newTimeSet = true;
            }
            if(p.millis() - newTime <= 500) {
                explosion.resize(60,60);
                p.image(explosion, xpos - 40, ypos - 20);
//                explosionSize += 2;
            }
            else
                explode = true;
        }
    }

    public boolean intersect(float x, float y){
        if(x >= xpos-(containMouse.cWidth+15)/2 - 7 && x <= xpos + (containMouse.cWidth+15)/2 + 7 && y >= ypos+5-(containMouse.cHeight+5)/2 - 7 && y <= ypos + (containMouse.cHeight+5)/2 + 7)  {
            return true;
        }
        return  false;
    }

}
