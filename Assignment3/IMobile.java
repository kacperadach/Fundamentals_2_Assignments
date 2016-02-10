// Assignment 1
// partner1-Adach partner1-Kacper
// partner1-kadach

import tester.*;
import javalib.colors.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;

interface IMobile {
    int totalWeight();
    int totalHeight();
    Boolean isBalanced();
    IMobile buildMobile(int length, int totalLength, IMobile m);
    int curWidth();
    int getLeftWidth();
    int getRightWidth();
    WorldImage drawMobile(Posn p);
}


class Simple implements IMobile {
    int length;
    int weight;
    IColor color;
    
    Simple(int length, int weight, IColor color) {
        this.length = length;
        this.weight = weight;
        this.color = color;
    }
    
    public int totalWeight() {
        return this.weight;
    }
    
    public int totalHeight() {
        return (this.weight / 10) + this.length;
    }
    
    public Boolean isBalanced() {
        return true;
    }
    
    public IMobile buildMobile(int length, int totalLength, IMobile m) {
        int left = 1;
        for (int a = 1; a < totalLength; a++) {
            if ((a * this.totalWeight()) == ((totalLength - a) * m.totalWeight())) {
                left = a;
            }
        }
        return new Complex(length, left, totalLength - left, this, m);
    }
    
    public int getLeftWidth() {
        return this.curWidth() / 2;
    }
    
    public int getRightWidth() {
        return this.curWidth() / 2;
    }
    
    public int curWidth() {
        return (int) Math.ceil(this.weight / 10);
    }
    
    public WorldImage drawMobile(Posn p) {
        int lineTop = p.y + this.length;
        Posn bot = new Posn(p.x, lineTop);
        WorldImage rect = new RectangleImage(bot, this.curWidth(), this.totalHeight(), color);
        WorldImage line = new LineImage(p, bot, new Black());
        return line.overlayImages(rect);
    }
}

class Complex implements IMobile {
    int length;
    int leftside;
    int rightside;
    IMobile left;
    IMobile right;
    
    Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
        this.length = length;
        this.leftside = leftside;
        this.rightside = rightside;
        this.left = left;
        this.right = right;
    }
    
    public int totalWeight() {
        return this.left.totalWeight() + this.right.totalWeight();
    }
    
    public int totalHeight() {
        int left = this.length + this.left.totalHeight();
        int right = this.length + this.right.totalHeight();
        if (left > right) {
            return left;
        }
        else {
            return right;
        }
    }
    
    public Boolean isBalanced() {
        return ((this.leftside * this.left.totalWeight()) == 
                (this.rightside + this.right.totalWeight())) 
                && this.left.isBalanced() && this.right.isBalanced();
    }
    
    public IMobile buildMobile(int length, int totalLength, IMobile m) {
        int left = 0;
        for (int a = 1; a < totalLength; a++) {
            if ((a * this.totalWeight()) == ((totalLength - a) * m.totalWeight())) {
                left = a;
            }
        }
        return new Complex(length, left, totalLength - left, this, m);
    }
    
    public int getLeftWidth() {
        return this.leftside + this.left.getLeftWidth();
    }
    
    public int getRightWidth() {
        return this.rightside + this.right.getRightWidth();
    }
    
    public int curWidth() {
        return this.rightside + this.leftside + this.right.getRightWidth() 
            + this.left.getLeftWidth();
    }
    
    public WorldImage drawMobile(Posn p) {
        IColor black = new Black();
        int lineTop = p.y + this.length;
        Posn middle = new Posn(p.x, lineTop);
        Posn right = new Posn(middle.x + this.rightside, middle.y);
        Posn left = new Posn(middle.x - this.leftside, middle.y);
        WorldImage line1 = new LineImage(p, middle, black);
        WorldImage line2 = new LineImage(middle, right, black);
        WorldImage line3 = new LineImage(middle, left, black);
        return line1.overlayImages(line2, line3, this.right.drawMobile(right), 
                this.left.drawMobile(left));
    }
    
    
}

class ExamplesMobiles {
    
    IColor Green = new Green();
    IColor Red = new Red();
    IColor Blue = new Blue();
    
    IMobile exampleSimple = new Simple(2, 20, Blue);
    
    IMobile simpleOne = new Simple(1, 60, Green);
    IMobile simpleTwo = new Simple(2, 36, Red);
    IMobile simpleThree = new Simple(1, 12, Red);
    IMobile simpleFour = new Simple(1, 36, Blue);
    
    IMobile complexOne = new Complex(2, 5, 3, simpleTwo, simpleOne);
    IMobile complexTwo = new Complex(2, 8, 1, simpleThree, complexOne);
    IMobile exampleComplex = new Complex(1, 9, 3, simpleFour, complexTwo);
        
    IMobile example3 = new Simple(10, 10, Red);
    
    boolean testTotalWeight(Tester t) {
        return t.checkExpect(this.exampleSimple.totalWeight(), 20) 
                && t.checkExpect(this.exampleComplex.totalWeight(), 144);
    }
    
    boolean testTotalHeight(Tester t) {
        return t.checkExpect(this.exampleSimple.totalHeight(), 4) 
                && t.checkExpect(this.exampleComplex.totalHeight(), 12);
    }
    
    boolean testIsBalanced(Tester t) {
        return t.checkExpect(this.exampleSimple.isBalanced(), true) 
                && t.checkExpect(this.exampleComplex.isBalanced(), false);
    }
    
    boolean testcurWidth(Tester t) {
        return t.checkExpect(this.exampleSimple.curWidth(), 2) 
                && t.checkExpect(this.exampleComplex.curWidth(), 20);
    }
    
    boolean DrawImagetest(Tester t) {
        WorldCanvas c = new WorldCanvas(400, 300);
        return t.checkExpect(
                c.show() &&
                c.drawImage(exampleComplex.drawMobile(new Posn(100, 100))), true);
    }
}