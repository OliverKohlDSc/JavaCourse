package com.company;

public class Main {

    public static void main(String[] args) {
	    Zoo zoo = new Zoo();

	    //zoo.registerAnimalAddedListener(animal -> System.out.println("Animal has been added: " + animal));
	    zoo.registerAnimalAddedListener(new PrintNameAnimalAddedListener());

	    zoo.addAnimal(new Animal("Dog"));
        zoo.addAnimal(new Animal("Cat"));
    }
}
