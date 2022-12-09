import processing.core.PApplet;
import java.util.ArrayList;
public class frogga extends PApplet {
    public static void main(String[] args) {
        PApplet.main("frogga");
    }

    public void settings() {
        size(800, 600);
    }
    int x;
    int y;
    int n = 5;
    float [] ypos = new float[n];
    float [] xpos = new float[n];
    float [] speed = new float[n];

    public void setup(){
        x = width/2;
        y = height/2;
        for(int i = 0; i < xpos.length; i++){
            xpos[i] = (i+1)*50;
        }
        for(int i = 0; i < ypos.length; i++){
            ypos[i] = (i+1)*100;
        }
        for(int i = 0; i < speed.length; i++){
            speed[i] = random(1,5);
        }
    }
    public void draw(){
        rectMode(CENTER);
        background(52, 204, 235);
        for(int i = 0; i < n; i++){
            rect(xpos[i],ypos[i],100,height/20);
        }
        for(int i = 0; i < speed.length; i++){
            xpos[i] += speed[i];
        }
        rect(x,y,70,22);
        for(int i = 0; i < n; i++){
            if(xpos[i] >= width+50) {
                xpos[i] = -50;
                speed[i] = random(1,15);
            }
        }
        for(int i = 0; i < n; i++){
            if(x+35 >= xpos[i]-50 && x-35 <= xpos[i]+50 && y+11 >= ypos[i]-15 && y-11 <= ypos[i]+15){
                for(int j = 0; j < n; j++){
                    speed[j] = 0;
                }
            }
        }

    }
    public void keyReleased(){
        y += height/20;
        if(y >= height)
            y = 0;
    }
}