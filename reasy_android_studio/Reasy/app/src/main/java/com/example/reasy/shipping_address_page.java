package com.example.reasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class shipping_address_page extends AppCompatActivity {
    private int id,sid,foundp=0,foundq=0,f=0,pid,qu,g;
    private int supp_id;
    private double cost;
    private String name;
    private ArrayList<supplier> supp_list = new ArrayList<>();
    private product_supplier cps;
    private ArrayList<product_supplier> ps=new ArrayList<>(),arrayList=new ArrayList<>();
    private ArrayList<Integer> sl=new ArrayList<>(),sp=new ArrayList<>();
    private ArrayList<Integer> spr=new ArrayList<>(),sq=new ArrayList<>(),supr=new ArrayList<>(),suq=new ArrayList<>();

    private ArrayList<String> sln=new ArrayList<>();
    private double tc;
    private TextView text5,b;
    private ArrayList<shop> sli=new ArrayList<>();
    private EditText a;


    public void fill(View view){
        if(a.length()==0){
            text5.setText("You have to fill the address.");
        }
        else {
            for(int pr: spr){
                arrayList.add(product_supplier.getPrD(shipping_address_page.this,pr,sid));
            }
            for(product_supplier r: arrayList){
                tc=tc+r.getPrice()*sq.get(spr.indexOf(r.getId()));
            }
            product_supplier.updatePSQ(shipping_address_page.this,arrayList,suq,supr);
            supply.createSupply(shipping_address_page.this,id,sid,"null","false",tc,arrayList,sq,spr);
            popupMessage();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        text5=findViewById(R.id.tv5);
        a=findViewById(R.id.add);

        sl=bundle.getIntegerArrayList("sl");
        sln=bundle.getStringArrayList("sln");
        sp=bundle.getIntegerArrayList("sp");
        sid=bundle.getInt("sid");
        spr=bundle.getIntegerArrayList("spr");
        sq=bundle.getIntegerArrayList("sq");
        supr=bundle.getIntegerArrayList("supr");
        suq=bundle.getIntegerArrayList("suq");
        sli=shop.getShops(shipping_address_page.this);
        for(shop c: sli) {
            if (c.getId() == id) {
                b = findViewById(R.id.textView12);
                b.setText(String.valueOf(user.getBalance(shipping_address_page.this,id))+"\u20AC");
            }
        }
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, sample_request_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid", sid);
        b.putInt("id", id);
        b.putIntegerArrayList("sl", sl);
        b.putStringArrayList("sln", sln);
        b.putIntegerArrayList("sp", sp);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your order!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(shipping_address_page.this,shop_main_page.class);
                Bundle b = new Bundle();
                //Add your data to bundle
                b.putInt("id", id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}