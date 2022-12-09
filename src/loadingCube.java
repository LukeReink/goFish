import org.w3c.dom.ls.LSOutput;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class loadingCube {

    PApplet p;

    float theta;

    float size;

    boolean front = false;
    //gives system timer for settting up
    boolean readyToPlay = false;

    //actual boolean for play
    boolean play = false;

    float time;
    float xpos;
    float ypos;
    float zpos;

    float thetaTest = 0;

    float rectXpos = 450;

    float rectYpos = 450;

    float randomNum = 0;

    PImage fish1;

    PImage fish2;

    PImage fish3;

    PImage finalBoss;

    PImage swedishFish;

    public loadingCube(PApplet p){
        this.p = p;

        theta = 0;

        size = 450;

        time = 0;
        xpos = p.width/2;
        ypos = p.height/2;
        zpos = 0;

        randomNum = p.random( 0.027F, 0.035F);

        swedishFish = p.loadImage("swedishfish.png");
        fish1 = p.loadImage("nemo.png");
        fish2 = p.loadImage("fish1.png");
        fish3 = p.loadImage("eel1.png");
        finalBoss = p.loadImage("blobfish.png");
        finalBoss.resize(220,220);
        swedishFish.resize(80,80);
        fish1.resize(80,80);
        fish2.resize(80,80);
        fish3.resize(80,80);

    }

    public void display(){
        //if play button pressed, craziness
        if(readyToPlay){
            //time set
            //for x seconds run this code
            if(p.millis() - time <= 7500){
                p.pushMatrix();
                p.translate(xpos, ypos, zpos);
//                xpos += 1.2;
//                ypos += 1;
                zpos += 2.6;
                p.rotateX(theta);
                p.rotateY(theta);
                p.rotate(theta);
                putPlayButton(-1,0);
                putCreatedByText(0,-1);
                putWelcomeText(0,1);
                p.fill(237, 198, 26);
                p.box(size);
                theta += randomNum;
                p.popMatrix();
            }
            else
                play = true;
            //^else the real play is true
        }
        else {
            //loading screen graphics
            //save matrix as is
            p.pushMatrix();
            //translate to middle
            p.translate(p.width / 2, p.height / 2);
            p.stroke(0);
            //rotate theta around new y axis (center of box)
            p.rotateY(theta);
            p.fill(237, 198, 26);
            p.box(size);
            putPlayButton(-1, 0);
            putWelcomeText(0,1);
            putCreatedByText(0,-1);
            putInstructionText(1,0);
      //      p.noFill();
            //add to theta
            theta += 0.0085;
            //bring matrix back
            p.popMatrix();
        }
    }

    /**
     * display a play button
     * @param directionL2R if -1 button being drawn is to the left, 1  is to the right, 0 the direction is front/back
     * @param directionB2F if -1 button being drawn is behind, 1 in front, 0 the direction is right/left
     */
    public void putPlayButton(int directionL2R, int directionB2F) {
        //for beginning display ad
        if (p.millis() <= 10000) {
            p.pushMatrix();
            p.translate(directionL2R * size / 2, 0, 0);
            p.rotateY(10.99558F);
            p.textSize(40);
            p.fill(3, 123, 252);
            p.text("Battle All Kinds of \n  Rare Mongolian Fish", -185, -180, 1);
            p.translate(0,0,1);
            p.image(fish1, -105, -105);
            p.image(fish2, 50, -105);
            p.image(fish3, -105, 0);
            p.image(swedishFish, 50, 0);
            p.translate(0,0,-1);

            p.popMatrix();

        } else {
            //move origin halfway to the direction indicated
            if (directionB2F == 0)
                p.translate(directionL2R * size / 2, 0);
            else
                p.translate(0, 0, directionB2F * size / 2);
            //draw shape
            p.fill(0,255,0);
            if (!readyToPlay) {
                p.beginShape();
                p.vertex(-1, 0, 80);
                p.vertex(-1, -95, -80);
                p.vertex(-1, 95, -80);
                p.endShape(p.CLOSE);
            } else {
                p.beginShape();
                p.vertex(-1, 0, 80);
                p.vertex(-1, -95, -80);
                p.vertex(-1, 95, -80);
                p.endShape(p.CLOSE);
                p.beginShape();
                p.vertex(1, 0, 80);
                p.vertex(1, -95, -80);
                p.vertex(1, 95, -80);
                p.endShape(p.CLOSE);
            }
            p.fill(3, 123, 252);

//      debugging   System.out.println(theta);
            //check when the play button is up front
            if (theta >= 6.3) {
                theta = 0;
            }
            //the play button is facing the user for these values of theta
            front = theta >= 0 && theta <= 3;
            if (front && !readyToPlay) {
                p.pushMatrix();
                p.rotateY(11);
//            debugging: thetaTest += 0.01;
//            System.out.println(thetaTest);
                p.textSize(40);
                if (!readyToPlay)
                    p.text("Click Button if You Dare", -205, -125, 1);
                else {
                    p.text("Click Button if You Dare", -205, -125, -1);
                    p.text("Click Button if You Dare", -205, -125, 1);
                }


                p.popMatrix();
                //guestimate for when the mouse is over the play button
                if ((p.mouseY / theta <= 4000 && p.mouseY / theta >= 2000 && p.mouseX / theta <= 4000 && p.mouseX / theta >= 2000 && p.mousePressed) || (p.mouseY / theta <= 680 && p.mouseY / theta >= 200 && p.mouseX / theta <= 680 && p.mouseX / theta >= 190 && p.mousePressed)) {
                    time = p.millis();
                    //start new screen loading
                    readyToPlay = true;
                }
            }

            //move origin back
            if (directionB2F == 0)
                p.translate(-directionL2R * size / 2, 0);
            else
                p.translate(0, 0, -directionB2F * size / 2);

        }
    }

    public void putWelcomeText(int directionL2R, int directionB2F){
        p.pushMatrix();
        //move origin halfway to the direction indicated
        if(directionB2F == 0)
            p.translate(directionL2R * size/2, 0);
        else
            p.translate(0, 0, directionB2F * size/2);
        //draw shape
        p.textSize(50);
        if(!readyToPlay) {
            p.text("Welcome to Go Fish", -200, 0, 1);
        }
        else {
            p.text("Welcome to Go Fish", -200, 0, -1);
            p.text("Welcome to Go Fish", -200 ,0, 1);
        }
        //move origin back
        if(directionB2F == 0)
            p.translate(-directionL2R * size/2, 0);
        else
            p.translate(0, 0, -directionB2F * size/2);
        p.popMatrix();

    }

    public void putInstructionText(int directionL2R, int directionB2F){
        p.pushMatrix();
        //move origin halfway to the direction indicated
        if(directionB2F == 0)
            p.translate(directionL2R * size/2, 0);
        else
            p.translate(0, 0, directionB2F * size/2);
        //draw shape
        p.textSize(50);
        p.rotateY(14.14F);
//        System.out.println(thetaTest);
//        thetaTest += 0.01;
        if(!readyToPlay) {
            p.text("Defeat the Final Boss", -225, -100, 1);
            p.translate(0,0,1);
            p.tint(0);
            p.image(finalBoss, -100,-25);
            p.noTint();
            p.fill(255);
            p.textSize(175);
            p.translate(0,0,1);
            p.text("?", -25, 125);
            p.translate(0,0,-2);
        }
        else {
            p.text("Defeat the Final Boss", -225, -100, -1);
            p.text("Defeat the Final Boss", -225 ,-100, 1);
        }
        //move origin back
        if(directionB2F == 0)
            p.translate(-directionL2R * size/2, 0);
        else
            p.translate(0, 0, -directionB2F * size/2);
        p.popMatrix();

    }

    public void putCreatedByText(int directionL2R, int directionB2F){
        p.pushMatrix();
        //move origin halfway to the direction indicated
        if(directionB2F == 0)
            p.translate(directionL2R * size/2, 0);
        else
            p.translate(0, 0, directionB2F * size/2);
        //draw shape
        p.pushMatrix();
        p.rotateY(28.3F);
        p.textSize(35);
        if(!readyToPlay)
            p.text("Created By Luke R  \n\n                      & \n\n                                  Jack Stahl", -200 ,-150, 7);
        else {
            p.text("Created By Luke R  \n\n                      & \n\n                                  Jack Stahl", -200, -150, 7);
            p.text("Created By Luke R  \n\n                      & \n\n                                  Jack Stahl", -200, -150, -1);
        }


        p.popMatrix();
        //move origin back
        if(directionB2F == 0)
            p.translate(-directionL2R * size/2, 0);
        else
            p.translate(0, 0, -directionB2F * size/2);
        p.popMatrix();

    }

}
