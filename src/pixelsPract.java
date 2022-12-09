import processing.core.PApplet;
import processing.core.PImage;

public class pixelsPract extends PApplet {
    public static void main(String[] args) {

        PApplet.main("pixelsPract");

    }

    PImage img;

    public void settings() {
        size(600, 600, P3D);

    }

    public void setup() {
//        img = loadImage("sunflower.jpg");
//        img.resize(width,height);
    }
    int loc;

    public void draw() {
        int loc;
        float distance;
        float iOn255Scale;

        loadPixels();
        for(int y = 0; y < pixelWidth; y++) {
            for (int i = 0; i < pixelWidth; i++) {
                loc = i + y*pixelWidth;
                distance = map(dist(i,y,pixelWidth/2,pixelHeight/2),0,dist(pixelWidth,pixelHeight,pixelWidth/2,pixelHeight/2),255,200);
                pixels[loc] = color(255-distance);
            }
        }
        updatePixels();

//        // We must also call loadPixels() on the PImage since we are going to read its pixels.
//        img.loadPixels();
//        for (int y = 0; y < img.height; y+=5) {
//            for (int x = 0; x < img.width; x+=5) {
//                loc = x + y * img.width;
//                // The functions red(), green(), and blue() pull out the three color components from a pixel.
//                float r = red(img.pixels[loc]);
//                float g = green(img.pixels[loc]);
//                float b = blue(img.pixels[loc]);
//                float z = map(mouseX - x, 0, width, 8,0);
//                z = -z*z*z;
//
//                // Image Processing would go here
//                // If we were to change the RGB values, we would do it here, before setting the pixel in the display window.
//
//                // Set the display pixel to the image pixel
//                noStroke();
//                pushMatrix();
//                translate(x,y,z);
//                fill(r,g,b);
////                rect(0,0,10,10);
////                popMatrix();
//            }
//        }
//        img.updatePixels();
    }
}
