
import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PImage;

public class harpoon extends PApplet{
    float xpos;
    float ypos;

    PImage harpoon;

    boolean released;

    boolean alive;

    PApplet p;


    public harpoon(PApplet p){
        this.p = p;
        //defualt xpos halfway
        xpos = p.width/2 + 3;
        ypos = p.height-90 + 15;
        //boolean for if the harpoon has been shot
        released = false;
        //boolean for if the harpoon is on the screen
        alive = true;
        harpoon = p.loadImage("harpoon12.png");
        harpoon.resize(17,72);
    }

    public void display(){
        //mouse position deciphers xpos while not released so we can aim;
        p.image(harpoon, xpos - 6, ypos - 30);
        if(!released) {
            xpos = p.mouseX;
        }
        //on event press the harpoon is released
        if(p.keyPressed && !released) {
            //harpoon count -1
            jackluke.harpoonCount--;
            //give boolean
            jackluke.harpoonShot = true;
            //set time
            jackluke.timeCurrentforAddFish = p.millis();
            released = true;
        }
        //upon release
        if(released){
            //ypos increases
            ypos -= 10;
        }
        if(ypos <= 10){
            //if off the screen ypos decreases
            alive = false;
        }

    }
    //checks if the harpoon has intersected a fish
    //param x: xpos of object you are checking intersect with
    //param y: ypos of object you are checking intersect with
    public boolean intersect(float x, float y){
        //if intersecting with a fish object return true
        p.fill(255);
        p.ellipse(xpos, ypos, 10, 10);
        if(ypos <= y + 35 && ypos >= y  - 25 && xpos <= x + 42  && xpos >= x-20)
            return true;
        return false;
    }

    //animation for trail
    public void trail(){
        //arraylist of circles that make up trail
    }


}
