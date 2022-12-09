import processing.core.PApplet;


public class trailIndv extends PApplet {

    PApplet p;
    float xpos;
    float ypos;

    float size;
    public trailIndv(PApplet p, float xpos, float ypos, float size){
        this.p = p;
        this.xpos = xpos;
        this.ypos = ypos;
        this.size = size;

    }

    public void display(){
        p.fill(200,10,122);
        p.ellipse(xpos, ypos, size, size);

    }
}
