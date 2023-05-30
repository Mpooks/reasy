package com.example.reasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class reception_options_page extends AppCompatActivity {
    private LinearLayout mg,ct;
    private int id,g,car,ca,cc;
    private double bal,cost,costa,costc,totalc;
    private String date;
    private ArrayList<Integer> ar_r=new ArrayList<>(),a_r=new ArrayList<>(),c_r=new ArrayList<>();
    private ArrayList<String> ar_n=new ArrayList<>(),a_n=new ArrayList<>(),c_n=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<Double> catl=new ArrayList<>();
    private ArrayList<Double> al=new ArrayList<>();

    private TextView p,b;
    private TextView w6,w7,w4;

    public void chooseCateringAndArtist(View v){
        if(w6.getText().toString().length()==0 || w7.getText().toString().length()==0){
            w4.setText("You have to choose an artist and a catering.");
        }
        else{
            bal=user.getBalance(reception_options_page.this,id);

            DatabaseManager dbm = new DatabaseManager(reception_options_page.this);
            try {
                totalc=cost+costc+costa;
                if(bal>=costc+costa) {
                    user.setBalance(reception_options_page.this,id,bal-costc-costa);
                    dbm.open();
                    dbm.insertR(id,g,date,car,ca,cc);
                    dbm.close();
                    popupMessageOk();
                }
                else{
                    dbm.open();
                    dbm.insertR(id,g,date,car,0,0);
                    dbm.close();
                    popupMessageOk();
                }
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_options_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        g= bundle.getInt("g");
        date= bundle.getString("date");
        ar_r= bundle.getIntegerArrayList("ar_r");
        ar_n=bundle.getStringArrayList("ar_n");
        car=bundle.getInt("car");
        cost=bundle.getInt("cost");
        a_r= bundle.getIntegerArrayList("a_r");
        a_n=bundle.getStringArrayList("a_n");
        c_r= bundle.getIntegerArrayList("c_r");
        c_n=bundle.getStringArrayList("c_n");
        cl=customer.getCustomer(reception_options_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView15);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView17);
                b.setText(String.valueOf(user.getBalance(reception_options_page.this,id))+"\u20AC");
            }
        }

        ct = findViewById(R.id.ct1);
        mg = findViewById(R.id.mg1);
        w6 = findViewById(R.id.warning9);
        w7 = findViewById(R.id.warning10);
        w4 = findViewById(R.id.warning8);
        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            DatabaseManager dbm = new DatabaseManager(reception_options_page.this);
            try {
                dbm.open();
                for(int i:c_r) {
                    Cursor cursor = dbm.fetchCatC(i);
                    catl.add(cursor.getDouble(0));
                    cursor.close();
                }
                for(int i:a_r) {
                    Cursor cu = dbm.fetchAC(id);
                    al.add(cu.getDouble(0));
                    cu.close();
                }
                dbm.close();
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }

        for (int r : c_r) {
            TextView tv = new TextView(this);
            tv.setText("Catering id: " + r + "   Name: " + c_n.get(c_r.indexOf(r))+"   Cost: "+catl.get(c_r.indexOf(r)));
            tv.setTextSize(18);
            tv.setHeight(160);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cc=r;
                    costc=catl.get(c_r.indexOf(r));
                    w7.setText("Chosen catering id:"+cc);
                }
            });
            tv.setId(r);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.menu_item);
            tv.setTypeface(typeface);

            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            ct.addView(tv);
        }
        for (int r : a_r) {
            TextView tv = new TextView(this);
            tv.setText("Artist id: " + r + "   Name: " + a_n.get(a_r.indexOf(r))+"   Cost: "+al.get(a_r.indexOf(r)));
            tv.setTextSize(18);
            tv.setHeight(160);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setId(r);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ca=r;
                    costa=al.get(a_r.indexOf(r));
                    w6.setText("Chosen artist id:"+ca);
                }
            });
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.menu_item);
            tv.setTypeface(typeface);

            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            mg.addView(tv);
        }
    }
    public void goBack(View v){
        Intent intent = new Intent(reception_options_page.this, additional_reception_info_page.class);
        //Create the bundle
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putString("date", date);
        b.putIntegerArrayList("ar_r", ar_r);
        b.putInt("g", g);
        b.putStringArrayList("ar_n", ar_n);
        b.putInt("car", car);
        b.putDouble("cost",cost);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void popupMessageOk() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully organized the reception.");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(reception_options_page.this,main_page.class);
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