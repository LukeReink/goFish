import processing.core.PApplet;

public class IndSpike {
    PApplet p;
    float theta;
    float spinD;
    float x1;
    float y1;
    float x2;
    float y2;
    float x3;
    float y3;
    public IndSpike(PApplet p, float theta){
        this.p = p;
        this.theta = theta;
        spinD = (float) 0.04;
    }

    public void display(float xpos, float ypos, float s, float c){
        theta += spinD;
        x1 = xpos + p.cos(theta)*(s/2);
        y1 = ypos + p.sin(theta)*(s/2);
        x2 = xpos + p.cos(theta+30)*(s/2);
        y2 = ypos + p.sin(theta+30)*(s/2);
        x3 = xpos + p.cos(theta+15)*((s-s*4)/2);
        y3 = ypos + p.sin(theta+15)*((s-s*4)/2);
        p.fill(c,0,0);
//        p.stroke(255);
        p.noStroke();
        p.triangle(x1,y1,x2,y2,x3,y3);
    }
}
