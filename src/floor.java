import processing.core.PApplet;

import java.util.ArrayList;

public class floor {
    PApplet p;
    ArrayList<floorPiece> floors;
    float z;
    boolean emptyYes;


    public floor(PApplet p){
        this.p = p;
        floors = new ArrayList<floorPiece>();
        float f = floorPiece.depth;
        for(int i = 0; i < 24; i++){
            emptyYes = true;
            if(i < 3) {
                z = f;
                emptyYes = false;
            }
            else if(i < 6) {
                z = 0;
                emptyYes = false;
            }
            else if(i < 9)
                z = f * -1;
            else if(i < 12)
                z = f * -2;
            else if(i < 15)
                z = f * -3;
            else if(i < 18)
                z = f * -4;
            else if(i < 21)
                z = f * -5;
            else
                z = f * -6;
            floorPiece fp = new floorPiece(p, 250+(i%3)*50, 300, z, emptyYes);
            floors.add(fp);
        }
    }
    public void display(){
        for(int i = 0; i < floors.size(); i++){
            floors.get(i).display();
            floors.get(i).move();
        }
    }

}
