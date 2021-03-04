package com.example.fruitmastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.fruitmastermind.GameClasses.Fruit;
import com.example.fruitmastermind.GameClasses.FruitArray;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    FruitArray gBoard = new FruitArray();


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


        Fruit[] myCombo = gBoard.generateFruitBoard();

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

        Button showFruits = (Button)findViewById(R.id.Fruit1);
        showFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardFruits();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header, menu);

        return true;
    }

    public void keyboardFruits(){
        
    }

}