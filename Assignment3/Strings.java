// Assignment 1
// partner1-Adach partner1-Kacper
// partner1-kadach
// partner2-Boni partner2-Chase
// partner2-cboni

import tester.*;

// to represent a list of Strings
interface ILoString {
    // combine all Strings in this list into one
	String combine();
	ILoString insert(String s);
	ILoString sort();
	Boolean isSorted();
	Boolean compareStrings(String s);
	ILoString interleave(ILoString list);
	ILoString merge(ILoString list);
	String getFirst();
	Boolean isEmpty();
	ILoString reverse();
	Boolean isDoubledList();
	Boolean isEqual(ILoString list);
	Boolean isPalindromeList();
}

// to represent an empty list of Strings
class MtLoString implements ILoString {
	MtLoString(){}
    
    // combine all Strings in this list into one
	public String combine() {
		return "";
	}

	public ILoString insert(String s) {
		return new ConsLoString(s, new MtLoString());
	}

	public ILoString sort() {
		return this;
	}

	public Boolean compareStrings(String s) {
		return true;
	}

	public Boolean isSorted() {
		return true;
	}

	public ILoString interleave(ILoString list) {
		return new MtLoString();
	}
	
	public ILoString merge(ILoString list) {
		return list;
	}
	
	public String getFirst() {
		return "";
	}
	
	public Boolean isEmpty() {
    	return true;
    }
	
	public ILoString reverse() {
		return null;
	}
	
	public Boolean isDoubledList() {
		return false;
	}
	
	public Boolean isEqual(ILoString list) {
	    return true;
	}
	
	public Boolean isPalindromeList() {
		return false;
	}
}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
    String first;
    ILoString rest;
    
    ConsLoString(String first, ILoString rest){
        this.first = first;
        this.rest = rest;  
    }
    
    public String combine(){
        return this.first.concat(this.rest.combine());
    }
    
    public ILoString insert(String s) {
    	int compare = this.first.toLowerCase().compareTo(s.toLowerCase());
    	if (compare < 0) {
    		return new ConsLoString(this.first, this.rest.insert(s));
    	} else {
    		return new ConsLoString(s, this);
    	}	
    }
    
    public ILoString sort() {
    	return this.rest.sort().insert(this.first);
    }
    
    public Boolean compareStrings(String s) {
    	int compare = this.first.toLowerCase().compareTo(s.toLowerCase());
    	if (compare < 0) {
    		return false; // this.first before s
    	} else {
    		return true; // s before this.first
    	}	
    }
    
    public Boolean isSorted() {
    	return this.rest.compareStrings(this.first) && this.rest.isSorted();
    }
    
    public ILoString interleave(ILoString list) {
    	return new ConsLoString(this.first, list.interleave(this.rest));
    }
    
    public ILoString merge(ILoString list) {
    	Boolean first = this.compareStrings(list.first); // cant do list.first if list is MtLoStrings
    	if (first) {
    		// list.first before this.first
    		return new ConsLoString(list.first, this.merge(list.rest));
    	} else {
    		// this.first before list.first
    		return new ConsLoString(this.first, list.merge(this.rest));
    	}
    }
    
    public String getFirst() {
    	return this.first;
    }
    
    public Boolean isEmpty() {
    	return false;
    }
    
    public ILoString reverse() {
    	if(this.rest.isEmpty()) {
    		return this;
    	} else {
    		ConsLoSting restList = this.rest;
    		this.rest = null;
    		ConsLoString reverseRest = restList.reverse();
    		restList.rest = this;
    		return reverseRest;
    	}
	}
    
    public Boolean isDoubledList() {
		return (this.first == this.rest.getFirst() || this.rest.isDoubledList());
	}
    
    public Boolean isEqual(ILoString list) {
        return list.first == this.first && this.rest.isEqual(list.rest);
    }
    
    public Boolean isPalindromeList() {
		ConsLoString palindrome = this.reverse();
		return this.isEqual(palindrome);
	}
}
    

// to represent examples for lists of strings
class ExamplesStrings{
    
    ILoString mary = new ConsLoString("Mary ",
                    new ConsLoString("had ",
                        new ConsLoString("a ",
                            new ConsLoString("little ",
                                new ConsLoString("lamb.", new MtLoString())))));
    
    // test the method combine for the lists of Strings
    boolean testCombine(Tester t){
        return 
            t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
    }
}