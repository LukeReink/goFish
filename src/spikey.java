import processing.core.PApplet;

import java.util.ArrayList;


public class spikey {
    float xpos;
    float ypos;
    float theta;
    float xspeed;
    float yspeed;
    float s;
    float c;
    PApplet p;
    ArrayList<IndSpike> spikes;

    public spikey(PApplet p){
        this.p = p;
        xpos = p.random(p.width);
        ypos = p.random(200);
        theta = 0;
        xspeed = 1.6F;
        yspeed = 1.6F;
        s = p.random(15,25);
        c = p.random(100,255);
        spikes = new ArrayList<IndSpike>();
        for(int i = 0; i < 12; i++){
            IndSpike aww = new IndSpike(p, i*p.PI/6);
            spikes.add(aww);
        }

    }

    public void move() {
        p.fill(c, 0, 0);
        p.noStroke();
        p.ellipse(xpos, ypos, s, s);
        xpos += xspeed;
        ypos += yspeed;
        for (int i = 0; i < spikes.size(); i++) {
            spikes.get(i).display(xpos, ypos, s, c);
            if (xpos + p.abs(spikes.get(i).x3 - spikes.get(i).x2) > p.width) {
                xpos = p.width - (p.abs(spikes.get(i).x3 - spikes.get(i).x2))-3;
                xspeed *= -1;
//                rotateAll();
            }
            if (ypos + p.abs(spikes.get(i).y3 - spikes.get(i).y2) > p.height) {
                ypos = p.height - (p.abs(spikes.get(i).y3 - spikes.get(i).y2))-3;
                yspeed *= -1;
//                rotateAll();
            }
            if (ypos < p.abs(spikes.get(i).y3 - spikes.get(i).y2)) {
                ypos = (p.abs(spikes.get(i).y3 - spikes.get(i).y2))+3;
                yspeed *= -1;
//                rotateAll();
            }
            if (xpos < p.abs(spikes.get(i).x3 - spikes.get(i).x2)) {
                xpos = (p.abs(spikes.get(i).x3 - spikes.get(i).x2))+3;
                xspeed *= -1;
//                rotateAll();
            }

        }
    }
    public void intersect(orb o){
        if(xpos-s/2-7.5 <= o.lX + 40 && xpos+s/2+7.5 >= o.lX && ypos - s/2-7.5 >= o.lY && ypos + s/2 + 7.5 <= o.lY + 30){
            o.shatter();
            yspeed *= -1;
        }
    }
    public void intersect(log l){
        //need to reset yposition to out of range from area
        if(xpos - s/2 - 7.5 <= l.xpos + 183 && xpos + s/2 + 7.5 >= l.xpos - 15 && ypos - s/2 - 7.5 >= l.ypos-30 && ypos + s/2 + 7.5 <= l.ypos){
            yspeed *= -1;
        }
        if(xpos - s/2 - 7.5 <= l.xpos + 183 && xpos + s/2 + 7.5 >= l.xpos - 15 && ypos - s/2 - 7.5 >= l.ypos+30 && ypos + s/2 + 7.5 <= l.ypos+ 60){
            yspeed *= -1;
        }
    }
}
