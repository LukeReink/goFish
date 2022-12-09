import processing.core.PApplet;
import processing.core.PImage;

//NEWton mode where have to get apple to land on newton's head
//Had to offset the if statement for intersect in this class to fit the specifics of the image (adjust accordingly)

public class apple {
    float xpos;
    float ypos;
    PImage im;
    PImage im2;
    PImage im3;
    float yim1;
    PApplet p;
    float ydirec;
    float gravity;
    float xspeed;
    float xG;
    int s;
    boolean dead;

    public apple(PApplet p) {
        this.p = p;
        im = p.loadImage("apple1.png");
        s = 60;
        im.resize(s, s);
        yim1 = 0;
        xpos = p.width / 2;
        ypos = p.height - 100;
        ydirec = 1;
        gravity = (float) 0.18;
        xspeed = 4;
        dead = false;
        xG = (float) 0.2;
        im2 = p.loadImage("appleHalf.png");
        im2.resize(80, 80);
        im3 = p.loadImage("appleHalf.png");
        im3.resize(80, 80);
    }
    float xim1;
    float ydirec1;
    public void display() {
        if (!dead) {
            p.image(im, xpos, ypos);
            ypos += ydirec;
            ydirec += gravity;
            xpos += xspeed;
            if (xspeed > 0) {
                xspeed -= xG;
            }
            if (xspeed <= 0) {
                xspeed += xG;
            }
            if (ypos >= p.height - 60) {
                ypos = p.height - 60;
            }
        }
        if (dead) {
            p.image(im2, xim, yim);
            p.image(im3, xim1, yim1);
            yim += ydirec;
            xim += 0.5;
            xim1 -= 0.8;
            yim1 += ydirec1*p.random(0.5F,2);
            ydirec += gravity;
            ydirec1 += gravity;
            if (yim >= p.height - 65) {
                yim = p.height - 65;
                ydirec *= -0.75;
            }
            if (yim1 >= p.height - 65) {
                yim1 = p.height - 65;
                ydirec1 *= -0.75;
            }
        }
        move();
    }

    float xim;
    float yim;

    public void move() {
        if (!dead) {
            if (p.keyPressed && p.keyCode == p.UP && ydirec > -1.5) {
                ydirec = -5;
            }
            if (p.keyPressed && p.keyCode == p.RIGHT) {
                xspeed = 3;
            }
            if (p.keyPressed && p.keyCode == p.LEFT) {
                xspeed = -3;
            }
            if(xpos < -10){
                xspeed = 0;
                xpos = -10;
            }
            if(xpos > p.width-60){
                xspeed = 0;
                xpos = p.width - 60;
            }
        }
    }

    float tC;

    public boolean intersect(spikey spi1) {
        for (int i = 0; i < spi1.spikes.size(); i++) {
            if (PApplet.dist(spi1.xpos, spi1.ypos, xpos+30, ypos+40) <= spi1.s / 2 + PApplet.dist(spi1.spikes.get(i).x1, spi1.spikes.get(i).y1, spi1.spikes.get(i).x2, spi1.spikes.get(i).y2) + ((float)s / 2)) {
                ydirec = 1F;
                xspeed = 0;
                xim = xpos + 30;
                xim1 = xim - 50;
                yim = ypos;
                yim1 = ypos + p.random(10,20);
                dead = true;
                return true;
            }
        }
        return false;
    }
    public boolean intersect(log l){
        //here
        if(xpos + 30 >= l.xpos-15 && xpos <= l.xpos + 183 && ypos + 20 <= l.ypos && ypos+10 >= l.ypos - 25 && ydirec > 0){
            ydirec = l.ySpeed;
        }
        if(xpos + 30 >= l.xpos-15 && xpos <= l.xpos + 183 && ypos >= l.ypos+20 && ypos+10 <= l.ypos + 45 && ypos >= p.height - 70){
            ydirec = 1F;
            xspeed = 0;
            xim = xpos + 30;
            xim1 = xim - 50;
            yim = ypos;
            yim1 = ypos + p.random(10,20);
            dead = true;
            return true;
        }
        if(xpos + 30 >= l.xpos-15 && xpos <= l.xpos + 183 && ypos >= l.ypos+20 && ypos+10 <= l.ypos + 45 && ydirec < 0){
            ydirec *= -0.85;
        }
        return false;
    }
    public boolean intersect(orb o){
        if(xpos <= o.lX + 25 && xpos + 60 >= o.lX && ypos <= o.lY + 25 && ypos >= o.lY-30){
            return true;
        }
        return false;
    }
}
