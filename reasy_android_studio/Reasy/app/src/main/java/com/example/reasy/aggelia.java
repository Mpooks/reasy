package com.example.reasy;
import java.util.ArrayList;

public class aggelia {
    private int idKatasthmatos;
    private float misthos;
    private float empeiria;
    private String thesi;
    private String hmeromhniaEnarkshs;
    private String hmeromhniaLhkshs;
    private String perigrafh;
    private ArrayList<String> wrario = new ArrayList<String>();

    public aggelia(int idKatasthmatos, float misthos, float empeiria, String thesi, String hmeromhniaEnarkshs, String hmeromhniaLhkshs, String perigrafh, ArrayList<String> wrario) {
        this.idKatasthmatos = idKatasthmatos;
        this.misthos = misthos;
        this.empeiria = empeiria;
        this.thesi = thesi;
        this.hmeromhniaEnarkshs = hmeromhniaEnarkshs;
        this.hmeromhniaLhkshs = hmeromhniaLhkshs;
        this.perigrafh = perigrafh;
        this.wrario = wrario;
    }
}
