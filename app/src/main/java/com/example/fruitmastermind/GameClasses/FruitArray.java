package com.example.fruitmastermind.GameClasses;

import java.util.Random;


public class FruitArray {


    Fruit fraise = new Fruit(false,false, "Fraise", "@drawable/strawberry");
    Fruit banane = new Fruit(false,true, "Banane", "@drawable/bananas");
    Fruit framboise = new Fruit(false, false, "Framboise", "@drawable/raspberry");
    Fruit kiwi = new Fruit(false, true, "Kiwi", "@drawable/kiwi");
    Fruit orange = new Fruit(true, true, "Orange", "@drawable/orange");
    Fruit prune = new Fruit(true, false, "Prune", "@drawable/plum");
    Fruit raisin = new Fruit(true, false, "Raisin", "@drawable/grapes");
    Fruit citron = new Fruit(true, true, "Citron", "@drawable/lemon");

    Fruit[] baseFruitArray = {fraise,banane,framboise,kiwi,orange,prune,raisin,citron};

    public Fruit getBaseFruitArray(int index) {
        return baseFruitArray[index];
    }

    Fruit[] newCombiBoard;


    public FruitArray() {
    }

    public Fruit[] getBaseFruitArray() {
        return baseFruitArray;
    }
    public Fruit[] generateFruitBoard(){
        newCombiBoard = new Fruit[4];
        for (int i = 0; i < newCombiBoard.length; i++){
            Random r = new Random();
            int rnd = r.nextInt(baseFruitArray.length);

            newCombiBoard[i] = baseFruitArray[rnd];
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
