package com.example.fruitmastermind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;


import com.example.fruitmastermind.GameClasses.Clue;
import com.example.fruitmastermind.GameClasses.Fruit;
import com.example.fruitmastermind.GameClasses.FruitArray;
import com.example.fruitmastermind.GameClasses.ResultClues;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    TextView decountTries;
    int count = 10;

    FruitArray gBoard = new FruitArray();

    //@SuppressLint("WrongViewCast")
    List<Drawable[]> paramForCycle = new ArrayList<Drawable[]>();
    int posVH = 0;
    RecyclerView listTries;
    CustomAdapter myAdapter = new CustomAdapter(paramForCycle);

    Fruit fruit1 = gBoard.getBaseFruitArray()[0];
    Fruit fruit2 = gBoard.getBaseFruitArray()[1];
    Fruit fruit3 = gBoard.getBaseFruitArray()[2];
    Fruit fruit4 =gBoard.getBaseFruitArray()[3];

    Fruit arrayTestFruit [] = {fruit1, fruit2, fruit3, fruit4};
    Fruit[] userChoice = new Fruit[4];

    Fruit[] myCombo = gBoard.generateFruitBoard();

    public ImageButton[] bArray = new ImageButton[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        listTries = findViewById(R.id.layout_listOfTries);
        listTries.setAdapter(myAdapter);
        RecyclerView.LayoutManager myManager = new LinearLayoutManager(GameActivity.this);
        listTries.setLayoutManager(myManager);

        ImageButton b1 = (ImageButton) findViewById(R.id.Fruit1);
        ImageButton b2 = (ImageButton) findViewById(R.id.Fruit2);
        ImageButton b3 = (ImageButton) findViewById(R.id.Fruit3);
        ImageButton b4 = (ImageButton) findViewById(R.id.Fruit4);
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



        Button validate = (Button)findViewById(R.id.buttonValidate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("message","step 1");
                Drawable[] arrayToAdd = new Drawable[4];
                Log.v("message","step 2");

                for (int i = 0; i < 4; i++){
                    Log.v("message","step 3");

                    int imageResource = getResources().getIdentifier(userChoice[i].getImg(), null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    arrayToAdd[i]=res;
                    Log.v("message","step 4");
                    Log.v("tostring", res.toString());

                }
                Log.v("message","step 5");

                paramForCycle.add(arrayToAdd);
                myAdapter.notifyDataSetChanged();
                Log.v("message","step 6");


                //CustomAdapter.ViewHolder vh =  myAdapter.onCreateViewHolder(GameActivity.this.listTries, R.id.layout_listOfTries);
                //myAdapter.onBindViewHolder(vh,posVH);
                posVH+=1;

                //myManager.addView(vh.getImgView());


                if(count > 0) {
                    ResultClues rC = new ResultClues();
                    Clue[] resultArray = rC.checkUserAnswer(myCombo,userChoice);
                    boolean checkwin = true;
                    for (int i=0; i < resultArray.length; i++){
                        if (resultArray[i] != Clue.PERFECT){
                            checkwin = false;
                        }
                    }
                    if(checkwin){
                        loser("You win !","Although it's not a very difficult game you know...");
                    }
                        else{
                            //add recycler

                            //ligne d'entrées user + cluemachin carré
                        decountTries = (TextView) findViewById(R.id.attemptsLeft);
                        count--;
                        decountTries.setText(String.valueOf(count));}
                } else {
                    loser("You're a loser","But that's ok. It's just a game, you know...");
                }
            }
        });

        //Recylcer the revenge



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


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private final List<Drawable[]> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView[] imgView = new ImageView[4];
            ImageView imgView1;
            ImageView imgView2;
            ImageView imgView3;
            ImageView imgView4;

            public ViewHolder(View view) {
                super(view);

                Log.v("in adapter", "step 6.5 I guess");
                // Define click listener for the ViewHolder's View
                // this isn't called
                // what do

                imgView1 = (ImageView) view.findViewById(R.id.imgRecyclerFruit1);
                imgView2 = (ImageView) view.findViewById(R.id.imgRecyclerFruit2);
                imgView3 = (ImageView) view.findViewById(R.id.imgRecyclerFruit3);
                imgView4 = (ImageView) view.findViewById(R.id.imgRecyclerFruit4);

                imgView[0] = imgView1;
                imgView[1] = imgView2;
                imgView[2] = imgView3;
                imgView[3] = imgView4;
            }

            public ImageView[] getImgView() {
                return imgView;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public CustomAdapter(List<Drawable[]> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_list_of_tries, viewGroup, false);

            return new ViewHolder(view);
        }


        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element

            for (int i = 0; i < 4; i++){
                Log.v("in adapter", "step 7");
                viewHolder.getImgView()[i].setImageDrawable(paramForCycle.get(position)[i]);
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
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
        bArray[input].setImageDrawable(res);
        //bArray[input].setText("");
        userChoice[input] = gBoard.getBaseFruitArray(inputFruit);
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

    public void loser(String a, String b){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(a);
        builder.setMessage(b);        // add the buttons
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

    public void showPeelable(MenuItem item) {
        TableLayout peelableTable = findViewById(R.id.layout_clue1);
        peelableTable.setVisibility(View.VISIBLE);
        count -= 2;
    }

    public void showSeeds(MenuItem item) {
        TableLayout seedTable = findViewById(R.id.layout_clue2);
        seedTable.setVisibility(View.VISIBLE);
        count -= 3;
    }
}



