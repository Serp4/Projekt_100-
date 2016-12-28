package com.example.bartek.projekt2016.GRA;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartek.projekt2016.R;

import java.util.ArrayList;

public class GraActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private EditText editText;
    private ArrayList<Button> gridlista;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra);
        context=(Context) this;

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        gridlista = new ArrayList<Button>();
        gridView=(GridView) findViewById(R.id.gridview);
        editText=(EditText) findViewById(R.id.edit);
        setbutton(gridlista);
        gridView.setAdapter(new GridAdapter(gridlista));

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "Section ";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
          switch (getArguments().getInt(ARG_SECTION_NUMBER) )
          {
              case 1:
                  View pytania = inflater.inflate(R.layout.fragment_pytania, container, false);
                  TextView textView = (TextView) pytania.findViewById(R.id.pytanie_id);
                  textView.setText("Zuzia to debil nr :");
                  return pytania;

              case 2:
                  View odpowiedzi = inflater.inflate(R.layout.fragment_odpowiedzi, container, false);
                  return odpowiedzi;

              default:
                  return null;

          }

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pytania";
                case 1:
                    return "Odpowiedzi";

            }
            return null;
        }
    }

    public void setbutton(ArrayList<Button> mButtons) {
        editText.setInputType(InputType.TYPE_NULL);
        String klawisze="0123456789qwertyuiopasdfghjkl  zxcvbnm  ";
        char klawisz[]=klawisze.toCharArray();
        Button cb = null;
        for (int i=0; i < klawisze.length(); i++) {
            cb = new Button(this);
            cb.setText("" +klawisz[i]);
            cb.setPadding(0, 0, 0, 0);
            cb.setId(klawisz[i]);
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            cb.setTextColor(Color.parseColor("black"));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    String buttonText = button.getText().toString();
                    editText.setText(editText.getText().toString() + buttonText);
                    final Toast finalToast = Toast.makeText(context, "" + buttonText, Toast.LENGTH_SHORT);
                    new CountDownTimer(400, 100)
                    {
                        public void onTick(long millisUntilFinished) {
                            finalToast.show();
                        }

                        public void onFinish() {
                            finalToast.cancel();

                        }
                    }.start();
                }
            });
            mButtons.add(cb);
        }

        Button pusto= mButtons.get(30);
        pusto.setText("♣");
        pusto.setEnabled(false);
        Button spacja=mButtons.get(38);
        spacja.setText("_");
        spacja.setId('<');
        spacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().toString()+" ");
                final Toast finalToast = Toast.makeText(context, "Nacisnąłeś\n  SPACJE", Toast.LENGTH_SHORT);
                new CountDownTimer(800, 100)
                {
                    public void onTick(long millisUntilFinished) {
                        finalToast.show();
                    }

                    public void onFinish() {
                        finalToast.cancel();

                    }
                }.start();
            }
        });

        Button del=mButtons.get(39);
        del.setText("DEL");
        del.setId('<');
        del.setTextColor(Color.parseColor("blue"));
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();
                String text= editText.getText().toString();
                if (text.length() != 0) {
                    editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));

                    final Toast finalToast = Toast.makeText(context, " Nacisnąłeś\nBACKSPACE", Toast.LENGTH_SHORT);
                    new CountDownTimer(800, 100)
                    {
                        public void onTick(long millisUntilFinished) {
                            finalToast.show();
                        }

                        public void onFinish() {
                            finalToast.cancel();

                        }
                    }.start();

                } else {

                    Toast.makeText(context, "There is NOTHING to remove here ", Toast.LENGTH_SHORT ).show();
                }

            }
        });
        Button ok=mButtons.get(29);
        ok.setText("OK");
        ok.setId('=');
        ok.setTextColor(Color.parseColor("blue"));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();
                String text= editText.getText().toString();


                    final Toast finalToast = Toast.makeText(context, "Nacisnąłeś\n    ENTER", Toast.LENGTH_SHORT);
                    new CountDownTimer(800, 100)
                    {
                        public void onTick(long millisUntilFinished) {
                            finalToast.show();
                        }

                        public void onFinish() {
                            finalToast.cancel();

                        }
                    }.start();



            }
        });
    }

}
