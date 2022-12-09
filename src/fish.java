import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PImage;

public class fish extends PApplet {
    float xpos = 0;
    float ypos = 0;
    float xd;
    float yd = 3;
    float random = 0;
    boolean b;
    int numFishKilled;

    int fishNum;


    float theta = 0;

    boolean alive = true;

    PImage fish;
    PApplet p;


    public fish(PApplet p, PImage fish, float xpos, float ypos, int fishNum) {
        this.p = p;
        this.fish = fish;
        this.xpos = xpos;
        this.ypos = ypos;
        this.fishNum = fishNum;
        if(fishNum == 1)
            fish.resize(80,60);
        else
            fish.resize(80,40);
        if((int)random(2) == 1)
            xd = 3;
        else
            xd = -3;




    }

    public void fall() {
        if(alive) {
            rotate(fish);
            xpos += xd;
            ypos += yd;

            if (xpos + 30 > 900) {
                xd *= -1;
                xpos = 870;
            }
            if(xpos - 30 < 0){
                xd *= -1;
                xpos = 30;
            }

//            if (ypos >= 600 && b) {
//                count++;
//                b = false;
//                System.out.println(count);
//            }
        }


    }



    public void rotate(PImage f){
        p.pushMatrix();
        p.fill(0);
        p.translate(xpos,ypos);
        p.rotate(theta);
        theta += 0.01;
        //offset for nemo
        if(fishNum != 1)
            p.image(f, -40,-30);
        else
            p.image(f, -60, -45);
        p.fill(0);
        p.ellipse(0, 0, 10, 10);
        p.translate(-xpos,-ypos);
        p.popMatrix();
    }
}





