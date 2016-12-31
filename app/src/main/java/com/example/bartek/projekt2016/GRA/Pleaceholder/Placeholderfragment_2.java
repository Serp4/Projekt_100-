package com.example.bartek.projekt2016.GRA.Pleaceholder;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


import com.example.bartek.projekt2016.GRA.Baza;
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
    private Baza zestaw;
    private String text;
    private ViewPager pager;

    public Placeholderfragment_2() {
    }

    public static Placeholderfragment_2 newInstance(int sectionNumber, int number) {
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
        Baza baza = new Baza();
        zestaw = baza.getZestaw().get(getArguments().getInt("number"));

        buttons_view = new ArrayList<Button>();
        gridView_buttom = (GridView) answer.findViewById(R.id.button_viev);
        setViewResponse(buttons_view, zestaw);

        pager = (ViewPager) getActivity().findViewById(R.id.container);
        editText = (EditText) getActivity().findViewById(R.id.edit);
        gridView_buttom.setAdapter(new GridAdapter(buttons_view));
        final Button button = (Button) getActivity().findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                text = editText.getText().toString();

                delay(buttons_view.get(0), zestaw.getOdp_1(), text);
                delay(buttons_view.get(1), zestaw.getOdp_2(), text);
                delay(buttons_view.get(2), zestaw.getOdp_3(), text);
                if (buttons_view.size() == 4) {
                    delay(buttons_view.get(3), zestaw.getOdp_4(), text);
                }
                checkWin(buttons_view);

            }
        });
        return answer;
    }

    public void setViewResponse(ArrayList<Button> mButtons, final Baza base) {

        Button cb = null;
        for (int i = 0; i < base.getOdp(); i++) {
            cb = new Button(getActivity());
            cb.setText("");
            cb.setId(i);
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    switch (view.getId()) {
                        case 0:
                            response(button, base.getOdp_1());
                            break;
                        case 1:
                            response(button, base.getOdp_2());
                            break;
                        case 2:
                            response(button, base.getOdp_3());
                            break;
                        case 3:
                            response(button, base.getOdp_4());
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
            }
            {
                final Toast finalToast = Toast.makeText(getActivity(), "SORRY -10 points =( ", Toast.LENGTH_SHORT);
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
            final Toast finalToast = Toast.makeText(getActivity(), "GREAT +10 points =) ", Toast.LENGTH_SHORT);
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

    public void showToast(String value) {
        final Toast finalToast = Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT);
        new CountDownTimer(800, 100) {
            public void onTick(long millisUntilFinished) {

                finalToast.show();
            }

            public void onFinish() {

                finalToast.cancel();
            }
        }.start();
    }

    public void delay(Button button, String getzestaw, String text_1) {

        String win = "GRATULACJE\n  10 punktow =)";

        if (text_1.equalsIgnoreCase(getzestaw) == true) {
            button.setText(getzestaw);
            button.setEnabled(false);
            editText.setText("");
            showToast(win);
            new CountDownTimer(1800, 200) {
                @Override
                public void onTick(long l) {
                    pager.setCurrentItem(1, true);
                }

                @Override
                public void onFinish() {
                    pager.setCurrentItem(0, true);
                }
            }.start();
        }


    }

    public void checkWin(ArrayList<Button> buttonlist) {

        int flag = 0;

        for (int i = flag; i < buttonlist.size(); i++) {
            if (buttonlist.get(i).isEnabled() == false) {
                flag = flag + 1;
            }
        }
        if (flag == buttonlist.size()) {

        }
    }
}



