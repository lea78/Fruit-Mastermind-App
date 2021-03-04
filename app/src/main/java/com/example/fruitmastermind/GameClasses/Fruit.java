package com.example.fruitmastermind.GameClasses;

public class Fruit {

    boolean seeds ;
    boolean peel;
    String name;
    //add picture param

    public Fruit(boolean seeds, boolean peel, String name) {
        this.seeds = seeds;
        this.peel = peel;
        this.name = name;
    }

    public boolean isSeeds() {
        return seeds;
    }

    public boolean isPeel() {
        return peel;
    }

    public String getName() {
        return name;
    }
}
