package com.example.fruitmastermind;

public enum Clue {
    PERFECT("Perfect"),
    GOOD("Good"),
    WRONG("Wrong");

    public String clueValue;

    private Clue(String clueValue){this.clueValue = clueValue; }
}
