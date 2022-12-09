import processing.core.PApplet;
import processing.core.PImage;

public class log {
    PApplet p;
    PImage im;
    float xpos;
    float ypos;
    float ySpeed;
    float xSpeed;
    orb o;

    public log(PApplet p, float x, float y){
        this.p = p;
        xpos = x;
        ypos = y;
        xSpeed = p.random(2,5);
        ySpeed = (float) 0.65;
        im = p.loadImage("log.png");
        im.resize(300,150);
        o = new orb(this.p);
    }

    public void display(){
        p.image(im, xpos, ypos);
        o.display(xpos + 60, ypos - 90);
    }
    public void move(){
        ypos += ySpeed;
        if(ypos >= p.height + 80) {
            o.loseIdea();
            ypos = -75;
            if(xpos < 70)
                xpos += 75;
            else if(xpos > 70 && xpos < 80)
                xpos -= 75;
            else if(xpos < 401 && xpos > 399)
                xpos += 40;
            else if(xpos > 439)
                xpos -= 40;
        }
    }

}
