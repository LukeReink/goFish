import processing.core.PApplet;
import processing.core.PImage;

public class harpoonFal {
    PApplet p;
    PImage harpoon;
    float xpos;
    float ypos;

    float xS;

    float yS;

    float gravity = 0.28F;


    float theta;

    boolean alive = true;

    float timeAlive;


    public harpoonFal(PApplet p, float ypos, float xpos){
        this.xpos = xpos;
        this.ypos = ypos;
        this.p = p;
        harpoon = p.loadImage("harpoon12.png");
        harpoon.resize(17,72);
        xS = p.random(-2, 2);
        yS = p.random(2.5F,4F);
        theta = p.random(2,5);
        timeAlive = p.millis();

    }

    public void display(){
        if (alive) {
            p.pushMatrix();
            p.translate(xpos, ypos);
            p.rotate(theta);
            p.image(harpoon, 0, 0);
            p.translate(-xpos, -ypos);
            ypos += yS * p.random(0.5F, 2);
            xpos += xS;
            yS += gravity;
            if (ypos >= p.height - 15) {
                ypos = p.height - 15;
                yS *= -0.75;
            }
            p.popMatrix();
                if(goFishGame.h.intersect(xpos,ypos + 20)){
                    p.fill(0);
                    p.textSize(30);
                    p.text("+1", xpos, ypos);
                    goFishGame.harpoonCount++;
                    alive = false;
                }
            if(p.millis() - timeAlive >= 9500)
                alive = false;
        }
//        xpos += xS;
//        ypos += yS;
//        yS += gravity;
//        gravity *= 0.92;
//        if(ypos >= p.height-30) {
//            yS *= -1;
//            ypos = p.height-30;
//        }
    }
}
