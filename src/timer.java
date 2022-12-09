import processing.core.PApplet;

public class timer {

    public static int count = -1;
    PApplet p;
    float t1;

    public timer(float t, PApplet p){
        this.p = p;
        t1 = t;
        count ++;
    }

    public boolean go(float time){
        return p.millis() - t1 >= time*1000;
    }

    /**
     * returns true when time 'newT' has elapsed in seconds since currentT
     * @param newT
     * @return
     */
    public boolean time(float newT, float currentT){
        if(p.millis()-currentT >= newT*1000)
            return  true;
        return  false;
    }
}
