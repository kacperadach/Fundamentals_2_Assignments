// Assignment 1
// partner1-Adach partner1-Kacper
// partner1-kadach
// partner2-Boni partner2-Chase
// partner2-cboni

interface ITaco {    
}

class EmptyShell implements ITaco {
    boolean softShell;
    
    EmptyShell(boolean softShell) {
        this.softShell = softShell;
    }
}

class Filled implements ITaco {
    ITaco taco;
    String filling;
    
    Filled(ITaco taco, String filling) {
        this.taco = taco;
        this.filling = filling;
    }
}

class ExamplesTaco {
    EmptyShell softShelled = new EmptyShell(true);
    EmptyShell hardShelled = new EmptyShell(false);
    
    Filled filled1 = new Filled(softShelled, "carnitas");
    Filled filled2 = new Filled(filled1, "salsa");
    Filled filled3 = new Filled(filled2, "lettuce");
    Filled order1 = new Filled(filled3, "cheddar cheese");
    
    Filled filled4 = new Filled(hardShelled, "veggies");
    Filled filled5 = new Filled(filled4, "guacamole");
    Filled order2 = new Filled(filled5, "sour cream");   
}