package com.example.fruitmastermind;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.fruitmastermind.GameClasses.Fruit;
import com.example.fruitmastermind.GameClasses.FruitArray;
import com.example.fruitmastermind.GameClasses.UserArray;

import java.io.BufferedReader;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    TextView decountTries;
    int count = 10;

    FruitArray gBoard = new FruitArray();

    Fruit[] userChoice = new Fruit[4];

    Fruit[] myCombo = gBoard.generateFruitBoard();

    public Button[] bArray = new Button[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button b1 = (Button) findViewById(R.id.Fruit1);
        Button b2 = (Button) findViewById(R.id.Fruit2);
        Button b3 = (Button) findViewById(R.id.Fruit3);
        Button b4 = (Button) findViewById(R.id.Fruit4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);


        bArray[0] = b1;
        bArray[1] = b2;
        bArray[2] = b3;
        bArray[3] = b4;

        LinearLayout resultsRoot = (LinearLayout) findViewById(R.id.layout_finalResult);
        ImageView fruit1 = resultsRoot.findViewById(R.id.imgFruit1);
        ImageView fruit2 = resultsRoot.findViewById(R.id.imgFruit2);
        ImageView fruit3 = resultsRoot.findViewById(R.id.imgFruit3);
        ImageView fruit4 = resultsRoot.findViewById(R.id.imgFruit4);
        ImageView[] fruitImage = {fruit1, fruit2, fruit3, fruit4};
        TextView tv1 = findViewById(R.id.clue1Indice1);
        TextView tv2 = findViewById(R.id.clue1Indice2);
        TextView tv3 = findViewById(R.id.clue1Indice3);
        TextView tv4 = findViewById(R.id.clue1Indice4);
        TextView[] fruitSeeds = {tv1, tv2, tv3, tv4};
        TextView tv5 = findViewById(R.id.clue2Indice1);
        TextView tv6 = findViewById(R.id.clue2Indice2);
        TextView tv7 = findViewById(R.id.clue2Indice3);
        TextView tv8 = findViewById(R.id.clue2Indice4);
        TextView[] fruitPeelable = {tv5, tv6, tv7, tv8};


        for (int i = 0; i < myCombo.length; i++){
            int imageResource = getResources().getIdentifier(myCombo[i].getImg(), null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            fruitImage[i].setImageDrawable(res);

            boolean seed = myCombo[i].isSeeds();
            if (seed){
                fruitSeeds[i].setText("Has seeds");
            }else{
                fruitSeeds[i].setText("No seeds");
            }

            boolean peelable = myCombo[i].isPeel();
            if (peelable){
                fruitPeelable[i].setText("Is peelable");
            }else{
                fruitPeelable[i].setText("Not peelable");
            }
        }

        /*Button showFruits = (Button)findViewById(R.id.Fruit1);
        showFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardFruits();
            }
        });


        Button validate = (Button)findViewById(R.id.buttonValidate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0) {
                    decountTries = (TextView) findViewById(R.id.attemptsLeft);
                    count--;
                    decountTries.setText(String.valueOf(count));
                } else {
                    looser();
                }
            }
        });
        });*/
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

    public void keyboardFruits(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Pick a fruit");
        builder.setItems(R.array.fruitArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        enterInUserSub(which,arrIndex);
                    }
                });

        AlertDialog alert1 = builder.create();
        alert1.show();



    }


    public void enterInUserSub(int inputFruit, int input){
        int imageResource = getResources().getIdentifier(gBoard.getBaseFruitArray(inputFruit).getImg(), null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        bArray[input].setBackgroundDrawable(res);
        bArray[input].setText("");
    }

    int arrIndex;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Fruit1:
                keyboardFruits();
                arrIndex = 0;
                break;
            case R.id.Fruit2:
                keyboardFruits();
                arrIndex = 1;
                break;
            case R.id.Fruit3:
                keyboardFruits();
                arrIndex = 2;
                break;
            case R.id.Fruit4:
                keyboardFruits();
                arrIndex = 3;
                break;
        }
    }

    private void goToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void looser(){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You're a loser");
        builder.setMessage("But that's ok. It's just a game, you know...");        // add the buttons
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToMainMenu();

            }
        });        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


