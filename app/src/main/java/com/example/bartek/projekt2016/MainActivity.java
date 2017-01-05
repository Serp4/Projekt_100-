package com.example.bartek.projekt2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bartek.projekt2016.GRA.Zestaw;
import com.example.bartek.projekt2016.Punkty.ZbierajActivity;


/**
 * Klasa aktywność głowna uruchamiana domyślnie  przy starcie pozwalająca uruchamiać inne aktyności
 */
public class MainActivity extends AppCompatActivity {


    /**
     * Tworzy widok aktyności
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Metoda onClick dla Buttonu nr 1
     */
    public void on_graj(View v) {
        Intent intent = new Intent(this, Zestaw.class);
        startActivity(intent);
    }


    /**
     * Metoda onClick dla Buttonu nr 2
     */
    public void on_zbieraj(View v) {
        Intent intent = new Intent(this, ZbierajActivity.class);
        startActivity(intent);
    }

    /**
     * Metoda onClick dla Buttonu nr 3
     */
    public void on_instrukcja(View v) {
        Intent intent = new Intent(this, InstrukcjaActivity.class);
        startActivity(intent);
    }


}