package com.example.bartek.projekt2016.GRA;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.bartek.projekt2016.GRA.Pleaceholder.Placeholderfragment_1;
import com.example.bartek.projekt2016.GRA.Pleaceholder.Placeholderfragment_2;
import com.example.bartek.projekt2016.R;

import java.util.ArrayList;

public class GraActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager mViewPager;
    private EditText editText;
    private ArrayList<Button> gridlista;
    private GridView gridView;
    private Context context;
    private int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra);
        context = (Context) this;
        editText = (EditText) findViewById(R.id.edit);
        gridlista = new ArrayList<Button>();
        gridView = (GridView) findViewById(R.id.gridview);
        setbutton(gridlista);
        gridView.setAdapter(new GridAdapter(gridlista));
        Intent intent = getIntent();
        number = intent.getIntExtra("number", 1);

        mViewPager = (ViewPager) findViewById(R.id.container);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(sectionsPagerAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Zestaw.class);
        startActivity(intent);
    }


    public void setbutton(ArrayList<Button> mButtons) {
        editText.setInputType(InputType.TYPE_NULL);
        String klawisze = "0123456789qwertyuiopasdfghjkl  zxcvbnm ";
        char klawisz[] = klawisze.toCharArray();
        Button cb = null;
        for (int i = 0; i < klawisze.length(); i++) {
            cb = new Button(this);
            cb.setText("" + klawisz[i]);
            cb.setId(klawisz[i]);
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    String buttonText = button.getText().toString();
                    editText.setText(editText.getText().toString() + buttonText);
                }
            });
            mButtons.add(cb);
        }

        Button pusto = mButtons.get(30);
        pusto.setBackground(null);

        Button spacja = mButtons.get(38);
        spacja.setText("__");
        spacja.setId('<');
        spacja.setTextColor(Color.parseColor("#2f63ca"));
        spacja.setBackground(this.getResources().getDrawable(R.drawable.key_shapce_inv));
        spacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().toString() + " ");
                final Toast finalToast = Toast.makeText(context, "SPACJA", Toast.LENGTH_SHORT);
                new CountDownTimer(800, 100) {
                    public void onTick(long millisUntilFinished) {
                        finalToast.show();
                    }

                    public void onFinish() {
                        finalToast.cancel();
                    }
                }.start();
            }
        });

        Button del = mButtons.get(29);
        del.setText("DEL");
        del.setId('<');
        del.setTextColor(Color.parseColor("#2f63ca"));
        del.setBackground(this.getResources().getDrawable(R.drawable.key_shapce_inv));
        del.setOnClickListener(new View.OnClickListener() {
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


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Placeholderfragment_1.newInstance(position, number);
                case 1:
                    return Placeholderfragment_2.newInstance(position, number);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
