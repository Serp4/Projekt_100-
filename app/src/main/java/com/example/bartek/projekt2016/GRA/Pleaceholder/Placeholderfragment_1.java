package com.example.bartek.projekt2016.GRA.Pleaceholder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartek.projekt2016.GRA.Baza;
import com.example.bartek.projekt2016.R;


/**
 * Created by Bartek on 29.12.2016.
 */

public class Placeholderfragment_1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "Section ";

    public Placeholderfragment_1() {
    }

    public static Placeholderfragment_1 newInstance(int sectionNumber, int number) {
        Placeholderfragment_1 fragment = new Placeholderfragment_1();
        Bundle args = new Bundle();
        args.putInt("number", number);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public static Placeholderfragment_1 newInstance(int sectionNumber) {
        Placeholderfragment_1 fragment = new Placeholderfragment_1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Baza zestaw;
        Baza baza = new Baza();
        zestaw = baza.getZestaw().get(getArguments().getInt("number"));

        View question = inflater.inflate(R.layout.fragment_pytania, container, false);
        TextView textView = (TextView) question.findViewById(R.id.pytanie_id);
        textView.setText(zestaw.getPytanie());
        return question;
    }

}