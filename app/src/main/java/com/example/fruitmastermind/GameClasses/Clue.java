package com.example.fruitmastermind.GameClasses;

public enum Clue {

    /*The clues about the fruit the user have chosen, compare to what the computer has chosen.
    If the fruit exists and it's the right place, it's a "perfect".
    If the fruit exists but the place isn't right, it's a "good". If the fruit doesn't exist, it's a "wrong"*/

    PERFECT("Perfect"),
    GOOD("Good"),
    WRONG("Wrong");

    public String clueValue;

    private Clue(String clueValue){this.clueValue = clueValue; }
}
