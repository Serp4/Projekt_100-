package com.example.bartek.projekt2016.Punkty;

import com.example.bartek.projekt2016.R;

import java.util.ArrayList;

/**
 * Created by Bartek on 01.01.2017.
 */

public class Pad {
    public ArrayList<Pad> padArrayList;

    private int foto;
    private String rowanie;
    private Integer[] Imgage = {
            R.drawable.zag_1, R.drawable.zag_2,
            R.drawable.zag_3, R.drawable.zag_4, R.drawable.zag_5,
    };
    private String[] rowania={"6+3=9","4+3=7","7+2=9","3+3=6","9-3=6"};

    public Pad(int i)
    {
            this.foto=Imgage[i];
            this.rowanie=rowania[i];
    }


    public Pad (){
        padArrayList=new ArrayList<Pad>();
        for (int i=0;i<rowania.length;i++)
        {
            padArrayList.add(new Pad(i));
        }

    }
    public int getFoto() {
        return foto;
    }

    public String getRowanie() {
        return rowanie;
    }
}

