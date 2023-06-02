package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class main_page extends AppCompatActivity {
    private int id;
    private ArrayList<Integer> recid=new ArrayList<>();
    private ArrayList<String> recd=new ArrayList<>();

    private ArrayList<reception> rec=new ArrayList<>();


    public void createInvitation(View v){
        rec=customer.getReceptions(main_page.this,id);
        for(reception r:rec){
            if(r.getInv(main_page.this,r.getReception_id())==1){
                recid.add(r.getReception_id());
                recd.add(r.getDate());
            }
        }
        show();
    }
    public void show(){
        Intent intent=new Intent(this,active_receptions_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putIntegerArrayList("recid", recid);
        b.putStringArrayList("recd", recd);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void rate(View v){
        ArrayList<Integer> sl=new ArrayList<>();
        sl=user.getReservationsC(main_page.this,id);
        showRF(sl);
    }
    public void showRF(ArrayList<Integer> sl){
        Intent intent=new Intent(this,rating_form_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putIntegerArrayList("sl",sl);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
    public void createRes(View v){
        Intent intent=new Intent(this,search_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void createRec(View v){
        Intent intent=new Intent(this,organize_reception_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void order(View v){
        Intent intent=new Intent(this,order_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        intent.putExtras(b);
        startActivity(intent);
    }
}