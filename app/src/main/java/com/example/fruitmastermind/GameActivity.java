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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables for the decount of tries

    TextView decountTries;
    int count = 10;

    //Instantiate a new object of fruits which stores all the fruits

    FruitArray gBoard = new FruitArray();

    //Instantiate an empty list for the recycler

    List<Drawable[]> paramForCycle = new ArrayList<Drawable[]>();
    int posVH = 0;
    RecyclerView listTries;
    CustomAdapter myAdapter = new CustomAdapter(paramForCycle);

    Fruit fruit1 = gBoard.getBaseFruitArray()[0];
    Fruit fruit2 = gBoard.getBaseFruitArray()[1];
    Fruit fruit3 = gBoard.getBaseFruitArray()[2];
    Fruit fruit4 =gBoard.getBaseFruitArray()[3];

    Fruit arrayTestFruit [] = {fruit1, fruit2, fruit3, fruit4};

    //The user choice

    Fruit[] userChoice = new Fruit[4];

    //The computer choice

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

        /*The choice of the computer is applied with the corresponding images,
        and clues (seeds or not, peelable or not) are generated and store in arrays*/

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

            //The corresponding images are set

            int imageResource = getResources().getIdentifier(myCombo[i].getImg(), null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            fruitImage[i].setImageDrawable(res);

            //The clues are set

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

        /*All the used of the button "Validate" when onClick is activated*/

        Button validate = (Button)findViewById(R.id.buttonValidate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Log.v("message","step 1");
                Drawable[] arrayToAdd = new Drawable[4];

                for (int i = 0; i < 4; i++){

                    //Stotre the choice of the user and get the images corresponding

                    int imageResource = getResources().getIdentifier(userChoice[i].getImg(), null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    arrayToAdd[i]=res;

                }

                paramForCycle.add(arrayToAdd);
   myAdapter.notifyDataSetChanged();
                Log.v("message","step 6");


                //CustomAdapter.ViewHolder vh =  myAdapter.onCreateViewHolder(GameActivity.this.listTries, R.id.layout_listOfTries);
                //myAdapter.onBindViewHolder(vh,posVH);
                posVH+=1;

                //myManager.addView(vh.getImgView());


                if(count > 0) {

                    //Check the difference between the user choice and the computer choice

                    ResultClues rC = new ResultClues();
                    Clue[] resultArray = rC.checkUserAnswer(myCombo,userChoice);
                    boolean checkwin = true;
                    for (int i=0; i < resultArray.length; i++){
                        if (resultArray[i] != Clue.PERFECT){
                            checkwin = false;
                        }
                    }

                    //If the user choice and the computer choice are the same

                    if(checkwin){
                        loser("You win !","Although it's not a very difficult game you know...");
                    }
                        else{
                            //add recycler

                            //ligne d'entrées user + cluemachin carré

                        //Decount the number of tries when the user submits an answer

                        decountTries = (TextView) findViewById(R.id.attemptsLeft);
                        count--;
                        decountTries.setText(String.valueOf(count));}
                } else {

                    //If the number of tries is under 0

                    loser("You're a loser","But that's ok. It's just a game, you know...");
                }

                resetChoiceButton();

            }
        });

        //Reset the image when click on reset button

        Button reset = (Button)findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChoiceButton();
            }
        });


        //Recylcer the revenge
    }


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private final List<Drawable[]> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView[] imgView = new ImageView[4];
            private final LinearLayout posFruit = new LinearLayout(GameActivity.this);
            ImageView imgView1;
            ImageView imgView2;
            ImageView imgView3;
            ImageView imgView4;
            public TextView T1;
            public TextView T2;
            public TextView T3;
            public TextView T4;

            public ViewHolder(View view) {
                super(view);

                //Log.v("in adapter", "step 6.5 I guess");
                // Define click listener for the ViewHolder's View
                // this isn't called
                // what do

                imgView1 = (ImageView) view.findViewById(R.id.imgRecyclerFruit1);
                imgView2 = (ImageView) view.findViewById(R.id.imgRecyclerFruit2);
                imgView3 = (ImageView) view.findViewById(R.id.imgRecyclerFruit3);
                imgView4 = (ImageView) view.findViewById(R.id.imgRecyclerFruit4);

                T1 = (TextView) view.findViewById(R.id.firstPositionTextView);
                T2 = (TextView) view.findViewById(R.id.secondPositionTextView);
                T3 = (TextView) view.findViewById(R.id.thirdPositionTextView);
                T4 = (TextView) view.findViewById(R.id.fourthPositionTextView);

                imgView[0] = imgView1;
                imgView[1] = imgView2;
                imgView[2] = imgView3;
                imgView[3] = imgView4;
            }

            public ImageView[] getImgView() {
                return imgView;
            }


            public LinearLayout getPosFruit(){
                return  posFruit;
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



            TextView[] tView = new TextView[4];
            tView[0] = viewHolder.T1;
            tView[1] = viewHolder.T2;
            tView[2] = viewHolder.T3;
            tView[3] = viewHolder.T4;


            ResultClues rC2 = new ResultClues();
            Clue[] resultArray2 = rC2.checkUserAnswer(myCombo,userChoice);
            //Log.v("test",resultArray2.toString());

            for (int i = 0; i < resultArray2.length; i++){
                Log.v("test","im inside");
                Log.v("value", resultArray2[i].clueValue);
                switch (resultArray2[i].clueValue)
                {
                    case "Perfect":
                        tView[i].setText("V");
                        Log.v("result","V");
                        break;
                    case "Good":
                        tView[i].setText("O");
                        Log.v("result","O");
                        break;
                    case "Wrong":
                        tView[i].setText("X");
                        Log.v("result","X");
                        break;
                }
            }

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


    //Method to go to the main menu

    public void goHome (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Method to customize the header menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header, menu);

        return true;
    }

    //All the fruits the user can choose to put it in the emplacement

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

    //The emplacement to pick the user choice

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

    //Go to the menu

    private void goToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //When the game is end

    public void loser(String a, String b){
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(a);
        builder.setMessage(b);

        // add the buttons
        //restart a new game
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(getIntent());
            }
        });

        //Go to the main menu
        builder.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToMainMenu();

            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Decount of 2 when the user show the clue

    public void showPeelable(MenuItem item) {
        TableLayout peelableTable = findViewById(R.id.layout_clue2);
        peelableTable.setVisibility(View.VISIBLE);
        count -= 2;
    }

    //Decount of 3 when the user show the clue

    public void showSeeds(MenuItem item) {
        TableLayout seedTable = findViewById(R.id.layout_clue1);
        seedTable.setVisibility(View.VISIBLE);
        count -= 3;
    }

    //Set question mark on button images

    public void resetChoiceButton(){
        ImageButton btn1 = findViewById(R.id.Fruit1);
        btn1.setImageResource(R.drawable.question_color);
        ImageButton btn2 = findViewById(R.id.Fruit2);
        btn2.setImageResource(R.drawable.question_color);
        ImageButton btn3 = findViewById(R.id.Fruit3);
        btn3.setImageResource(R.drawable.question_color);
        ImageButton btn4 = findViewById(R.id.Fruit4);
        btn4.setImageResource(R.drawable.question_color);
    }
}



