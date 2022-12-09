import processing.core.PApplet;
import processing.core.PImage;

public class dvdLogo {

    PApplet p;

    float xpos;
    float ypos;
    float xspeed;
    float yspeed;

    float width;

    boolean alive = true;

    float height;

    PImage im;
    public dvdLogo(PApplet p) {
        this.p = p;
        xspeed = 2.5F;
        yspeed = 2.5F;
        width = 90;
        height = 60;
        im = p.loadImage("dvdlogo.png");
        im.resize(90, 130);
    }

    public void display(){
        p.fill(255, 0, 255);
//        p.rectMode(p.CENTER);
//        p.rect(xpos, ypos, width, height);
        p.image(im,xpos-45,ypos-65);
        if(alive) {
            xpos += xspeed;
            ypos += yspeed;
        }
        if(xpos > p.width - width/2){
            xpos = p.width - width/2;
            xspeed *= -1;
        }
        if(xpos < width/2){
            xpos = width/2;
            xspeed *= -1;
        }
        if(ypos < height /2){
            ypos = height/2;
            yspeed *= -1;
        }
        if(ypos > p.height- height/2){
            ypos = p.height- height/2;
            yspeed *= -1;
        }
    }
    public boolean intersect(float x, float y, String missing){
        if(!(missing.equals("left"))) {
            if (xpos - width /2 <= x && ypos + height/2 >= y && ypos-height/2 <= y)
                return  true;
        }
        if(!(missing.equals("right"))) {
            if (xpos + width /2 >= x && ypos + height/2 >= y && ypos-height/2 <= y)
                return  true;
        }
        if(!(missing.equals("up"))) {
            if (ypos - height/2 <= y && xpos - width /2 <= x && xpos + width /2 >= x)
                return  true;
        }
        if(!(missing.equals("down"))) {
            if (ypos + height / 2 >= y && xpos - width /2 <= x && xpos + width /2 >= x)
                return true;
        }
        return  false;
    }




}
