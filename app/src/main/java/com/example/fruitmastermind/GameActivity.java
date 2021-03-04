package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

    FruitArray gBoard = new FruitArray();



    Fruit[] userChoice = new Fruit[4];




    Fruit[] myCombo = gBoard.generateFruitBoard();

    Button p1;



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


        Button p2 = (Button) findViewById(R.id.f2);
        Button p3 = (Button) findViewById(R.id.f3);
        Button p4 = (Button) findViewById(R.id.f4);
        Button p5 = (Button) findViewById(R.id.f5);
        Button p6 = (Button) findViewById(R.id.f6);
        Button p7 = (Button) findViewById(R.id.f7);
        Button p8 = (Button) findViewById(R.id.f8);

        /*p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        p4.setOnClickListener(this);
        p5.setOnClickListener(this);
        p6.setOnClickListener(this);
        p7.setOnClickListener(this);
        p8.setOnClickListener(this);*/



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
        //final AlertDialog MyDialog = new AlertDialog(GameActivity.this);
        //AlertDialog MyDialog = new AlertDialog(GameActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        //builder.setView(inflater.inflate(R.layout.choose_of_fruits, null));
        builder.setTitle("Pick a fruit");
        builder.setItems(R.array.fruitArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        enterInUserSub(which,arrIndex);
                    }
                });

        AlertDialog alert1 = builder.create();
        alert1.show();
        //alert1.setContentView(R.layout.choose_of_fruits);
        //alert1.setButton();
        //alert1.show();

        /*MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.choose_of_fruits);
        MyDialog.show();
        public void onClick(MyDialog, p1){

        }

        //p1.setOnClickListener(this);*/




    }


    //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



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
            /*case R.id.f1:
                enterInUserSub(0,arrIndex);
                break;
            case R.id.f2:
                enterInUserSub(1,arrIndex);
                break;
            case R.id.f3:
                enterInUserSub(2,arrIndex);
                break;
            case R.id.f4:
                enterInUserSub(3,arrIndex);
                break;
            case R.id.f5:
                enterInUserSub(4,arrIndex);
                break;
            case R.id.f6:
                enterInUserSub(5,arrIndex);
                break;
            case R.id.f7:
                enterInUserSub(6,arrIndex);
                break;
            case R.id.f8:
                enterInUserSub(7,arrIndex);
                break;*/
        }
    }
}