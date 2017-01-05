package com.example.bartek.projekt2016.GRA.Pleaceholder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.bartek.projekt2016.GRA.Baza;
import com.example.bartek.projekt2016.R;


/**
 * Zarządzanie zawartością freagmentu nr 1 ( PYTANIE)
 */

public class Placeholderfragment_1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "Section ";

    public Placeholderfragment_1() {
    }

    /**
     * Zwraca nową instancję tego fragmentu dla 1 numeru sekcji.
     */

    public static Placeholderfragment_1 newInstance(int sectionNumber, int number) {
        Placeholderfragment_1 fragment = new Placeholderfragment_1();
        Bundle args = new Bundle();
        args.putInt("number", number);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Tworzy widok fragmentu nr 1
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Baza zestaw;
        Baza baza = new Baza();
        /** Pobieranie wartości z poprzsedniej aktywności */
        zestaw = baza.getZestaw().get(getArguments().getInt("number"));
        /** Przypisanie obiektu widoku do zmiennej */
        View question = inflater.inflate(R.layout.fragment_pytania, container, false);
        /** Przypisanie obiektu widoku do zmiennej */
        TextView textView = (TextView) question.findViewById(R.id.pytanie_id);
        /** Ustanienie tekstu w polu  @param pytanie_id */
        textView.setText(zestaw.getPytanie());
        /** Zwracanie widoku */
        return question;
    }

}