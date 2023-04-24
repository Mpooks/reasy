package com.example.reasy;

import java.util.ArrayList;

public class paraggelia {

    private int idPelath;
    private int idParraggelias;
    private int idKatasthmatos;
    private float poso;
    private String troposParaggelias;
    private String troposPlhrwmhs;
    private ArrayList<proionParaggelias> proiontaParaggelias = new ArrayList<proionParaggelias>();


    public paraggelia(int idPelath, int idParraggelias, int idKatasthmatos, float poso, String troposParaggelias, String troposPlhrwmhs, ArrayList<proionParaggelias> proiontaParaggelias) {
        this.idPelath = idPelath;
        this.idParraggelias = idParraggelias;
        this.idKatasthmatos = idKatasthmatos;
        this.poso = poso;
        this.troposParaggelias = troposParaggelias;
        this.troposPlhrwmhs = troposPlhrwmhs;
        this.proiontaParaggelias = proiontaParaggelias;
    }



}
