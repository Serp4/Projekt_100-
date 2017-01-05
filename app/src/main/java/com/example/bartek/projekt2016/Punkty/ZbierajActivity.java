package com.example.bartek.projekt2016.Punkty;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartek.projekt2016.GRA.GridAdapter;
import com.example.bartek.projekt2016.GRA.Zestaw;
import com.example.bartek.projekt2016.MainActivity;
import com.example.bartek.projekt2016.R;

import java.util.ArrayList;

import static android.content.DialogInterface.*;

/**
 * Klasa odpowiedzialna za tworzenie pytań sprawodzaniu poprawności oraz przyznawaniu punktów podpowiedzi
 */
public class ZbierajActivity extends AppCompatActivity {
    private EditText editText;
    private Context context;
    private TextView textView;
    private Pad pad;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private ArrayList<Pad> arrayList;
    private ArrayList<Button> gridlista;
    private GridView gridView;
    private ImageView imageView;
    private Button ok;
    private int points;
    private int number;
    private Intent next;
    private Intent back;

    /**
     * Metoda odpowiedzialna za naciścienie w telefonie przyciku wróc oraz uruchomieniu nowej aktywności
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        editor.putInt(getString(R.string.POINTS), points);
        editor.apply();
        back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    /**
     * Tworzy widok aktywności
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbieraj);
        editText = (EditText) findViewById(R.id.zbierak_edit);
        editText.setEnabled(false);
        imageView = (ImageView) findViewById(R.id.imageView);
        gridView = (GridView) findViewById(R.id.zbieraj_gridview);
        textView = (TextView) findViewById(R.id.points);
        ok = (Button) findViewById(R.id.zbieraj_ok);
        context = (Context) this;
        gridlista = new ArrayList<Button>();
        setbutton(gridlista);
        ;
        gridView.setAdapter(new GridAdapter(gridlista));
        pad = new Pad();
        arrayList = pad.padArrayList;
        preferences = context.getSharedPreferences(getString(R.string.CODE), Context.MODE_PRIVATE);
        editor = preferences.edit();
        points = preferences.getInt(getString(R.string.POINTS), 100);
        textView.setText("" + points);
        number = preferences.getInt(getString(R.string.FOTO), 0);

        imageView.setImageResource(arrayList.get(number).getFoto());


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    /**
     * Tworzy funkcjonalność klawiatury numerycznej i zwraca dany button do listy obiektów
     */
    public void setbutton(ArrayList<Button> mButtons) {
        String keypad = "789-456+123= 0  ";
        char[] ketpads = keypad.toCharArray();
        Button cb = null;
        for (int i = 0; i < keypad.length(); i++) {
            cb = new Button(this);
            cb.setText("" + ketpads[i]);
            cb.setId(i);
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    String edit = editText.getText().toString();
                    editText.setText("" + edit + button.getText());
                }
            });
            mButtons.add(cb);
        }
        Button button = mButtons.get(15);
        button.setText("DEL");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if (text.length() != 0) {
                    editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
                    final Toast finalToast = Toast.makeText(context, "BACKSPACE", Toast.LENGTH_SHORT);
                    new CountDownTimer(600, 100) {
                        public void onTick(long millisUntilFinished) {
                            finalToast.show();
                        }

                        public void onFinish() {
                            finalToast.cancel();
                        }
                    }.start();
                } else {
                    Toast.makeText(context, "Nie ma tu nic do usunięcia ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Sprawdza wpisaną odpowiedz z odpowiedzią podaną w bazie
     */
    public void check() {
        String n1 = editText.getText().toString();
        String n2 = arrayList.get(number).getRowanie();

        if (n1.equalsIgnoreCase(n2) == true) {
            Toast.makeText(context, "Gratulacje +25 punktów", Toast.LENGTH_SHORT).show();
            points = points + 25;
            textView.setText("" + points);
            editText.setText("");
            if (number == arrayList.size() - 1) {
                dialog();
            } else {
                next();
            }

        } else {
            Toast.makeText(context, "Coś poszło nie tak...", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
    }

    /**
     * Metoda odpowiadające za przejście do następnego wyzwania
     */
    public void next() {
        number = number + 1;
        editor.putInt(getString(R.string.FOTO), number);
        editor.putInt(getString(R.string.POINTS), points);
        editor.apply();
        next = new Intent(this, ZbierajActivity.class);
        startActivity(next);
        finish();
    }

    /**
     * Metoda ukazująca informacje
     */
    public void dialog() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Uwaga");
        helpBuilder.setMessage("Niestety dziś wykorzystałeś już wszystkie opcje wróć jutro =(");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}
