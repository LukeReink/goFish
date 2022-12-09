import processing.core.PApplet;
import processing.core.PImage;

public class orb {
    PApplet p;
    PImage im;
    PImage im2;
    float lX;
    float lY;
    boolean discovered;

    public orb(PApplet p) {
        this.p = p;
        discovered = false;
        im = p.loadImage("newton.png");
        im2 = p.loadImage("empty.png");
        im2.resize(30,50);
        im.resize(80, 120);
    }

    public void display(float x, float y) {
        p.image(im, x, y);
        if(!discovered) {
            lX = x + 25;
            lY = y - 55;
        }
        if(discovered){
            lX = x + 10;
            lY = y - 70;
        }
        p.image(im2, lX, lY);
    }
    public void setIdea(){
        im2 = p.loadImage("glow.png");
        im2.resize(60,70);
        discovered = true;
    }
    public void loseIdea(){
        discovered = false;
        lX += 40;
        lY += 10;
        im2 = p.loadImage("empty.png");
        im2.resize(30,50);
    }
    public void shatter(){
        if(!discovered) {
            discovered = true;
            im2 = p.loadImage("broken1.png");
            im2.resize(70, 70);
        }
    }
}
