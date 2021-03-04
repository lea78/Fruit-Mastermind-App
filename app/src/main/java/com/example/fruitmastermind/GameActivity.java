package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.fruitmastermind.GameClasses.Fruit;
import com.example.fruitmastermind.GameClasses.FruitArray;
import com.example.fruitmastermind.GameClasses.RecyclerAdapter;
import com.example.fruitmastermind.GameClasses.UserArray;

public class GameActivity extends AppCompatActivity {

    FruitArray gBoard = new FruitArray();


    //@SuppressLint("WrongViewCast")
    int imagesTries[];
    RecyclerView listTries;
    Fruit fruit1 = gBoard.getBaseFruitArray()[0];
    Fruit fruit2 = gBoard.getBaseFruitArray()[1];
    Fruit fruit3 = gBoard.getBaseFruitArray()[2];
    Fruit fruit4 =gBoard.getBaseFruitArray()[3];

    Fruit arrayTestFruit [] = {fruit1, fruit2, fruit3, fruit4};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout resultsRoot = (LinearLayout) findViewById(R.id.layout_finalResult);
        ImageView fruit1 = resultsRoot.findViewById(R.id.imgFruit1);
        ImageView fruit2 = resultsRoot.findViewById(R.id.imgFruit2);
        ImageView fruit3 = resultsRoot.findViewById(R.id.imgFruit3);
        ImageView fruit4 = resultsRoot.findViewById(R.id.imgFruit4);
        ImageView[] fruitImage = {fruit1, fruit2, fruit3, fruit4};

        String kappa = "non";
        if (fruitImage[1] != null){
            kappa = "oui";
        }
        Log.v("lul", kappa);

        Fruit[] myCombo = gBoard.generateFruitBoard();
        //fruitImage = new ImageView[4];


        for (int i = 0; i < myCombo.length; i++){
            int imageResource = getResources().getIdentifier(myCombo[i].getImg(), null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            fruitImage[i].setImageDrawable(res);
            //fruitImage[i].setImageResource(R.drawable.bananas);
        }

        // RECYCLER VIEW
        listTries = findViewById(R.id.listOfTries);
        String test1 = "Fruitivore";
        RecyclerAdapter myAdapter = new RecyclerAdapter(this, arrayTestFruit);
        listTries.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header, menu);

        return true;
    }

}