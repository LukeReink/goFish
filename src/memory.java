import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class memory extends PApplet {
    public static void main(String[] args) {
        PApplet.main("memory");
    }

    public void settings() {
        size(600, 600);
    }
    ArrayList<timer> Timers = new ArrayList<timer>();
    public void setup() {
//        for(int i = 0; i < 20; i++) {
//            timer t = new timer(3, this);
//            Timers.add(t);
//        }
    }
    timer t;
    float previousTime = 0;
    boolean home = true;
    boolean button1 = false;
    public void draw() {
        if(millis() - previousTime >= 3000){
            previousTime = millis();
        }
        rectMode(CENTER);
        background(255);
        textAlign(CENTER);
       if(home) {
            background(4, 39, 87);
            fill(58, 199, 214);
            rect(150, 150, 300, 300);
            fill(255);
            textSize(40);
            text("Size Recognition", 150, 150);
       }
       if(mouseX >= 0 && mouseX <= 300 && mouseY>= 0 && mouseY <= 300 && mousePressed) {
           button1 = true;
       }
       if(button1) {
           float sizeE = 0;
           home = false;
           background(245, 241, 22);
           fill(0);
           stroke(0);
           textSize(20);
           t = new timer(millis(),this);
           Timers.add(t);
           if(!(millis() - previousTime >= 3000)) {
               text("Welcome to Size Recognition... \nYou will see a shape at a certain size for 3 seconds \nthen you will see a growing shape.\nPress any button to stop the shape's movement -\nthe closer you stop it at the original shape the more points you get!", 300, 225);
           }
           else {
               previousTime = millis();
               timer t1 = new timer(millis(),this);
               Timers.add(t1);
               if(!Timers.get(1).go(3)){
                   float sizeOfficial = random(200,400);
                   ellipse(width/2,height/2, sizeOfficial, sizeOfficial);
               }
               else {
                   sizeE = 20;
                   noFill();
                   stroke(0);
                   timer t2 = new timer(millis(),this);
                   Timers.add(t2);
                   if(!Timers.get(2).go(3)) {
                       ellipse(width / 2, height / 2, sizeE, sizeE);
                       sizeE += 2;
                   }
               }
           }
       }
       }
    }















