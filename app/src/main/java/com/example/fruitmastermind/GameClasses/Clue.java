package com.example.fruitmastermind.GameClasses;

public enum Clue {
    PERFECT("Perfect"),
    GOOD("Good"),
    WRONG("Wrong");

    public String clueValue;

    private Clue(String clueValue){this.clueValue = clueValue; }
}
