package com.example.bartek.projekt2016.GRA.Pleaceholder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.bartek.projekt2016.GRA.Baza;
import com.example.bartek.projekt2016.GRA.GridAdapter;
import com.example.bartek.projekt2016.GRA.Zestaw;
import com.example.bartek.projekt2016.R;


/**
 * Zarządzanie zawartością freagmentu nr 2 ( ODPOWIEDZI)
 */

public class Placeholderfragment_2 extends Fragment {

    /**
     * Argument sekcji (fragment)
     */
    private static final String ARG_SECTION_NUMBER = "Section ";
    /**
     * Widok składający się z 3 lub 4 buttonów
     */
    private static GridView gridView_buttom;
    /**
     * Lista z argumentem jako Button
     */
    private static ArrayList<Button> buttons_view;
    /**
     * Pole edycji przedstawienie znaków pobranych z klawiatury
     */
    private EditText editText;
    /**
     * Obiek klasy Baza  przechowywujący baze danych
     */
    private Baza zestaw;
    /**
     * Interfejs do uzyskiwania dostępu danych
     */
    SharedPreferences preferences;
    /**
     * Interfejs do modyfikoacji danych
     */
    SharedPreferences.Editor editor;
    private String text;
    private ViewPager pager;
    private Context context;
    /**
     * Wysyładnie danych pomierzy aktywnościami oraz ich uruchamianie
     */
    private Intent intent;
    /**
     * zmienna przechowywująca numer pytania
     */
    private int number;
    /**
     * Zmienna przechowywująca liczbę punktów podpowiedzi
     */
    private int points;
    /**
     * Pole ukazujące w aktywności liczbę punktów podpowiedzi
     */
    private TextView pointsView;

    public Placeholderfragment_2() {
    }

