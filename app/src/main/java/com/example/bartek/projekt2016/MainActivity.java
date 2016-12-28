package com.example.bartek.projekt2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bartek.projekt2016.GRA.GraActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void on_graj(View v) {
        Intent intent = new Intent(this, GraActivity.class);
        startActivity(intent);
    }

    /*
    public void on_zbieraj(View v) {
        Intent intent = new Intent(this, ZbierajActivity.class);
        startActivity(intent);
    }

    public void on_tworca(View v) {
        Intent intent = new Intent(this, TworcaActivity.class);
        startActivity(intent);
    }

    public void on_instrukcja(View v) {
        Intent intent = new Intent(this, InstrukcjaActivity.class);
        startActivity(intent);
    }*/
}