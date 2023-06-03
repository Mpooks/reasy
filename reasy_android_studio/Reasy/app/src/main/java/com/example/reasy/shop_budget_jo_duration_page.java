package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;

public class shop_budget_jo_duration_page extends AppCompatActivity {
    private ArrayList<shop> sl=new ArrayList<>();
    private TextView t1,b;
    private int id,foundm=0,er=0,jid;
    private double salary,budget,bal,experience;
    private double[] iog=new double[3];
    private String position,startd,endt;
    private int stay,stam,stad,endy,endm,endd;
    private EditText sty,stm,std,eny,enm,end,exp;
    private CheckBox oc,ipc;

    public void fillDate(View v) {
        LocalDate currentdate = LocalDate.now();
        if (!(oc.isChecked()) && (!(ipc.isChecked()))) {
            foundm = 1;
            t1.setText("You must choose one of the options.");
        } else if ((oc.isChecked()) && ((ipc.isChecked()))) {
            foundm = 1;
            t1.setText("You must choose only one.");
        }
        if(foundm==0){
            if (sty.getText().toString().matches("")||stm.getText().toString().matches("")||std.getText().toString().matches("")||eny.getText().toString().matches("")||enm.getText().toString().matches("")||end.getText().toString().matches("")) {
                t1.setText("You have to fill all the gaps first.");
            }
            else {
                stay=Integer.valueOf(sty.getText().toString());
                stam=Integer.valueOf(stm.getText().toString());
                stad=Integer.valueOf(std.getText().toString());
                endy=Integer.valueOf(eny.getText().toString());
                endm=Integer.valueOf(enm.getText().toString());
                endd=Integer.valueOf(end.getText().toString());
                if (stay < currentdate.getYear() || endy < currentdate.getYear() || stam < 1 || stam > 12 || endm < 1 || endm > 12) {
                    t1.setText("Not valid date.");
                    er = 1;
                }
                if ((stam == 1 || stam == 3 || stam == 5 || stam == 7 || stam == 8 || stam == 10 || stam == 12) && (stad < 1 || stad > 31)) {
                    t1.setText("Not valid date.");
                    er = 1;
                }
                if ((stam == 4 || stam == 6 || stam == 9 || stam == 11) && (stad < 1 || stad > 30)) {
                    t1.setText("Not valid date.");
                    er = 1;
                }
                if (stam == 2) {
                    if ((stay % 4 == 0) && (stay % 100 != 0 || stay % 400 == 0) && (stad < 1 || stad > 29)) {
                        t1.setText("Not valid date.");
                        er = 1;
                    } else {
                        if (stad < 1 || stad > 28) {
                            t1.setText("Not valid date.");
                            er = 1;
                        }
                    }
                }
                if ((endm == 1 || endm == 3 || endm == 5 || endm == 7 || endm == 8 || endm == 10 || endm == 12) && (endd < 1 || endd > 31)) {
                    t1.setText("Not valid date.");
                    er = 1;
                }
                if ((endm == 4 || endm == 6 || endm == 9 || endm == 11) && (endd < 1 || endd > 30)) {
                    t1.setText("Not valid date.");
                    er = 1;
                }
                if (endm == 2) {
                    if ((endy % 4 == 0) && (endy % 100 != 0 || endy % 400 == 0) && (endd < 1 || endd > 29)) {
                        t1.setText("Not valid date.");
                        er = 1;
                    } else {
                        if (endd < 1 || endd > 28) {
                            t1.setText("Not valid date.");
                            er = 1;
                        }
                    }
                }
                if (er == 0) {
                    startd=stay+"-"+stam+"-"+stad;
                    endt=endy+"-"+endm+"-"+endd;
                    if(exp.getText().toString().matches("")){
                        jid=job_offer.createJO(shop_budget_jo_duration_page.this, id,position,salary,0,startd,endt);
                    }
                    else {
                        experience=Double.parseDouble(exp.getText().toString());
                        jid=job_offer.createJO(shop_budget_jo_duration_page.this, id,position,salary,experience,startd,endt);
                    }
                    if(oc.isChecked()){
                        bal=user.getBalance(shop_budget_jo_duration_page.this,id);
                        if(bal>=5) {
                            user.setBalance(shop_budget_jo_duration_page.this,id,bal-5);
                            notification.createNot(shop_budget_jo_duration_page.this,id,jid);
                        }
                    }
                    popupMessage();
                }
            }
        }
        foundm=0;
        er=0;
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully inserted the job offer!\n Your budget is: "+budget);
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(shop_budget_jo_duration_page.this,shop_main_page.class);
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
        setContentView(R.layout.activity_shop_budget_jo_duration_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        iog=bundle.getDoubleArray("iog");
        position=bundle.getString("position");
        salary=bundle.getDouble("salary");
        budget=bundle.getDouble("budget");
        oc=findViewById(R.id.y);
        ipc=findViewById(R.id.n);
        t1=findViewById(R.id.name9);
        sty=findViewById(R.id.sy);
        stm=findViewById(R.id.sm);
        std=findViewById(R.id.sd);
        eny=findViewById(R.id.ey);
        enm=findViewById(R.id.em);
        end=findViewById(R.id.ed);
        exp=findViewById(R.id.ex);
        sl=shop.getShops(shop_budget_jo_duration_page.this);
        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.textView40);
                b.setText(String.valueOf(user.getBalance(shop_budget_jo_duration_page.this,id))+"\u20AC");
            }
        }
    }
    public void goBack(View v){
            Intent intent=new Intent(this,set_salary_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putString("position",position);
            b.putDoubleArray("iog",iog);
            intent.putExtras(b);
            startActivity(intent);
        }
}