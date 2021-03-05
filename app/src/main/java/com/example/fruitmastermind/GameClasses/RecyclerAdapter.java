package com.example.fruitmastermind.GameClasses;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitmastermind.R;

import java.io.File;
import java.lang.reflect.Array;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyRecyclerViewHolder> {
    Context recyclerContext;
    UserArray imagesFruit[];
    Array imagesPositions[];
    Fruit[] test ;
    FruitArray fBoard = new FruitArray();
    Fruit fruit1 = fBoard.getBaseFruitArray()[0];
    Fruit fruit2 = fBoard.getBaseFruitArray()[1];
    Fruit fruit3 = fBoard.getBaseFruitArray()[2];
    Fruit fruit4 = fBoard.getBaseFruitArray()[3];

    ImageView fruitOfArray1;
    ImageView fruitOfArray2;
    ImageView fruitOfArray3;
    ImageView fruitOfArray4;

     //CONSTRUCTORS
    public RecyclerAdapter(Context myRecyclerContext, UserArray myImagesFruit[], Array myImagesPositions[]){
        this.recyclerContext = myRecyclerContext;
        this.imagesFruit = myImagesFruit;
        this.imagesPositions = myImagesPositions;
    }
     public RecyclerAdapter(Context myContext, Fruit myTest []){
        this.recyclerContext=myContext;
        this.test = myTest;
     }

     //METHODS
    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerContext);
        View viewInflater = inflater.inflate(R.layout.item_list_of_tries, parent, false);
        return new MyRecyclerViewHolder(viewInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {

        ImageView[] fruitOfArrayAll = {fruitOfArray1,fruitOfArray2,fruitOfArray3,fruitOfArray4};
        for(int i = 0; i <=test.length ; i++){
            //Drawable drawableOfFruit = Drawable.createFromPath();
            //fruitOfArrayAll[i].setImageDrawable(drawableOfFruit);
            Log.v("fruit", test[i].getImg());
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder{

        public MyRecyclerViewHolder (@NonNull View itemView) {
            super(itemView);
            fruitOfArray1 = itemView.findViewById(R.id.imgRecyclerFruit1);
            fruitOfArray2 = itemView.findViewById(R.id.imgRecyclerFruit2);
            fruitOfArray3 = itemView.findViewById(R.id.imgRecyclerFruit3);
            fruitOfArray4 = itemView.findViewById(R.id.imgRecyclerFruit4);
            Log.v("msg", "hey");
        }

    }
}
