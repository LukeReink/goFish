import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class frogGame extends PApplet {
    public static void main(String [] args){
        PApplet.main("frogGame");
    }
    public void settings(){
        size(600,600);
    }
    public void setup(){
        for(int i = 0; i < 75; i++){
            xpos.add((float) (width + (i%10)*100.0));
        }
        for(int i = 0; i < 75; i++){
            if(i <= 20)
            ypos.add(i*8+(random(10)));
            else
                ypos.add(random(height));
        }
        for(int i = 0; i < 75; i++){
            colors.add((float) color(random(255),random(255),random(255)));
        }
    }
    ArrayList<Float> xpos = new ArrayList<Float>();
    ArrayList<Float> ypos = new ArrayList<Float>();
    ArrayList<Float> colors = new ArrayList<Float>();

    public void draw(){
        rectMode(CENTER);
        if(game)
        background(255);
        for(int i = 0; i < xpos.size(); i++){
            fill(colors.get(i), 14,113);
            rect(xpos.get(i),ypos.get(i),30,30);
        }
        for(int i = 0; i < xpos.size(); i++){
            if(xpos.get(i)<= 0){
                xpos.set(i, (float) width + 31);
            }
        }
        for(int i = 0; i < xpos.size(); i++){
            xpos.set(i,xpos.get(i)-2);
        }
        fill(223,32,4,50);
        rect(mouseX, mouseY, 30, 30);
        for(int i = 0; i < xpos.size(); i++){
            if(mouseX >= xpos.get(i)-15 && mouseX <= xpos.get(i)+15 && mouseY >= ypos.get(i)-15 && mouseY <= ypos.get(i)+15){
                background(255,0,0);
                removeAll(xpos);
                removeAll(ypos);
                text("GAME OVER", width/2, height/2);
                game = false;
            }
        }
    }
    boolean game = true;
    public void removeAll(ArrayList<Float> g){
        while(g.size() > 0)
            g.remove(0);
    }
}
