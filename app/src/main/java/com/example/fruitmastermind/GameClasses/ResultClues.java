package com.example.fruitmastermind.GameClasses;

import java.util.ArrayList;
import java.util.List;

public class ResultClues {

    Clue[] compiledResult;

    public Clue[] checkUserAnswer(Fruit[] gameArray, Fruit[] userArray){

        List<Clue> compiledResult = new ArrayList<Clue>();
        Fruit gArr[] = gameArray;
        Fruit uArr[] = userArray;
        boolean resultAdded = false;

        for (int i = 0; i < uArr.length; i++){
            if (uArr[i] == gArr[i]){
                Clue resClue = Clue.PERFECT;
                compiledResult.add(resClue);
                resultAdded = true;
            }
        }
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
