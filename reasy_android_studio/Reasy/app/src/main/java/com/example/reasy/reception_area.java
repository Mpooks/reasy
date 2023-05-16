package com.example.reasy;

import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class reception_area {

    private String name;
    private int reception_area_id;
    private static int num_of_guests;
    private ArrayList<reception> receptions = new ArrayList<reception>();
    private float cost;

    public reception_area(String name, int reception_area_id, int num_of_guests, ArrayList<reception> receptions, float cost) {
        this.name = name;
        this.reception_area_id = reception_area_id;
        this.num_of_guests = num_of_guests;
        this.receptions = receptions;
        this.cost = cost;
    }
/*
    public static int getAvailability(int n,int d) {
        tv1 = (EditText)findViewById(R.id.tv1);
        tv2 = (EditText)findViewById(R.id.tv2);
        tv3 = (EditText)findViewById(R.id.tv3);

        int n1 = Integer.parseInt(tv1.getText().toString());
        int n2 = Integer.parseInt(tv2.getText().toString());

        if(n1>n2){
            tv3.setText("n1>n2\n");
        }
        else if (n1<n2) {
            tv3.setText("n1<n2\n");
        }
        else {
            tv3.setText("n1=n2\n");
        }
    }

 */
    public void addtoReceptionArea(reception new_r) {
        receptions.add(new_r);
    }




}
