import processing.core.PApplet;

public class ball {
    PApplet p;
    float xpos;
    float ypos;
    float zpos;
    float theta;
    float xdirec;
    float gravity;
    float ydirec;
    boolean block;
    boolean xmove;
    public ball(PApplet p){
        this.p = p;
        xpos = p.width/2;
        ypos = 255;
        zpos = 300;
        theta = 0;
        xdirec = 0;
        gravity = 0.4F;
        ydirec = 0;
        block = true;
        xmove = true;
    }

    public void display(floor fP){
        p.pushMatrix();
        p.translate(xpos,ypos,zpos);
        p.rotateX(theta);
//        p.stroke(0,20,230,60);
//        p.fill(0,20,230,60);
        p.sphere(20);
        p.popMatrix();
        theta += 0.1;
        xpos += xdirec;
        for(int i = 0; i < fP.floors.size(); i++){
            if(zpos >= fP.floors.get(i).zpos && zpos <= fP.floors.get(i).zpos + 100 && ypos == 255 && xpos >= fP.floors.get(i).xpos && xpos <= fP.floors.get(i).xpos+35 && fP.floors.get(i).empty){
                block = false;
            }
        }
        for(int i = 0; i < fP.floors.size(); i++){
            if(zpos >= fP.floors.get(i).zpos && zpos <= fP.floors.get(i).zpos + 100 && ypos > 255 && xpos >= fP.floors.get(i).xpos && xpos <= fP.floors.get(i).xpos+35 && fP.floors.get(i).empty){
                if(xpos > fP.floors.get(i).xpos + 10){
                    xdirec *= -1;
                    xpos = fP.floors.get(i).xpos + 10;
                    System.out.println("SIII");
                }
                if(xpos < fP.floors.get(i).xpos){
                    xdirec *= -1;
                    xpos = fP.floors.get(i).xpos;
                }
            }
        }
        if(ypos < 255){
            block = true;
        }
        ypos += ydirec;
        ydirec += gravity;
        if(block){
            if(ypos >= 255) {
                ydirec = 0;
                ypos = 255;
            }
        }
    }
}
