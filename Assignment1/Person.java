// Assignment 1
// partner1-Adach partner1-Kacper
// partner1-kadach
// partner2-Boni partner2-Chase
// partner2-cboni

class Person {
	String name;
	int yob;
	String state;
	boolean citizen;

	Person(String name, int yob, String state, boolean citizen) {
		this.name = name;
		this.yob = yob;
		this.state = state;
		this.citizen = citizen;
	}
}

class ExamplesPerson {
	Person jackie = new Person("Jackie Robinson", 1920, "NY", true);
	Person golda = new Person("Golda Meir", 1930, "MA", false);
}