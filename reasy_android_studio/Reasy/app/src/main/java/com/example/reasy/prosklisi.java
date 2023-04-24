package com.example.reasy;

import java.util.ArrayList;

public class prosklisi {

    private int idDeksiosis;
    private int idPelath;
    private ArrayList<pelaths> proskeklhmenoi= new ArrayList<pelaths>();
    private String hmeromhnia;
    private ArrayList<ArrayList<Integer>> diatakshtrapeziwn = new ArrayList<ArrayList<Integer>>();

    public prosklisi(int idDeksiosis, int idPelath, ArrayList<pelaths> proskeklhmenoi, String hmeromhnia, ArrayList<ArrayList<Integer>> diatakshtrapeziwn) {
        this.idDeksiosis = idDeksiosis;
        this.idPelath = idPelath;
        this.proskeklhmenoi = proskeklhmenoi;
        this.hmeromhnia = hmeromhnia;
        this.diatakshtrapeziwn = diatakshtrapeziwn;
    }




}
