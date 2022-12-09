import processing.core.PApplet;

import java.util.ArrayList;

import processing.core.PImage;

import processing.core.PMatrix3D;

public class jackluke extends PApplet {

    public static void main(String[] args) {
        PApplet.main("jackluke");
    }

    static harpoon h;
    fish f;

    loadingCube c;

    boolean gOver = false;

    PImage harpoonIm;

    float lives = 3;

    int numFishKilled;
    int fishNum;

    PImage heart;

    static int harpoonCount = 10;

    static boolean harpoonShot = false;

    int releasedCount = 0;
    float time4CountDown;
    PImage fish1;

    PImage fish2;

    PImage fish3;

    PImage swedishFish;

    boolean playerReady = false;

    PImage finalBoss;

    boolean finalBossinPlay = false;
    float coolDownForAddFish = 600;

    static float timeCurrentforAddFish = 0;
    //how frequently a fish spawns
    float difficultyTime = 3000;

    float previousTime = 0;

    int numToCheck4FishKilled = 10;

    ArrayList<PImage> fishImages = new ArrayList<>();

    ArrayList<harpoon> Harpoons = new ArrayList<>();

    ArrayList<fish> fishies = new ArrayList<>();

    PImage underwater;

    int numRareFishKilled = 0;

    PImage blood;

    finalBoss boss;

    public void settings() {
        size(900, 850, P3D);
    }

    public void setup() {
        h = new harpoon(this);

        harpoonIm = loadImage("harpoon.png");
        harpoonIm.resize(17, 72);
        swedishFish = loadImage("swedishfish.png");
        fish1 = loadImage("nemo.png");
        fish2 = loadImage("fish1.png");
        fish3 = loadImage("eel1.png");
        blood = loadImage("blood.png");
        blood.resize(380, 320);
        swedishFish.resize(90, 22);
        fish1.resize(100, 66);
        fish2.resize(90, 38);
        fish3.resize(90, 32);
        fishImages.add(swedishFish);
        fishImages.add(fish1);
        fishImages.add(fish2);
        fishImages.add(fish3);
        heart = loadImage("heart12.png");
        heart.resize(40, 40);
        int randomFishNumber = (int) random(4);
        f = new fish(this, fishImages.get(randomFishNumber), 300, 300, randomFishNumber);
        fishies.add(f);
        c = new loadingCube(this);
        harpoon h1 = new harpoon(this);
        Harpoons.add(h1);
        numFishKilled = 0;
        fishNum = 0;
        underwater = loadImage("underwater.jpg");
        underwater.resize(900, 900);
        finalBoss = loadImage("blobfish.png");
        finalBoss.resize(220,220);
        boss = new finalBoss(this, finalBoss);
    }
    ArrayList<harpoonFal> harpoonFals = new ArrayList<>();

