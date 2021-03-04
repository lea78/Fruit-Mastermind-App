package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FruitArray gBoard = new FruitArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fruit[] myCombo = gBoard.generateFruitBoard();
        for (int i = 0; i < myCombo.length; i++){
            Log.v("fruit name",myCombo[i].getName());
        }
    }
}