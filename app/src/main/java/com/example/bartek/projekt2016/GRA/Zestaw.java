package com.example.bartek.projekt2016.GRA;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.bartek.projekt2016.R;

import java.util.ArrayList;

public class Zestaw extends AppCompatActivity {

    private ArrayList<Button> zestaw_view;
    private GridView gridView_zestaw;
    private Baza baza;
    private Intent intent;
    private int get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zestaw);
        baza= new Baza();
        zestaw_view = new ArrayList<Button>();
        gridView_zestaw = (GridView) findViewById(R.id.zestaw);
        setViewResponse(zestaw_view);
//        get=intent.getIntExtra("number",0);
        get=1;
        setViewResponse(zestaw_view,get);
        gridView_zestaw.setAdapter(new GridAdapter(zestaw_view));
        intent = new Intent(this, GraActivity.class);
    }
    public void setViewResponse(ArrayList<Button> mButtons) {

        Button cb = null;
        for (int i = 0; i < baza.getZestaw().size(); i++) {
            cb = new Button(this);
            cb.setText("Pytanie - "+ (i+1));
            cb.setId(i);
            cb.setBackground(this.getResources().getDrawable(R.drawable.key_shape));
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    intent.putExtra("number",button.getId());
                    startActivity(intent);

                }
            });
            mButtons.add(cb);
        }
    }
    public void setViewResponse(ArrayList<Button> mButtons, int zestaw) {

        for (int i= baza.getZestaw().size();i>zestaw ; i--) {
            Button cb = mButtons.get(i-1);
            cb.setBackground(this.getResources().getDrawable(R.drawable.button_shape));
            cb.setTextColor(Color.parseColor("#FFFFFF"));
            cb.setEnabled(false);

        }
    }


}
