package com.example.reasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class sample_request_page extends AppCompatActivity {
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
    private CheckBox yb,nb;
    private double tc;
    private TextView text5;

    public void chooseSample(View view){
        if(!(yb.isChecked())&&(!(nb.isChecked()))){
            text5.setText("You must choose.");
        }
        else if((yb.isChecked())&&((nb.isChecked()))){
            text5.setText("You must choose only one.");
        }
        else{
            text5.setText("");
            if(yb.isChecked()){
               show();
            }
            else{
                for(int pr: spr){
                    arrayList.add(product_supplier.getPrD(sample_request_page.this,pr,sid));
                }
                for(product_supplier r: arrayList){
                    tc=tc+r.getPrice()*sq.get(spr.indexOf(r.getId()));
                }
                product_supplier.updatePSQ(sample_request_page.this,arrayList,suq,supr);
                supply.createSupply(sample_request_page.this,id,sid,"null","false",tc,arrayList,sq,spr);
                popupMessage();
            }
        }
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your order!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(sample_request_page.this,shop_main_page.class);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_request_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        text5=findViewById(R.id.tv5);
        yb=findViewById(R.id.y);
        nb=findViewById(R.id.n);

        sl=bundle.getIntegerArrayList("sl");
        sln=bundle.getStringArrayList("sln");
        sp=bundle.getIntegerArrayList("sp");
        sid=bundle.getInt("sid");
        spr=bundle.getIntegerArrayList("spr");
        sq=bundle.getIntegerArrayList("sq");
        supr=bundle.getIntegerArrayList("supr");
        suq=bundle.getIntegerArrayList("suq");

    }

    public void show() {
        Intent intent = new Intent(this, shipping_address_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid", sid);
        b.putInt("id", id);
        b.putIntegerArrayList("sl", sl);
        b.putStringArrayList("sln", sln);
        b.putIntegerArrayList("sp", sp);
        b.putIntegerArrayList("spr", spr);
        b.putIntegerArrayList("sq", sq);
        b.putIntegerArrayList("supr", supr);
        b.putIntegerArrayList("suq", suq);
        intent.putExtras(b);
        startActivity(intent);
    }


    public void goBack(View v) {
        Intent intent = new Intent(this, supplier_products_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("sid", sid);
        b.putInt("id", id);
        b.putIntegerArrayList("sl", sl);
        b.putStringArrayList("sln", sln);
        b.putIntegerArrayList("sp", sp);
    }
}