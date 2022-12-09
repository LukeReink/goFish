import processing.core.PApplet;

public class floorPiece {
    PApplet p;
    float xpos;
    float ypos;
    float zpos;
    float chance;
    public static final float depth = 200;
    boolean empty;
    float speed;

    public floorPiece(PApplet p, float xpos, float ypos, float zpos, boolean canEmp){
        this.p = p;
        this.xpos = xpos;
        this.ypos = ypos;
        this.zpos = zpos;
        //lower = more likelihood
        chance = 0.75f;
        //higher = harder
        speed = 7;
        if(canEmp) {
            if (p.random(1) >= chance)
                empty = true;
            else
                empty = false;
        }
        else
            empty = false;
    }

    public void display(){
        p.pushMatrix();
        p.translate(xpos, ypos, zpos);
        if(empty){
            p.noStroke();
            p.noFill();
        }
        else {
            p.stroke(0);
            p.fill(150);
        }
        p.box(50,50,depth);
        p.popMatrix();
    }
    public void move(){
        if(zpos >= 600){
            zpos = -1000;
            if(p.random(1) >= chance){
                empty = true;
            }
            else
                empty = false;
        }
        zpos += speed;
    }
}
