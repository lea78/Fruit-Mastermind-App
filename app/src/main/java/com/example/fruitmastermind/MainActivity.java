package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fruit fraise = new Fruit(false,false, "Fraise");
    Fruit banane = new Fruit(false,true, "Banana");
    Fruit framboise = new Fruit(false, false, "Framboise");
    Fruit kiwi = new Fruit(false, true, "Kiwi");
    Fruit orange = new Fruit(true, true, "Orange");
    Fruit prune = new Fruit(true, false, "Prune");
    Fruit raisin = new Fruit(true, false, "Raisin");
    Fruit citron = new Fruit(true, true, "Ctiron");

    Fruit fruitArray[] = {fraise,banane,framboise,kiwi,orange,prune,raisin,citron};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}