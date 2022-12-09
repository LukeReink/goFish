import processing.core.PApplet;

public class runGame extends PApplet {

    public static void main(String [] args){
        PApplet.main("runGame");
    }
    floor f;
    ball b;
    public void settings(){
        size(600,600, P3D);
    }
    public void setup(){
        f = new floor(this);
        b = new ball(this);

    }
    public void draw(){
        camera(width/2, height/2 - 130, (height/2) / tan((float) (PI*30.0 / 180.0)), width/2, height/2, 0, 0, 1, 0);
        background(255);
        f.display();
        b.display(f);
    }
    public void keyPressed(){
        if(b.xmove) {
            if (keyCode == RIGHT) {
                b.xdirec = 2;
            }
            if (keyCode == LEFT) {
                b.xdirec = -2;
            }
        }
        if(keyCode == UP){
            b.ydirec = -7;
        }
    }

}
