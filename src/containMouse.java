import processing.core.PApplet;

public class containMouse {

    float xpos;
    float ypos;

    String missing = "none";

    static float cWidth;

    static float cHeight;

    boolean oneKeyPressed;

    boolean drawUp;

    boolean drawDown;

    boolean drawRight;

    boolean alive = true;

    boolean drawLeft;
    PApplet p;

    public containMouse(PApplet p){
        this.p = p;
        cWidth = 10;
        cHeight = 10;
        oneKeyPressed = false;
        drawDown = true;
        drawLeft = true;
        drawRight = true;
        drawUp = true;
    }

    public void draw(){
        if(alive) {
            xpos = p.mouseX;
            ypos = p.mouseY;
        }
        p.noFill();
        p.stroke(0);
        p.ellipse(xpos, ypos, cWidth, cHeight);
        if(alive)
            p.stroke(255,0,0);
        else
            p.stroke(0);
        if(drawLeft)
            p.line(0, ypos, xpos-(cWidth/2F), ypos);
        if(drawRight)
            p.line(p.width, ypos, xpos+(cWidth/2F), ypos);
        if(drawUp)
            p.line(xpos, 0, xpos, ypos-(cHeight/2F));
        if(drawDown)
            p.line(xpos, p.height, xpos, ypos+(cHeight/2F));
    }

    public void removeLines(){

        if(p.keyPressed && p.keyCode == p.UP){
            drawUp = false;
            drawLeft = true;
            drawRight = true;
            drawDown = true;
            missing = "up";
        }
        else if (p.keyPressed && p.keyCode == p.RIGHT) {
            drawUp = true;
            drawLeft = true;
            drawRight = false;
            drawDown = true;
            missing = "right";
        }
        else if (p.keyPressed && p.keyCode == p.LEFT) {
            drawUp = true;
            drawLeft = false;
            drawRight = true;
            drawDown = true;
            missing = "left";
        }
        else if (p.keyPressed && p.keyCode == p.DOWN) {
            drawUp = true;
            drawLeft = true;
            drawRight = true;
            drawDown = false;
            missing = "down";
        }

    }
}
