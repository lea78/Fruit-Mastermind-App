package com.example.fruitmastermind.GameClasses;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fruitmastermind.R;

public class PopUp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_of_fruits);

        Button p1 = (Button) findViewById(R.id.f1);
        Button p2 = (Button) findViewById(R.id.f2);
        Button p3 = (Button) findViewById(R.id.f3);
        Button p4 = (Button) findViewById(R.id.f4);
        Button p5 = (Button) findViewById(R.id.f5);
        Button p6 = (Button) findViewById(R.id.f6);
        Button p7 = (Button) findViewById(R.id.f7);
        Button p8 = (Button) findViewById(R.id.f8);
        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        p4.setOnClickListener(this);
        p5.setOnClickListener(this);
        p6.setOnClickListener(this);
        p7.setOnClickListener(this);
        p8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
