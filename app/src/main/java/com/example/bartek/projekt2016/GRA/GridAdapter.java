package com.example.bartek.projekt2016.GRA;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Klasa dziedzicząca po klasie BaseAdapter odpowiedzialna za budowe customowego widoku gridView [klawiatura querty, przyciski podpowiedzi
 */

public class GridAdapter extends BaseAdapter {
    /**
     * Lista obiektów typu Button
     */
    private ArrayList<Button> mButtons = null;


    public GridAdapter(ArrayList<Button> b) {
        mButtons = b;
    }

    /**
     * Zwraca wielkość (długośc) listy
     */
    @Override
    public int getCount() {
        return mButtons.size();
    }

    /**
     * Pobiera objekty typu Buton z listy on podanym indeksie
     */
    @Override
    public Object getItem(int position) {
        return (Object) mButtons.get(position);
    }

    /**
     * Zwraca pozycje elementu
     */
    @Override
    public long getItemId(int position) {
        // in our case position and id are synonymous
        return position;
    }

    /**
     * Pobiera pozycje  widok i zwraca w to miejsce elementy typu Button jeżeli w danym miejscu jest puste pole
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) convertView;
        }
        return button;
    }
}