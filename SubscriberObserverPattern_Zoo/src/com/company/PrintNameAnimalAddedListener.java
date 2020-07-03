package com.company;

public class PrintNameAnimalAddedListener implements AnimalAddedListener {
    @Override
    public void onAnimalAdded(Animal animal) {
        System.out.println("Added a new animal with name '" + animal.getName() + "'.");
    }
}