package com.example.fruitmastermind.GameClasses;

public class Fruit {

    boolean seeds ;
    boolean peel;
    String name;
    String img;

    public Fruit(boolean seeds, boolean peel, String name, String img) {
        this.seeds = seeds;
        this.peel = peel;
        this.name = name;
        this.img = img;
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

    public String getImg() { return img; }
}
