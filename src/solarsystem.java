import processing.core.PApplet;

import java.util.ArrayList;

public class solarsystem extends PApplet {

    public static void main(String [] args){
        PApplet.main("solarsystem");
    }

    public void settings(){
        size(600,600, P3D);
    }

    ArrayList<planet> Planets = new ArrayList<planet>();

    public void setup(){
        for(int i = 0; i < 5; i++){
            planet p = new planet(this);
            Planets.add(p);
        }

    }
    float theta = 0;

    public void draw(){
        background(0);
        translate(width/2,height/2);
        pushMatrix();
        fill(0,255,0);
        rotateY(theta);
        rotateX(theta/4);
        sphere(100);
        popMatrix();
        for(int i = 0; i < Planets.size(); i++){
            Planets.get(i).display();
        }
        theta += 0.01;

    }

}
