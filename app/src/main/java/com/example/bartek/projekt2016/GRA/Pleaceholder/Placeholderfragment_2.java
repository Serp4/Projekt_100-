package com.example.bartek.projekt2016.GRA.Pleaceholder;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bartek.projekt2016.GRA.Baza;
import com.example.bartek.projekt2016.GRA.GraActivity;
import com.example.bartek.projekt2016.GRA.GridAdapter;
import com.example.bartek.projekt2016.R;

import java.util.ArrayList;


/**
 * Created by Bartek on 29.12.2016.
 */

public class Placeholderfragment_2 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "Section ";
    private static GridView gridView_buttom;
    private static ArrayList<Button> buttons_view;
    private EditText editText;
    private  Baza zestaw;
    private  String text;
    public Placeholderfragment_2() {
    }

    public static Placeholderfragment_2 newInstance(int sectionNumber,int number) {
        Placeholderfragment_2 fragment = new Placeholderfragment_2();
        Bundle args = new Bundle();
        args.putInt("number", number);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public static Placeholderfragment_2 newInstance(int sectionNumber) {
        Placeholderfragment_2 fragment = new Placeholderfragment_2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View answer = inflater.inflate(R.layout.fragment_odpowiedzi, container, false);
        Baza baza=new Baza();
        zestaw=baza.getZestaw().get(getArguments().getInt("number"));
        buttons_view = new ArrayList<Button>();
        gridView_buttom = (GridView) answer.findViewById(R.id.button_viev);
        setViewResponse(buttons_view,zestaw);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        editText=(EditText) getActivity().findViewById(R.id.edit);
        gridView_buttom.setAdapter(new GridAdapter(buttons_view));
        final Button button=(Button) getActivity().findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                text=editText.getText().toString();
                String win="GRATULACJE\n  10 punktow =)";

                if (text.equalsIgnoreCase(zestaw.getOdp_1().toString())==true)
                {
                    buttons_view.get(0).setText(zestaw.getOdp_1());
                    editText.setText("");
                    showToast(win);
                }
                else if (text.equalsIgnoreCase(zestaw.getOdp_2())==true)
                {
                    buttons_view.get(1).setText(zestaw.getOdp_2());
                    editText.setText("");
                    showToast(win);
                }
                else if (text.equalsIgnoreCase(zestaw.getOdp_3())==true)
                {
                    buttons_view.get(2).setText(zestaw.getOdp_3());
                    editText.setText("");
                    showToast(win);
                }
                else if (text.equalsIgnoreCase(zestaw.getOdp_4())==true)
                {
                    buttons_view.get(4).setText(zestaw.getOdp_4());
                    editText.setText("");
                    showToast(win);
                }
                else
                {
                    showToast("      ->  "+text+"  <-\n to błedne hasło =(");
                }

            }
        });
        return answer;
    }
    public void  setViewResponse(ArrayList<Button> mButtons,final Baza base)
    {

        Button cb = null;
        for (int i = 0; i < base.getOdp(); i++) {
            cb = new Button(getActivity());
            cb.setText("" );
            cb.setId(i);
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button= (Button) view;
                    switch (view.getId()) {
                        case 0:
                            response(button,base.getOdp_1());
                            break;
                        case 1:
                            response(button,base.getOdp_2());
                            break;
                        case 2:
                            response(button,base.getOdp_3());
                            break;
                        case 3:
                            response(button,base.getOdp_4());
                            break;
                    }
                }
            });
            mButtons.add(cb);
        }
    }

    public void response(final Button mbutton, final String force) {
        String ok = mbutton.getText().toString().replace("_", "");
        ok = ok.replace(" ", "");
        char[] char_force = force.toCharArray();
        String displayWord = "";

        if (ok.length() < force.length()) {
            for (int i = 0; i < force.length(); i++) {
                if (i <= ok.length()) {
                    displayWord = displayWord + String.valueOf(char_force[i]);
                } else {
                    displayWord = displayWord + "_ ";
                }
            }{
                final Toast finalToast = Toast.makeText(getActivity(),  "SORRY -10 points =( ", Toast.LENGTH_SHORT);
                new CountDownTimer(800, 100) {
                    public void onTick(long millisUntilFinished) {
                        finalToast.show();
                    }

                    public void onFinish() {
                        finalToast.cancel();
                    }
                }.start();
            }

        }
        mbutton.setText(displayWord);
        if (displayWord.length() == force.length()) {
            displayWord = force.toString();
            final Toast finalToast = Toast.makeText(getActivity(),  "GREAT +10 points =) ", Toast.LENGTH_SHORT);
            new CountDownTimer(2000, 100) {
                public void onTick(long millisUntilFinished) {
                    finalToast.show();
                }

                public void onFinish() {
                    finalToast.cancel();
                }
            }.start();
            mbutton.setEnabled(false);
        }
    }
    public  void  check(String proposition)
    {
       if(proposition == zestaw.getOdp_1()){
           Button mbutton =buttons_view.get(0);
           mbutton.setText(proposition);
           final Toast finalToast = Toast.makeText(getActivity(),  "GREAT +10 points =) ", Toast.LENGTH_SHORT);
           new CountDownTimer(2000, 100) {
               public void onTick(long millisUntilFinished) {
                   finalToast.show();
               }

               public void onFinish() {
                   finalToast.cancel();
               }
           }.start();
           mbutton.setEnabled(false);

       }
       else if(proposition == zestaw.getOdp_2()){
           Button mbutton =buttons_view.get(1);
           mbutton.setText(proposition);
           final Toast finalToast = Toast.makeText(getActivity(),  "GREAT +10 points =) ", Toast.LENGTH_SHORT);
           new CountDownTimer(2000, 100) {
               public void onTick(long millisUntilFinished) {
                   finalToast.show();
               }

               public void onFinish() {
                   finalToast.cancel();
               }
           }.start();
           mbutton.setEnabled(false);
       }
       else if(proposition == zestaw.getOdp_3()){
           Button mbutton =buttons_view.get(2);
           mbutton.setText(proposition);
           final Toast finalToast = Toast.makeText(getActivity(),  "GREAT +10 points =) ", Toast.LENGTH_SHORT);
           new CountDownTimer(2000, 100) {
               public void onTick(long millisUntilFinished) {
                   finalToast.show();
               }

               public void onFinish() {
                   finalToast.cancel();
               }
           }.start();
           mbutton.setEnabled(false);
       }
       else if(proposition == zestaw.getOdp_4()){
           Button mbutton =buttons_view.get(3);
           mbutton.setText(proposition);
           final Toast finalToast = Toast.makeText(getActivity(),  "GREAT +10 points =) ", Toast.LENGTH_SHORT);
           new CountDownTimer(2000, 100) {
               public void onTick(long millisUntilFinished) {
                   finalToast.show();
               }

               public void onFinish() {
                   finalToast.cancel();
               }
           }.start();
           mbutton.setEnabled(false);
       }
        else {
           final Toast finalToast = Toast.makeText(getActivity(), "Błędna odpowiedz", Toast.LENGTH_SHORT);
           new CountDownTimer(800, 100) {
               public void onTick(long millisUntilFinished) {
                   finalToast.show();
               }

               public void onFinish() {
                   finalToast.cancel();
               }
           }.start();
       }

    }
    public void  showToast(String value)
    {
        final Toast finalToast = Toast.makeText(getActivity(),value, Toast.LENGTH_SHORT);
        new CountDownTimer(800, 100) {
            public void onTick(long millisUntilFinished) {
                finalToast.show();
            }

            public void onFinish() {
                finalToast.cancel();
            }
        }.start();
    }
}


