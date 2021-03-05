package com.example.fruitmastermind.GameClasses;

import java.util.ArrayList;
import java.util.List;

public class ResultClues {

    //Store the result in an abject array "Clue"

    Clue[] compiledResult;

    //Method the verify the choice of the user and what the computer has chosen

    public Clue[] checkUserAnswer(Fruit[] gameArray, Fruit[] userArray){

        List<Clue> compiledResult = new ArrayList<Clue>();
        Fruit gArr[] = gameArray;
        Fruit uArr[] = userArray;
        boolean resultAdded = false;

        //Check if the answers are exactly the same on the same index

        for (int i = 0; i < uArr.length; i++){
            if (uArr[i] == gArr[i]){
                Clue resClue = Clue.PERFECT;
                compiledResult.add(resClue);
                resultAdded = true;
            }
        }

        //Check if the answers are the same but not on the same index

        if (!resultAdded){
            for (int i = 0; i < uArr.length; i++){
                for (int j = 0; j < gArr.length; j++){
                    if (uArr[i] == gArr[j]){
                        Clue resClue = Clue.GOOD;
                        compiledResult.add(resClue);
                        resultAdded = true;
                    }
                }
            }
        }

    for (int i = compiledResult.size(); i < uArr.length; i++){
        Clue resClue = Clue.WRONG;
        compiledResult.add(resClue);
    }


        Clue[] compiledArr = new Clue[4];
        compiledArr = compiledResult.toArray(compiledArr);
        return compiledArr;
    }

    public Clue[] getCompiledResult() {
        return compiledResult;
    }
}
