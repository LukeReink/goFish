import processing.core.PApplet;
import processing.core.PImage;

public class finalBoss {
    PApplet p;
    float xpos;
    float ypos;
    float yspeed;
    float xspeed;

    int lives;
    PImage boss;

    public finalBoss(PApplet p, PImage b){
        this.p = p;
        boss = b;
        xpos = 300;
        ypos = 0;
        xspeed = 0.09F;
        yspeed = 0.3F;
        b.resize(220,220);
        lives = 10;
    }

    public void display(){
        if(lives > 0) {
            p.image(boss, xpos, ypos);
            p.fill(0);
            p.ellipse(xpos, ypos, 10, 10);
            xpos += xspeed;
            ypos += yspeed;
            p.textSize(35);
            if (lives > 15)
                p.fill(0, 255, 0);
            else if (lives >= 10)
                p.fill(235, 143, 52);
            else
                p.fill(255, 0, 0);
            p.text(lives, xpos + 75, ypos + 75);
            }
        }

    public boolean intersect(float x, float y){
        if(x >= xpos && x <= xpos + 220 && y >= ypos && y <= ypos + 205)
            return true;
        return false;
    }
}
