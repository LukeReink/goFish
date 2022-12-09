import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PMatrix;
import processing.core.PVector;

import java.awt.*;

public class proccessingDemo extends PApplet{
    public static void main(String [] args){
        PApplet.main("proccessingDemo");
    }

    //window size gets set in settings()
    public void settings(){
        size(600, 600, P3D);
    }

    public void setup() {

    }
    float s = 0;
    float theta = 0;
    public void draw(){
        background(255);
        noFill();
        translate(width/2,height/2);
//        pushMatrix();
        ellipse(0,0,100,100);
        rotate(theta);
        translate(100,100);
        ellipse(0,0,70,70);
//        pushMatrix();
        rotate(-theta*4);
        translate(70,70);
        ellipse(0,0,20,20);
        theta += 0.01;
//        popMatrix();
//        popMatrix();

    }

    public void drawTriangle(float t){
        beginShape(TRIANGLES);
        fill(120,12,12,30);
        vertex(-t,-t,-t);
        vertex(t,-t,-t);
        vertex(0,0,0);
        fill(21,43,12,24);
        vertex(t,-t,-t);
        vertex(t,t,-t);
        vertex(0,0,0);
        fill(21,1,42,21);
        vertex(t,t,-t);
        vertex(-t,t,-t);
        vertex(0,0,0);
        fill(10,240,2,12);
        vertex(-t,t,-t);
        vertex(-t,-t,-t);
        vertex(0,0,0);
        endShape(CLOSE);
    }

}
