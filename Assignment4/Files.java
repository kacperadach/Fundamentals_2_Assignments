
// Assignment 4
// partner1-Adach partner1-Kacper
// partner1-kadach

import tester.*;

interface IFile {

    int size();

    int downloadTime(int rate);

    boolean sameOwner(AFile that);
}

abstract class AFile implements IFile {
    String name;
    String owner;

    AFile(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public abstract int size();

    public int downloadTime(int rate) {
        return (int) Math.ceil(this.size() / rate);
    }

    public boolean sameOwner(AFile that) {
        return this.owner == that.owner;
    }
}

class TextFile extends AFile {
    int length;

    TextFile(String name, String owner, int length) {
        super(name, owner);
        this.length = length;
    }

    public int size() {
        return this.length;
    }
}

class ImageFile extends AFile {
    int width; // in pixels
    int height; // in pixels

    ImageFile(String name, String owner, int width, int height) {
        super(name, owner);
        this.width = width;
        this.height = height;
    }

    public int size() {
        return this.width * this.height;
    }
}

class AudioFile extends AFile {
    int speed; // in bytes per second
    int length; // in seconds of recording time

    AudioFile(String name, String owner, int speed, int length) {
        super(name, owner);
        this.speed = speed;
        this.length = length;
    }

    public int size() {
        return this.speed * this.length;
    }
}

class ExamplesFiles {

    AFile text1 = new TextFile("English paper", "Maria", 1234);
    AFile picture = new ImageFile("Beach", "Maria", 400, 200);
    AFile song = new AudioFile("Help", "Pat", 200, 120);

    boolean testSize(Tester t) {
        return t.checkExpect(this.text1.size(), 1234) && 
               t.checkExpect(this.picture.size(), 80000) &&
               t.checkExpect(this.song.size(), 24000);
    }

    boolean testDownloadTime(Tester t) {
        return t.checkExpect(this.text1.downloadTime(10), 123) && 
               t.checkExpect(this.picture.downloadTime(10), 8000) &&    
               t.checkExpect(this.song.downloadTime(10), 2400);
    }

    boolean testSameOwner(Tester t) {
        return t.checkExpect(this.text1.sameOwner(this.picture), true)
                && t.checkExpect(this.picture.sameOwner(this.song), false)
                && t.checkExpect(this.song.sameOwner(this.text1), false);
    }
}