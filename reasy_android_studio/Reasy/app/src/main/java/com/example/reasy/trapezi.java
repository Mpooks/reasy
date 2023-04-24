package com.example.reasy;

import java.util.ArrayList;

public class trapezi {

    private int idTrapeziou;
    private int xwrhtikothta;
    private int idKatasthmatos;
    private krathsh krathseisTrapeziou;
    private ArrayList<Integer> geitonikaTrapezia = new ArrayList<Integer>();

    public trapezi(int idTrapeziou, int xwrhtikothta, int idKatasthmatos, krathsh krathseisTrapeziou, ArrayList<Integer> geitonikaTrapezia) {
        this.idTrapeziou = idTrapeziou;
        this.xwrhtikothta = xwrhtikothta;
        this.idKatasthmatos = idKatasthmatos;
        this.krathseisTrapeziou = krathseisTrapeziou;
        this.geitonikaTrapezia = geitonikaTrapezia;
    }


}
