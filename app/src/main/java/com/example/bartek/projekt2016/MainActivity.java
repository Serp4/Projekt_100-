package com.example.bartek.projekt2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bartek.projekt2016.GRA.Zestaw;
import com.example.bartek.projekt2016.Punkty.ZbierajActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void on_zbieraj(View v) {
        Intent intent = new Intent(this, ZbierajActivity.class);
        startActivity(intent);
    }

    public void on_graj(View v) {
        Intent intent = new Intent(this, Zestaw.class);
        startActivity(intent);
    }


    public void on_instrukcja(View v) {
        Intent intent = new Intent(this, InstrukcjaActivity.class);
        startActivity(intent);
    }


}