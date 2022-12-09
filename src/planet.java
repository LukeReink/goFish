import processing.core.PApplet;

public class planet {

    PApplet p;
    float theta;
    float w;
    float s;
    float sp;
    int c;

    public planet(PApplet p){
        this.p = p;
        theta = 0;
        w = p.random(150,250);
        c = p.color(p.random(100,255),p.random(255),p.random(100,200));
        s = p.random(25,50);
        sp = p.random(-2,2);
    }

    public void display(){
        p.pushMatrix();
        p.rotateY(theta*sp);
        p.rotateX(theta*sp/2);
        p.fill(c);
        p.translate(w,w);
        p.rotateY(theta);
        p.rotateX(theta);
        p.sphere(s);
        p.popMatrix();
        theta += 0.01;
    }
}
