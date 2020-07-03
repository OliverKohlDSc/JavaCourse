package com.company;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<Animal> animals = new ArrayList<>();
    private List<AnimalAddedListener> listeners = new ArrayList<>();

    public void addAnimal (Animal animal) {
        this.animals.add(animal);
        this.notifyAnimalAddedListeners(animal);
    }

    public void deregisterAnimalAddedListener (AnimalAddedListener listener) {
        this.listeners.remove(listener);
    }

    public void registerAnimalAddedListener (AnimalAddedListener listener) {
        this.listeners.add(listener);
    }

    protected void notifyAnimalAddedListeners (Animal animal) {
        // Notify each of the listeners in the list (registered listeners)
        this.listeners.forEach(listener -> listener.onAnimalAdded(animal));
    }
}