    public void draw() {
        if (!gOver) {
            image(underwater, 0, 0);
            fill(255);
            textSize(50);
            if (!c.play) {
                c.display();
                time4CountDown = millis();
            } else {
                if (!playerReady) {
                    //display lives
                    image(heart, 30, 30);
                    image(heart, 90, 30);
                    image(heart, 150, 30);
                    textSize(200);
                    fill(0);
                    h.display();
                    if (millis() - time4CountDown <= 1000)
                        text("3", 400, 450);
                    else if (millis() - time4CountDown <= 2000)
                        text("2", 400, 450);
                    else if (millis() - time4CountDown <= 3000)
                        text("1", 400, 450);
                    else
                        playerReady = true;
                    fill(0);
                    textSize(30);
                    text("Harpoon Count: " + harpoonCount, 650, 50);
                } else {
                    if (lives == 3) {
                        image(heart, 30, 30);
                        image(heart, 90, 30);
                        image(heart, 150, 30);
                    }
                    if (lives == 2) {
                        image(heart, 90, 30);
                        image(heart, 150, 30);
                        image(blood, -150, 680);
                        image(blood, 650, 680);
                    }
                    if (lives == 1) {
                        image(heart, 150, 30);
                        image(blood, -150, 680);
                        image(blood, 650, 680);
                        image(blood, -100, 700);
                        image(blood, 600, 700);
                        image(blood, 30, 770);
                        image(blood, -200, -100);
                        image(blood, 600, -100);
                    }
                    if (lives == 0)
                        gOver = true;
                    fill(0);
                    textSize(30);
                    text("Harpoon Count: " + harpoonCount, 650, 50);
                    if(!finalBossinPlay)
                        text("Fish To Final Boss " + (numToCheck4FishKilled - numFishKilled), 625, 80);
                    text("Fish Killed: " + numFishKilled, 60, 120);
                    text("Rare Fish Killed: " + numRareFishKilled, 20, 170);
                    if(finalBossinPlay)
                        difficultyTime = 2500;
                    else {
                        if (numFishKilled > 4) {
                            difficultyTime = 2500;
                        }
                        if (numFishKilled > 8)
                            difficultyTime = 2150;
                        if (numFishKilled > 12)
                            difficultyTime = 1750;
                        if (numFishKilled > 16)
                            difficultyTime = 1500;
                        if (numFishKilled > 20)
                            difficultyTime = 1150;
                    }
                        if (numToCheck4FishKilled - numFishKilled < 0) {
                            finalBossinPlay = true;
                            numToCheck4FishKilled += 1000000;
                        }
                    if (harpoonShot && harpoonCount > 0 && millis() - timeCurrentforAddFish >= coolDownForAddFish) {
                        harpoon h1 = new harpoon(this);
                        Harpoons.add(h1);
                        harpoonShot = false;
                    }
                    if(finalBossinPlay){
                        boss.display();
                        for (harpoon harpoon : Harpoons) {
                            if (boss.intersect(harpoon.xpos, harpoon.ypos)) {
                                Harpoons.remove(harpoon);
                                boss.lives--;
                                break;
                            }
                        }
                        if(boss.lives < 1) {
                            for(int i = 0; i < 25; i++){
                                harpoonFal hF = new harpoonFal(this, random(boss.ypos, boss.ypos+50), random(boss.xpos, boss.xpos + 80));
                                harpoonFals.add(hF);
                            }
//                            fill(0);
//                            textSize(30);
//                            text("+10!", 700, 50);
//                            harpoonCount += 10;
                            finalBossinPlay = false;
                        }
                        if(boss.ypos >= height - 220)
                            gOver = true;
                    }
                    for (harpoonFal harpoonFal : harpoonFals) {
                        harpoonFal.display();
                    }
                    fill(0, 0, 0);
                    if (Harpoons.size() > 0) {
                        for (int i = 0; i < Harpoons.size(); i++) {
                            Harpoons.get(i).display();
                            for (fish fishy : fishies) {
                                if (fishy.alive && Harpoons.get(i).intersect(fishy.xpos, fishy.ypos) && harpoonShot) {
                                    //swedishFish
                                    fill(0);
                                    textSize(30);
                                    float xTemp = fishy.xpos;
                                    float yTemp = fishy.ypos;
                                    fishy.alive = false;
                                    Harpoons.remove(i);
                                    if (fishy.fishNum == 0) {
                                        numFishKilled++;
                                        harpoonCount += 1;
                                        text("+1", xTemp, yTemp);
                                    }
                                    //Nemo
                                    if (fishy.fishNum == 1) {
                                        numFishKilled ++;
                                        harpoonCount += 2;
                                        text("+2", xTemp, yTemp);
                                    }
                                    //other weird fish
                                    if (fishy.fishNum == 2) {
                                        numFishKilled ++;
                                        harpoonCount += 2;
                                        text("+2", xTemp, yTemp);
                                    }
                                    //eel
                                    if (fishy.fishNum == 3) {
                                        numFishKilled ++;
                                        numRareFishKilled ++;
                                        harpoonCount += 3;
                                        text("+3", xTemp, yTemp);
                                    }
                                    //exit loop
                                    break;
                                }
                            }
                        }
                    }
                    if (millis() - previousTime >= difficultyTime) {
                        previousTime = millis();
                        int randomFishNum = (int) random(3.24F);
                        fish f1 = new fish(this, fishImages.get(randomFishNum), random(width), -30, randomFishNum);
                        fishies.add(f1);
                    }
                    for (fish fishy : fishies) {
                        fishy.fall();
                        if (fishy.ypos >= height - 30 && fishy.alive) {
                            background(255, 0, 0);
                            fishy.alive = false;
                            lives--;
                        }
                    }

                }
            }

        } else {
            image(underwater, 0, 0);
            fill(0);
            textSize(30);
            text("Harpoon Count: " + harpoonCount, 650, 50);
            text("Fish To Final Boss " + (numToCheck4FishKilled - numFishKilled), 625, 80);
            text("Fish Killed: " + numFishKilled, 60, 50);
            text("Rare Fish Killed: " + numRareFishKilled, 20, 85);
            fill(255,0,0);
            textSize(125);
            text("Game Over!", 160, 300);
            textSize(65);
            text("Tap to Play Again", 200, 450);
            if(mousePressed){
                harpoonCount = 10;
                numFishKilled = 0;
                lives = 3;
                difficultyTime = 3000;
                gOver = false;
            }

        }

    }
}

