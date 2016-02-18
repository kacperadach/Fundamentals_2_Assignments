
// Assignment 5
// partner1-Adach partner1-Kacper
// partner1-kadach

import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

class Apple {

    int speed = 10;
    int x;
    int y;

    Apple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Apple moveDown() {
        return new Apple(this.x, this.y + speed);
    }

    public boolean onTheGround() {
        return this.y >= 390;
    }

    public Apple fall() {
        if (this.moveDown().onTheGround()) {
            return new Apple(new Random().nextInt(400), new Random().nextInt(200));
        } 
        else {
            return this.moveDown();
        }
    }
}

class Basket {

    int speed = 30;
    int length = 50;
    int x;

    Basket(int x) {
        this.x = x;
    }

    public Basket moveOnKey(String ke) {
        if (ke.equals("left")) {
            return new Basket(this.x - speed);
        } 
        else if (ke.equals("right")) {
            return new Basket(this.x + speed);
        } 
        else {
            return this;
        }
    }
}

class AppleGame extends World {

    int width = 400;
    int height = 400;
    int caught;
    ArrayList<Apple> a;
    Basket b;

    AppleGame(int caught, ArrayList<Apple> a, Basket b) {
        super();
        this.caught = caught;
        this.a = a;
        this.b = b;
    }

    public boolean caughtApple(Apple a, Basket b) {
        if (a.y < 380) {
            return false;
        } 
        else {
            return a.x >= (b.x - b.length) && a.x <= (b.x + b.length);
        }
    }

    public WorldImage background = new FromFileImage("apple-tree.png");
    public WorldImage apple1 = new FromFileImage("small-red-apple.png");
    public WorldImage apple2 = new FromFileImage("yellow-apple.png");
    public WorldScene finalScene = this.getEmptyScene()
            .placeImageXY(this.background, this.width / 2, this.height / 2)
            .placeImageXY(new TextImage("You won!", 40, Color.BLACK), 200, 200);

    @Override
    public World onTick() {
        int count = 0;
        for (int i = 0; i < this.a.size(); i++) {
            if (this.caughtApple(this.a.get(i), this.b)) {
                count++;
            }
            this.a.set(i, this.a.get(i).fall());
        }
        return new AppleGame(this.caught + count, this.a, this.b);
    }

    @Override
    public World onKeyEvent(String ke) {
        return new AppleGame(this.caught, this.a, this.b.moveOnKey(ke));
    }

    @Override
    public WorldEnd worldEnds() {
        if (this.caught == 10) {
            return new WorldEnd(true, this.finalScene);
        } 
        else {
            return new WorldEnd(false, this.makeScene());
        }
    }

    @Override
    public WorldScene makeScene() {
        WorldScene base = this.getEmptyScene()
            .placeImageXY(this.background, this.width / 2, this.height / 2)
            .placeImageXY(new RectangleImage(this.b.length, 10, OutlineMode.SOLID, Color.BLACK),
                        this.b.x, 399)
            .placeImageXY(new TextImage("Score: " + Integer.toString(this.caught), 30, Color.BLACK),
                        330, 10);
        for (int i = 0; i < this.a.size(); i++) {
            if (i == 0) {
                base = base.placeImageXY(this.apple1, this.a.get(i).x, this.a.get(i).y);
            } 
            else {
                base = base.placeImageXY(this.apple2, this.a.get(i).x, this.a.get(i).y);
            }
        }
        return base;
    }

}

class ExamplesAppleGame {

    Apple a1 = new Apple(200, 100);
    Apple a2 = new Apple(300, 300);
    ArrayList<Apple> list = new ArrayList<Apple>();
    Basket b = new Basket(200);

    boolean testMoveDown(Tester t) {
        return t.checkExpect(this.a1.moveDown(), new Apple(200, 110));
    }

    boolean testOnTheGround(Tester t) {
        return t.checkExpect(this.a1.onTheGround(), false) &&
                t.checkExpect(new Apple(200, 400).onTheGround(), true);
    }

    boolean testFall(Tester t) {
        return t.checkExpect(this.a1.fall(), new Apple(200, 110)) &&
                t.checkRange(new Apple(200, 400).fall().x, 0, 400) &&
                t.checkRange(new Apple(200, 400).fall().y, 0, 200);
    }

    boolean testMoveOnKey(Tester t) {
        return t.checkExpect(this.b.moveOnKey("left"), new Basket(170))
                && t.checkExpect(this.b.moveOnKey("right"), new Basket(230))
                && t.checkExpect(this.b.moveOnKey("test"), this.b);
    }

    boolean testCaughtApple(Tester t) {
        AppleGame ag = new AppleGame(0, list, this.b);
        return t.checkExpect(ag.caughtApple(a1, b), false)
                && t.checkExpect(ag.caughtApple(new Apple(200, 400), b), true);
    }

    public static void main(String[] argv) {

        Apple a1 = new Apple(200, 100);
        Apple a2 = new Apple(300, 300);
        ArrayList<Apple> list = new ArrayList<Apple>();
        list.add(a1);
        list.add(a2);
        Basket b = new Basket(200);
        AppleGame ag = new AppleGame(0, list, b);
        ag.bigBang(400, 400, 0.05);
    }
}