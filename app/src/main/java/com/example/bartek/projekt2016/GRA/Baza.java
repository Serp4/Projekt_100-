package com.example.bartek.projekt2016.GRA;


import java.util.ArrayList;


public class Baza {

    private String Pytanie;
    private String Odp_1;
    private String Odp_2;
    private String Odp_3;
    private String Odp_4;

    private int Odp;
    private ArrayList<Baza> Zestaw;

    public ArrayList<Baza> getZestaw() {
        return Zestaw;
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
        Baza n1 = new Baza("Gazy szlachetne", 4, "Argon", "Ksenon", "Hel", "Neon");
        Baza n2 = new Baza("3 największe państwa świata", 3, "Rosja", "Kanada", "Chiny");
        Baza n3 = new Baza("Imię męskie bez litery 'a'", 4, "Igor", "Emil", "Grzegorz", "Krzysztof");
        Baza n4 = new Baza("Jakie mamy trójkąty?", 3, "Bermudzki", "pitagorejski", "muzyczny");
        Baza n5 = new Baza("Instrumenty dęte", 3, "puzon", "saksofon", "flet", "klarnet");
        Baza n6 = new Baza("Największe planety Układu Słonecznego", 4, "Jowisz", "Saturn", "Uran", "Neptun");
        Baza n7 = new Baza("Nazwiska znanych kompozytorów", 4, "Chopin", "Bach", "Mozart", "Beethoven");
        Baza n8 = new Baza("Najwyższe pasma górskie", 3, "Himalaje", "Andy", "Kordyliery");
        Baza n9 = new Baza("Więcej niż jedno zwierzę to", 3, "stado", "wataha", "tabun");
        Baza n10 = new Baza("Państwa powstałe po rozpadzie ZSRR", 3, "Rosja", "Ukraina", "Litwa");

        Zestaw = new ArrayList<Baza>();
        Zestaw.add(n1);
        Zestaw.add(n2);
        Zestaw.add(n3);
        Zestaw.add(n4);
        Zestaw.add(n5);
        Zestaw.add(n6);
        Zestaw.add(n7);
        Zestaw.add(n8);
        Zestaw.add(n9);
        Zestaw.add(n10);

    }

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

    public int getOdp() {
        return Odp;
    }

    public void setOdp(int odp) {
        Odp = odp;
    }

}

