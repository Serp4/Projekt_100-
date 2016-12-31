package com.example.bartek.projekt2016.GRA;


import java.util.ArrayList;


public class Baza {
    public String getPytanie() {
        return Pytanie;
    }

    public void setPytanie(String pytanie) {
        Pytanie = pytanie;
    }

    public String getOdp_1() {
        return Odp_1;
    }

    public void setOdp_1(String odp_1) {
        Odp_1 = odp_1;
    }

    public String getOdp_2() {
        return Odp_2;
    }

    public void setOdp_2(String odp_2) {
        Odp_2 = odp_2;
    }

    public String getOdp_3() {
        return Odp_3;
    }

    public void setOdp_3(String odp_3) {
        Odp_3 = odp_3;
    }

    public String getOdp_4() {
        return Odp_4;
    }

    public void setOdp_4(String odp_4) {
        Odp_4 = odp_4;
    }

    private String Pytanie;
    private String Odp_1;
    private String Odp_2;
    private String Odp_3;
    private String Odp_4;

    public int getOdp() {
        return Odp;
    }

    public void setOdp(int odp) {
        Odp = odp;
    }

    private int Odp;
    private ArrayList<Baza> Zestaw;

    public ArrayList<Baza> getZestaw() {
        return Zestaw;
    }


    public void setZestaw(ArrayList<Baza> zestaw) {
        Zestaw = zestaw;
    }

    public Baza() {
        getBaza();
    }

    public Baza(String pytanie, int odp, String odp_1, String odp_2, String odp_3) {

        setOdp_1(odp_1);
        setOdp_2(odp_2);
        setOdp_3(odp_3);
        setOdp(odp);
        setPytanie(pytanie);

    }

    public Baza(String pytanie, int odp, String odp_1, String odp_2, String odp_3, String odp_4) {

        setOdp_1(odp_1);
        setOdp_2(odp_2);
        setOdp_3(odp_3);
        setOdp_4(odp_4);
        setOdp(odp);
        setPytanie(pytanie);
    }

    public void getBaza() {
        Baza n1 = new Baza("Co monżna znaleźć pod maską samochodu ?", 3, "silnik", "akumulator", "chłodnice");
        Baza n2 = new Baza("Urządzenie posiadające głośnik ?", 4, "radio", "telefon", "telewizor", "laptop");
        Baza n3 = new Baza("Czerwone warzywo", 3, "papryka", "burak", "rzodkiewka");

        Zestaw = new ArrayList<Baza>();
        Zestaw.add(n1);
        Zestaw.add(n2);
        Zestaw.add(n3);

    }


}

