package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.fruitmastermind.GameClasses.Fruit;
import com.example.fruitmastermind.GameClasses.FruitArray;
import com.example.fruitmastermind.GameClasses.UserArray;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    FruitArray gBoard = new FruitArray();
    Button b1;
    Button b2;
    Button b3;
    Button b4;



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

        b1 = (Button) findViewById(R.id.Fruit1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.Fruit2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.Fruit3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.Fruit4);
        b4.setOnClickListener(this);

        Fruit[] myCombo = gBoard.generateFruitBoard();


        for (int i = 0; i < myCombo.length; i++){
            int imageResource = getResources().getIdentifier(myCombo[i].getImg(), null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            fruitImage[i].setImageDrawable(res);
            //fruitImage[i].setImageResource(R.drawable.bananas);
        }


    }





    public void goHome (View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header, menu);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Fruit1:
                //do
                break;
            case R.id.Fruit2:
                //do
                break;
            case R.id.Fruit3:
                //do
                break;
            case R.id.Fruit4:
                //do
                break;
        }
    }
}