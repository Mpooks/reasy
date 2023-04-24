package com.example.reasy;

public class proionMenu extends proion{

    private int idKatasthmatos;
    private int diathesimiPosotita;

    public proionMenu(String onoma, float timh,int idKatasthmatos, int diathesimiPosotita) {
        super(onoma, timh);
        this.idKatasthmatos = idKatasthmatos;
        this.diathesimiPosotita = diathesimiPosotita;
    }


}
