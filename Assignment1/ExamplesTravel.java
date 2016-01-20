// Assignment 1
// partner1-Adach partner1-Kacper
// partner1-kadach
// partner2-Boni partner2-Chase
// partner2-cboni

interface Habitation {
}

interface Transportation {
}

class Planet implements Habitation {
    String name;
    int population;
    int spaceports;
    
    Planet(String name, int population, int spaceports) {
        this.name = name;
        this.population = population;
        this.spaceports = spaceports;
    }
}

class SpaceStation implements Habitation {
    String name;
    int population;
    int transporterPads;
    
    SpaceStation(String name, int population, int transporterPads) {
        this.name = name;
        this.population = population;
        this.transporterPads = transporterPads;
    }
}

class Transporter implements Transportation {
    Habitation to;
    Habitation from;
    
    Transporter(Habitation to, Habitation from) {
        this.to = to;
        this.from = from;
    }
}

class Shuttle implements Transportation {
    Habitation to;
    Habitation from;
    int fuel;
    int capacity;
    
    Shuttle(Habitation to, Habitation from, int fuel, int capacity) {
        this.to = to;
        this.from = from;
        this.fuel = fuel;
        this.capacity = capacity;
    }
}

class SpaceElevator implements Transportation {
    Habitation to;
    Habitation from;
    int tonnage;
    
    SpaceElevator(Habitation to, Habitation from, int tonnage) {
        this.to = to;
        this.from = from;
        this.tonnage = tonnage;
    }
}

class ExamplesTravel {
    Habitation nausicant = new Planet("Nausicant", 6000000, 8);
    Habitation earth = new Planet("Earth", 9000000, 14);
    Habitation remus = new Planet("Remus", 18000000, 23);
    Habitation watcherGrid = new SpaceStation("WatcherGrid", 1, 0);
    Habitation deepSpace42 = new SpaceStation("Deep Space 42", 7, 8);
    Habitation alphaStation = new SpaceStation("Alpha Station", 2000, 4);
    
    Transportation shuttle1 = new Shuttle(nausicant, earth, 100, 250);
    Transportation transporter1 = new Transporter(deepSpace42, remus);
    Transportation elevator1 = new SpaceElevator(nausicant, watcherGrid, 500);
    Transportation shuttle2 = new Shuttle(remus, alphaStation, 130, 300);
    Transportation transporter2 = new Transporter(alphaStation, earth);
    Transportation elevator2 = new SpaceElevator(remus, deepSpace42, 800);
}