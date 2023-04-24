package com.example.reasy;

import java.util.ArrayList;

public class pelaths extends xrhsths{
    private int pontoi;
    private ArrayList<pelaths> filoi= new ArrayList<pelaths>();
    private int arithmosKrathsewn;
    private ArrayList<aksiologhsh> istorikoAksiologhsewn= new ArrayList<aksiologhsh>();
    private ArrayList<paraggelia> istorikoParaggeliwn= new ArrayList<paraggelia>();

    public pelaths(String onoma, int id, float xrhmatikoYpoloipo, ArrayList<krathsh> krathseis, int pontoi, ArrayList<pelaths> filoi, int arithmosKrathsewn, ArrayList<aksiologhsh> istorikoAksiologhsewn, ArrayList<paraggelia> istorikoParaggeliwn) {
        super(onoma, id, xrhmatikoYpoloipo, krathseis);
        this.pontoi = pontoi;
        this.filoi = filoi;
        this.arithmosKrathsewn = arithmosKrathsewn;
        this.istorikoAksiologhsewn = istorikoAksiologhsewn;
        this.istorikoParaggeliwn = istorikoParaggeliwn;
    }

}