    /**
     * Zwraca nową instancję tego fragmentu dla 2 numeru sekcji.
     */
    public static Placeholderfragment_2 newInstance(int sectionNumber, int number) {
        Placeholderfragment_2 fragment = new Placeholderfragment_2();
        Bundle args = new Bundle();
        /** Pobranie wartości number z poprzedniej aktywności */
        args.putInt("number", number);
        /** Pobranie wartości sectionNumberz poprzedniej aktywności */
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        /** Podanie wartości z poprzedniej aktywności do fragmentu */
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Tworzy widok fragmentu nr 2
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View answer = inflater.inflate(R.layout.fragment_odpowiedzi, container, false);
        final Button ok = (Button) getActivity().findViewById(R.id.ok);
        context = (Context) getActivity();
        intent = new Intent(getActivity(), Zestaw.class);
        gridView_buttom = (GridView) answer.findViewById(R.id.button_viev);
        pager = (ViewPager) getActivity().findViewById(R.id.container);
        editText = (EditText) getActivity().findViewById(R.id.edit);
        pointsView = (TextView) getActivity().findViewById(R.id.points);
        number = getArguments().getInt("number");

        preferences = context.getSharedPreferences(getString(R.string.CODE), Context.MODE_PRIVATE);
        editor = preferences.edit();
        points = preferences.getInt(getString(R.string.POINTS), 100);
        pointsView.setText("" + points);

        Baza baza = new Baza();
        buttons_view = new ArrayList<Button>();
        zestaw = baza.getZestaw().get(number);
        setViewResponse(buttons_view, zestaw);
        gridView_buttom.setAdapter(new GridAdapter(buttons_view));
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                text = editText.getText().toString();
                if (text.equalsIgnoreCase(zestaw.getOdp_1()) == true) {
                    delay(buttons_view.get(0), zestaw.getOdp_1());
                } else if (text.equalsIgnoreCase(zestaw.getOdp_2()) == true) {
                    delay(buttons_view.get(1), zestaw.getOdp_2());
                } else if (text.equalsIgnoreCase(zestaw.getOdp_3()) == true) {
                    delay(buttons_view.get(2), zestaw.getOdp_3());
                } else if (buttons_view.size() == 4) {
                    if (text.equalsIgnoreCase(zestaw.getOdp_4()) == true) {
                        delay(buttons_view.get(3), zestaw.getOdp_4());
                    } else {
                        showToast("To hasło jest nieprawidłowe");
                        editText.setText("");
                    }
                } else {
                    showToast("To hasło jest nieprawidłowe");
                    editText.setText("");
                }
                checkWin(buttons_view);
            }
        });
        return answer;
    }

    /**
     * Metoda wypełniająca widok @param  gridView_buttom oraz ustawia funkcjonalność buttonów
     */
    public void setViewResponse(ArrayList<Button> mButtons, final Baza base) {
        Button cb = null;
        for (int i = 0; i < base.getOdp(); i++) {
            /** Utworzenie nowego buttona z argumentem jako dostęp do zasobów specyficznych dla aplikacji i klasy */
            cb = new Button(context);
            /** Nadanie textu*/
            cb.setText("");
            /** Nadanie id */
            cb.setId(i);
            /** Nadanie tła*/
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            /** Nadanie koloru czcionki*/
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            /** Nadanie nasłuchiwania na dany button */
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
                    checkWin(buttons_view);
                }
            });
            /** Dodanie buttona do listy*/
            mButtons.add(cb);
        }
    }

    /**
     * Metoda odpowiedzialna za uzyskiwanie podpowiedzi po naciśnięciu na wybrany element widoku
     */
    public void response(final Button mbutton, final String force) {
        /** Przypisanie do stringa wartości z danego buttona oraz zamiana "_" na "" */
        String ok = mbutton.getText().toString().replace("_", "");
        /** Usuniecie spacji z stringa */
        ok = ok.replace(" ", "");
       /** Zamiana stringa na tablice char-ów */
        char[] char_force = force.toCharArray();
        String displayWord = "";
        if (points >= 10) {
            points = points - 10;
            pointsView.setText("" + points);
            if (ok.length() < force.length()) {
                for (int i = 0; i < force.length(); i++) {
                    if (i <= ok.length()) {
                        displayWord = displayWord + String.valueOf(char_force[i]);
                    } else {
                        displayWord = displayWord + "_ ";
                    }
                }
               /** Komunikat */
                final Toast finalToast = Toast.makeText(getActivity(), "SORRY -10 points =( ", Toast.LENGTH_SHORT);
                /**Dodanie własnej długości wyświetlania komunikatu */
                new CountDownTimer(800, 100) {
                    public void onTick(long millisUntilFinished) {
                        finalToast.show();
                    }

                    public void onFinish() {
                        finalToast.cancel();
                    }
                }.start();
            }
            mbutton.setText(displayWord);
        } else {
            Toast.makeText(getActivity(), "Masz za mało punktów =(", Toast.LENGTH_SHORT).show();
        }
        if (displayWord.length() == force.length()) {
            points = points + 20;
            pointsView.setText("" + points);
            final Toast finalToast = Toast.makeText(getActivity(), "Gratulacje !\n+20 points =)", Toast.LENGTH_SHORT);
            new CountDownTimer(2000, 1000) {
                public void onTick(long millisUntilFinished) {
                    pager.setCurrentItem(1, true);
                    finalToast.show();
                    mbutton.setBackground(context.getResources().getDrawable(R.drawable.button_shape_response));
                    mbutton.setTextColor(Color.parseColor("#2f8cca"));
                }

                public void onFinish() {
                    finalToast.cancel();
                    pager.setCurrentItem(0, true);
                }
            }.start();
            mbutton.setEnabled(false);
        }
    }

    /**
     * Metoda pokazujące powiadomienie w dolnej części ekranu po wykonaniu określonej akcji
     */
    public void showToast(String value) {
        /**Komunikat */
        final Toast finalToast = Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT);
        /**Dodanie własnej długości wyświetlania komunikatu */
        new CountDownTimer(800, 100) {
            public void onTick(long millisUntilFinished) {
                finalToast.show();
            }

            public void onFinish() {
                finalToast.cancel();
            }
        }.start();
    }

    /**
     * Metoda odpowiedzialna za przechodzenia pomiędzy fragmentami aktywności oraz ukazywaniu wskazanej poprawnej odpowiedzi w widoku
     */
    public void delay(final Button button, final String getzestaw) {
        final String win = "   Gratulacje !\n +20 points =)";
        final String done = "To hasło już odgadnięto !";
        /**Jezeli button jest możliwy do klikniecia */
        if (button.isEnabled()) {
            /**Zablokuj click */
            button.setEnabled(false);
            /**Ustaw text pola edit text na pusty*/
            editText.setText("");
            showToast(win);
            /**Dodaj 20 puntków podpowiedzi */
            points = points + 20;
            /** Uaktualnij punkty w widoku */
            pointsView.setText("" + points);
            new CountDownTimer(2500, 1000) {
                @Override
                public void onTick(long l) {
                    pager.setCurrentItem(1, true);
                    int tmp = (int) l / 1000;
                    button.setBackground(context.getResources().getDrawable(R.drawable.button_shape_response));
                    button.setTextColor(Color.parseColor("#2f8cca"));
                    if (tmp == 1) {
                        button.setText(getzestaw);
                    }
                }

                @Override
                public void onFinish() {
                    pager.setCurrentItem(0, true);
                }
            }.start();
        } else {
            showToast(done);
            editText.setText("");
        }
    }

    /**
     * Metoda sprawdzająca czy dane pytanie zostało ukończone powodzeniem
     */
    public void checkWin(ArrayList<Button> buttonlist) {
        int flag = 0;
        for (int i = flag; i < buttonlist.size(); i++) {
            if (buttonlist.get(i).isEnabled() == false) {
                flag = flag + 1;
            }
        }
        if (flag == buttonlist.size()) {
            int get = preferences.getInt(getString(R.string.NUMBER), 0);
            number += 1;
            if (get > number) {
                number = get;
            }
            editor.putInt(getString(R.string.POINTS), points);
            editor.putInt(getString(R.string.NUMBER), number);
            /** Wysyłanie powyższych zmiennych do przechowania przy ponownym uruchomieniu*/
            editor.apply();
            startActivity(intent);
        }
    }
}



