// Assignment 2
// partner1-Adach partner1-Kacper
// partner1-kadach
// partner2-Boni partner2-Chase
// partner2-cboni


interface ILoItems {
	int totalImageSize(int size);
	int textLength(int length);
	String getImageString(String imageString);
}

interface Item {
	int getImageSize();
	int getTextLength();
	String getImageString();
}

class MtLoItems implements ILoItems {
	
	MtLoItems() {
	}
	
	public int totalImageSize(int size) {
		return size;
	}
	
	public int textLength(int length) {
		return length;
	}
	public String getImageString(String imageString) {
		return imageString;
	}
	
}
 class ConsLoItems implements ILoItems {
	 
	 ConsLoItems(Item item, ILoItems rest) {
		 this.item = item;
		 this.rest = rest;
	 }
	 
	 public int totalImageSize(int size) {
		 return this.item.getImageSize() + this.rest.totalImageSize(size);
	 }
	 
	 public int textLength(int length) {
		 return this.item.getTextLength() + this.rest.textLength(length);
	 }
	 
	 public String getImageString(String imageString) {
		 String newString = this.item.getImageString();
		 if(newString.length() > 0) {
			 imageString = imageString + ', ' + newString;
		 }
		 return this.rest.getImageString(imageString);
	 }
 }
 
 class Text implements Item {
	 
	 Text(String contents) {
		 this.contents = contents;
	 }
	 
	 public int getImageSize() {
		 return 0;
	 }
	 
	 public int getTextLength() {
		 return this.contents.length();
	 }
	 
	 public String getImageString() {
		 return '';
	 }
 }
 
 class Image implements Item {
	 
	 Image(String fileName, int size, String fileType) {
		 this.fileName = fileName;
		 this.size = size;
		 this.fileType = fileType;
	 }
	 
	 public int getImageSize() {
		 return this.size;
	 }
	 
	 public int getTextLength() {
		 return this.fileName.length() + this.fileType.length();
	 }
	 
	 public String getImageString() {
		 return this.fileName + '.' + this.fileType;
	 }
 }
 
 class Link implements Item {
	 
	 Link(String name, WebPage page) {
		 this.name = name;
		 this.page = page;
	 }
	 
	 public int getImageSize() {
		 return this.page.getImageSize();
	 }
	 
	 public int getTextLength() {
		 return this.name.length() + this.page.getTextLength();
	 }
	 
	 public String getImageString() {
		 return '';
	 }
 }
 
 class WebPage {
	 
	 WebPage(String url, String title, ILoItems items) {
		 this.url = url;
		 this.title = title;
		 this.items = items;
	 }
	 
	 public int totalImageSize() {
		 return this.items.totalImageSize();
	 }
	 
	 public int textLength() {
		 return this.title.length() + this.items.textLength();
	 }
	 
	 public String images() {
		 return this.items.getImageString('');
	 }
 }
 
//Describe (in English, or in a diagram, or in code...) 
//the contents of a web site that has at least one text, 
//two images, three pages, and four links.

//A website with one text, two images, three pages and four links
//would have to have  three WebPages each with a String url, a String
//title and a list of items. The Lists would contain the text, two 
//images and four links.
 
 class ExamplesWebPage {
	 
	 // Example of page described
	 Item text = new Text('This is the contents');
	 Item imageOne = new Image('samplePictureOne', 10, '.png');
	 Item imageTwo = new Image('samplePictureTwo', 15, '.jpg');
	 ILoItems mtList = new MtLoItems();
	 ILoItems listA = new ConsLoItems(text, mtList);
	 ILoItems listB = new ConsLoItems(imageOne, listA);
	 ILoItems listC = new ConsLoItems(imageTwo, listB);
	 WebPage pageOne = new WebPage('www.pageOne.com', 'Page One', listC);
	 Item linkOne = new Link('Link One!', pageOne);
	 ILoItems listD = new ConsLoItems(linkOne, listC);
	 WebPage pageTwo = new WebPage('www.pageTwo.com', 'Page Two', listD);
	 Item linkTwo = new Link('Link Two!', pageTwo);
	 ILoItems listE = new ConsLoItems(linkTwo, listC);
	 WebPage pageThree = new WebPage('www.pageThree.com', 'Page Three', listE);
	 Item linkThree = new Link('Link Three!', pageThree);
	 ILoItems listF = new ConsLoItems(linkThree, listC);
	 ILoItems listG = new ConsLoItems(linkOne, listF);
	 WebPage pageFour = new WebPage('www.pageFour.com', 'Page Four', listG);
	 
	 // Fundies II, HtDP, OOD
	 Item textHSH = new Text('Home sweet home');
	 Item imageLab = new Image("wvh-lab", 400, "png");
	 Item textStaff = new Text("The staff");
	 Item imageProfs = new Image("profs", 240, "jpeg");
	 Item textHtDP = new Text('How to Design Programs');
	 Item imageBook = new Image("htdp", 4300, "tiff");
	 Item textStay = new Text("Stay classy, Java");
	 ILoItems list1 = new ConsLoItems(textHtDP, mtList);
	 ILoItems list2 = new ConsLoItems(imageBook, list1);
	 WebPage HtDP = new WebPage("htdp.org", "HtDP", list2);
	 WebPage OOD = new WebPage("ccs.neu.edu/OOD", "OOD", list3);
	 Item linkALookBack = new Link("A Look Back", HtDP);
	 Item linkALookAhead = new Link("A Look Ahead", OOD);
	 Item linkBack = new Link("htdp.org", HtDP);
	 ILoItems list3 = new ConsLoItems(textStay, mtList);
	 ILoItems list4 = new ConsLoItems(linkBack, list3);
	 ILoItems listOne = new ConsLoItems(textHSH, mtList);
	 ILoItems listTwo = new ConsLoItems(imageLab, listOne);
	 ILoItems listThree = new ConsLoItems(textStaff, listTwo);
	 ILoItems listFour = new ConsLoItems(imageProfs, listThree);
	 ILoItems listFive = new ConsLoItems(linkALookBack, listFour);
	 ILoItems listSix = new ConsLoItems(linkALookAhead, listFive);
	 WebPage fundiesWP = new Webpage("css.neu.edu/Fundies2", "Fundies II", listSix);
	 
	 fundiesWP.totalImageSize();
	 fundiesWP.textLength();
	 fundiesWP.images();
	 
	 // htdp.tiff appears twice because during recursion whenever a link is encountered,
	 // the method goes to the linked webpage and runs the method on that page as well.
	 // htdp.tiff is linked on two pages so it gets run twice on that page resulting in
	 // two entries.
	 
 }
 