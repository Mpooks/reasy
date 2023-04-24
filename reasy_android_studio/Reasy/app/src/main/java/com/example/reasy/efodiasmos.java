package com.example.reasy;
import java.util.ArrayList;

public class efodiasmos {
    private int idEfodiasmou;
    private int idKatasthmatos;
    private int idPromithefth;
    private String diefthinsiApostolhs;
    private ArrayList<proionEfodiasmou> wrario = new ArrayList<proionEfodiasmou>();
    private boolean deigma;
    private float poso;

    public efodiasmos(int idEfodiasmou, int idKatasthmatos, int idPromithefth, String diefthinsiApostolhs, ArrayList<proionEfodiasmou> wrario, boolean deigma, float poso) {
        this.idEfodiasmou = idEfodiasmou;
        this.idKatasthmatos = idKatasthmatos;
        this.idPromithefth = idPromithefth;
        this.diefthinsiApostolhs = diefthinsiApostolhs;
        this.wrario = wrario;
        this.deigma = deigma;
        this.poso = poso;
    }

}
