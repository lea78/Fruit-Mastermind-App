package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toolbar;


import com.example.fruitmastermind.GameClasses.Fruit;
import com.example.fruitmastermind.GameClasses.FruitArray;

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


    // Start GameActivity

    public void startGame (View view){

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    // Quit Game

    public void quitGame (View view){

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header, menu);

        return true;
    }


}