package com.example.fruitmastermind.GameClasses;

import java.util.Random;


public class FruitArray {
    Fruit fraise = new Fruit(false,false, "Fraise");
    Fruit banane = new Fruit(false,true, "Banane");
    Fruit framboise = new Fruit(false, false, "Framboise");
    Fruit kiwi = new Fruit(false, true, "Kiwi");
    Fruit orange = new Fruit(true, true, "Orange");
    Fruit prune = new Fruit(true, false, "Prune");
    Fruit raisin = new Fruit(true, false, "Raisin");
    Fruit citron = new Fruit(true, true, "Citron");

    Fruit baseFruitArray[] = {fraise,banane,framboise,kiwi,orange,prune,raisin,citron};

    Fruit newCombiBoard[];


    public FruitArray() {
    }


    public Fruit[] generateFruitBoard(){
        newCombiBoard = new Fruit[4];
        for (int i = 0; i < newCombiBoard.length; i++){
            Random r = new Random();
            int rnd = r.nextInt(baseFruitArray.length);

            newCombiBoard[i] = baseFruitArray[rnd];
            //Log.v("nem", newCombiBoard[i].getName());
            for (int j = 0; j<newCombiBoard.length; j++){
                if (i != j && newCombiBoard[i] == newCombiBoard[j]){
                    i--;
                    break;
                }
            }
        }
        return newCombiBoard;
    }
}
