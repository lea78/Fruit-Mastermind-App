package com.example.fruitmastermind;

public class UserArray {
    Fruit a;
    Fruit b;
    Fruit c;
    Fruit d;

    public Fruit[] UserArray(Fruit a, Fruit b, Fruit c, Fruit d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        Fruit newArray[] = {a,b,c,d};
        return newArray;
    }
}
