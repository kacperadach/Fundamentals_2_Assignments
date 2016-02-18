// Assignment 5
// partner1-Adach partner1-Kacper
// partner1-kadach

import tester.Tester;

interface IItem {
    boolean sameItem(IItem item);
}

interface ILoItem {
    boolean sameLoItem(ILoItem list);
    boolean sameMtLoItem(MtLoItem list);
    boolean sameConsLoItem(ConsLoItem list);
}

class MtLoItem implements ILoItem {
    
    MtLoItem() {
        // empty constructor
    }
    
    public boolean sameLoItem(ILoItem list) {
        return list.sameMtLoItem(this);
    }
    
    public boolean sameConsLoItem(ConsLoItem list) {
        return false;
    }
    
    public boolean sameMtLoItem(MtLoItem list) {
        return true;
    }
    
}

class ConsLoItem implements ILoItem {
    IItem item;
    ILoItem rest;
    
    ConsLoItem(IItem item, ILoItem rest) {
        this.item = item;
        this.rest = rest;
    }
    
    public boolean sameLoItem(ILoItem list) {
        return list.sameConsLoItem(this);
    }
    
    public boolean sameConsLoItem(ConsLoItem list) {
        return this.item.sameItem(list.item) && this.rest.sameLoItem(list.rest);
    }
    
    public boolean sameMtLoItem(MtLoItem list) {
        return false;
    }
}

class Text implements IItem {
    String contents;
    
    Text(String contents) {
        this.contents = contents;
    }
    
    public boolean sameText(Text item) {
        return this.contents == item.contents;
    }
    
    public boolean sameItem(IItem item) {
        if (item instanceof Text && !(item instanceof Header) && !(item instanceof Paragraph)) {
            return this.sameText((Text)item);
        }
        else {
            return false;
        }
    }
}

class Image implements IItem {
    String fileName;
    int size;
    String fileType;
    
    Image(String fileName, int size, String fileType) {
        this.fileName = fileName;
        this.size = size;
        this.fileType = fileType;
    }
    
    public boolean sameImage(Image item) {
        return this.fileName == item.fileName && 
                this.size == item.size && 
                this.fileType == item.fileType;
    }
    
    public boolean sameItem(IItem item) {
        if (item instanceof Image) {
            return this.sameImage((Image)item);
        }
        else {
            return false;
        }
    }
}

class Link implements IItem {
    String name;
    WebPage page;
    
    Link(String name, WebPage page) {
        this.name = name;
        this.page = page;
    }
    
    public boolean sameLink(Link item) {
        return this.name == item.name && this.page.sameWebPage(item.page);
    }
    
    public boolean sameItem(IItem item) {
        if (item instanceof Link) {
            return this.sameLink((Link)item);
        }
        else {
            return false;
        }
    }
}

class WebPage {
    String url;
    String title;
    ILoItem items;
    
    WebPage(String url, String title, ILoItem items) {
        this.url = url;
        this.title = title;
        this.items = items;
    }
    
    boolean sameWebPage(WebPage page) {
        return this.url == page.url && 
                this.title == page.title && 
                this.items.sameLoItem(page.items);
    }
}

class Paragraph extends Text {
    
    Paragraph(String contents) {
        super(contents);
    }
    
    public boolean sameParagraph(Paragraph item) {
        return this.contents == item.contents;
    }
    
    public boolean sameItem(IItem item) {
        if (item instanceof Paragraph) {
            return this.sameParagraph((Paragraph)item);
        }
        else {
            return false;
        }
    }
}

class Header extends Text {
    
    Header(String contents) {
        super(contents);
    }
    
    public boolean sameHeader(Header item) {
        return this.contents == item.contents;
    }
    
    public boolean sameItem(IItem item) {
        if (item instanceof Header) {
            return this.sameHeader((Header)item);
        }
        else {
            return false;
        }
    }
}

class ExamplesWebPage {
    
    IItem text1 = new Text("text one");
    IItem text2 = new Text("text two");
    
    IItem image1 = new Image("imageone", 10, ".png");
    IItem image2 = new Image("imagetwo", 20, ".jpeg");
    
    ILoItem mt = new MtLoItem();
    ILoItem list1 = new ConsLoItem(text1, mt);
    ILoItem list2 = new ConsLoItem(image1, list1);
    
    WebPage page1 = new WebPage("www.pageone.com", "pageone", list2);
    
    IItem link1 = new Link("click", page1);
    
    ILoItem list3 = new ConsLoItem(link1, list2);
    
    WebPage page2 = new WebPage("www.pagetwo.com", "pagetwo", list3);

    
    boolean testSameText(Tester t) {
        Text sampletext = new Text("test");
        return t.checkExpect(sampletext.sameText(new Text("test")), true);
    }
    
    boolean testSameImage(Tester t) {
        Image sampleimage = new Image("testimage", 10, ".png");
        return t.checkExpect(sampleimage.sameImage(new Image("testimage", 10, ".png")), true);
    }
    
    boolean testSameLink(Tester t) {
        Link samplelink = new Link("www.test.com", page1);
        return t.checkExpect(samplelink.sameLink(new Link("www.test.com", page1)), true);
    }
    
    boolean testSameWebPage(Tester t) {
        return t.checkExpect(page2.sameWebPage(page2), true) &&
                t.checkExpect(page1.sameWebPage(page2), false);
    }
    
    boolean testSameItem(Tester t) {
        return t.checkExpect(text1.sameItem(text1), true) &&
                t.checkExpect(text1.sameItem(image1), false) &&
                t.checkExpect(image2.sameItem(image2), true) &&
                t.checkExpect(image2.sameItem(link1), false) &&
                t.checkExpect(link1.sameItem(link1), true) &&
                t.checkExpect(link1.sameItem(text2), false);
    }
    
    boolean testSameLoItem(Tester t) {
        return t.checkExpect(list3.sameLoItem(list3), true) &&
                t.checkExpect(list2.sameLoItem(list3), false) &&
                t.checkExpect(mt.sameLoItem(list2), false);
    }
    
    boolean testSameMtLoItem(Tester t) {
        ConsLoItem cons1 = new ConsLoItem(text1, mt);
        MtLoItem mt1 = new MtLoItem();
        return t.checkExpect(cons1.sameMtLoItem(mt1), false) &&
                t.checkExpect(mt1.sameMtLoItem(mt1), true);
    }
    
    boolean testSameConsLoItem(Tester t) {
        ConsLoItem cons1 = new ConsLoItem(text1, mt);
        ConsLoItem cons2 =  new ConsLoItem(text2, list1);
        MtLoItem mt1 = new MtLoItem();
        return t.checkExpect(cons1.sameConsLoItem(cons1), true) &&
                t.checkExpect(cons2.sameConsLoItem(cons1), false) &&
                t.checkExpect(mt1.sameConsLoItem(cons1), false);
    }
    
    boolean testParagraphHeader(Tester t) {
        Paragraph p1 = new Paragraph("test");
        Header h1 = new Header("test");
        return t.checkExpect(p1.sameParagraph(p1), true) &&
                t.checkExpect(p1.sameItem(h1), false) &&
                t.checkExpect(p1.sameItem(p1), true) &&
                t.checkExpect(h1.sameHeader(h1), true) &&
                t.checkExpect(h1.sameItem(p1), false) &&
                t.checkExpect(h1.sameItem(h1), true);
    }
    
    
}